package cn.edu.sjtu.se.dclab.haiercloud.deploy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadInstallJDK {
	
	static String shellPath="/home/yuan/workspace/Cloud";
	static String jdk32Path="/home/yuan/jdk_32/jdk1.7.0_02";
	static String jdk64Path="/home/yuan/jdk_64/jdk1.7.0_02";
	static String nodesPath="/home/hadoop/yuan";

	public static void main(String[] args) throws Exception {
			PrintStream ps=new PrintStream("hadoop.log");
			System.setOut(ps);
			HashSet<String> hs=new HashSet<String>();
			hs.add("192.168.1.183");
			hs.add("192.168.1.184");
			final Iterator<String> iterator=hs.iterator();
			ExecutorService threadPool=Executors.newFixedThreadPool(5);
			for(int i=0;i<hs.size();i++){
				threadPool.execute(new Runnable(){

					
					public void run() {
						while(iterator.hasNext()){
							String ip=iterator.next();
							boolean flag=true;
							System.out.println("Start to copy jdk files to the machine whose ip is "+ip);
							try{
							//1、先检测机器的位数
								Process p=Runtime.getRuntime().exec(shellPath+"/getMachineBit.sh"+" "+ip+" "+"111111");
								BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
								String res=null;
								String jdkPath="";
								while((res=br.readLine())!=null){
									if(res.equals("32"))
										jdkPath=jdk32Path;
									else if(res.equals("64"))
										jdkPath=jdk64Path;
								}
								p.waitFor();
								
							//2、copy文件
								Process pp=Runtime.getRuntime().exec(shellPath+"/copyDirTo.sh"+" "+ip+" "+"111111"+" "
																		+jdkPath+" "+nodesPath);
								br=new BufferedReader(new InputStreamReader(pp.getInputStream()));
								while((res=br.readLine())!=null);
								pp.waitFor();
							}catch(Exception e){
								System.out.println("Fail to copy jdk files to the machine whose ip is "+ip+"!");
								flag=false;
							}
							if(flag)
								System.out.println("Success to copy jdk files to the machine whose ip is "+ip+"!");
						}
					}
				});
			}
			threadPool.shutdown();
	}

}
