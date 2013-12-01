package cn.edu.sjtu.se.dslab.haiercloud.deploy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeployMongoDBCluster {

	private String clusterName = null;
	private List<ShardInfos> shardList = null;
	private Map<String, String> configServerMap = null;
	private Map<String, String> mongosMap = null;
	private Set<String> configServerSet = null;
	private Set<String> mongosSet = null;

	private Map<String, String> mongodMap = null;
	private Set<String> mongodSet = null;
	private Map<String, String> arbiterMap = null;
	private Set<String> arbiterSet = null;
	private Iterator<String> iterator = null;
	private Map<String, String> mongoDBMap = null;// all the node
	private Set<String> mongoDBSet = null;
	private Map<String, String> mongodShardMap = null;// mongodIP:shardName
	private Map<String, Map<String, String>> shardMap = null;// shardName:mongodsMap
	private Set<String> shardSet = null;

	// private String mongoDBFile=null;
	private String shellPath = null;
	private String mongoDBFilePath = null;
	private String mongoDBClusterLogPath = null;

	private String mongoDBName = null;
	private String mongoDBFileName = null;
	private String mongoDBPath = null;
	private String mongodDataPath = null;
	private String configServerDataPath = null;
	private String mongosDataPath = null;
	private int nThreads = 0;
	private FileWriter logWriter = null;

	private int i = 0;
	private Lock lock = new ReentrantLock();

	private void readBuffer(final InputStream input, final InputStream error) {
		new Thread() {

			public void run() {
				BufferedReader inputReader = new BufferedReader(
						new InputStreamReader(input));
				try {
					String str;
					while ((str = inputReader.readLine()) != null)
						System.out.println(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						input.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();

		new Thread() {
			public void run() {
				BufferedReader errorReader = new BufferedReader(
						new InputStreamReader(error));
				try {
					while (errorReader.readLine() != null)
						;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						error.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private void output(String str) {
		try {
			logWriter.write(str + "\n");
			logWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void init(MongoDBArguments args) {
		/*
		 * Properties prop=new Properties(); InputStream
		 * in=getClass().getResourceAsStream("/mongodb.properties"); try { //
		 * InputStream in=new FileInputStream(new
		 * File("/home/hadoop/workspace/shell/mongodb/mongodb.properties"));
		 * prop.load(in); } catch (IOException e) { e.printStackTrace(); }
		 */
		/*
		 * shellPath=prop.getProperty("shellPath");
		 * mongoDBFilePath=prop.getProperty("mongoDBFilePath");
		 * mongoDBClusterLogPath=prop.getProperty("mongoDBClusterLogPath");
		 * 
		 * mongoDBPath=prop.getProperty("mongoDBPath");
		 * nThreads=Integer.valueOf(prop.getProperty("nThreads"));
		 * 
		 * mongoDBFileName=prop.getProperty("mongoDBFileName");
		 * mongoDBName=prop.getProperty("mongoDBName");
		 * mongodDataPath=prop.getProperty("mongodDataPath");
		 * configServerDataPath=prop.getProperty("configServerDataPath");
		 * mongosDataPath=prop.getProperty("mongosDataPath");
		 */

		this.clusterName = args.getClusterName();
		this.shardList = args.getShardList();
		this.mongodMap = new HashMap<String, String>();
		this.mongodShardMap = new HashMap<String, String>();
		this.shardMap = new HashMap<String, Map<String, String>>();
		for (int i = 0; i < shardList.size(); i++) {
			ShardInfos shardInfos = shardList.get(i);
			mongodMap.putAll(shardInfos.getMongodMap());
			// arbiterMap.put(shardInfos.getArbiterIP(),shardInfos.getArbiterPwd());

			iterator = shardInfos.getMongodMap().keySet().iterator();
			String shardName = shardList.get(i).getShardName();
			while (iterator.hasNext()) {
				String ip = iterator.next();
				mongodShardMap.put(ip, shardName);
			}

			shardMap.put(shardName, shardInfos.getMongodMap());
		}
		if(mongodMap != null)
			this.mongodSet = mongodMap.keySet();
		// this.arbiterSet=arbiterMap.keySet();
		this.configServerMap = args.getConfigServerMap();
		if(configServerMap !=null)
			this.configServerSet = configServerMap.keySet();
		
		this.mongosMap = args.getMongosMap();
		if(mongosMap != null)
			this.mongosSet = mongosMap.keySet();
		
		if(shardMap != null)
			this.shardSet = shardMap.keySet();

		this.mongoDBMap = new HashMap<String, String>();
		mongoDBMap.putAll(mongodMap);
		if(configServerMap != null)
			mongoDBMap.putAll(configServerMap);
		
		//如果configServerMap为空，表明为addShards操作，无需加入mongos
		if(configServerMap != null)
			mongoDBMap.putAll(mongosMap);
		// mongoDBMap.putAll(arbiterMap);
		this.mongoDBSet = mongoDBMap.keySet();

		String currTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String logName = clusterName + "-" + currTime + ".log";
		try {
			logWriter = new FileWriter(new File(mongoDBClusterLogPath + "/"
					+ logName), true);
		} catch (Exception e) {
			e.printStackTrace();
			output("unable to creat MongoDBCluster's log file!");
		}
	}

	public boolean addCluster(MongoDBArguments args) {

		init(args);

		// １、copy　mongodb　file
		// ２、make　data　dir
		parallelInstallMongoDB();

		// ３、start　mongod
		parallelStartMongod();

		// 4、start replica set
		parallelStartReplicaSet();

		// 5、start config server
		parallelStartConfigServer();

		// 6、start mongos
		parallelStartMongos();

		// 7、connect mongos and add shards
		// parallelInitiateReplicaSet();
		initiateShard();
	
		return true;
	}

	private void parallelInstallMongoDB() {
		output("Install MongoDB parallely!");
		ExecutorService installThreadPool = Executors
				.newFixedThreadPool(nThreads);
		class Task implements Callable<Object> {
			public Object call() throws Exception {
				installMongoDB();
				return null;
			}
		}
		Task[] tasks = new Task[mongoDBSet.size()];
		for (int i = 0; i < mongoDBSet.size(); i++) {
			tasks[i] = new Task();
		}
		try {
			iterator = mongoDBSet.iterator();
			installThreadPool.invokeAll(Arrays.asList(tasks));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		installThreadPool.shutdown();
		if (installThreadPool.isTerminated())
			output("Finish installing MongoDB!");
	}

	private void parallelStartConfigServer() {
		output("Start config server:");
		ExecutorService startThreadPool = Executors
				.newFixedThreadPool(nThreads);
		class Task implements Callable<Object> {

			public Object call() throws Exception {
				startConfigServer();
				return null;
			}
		}
		Task[] tasks = new Task[configServerSet.size()];
		for (int i = 0; i < configServerSet.size(); i++) {
			tasks[i] = new Task();
		}
		try {
			iterator = configServerSet.iterator();
			startThreadPool.invokeAll(Arrays.asList(tasks));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		startThreadPool.shutdown();
		if (startThreadPool.isTerminated())
			output("Finish to start config server!");
	}

	private void parallelStartMongos() {
		output("Start Mongos:");
		ExecutorService startThreadPool = Executors
				.newFixedThreadPool(nThreads);
		class Task implements Callable<Object> {

			public Object call() throws Exception {
				startMongos();
				return null;
			}
		}
		Task[] tasks = new Task[mongosSet.size()];
		for (int i = 0; i < mongosSet.size(); i++) {
			tasks[i] = new Task();
		}
		try {
			iterator = mongosSet.iterator();
			startThreadPool.invokeAll(Arrays.asList(tasks));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		startThreadPool.shutdown();
		if (startThreadPool.isTerminated())
			output("Finish to start mongos!");
	}

	private void parallelStartMongod() {
		output("Start Mongod:");
		ExecutorService startThreadPool = Executors
				.newFixedThreadPool(nThreads);
		class Task implements Callable<Object> {

			public Object call() throws Exception {
				startMongod();
				return null;
			}
		}
		Task[] tasks = new Task[mongodSet.size()];
		for (int i = 0; i < mongodSet.size(); i++) {
			tasks[i] = new Task();
		}
		try {
			iterator = mongodSet.iterator();
			startThreadPool.invokeAll(Arrays.asList(tasks));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		startThreadPool.shutdown();
		if (startThreadPool.isTerminated())
			output("Finish to start mongod!");
	}

	private void parallelStartReplicaSet() {
		output("Initialise replication set:");
		ExecutorService initThreadPool = Executors.newFixedThreadPool(nThreads);
		class Task implements Callable<Object> {

			public Object call() throws Exception {
				startReplicaSet();
				return null;
			}
		}
		Task[] tasks = new Task[shardSet.size()];
		for (int i = 0; i < shardSet.size(); i++) {
			tasks[i] = new Task();
		}
		try {
			iterator = shardSet.iterator();
			initThreadPool.invokeAll(Arrays.asList(tasks));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		initThreadPool.shutdown();
		if (initThreadPool.isTerminated())
			output("Finish to initialise replication set!");
	}

	private void initiateShard() {
		output("Initialise shard:");
		iterator = mongosSet.iterator();
		// 任意一台mongos
		String mongosIP = iterator.next();
		String mongosPwd = mongosMap.get(mongosIP);
		String shardArgs = "";
		StringBuffer sb = new StringBuffer();
		int num = shardList.size();
		iterator = shardSet.iterator();
		while (iterator.hasNext()) {
			String shardName = iterator.next();
			sb.append("sh.addShard(\"");
			sb.append(shardName);
			sb.append("/");
			Iterator<String> mongodsIterator = shardMap.get(shardName).keySet()
					.iterator();
			if (mongodsIterator.hasNext())
				sb.append(mongodsIterator.next() + ":30000");
			while (mongodsIterator.hasNext())
				sb.append("," + mongodsIterator.next() + ":30000");
			sb.append("\")");
			sb.append(" ");
		}
		shardArgs = sb.toString();
		try {
			Process p = Runtime.getRuntime().exec(
					shellPath + "/initiateShard.sh" + " " + mongosIP + " "
							+ mongosPwd + " " + mongoDBPath + "/" + mongoDBName
							+ " " + num + " " + shardArgs);
			readBuffer(p.getInputStream(), p.getErrorStream());
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		output("Finish to initialise shard!");
	}

	private void installMongoDB() {
		while (iterator.hasNext()) {
			String ip = iterator.next();
			String pwd = mongodMap.get(ip);

			// copy mongoDB file
			copyMongoDBFile(ip, pwd);

			// make data file for mongod
			// makeShardData(ip,pwd);
		}
	}

	private void copyMongoDBFile(String ip, String pwd) {
		output("copy MongoDB File to " + ip);
		try {
			Process p = Runtime.getRuntime().exec(
					shellPath + "/copyDirTo.sh" + " " + ip + " " + pwd + " "
							+ mongoDBFilePath + " " + mongoDBPath + " "
							+ mongoDBFileName);
			readBuffer(p.getInputStream(), p.getErrorStream());
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			output("Fail to copy MongoDBFile!");
		}
	}

	private void makeData(String ip, String pwd, String path) {
		output("make data on the " + ip);
		try {
			Process p = Runtime.getRuntime().exec(
					shellPath + "/makeDir.sh " + ip + " " + pwd + " " + path);
			readBuffer(p.getInputStream(), p.getErrorStream());
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			output("Fail to copy make Data File!");
		}
	}

	private void startConfigServer() {
		while (iterator.hasNext()) {
			String ip = iterator.next();
			String pwd = configServerMap.get(ip);
			String journalFlag = "";
			lock.lock();
			try {
				journalFlag = "journal";
				if (i > 1)
					journalFlag = "nojournal";
				i++;
			} finally {
				lock.unlock();
			}
			try {
				Process p = Runtime.getRuntime().exec(
						shellPath + "/config-server.sh" + " " + ip + " " + pwd
								+ " " + configServerDataPath + " "
								+ mongoDBPath + "/" + mongoDBName + " "
								+ journalFlag);
				readBuffer(p.getInputStream(), p.getErrorStream());
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void startMongos() {
		while (iterator.hasNext()) {
			String ip = iterator.next();
			String pwd = mongosMap.get(ip);
			Iterator<String> csIterator = configServerSet.iterator();
			String cs = csIterator.next() + ":20000";
			while (csIterator.hasNext()) {
				cs += "," + csIterator.next() + ":20000";
			}
			try {
				Process p = Runtime.getRuntime().exec(
						shellPath + "/mongos.sh " + " " + ip + " " + pwd + " "
								+ mongosDataPath + " " + mongoDBPath + "/"
								+ mongoDBName + " " + cs);
				readBuffer(p.getInputStream(), p.getErrorStream());
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void startMongod() {
		while (iterator.hasNext()) {
			String ip = iterator.next();
			String pwd = mongodMap.get(ip);
			String shardName = mongodShardMap.get(ip);
			try {
				Process p = Runtime.getRuntime().exec(
						shellPath + "/mongod.sh " + " " + ip + " " + pwd + " "
								+ mongosDataPath + " " + mongoDBPath + "/"
								+ mongoDBName + " " + shardName);
				readBuffer(p.getInputStream(), p.getErrorStream());
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void startReplicaSet() {
		while (iterator.hasNext()) {
			String rsName = iterator.next();
			Map<String, String> mongodsMap = shardMap.get(rsName);
			Set<String> mongodsSet = mongodsMap.keySet();
			Iterator<String> mongodsIterator = mongodsSet.iterator();

			// replicaset journal
			String rsIP = mongodsIterator.next();
			String rsPwd = mongodsMap.get(rsIP);
			int num = 0;
			String configStr = "config={_id:'" + rsName
					+ "',members:[{_id:0,host:'" + rsIP + ":30000'" + "}";
			num++;
			while (mongodsIterator.hasNext()) {
				configStr += ",{_id:" + num + ",host:'"
						+ mongodsIterator.next() + ":30000'}";
				num++;
			}
			configStr += "]}";

			/*
			 * String configStr="config='{'_id:\"'\""+rsName+
			 * "\"'\",members:['{'_id:0,host:\"'\""+rsIP+":30000\"'\""+"'}'";
			 * num++; while(mongodsIterator.hasNext()){
			 * configStr+=",'{'_id:"+num
			 * +",host:\"'\""+mongodsIterator.next()+":30000\"'\"'}'"; num++; }
			 * configStr+="]'}'";
			 */
			try {
				Process p = Runtime.getRuntime().exec(
						shellPath + "/startReplicaSet.sh" + " " + rsIP + " "
								+ 30000 + " " + rsPwd + " " + mongoDBPath + "/"
								+ mongoDBName + " " + configStr);
				readBuffer(p.getInputStream(), p.getErrorStream());
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public boolean addShards(MongoDBArguments args) {
		//test
	/*	this.shellPath = "/home/hadoop/deploy/MongoDBShell";
		this.mongoDBFilePath = "/home/hadoop/deploy";
		this.mongoDBClusterLogPath = "/home/hadoop/deploy/logs";
		this.mongoDBName = "MongoDB";
		this.mongoDBFileName = "MongoDB";
		this.mongoDBPath = "/home/hadoop/yuan";
		this.mongodDataPath = "/home/hadoop/yuan/data";
		this.mongosDataPath = "/home/hadoop/yuan/data";
		this.nThreads = 2;
		*/
		
		
		
		//初始化，将mongos和要加入的shard的信息传入
		/*this.mongosMap = args.getMongosMap();
		this.mongosSet = this.mongosMap.keySet();
		this.shardList = args.getShardList();
		this.mongodMap = this.shardList.get(0).getMongodMap();
		this.mongodSet = this.mongodMap.keySet();
		this.iterator = mongodSet.iterator();
		String currTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String logName = clusterName + "-" + currTime + ".log";
		try {
			logWriter = new FileWriter(new File(mongoDBClusterLogPath + "/"
					+ logName), true);
		} catch (Exception e) {
			e.printStackTrace();
			output("unable to creat MongoDBCluster's log file!");
		}*/
		
		//copy mongodb文件夹
	/*	installMongoDB();
		
		//启动要加入shard的mongod
		//startMongod();
		iterator = mongodSet.iterator();
		while (iterator.hasNext()) {
			String ip = iterator.next();
			String pwd = mongodMap.get(ip);
			try {
				Process p = Runtime.getRuntime().exec(
						shellPath + "/startMongod.sh " + " " + ip + " " + pwd + " "
								+ mongosDataPath + " " + mongoDBPath+"/"+mongoDBName);
				readBuffer(p.getInputStream(), p.getErrorStream());
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//使用addshard将各mongod加入到shard中	
		String mongosIP=null;
		String mongosPassword=null;
		Iterator<String> it = mongosSet.iterator();
		if(it.hasNext()){
			mongosIP = it.next();
			mongosPassword = mongosMap.get(mongosIP);
		}
		StringBuffer mongodSB = new StringBuffer();
		it=mongodSet.iterator();
		if(it.hasNext())
			mongodSB.append(it.next()+":30000");
		while(it.hasNext())
			mongodSB.append(","+it.next()+":30000");
		
		String command="db.runCommand({addshard:\"" + mongodSB.toString() + "\",name:\"" 
							+ shardList.get(0).getShardName() + "\"})";
		
		try {
			Process p = Runtime.getRuntime().exec(shellPath + "/addShard.sh"+" "+mongosIP+" "+mongosPassword
										+" "+"30100"+" "+mongoDBPath+"/"+mongoDBName+" "+command);
			readBuffer(p.getInputStream(),p.getErrorStream());
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*以上只能加入含有单个mongod的shard*/
		
		//1.init
		init(args);
		
		//2.parallelInstallMongoDB
		parallelInstallMongoDB();
		
		//3.parallelStartMongod
		parallelStartMongod();
		
		//4.parallelStartReplicaSet
		parallelStartReplicaSet();
		
		//5.initiateShard
		initiateShard();
		
		return true;
	}

	public void deleteShards(MongoDBArguments args) {
		
		// test
		
		/* this.shellPath = "/home/hadoop/deploy/MongoDBShell";
		 this.mongoDBFilePath = "/home/hadoop/deploy";
		 this.mongoDBClusterLogPath = "/home/hadoop/deploy/logs";
		 this.mongoDBName = "MongoDB"; 
		 this.mongoDBFileName = "MongoDB";
		 this.mongoDBPath = "/home/hadoop/yuan"; 
		 this.mongodDataPath = "/home/hadoop/yuan/data";
		 this.mongosDataPath = "/home/hadoop/yuan/data";
		 this.nThreads = 2;*/
		 
		
		
		// 初始化，将mongos和要加入的shard的信息传入
/*		this.mongosMap = args.getMongosMap();
		this.mongosSet = this.mongosMap.keySet();
		this.shardList = args.getShardList();
		this.iterator = mongosSet.iterator();
		
		String mongosIP=null;
		String mongosPassword=null;
		String shardName=null;
		if(iterator.hasNext()){
			mongosIP = iterator.next();
			mongosPassword = mongosMap.get(mongosIP);
		}
		shardName=shardList.get(0).getShardName();
		
		String command = "db.runCommand({removeshard:\""+shardName+"\"})";
		
		try {
			Process  p = Runtime.getRuntime().exec(shellPath+"/removeShard.sh"+" "+mongosIP+" "+mongosPassword
										+" "+"30100"+" "+mongoDBPath+"/"+mongoDBName+" "+command);
			readBuffer(p.getInputStream(), p.getErrorStream());
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*以上只能删除含有一个mongod的shard*/
		
		//init
		init(args);
		
		iterator = mongosSet.iterator();
		String mongosIP = iterator.next();
		String mongosPassword = mongosMap.get(mongosIP);
		
		String command = null;
		StringBuffer sb = null;
		iterator = shardSet.iterator();
		while(iterator.hasNext()){
			sb = new StringBuffer("db.runCommand({removeshard:\"");
			String shardName = iterator.next();	
			sb.append(shardName + "\"})");
			sb.append("\n");
		}
		command = sb.toString();
		
		try {
			Process p = Runtime.getRuntime().exec(shellPath+"/removeShard.sh"+" "+mongosIP+" "+mongosPassword
											+" "+"30100"+" "+mongoDBPath+"/"+mongoDBName+" "+command);
			readBuffer(p.getInputStream(), p.getErrorStream());
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//stop all mongod thread and delete data file
		parallelStopMongod();
	}
	
	private void parallelStopMongod(){
		output("Stop mongod parallely!");
		ExecutorService installThreadPool = Executors
				.newFixedThreadPool(nThreads);
		class Task implements Callable<Object> {
			public Object call() throws Exception {
				stopMongod();
				return null;
			}
		}
		Task[] tasks = new Task[mongodSet.size()];
		for (int i = 0; i < mongodSet.size(); i++) {
			tasks[i] = new Task();
		}
		try {
			iterator = mongodSet.iterator();
			installThreadPool.invokeAll(Arrays.asList(tasks));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		installThreadPool.shutdown();
		if (installThreadPool.isTerminated())
			output("Finish stopping mongod!");
	}
	
	private void stopMongod(){
		while(iterator.hasNext()){
			String ip = iterator.next();
			String pwd = mongodMap.get(ip);
			try {
				Process p = Runtime.getRuntime().exec(shellPath+"/stopMongod.sh" + " " +ip+" "+pwd+" "+
									mongoDBPath+"/"+mongoDBName+" "+shellPath+" "+mongodDataPath);
				readBuffer(p.getInputStream(),p.getErrorStream());
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteCluster() {

	}

	public String getShellPath() {
		return shellPath;
	}

	public void setShellPath(String shellPath) {
		this.shellPath = shellPath;
	}

	public String getMongoDBFilePath() {
		return mongoDBFilePath;
	}

	public void setMongoDBFilePath(String mongoDBFilePath) {
		this.mongoDBFilePath = mongoDBFilePath;
	}

	public String getMongoDBClusterLogPath() {
		return mongoDBClusterLogPath;
	}

	public void setMongoDBClusterLogPath(String mongoDBClusterLogPath) {
		this.mongoDBClusterLogPath = mongoDBClusterLogPath;
	}

	public String getMongoDBName() {
		return mongoDBName;
	}

	public void setMongoDBName(String mongoDBName) {
		this.mongoDBName = mongoDBName;
	}

	public String getMongoDBFileName() {
		return mongoDBFileName;
	}

	public void setMongoDBFileName(String mongoDBFileName) {
		this.mongoDBFileName = mongoDBFileName;
	}

	public String getMongoDBPath() {
		return mongoDBPath;
	}

	public void setMongoDBPath(String mongoDBPath) {
		this.mongoDBPath = mongoDBPath;
	}

	public String getMongodDataPath() {
		return mongodDataPath;
	}

	public void setMongodDataPath(String mongodDataPath) {
		this.mongodDataPath = mongodDataPath;
	}

	public String getConfigServerDataPath() {
		return configServerDataPath;
	}

	public void setConfigServerDataPath(String configServerDataPath) {
		this.configServerDataPath = configServerDataPath;
	}

	public String getMongosDataPath() {
		return mongosDataPath;
	}

	public void setMongosDataPath(String mongosDataPath) {
		this.mongosDataPath = mongosDataPath;
	}

	public int getnThreads() {
		return nThreads;
	}

	public void setnThreads(int nThreads) {
		this.nThreads = nThreads;
	}

}
