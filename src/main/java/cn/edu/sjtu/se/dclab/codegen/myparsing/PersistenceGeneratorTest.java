package cn.edu.sjtu.se.dclab.codegen.myparsing;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PersistenceGeneratorTest {
	public static void main(String[] args) throws IOException {
		
		//mysql
		
		/*PersistenceArguments myArgs = new PersistenceArguments();
		myArgs.setUnitName("mysqlPU");
		Vector<String> beanNames = new Vector<String>();
		beanNames.add("com.impetus.kundera.examples.entities.Person");
		myArgs.setBeanNames(beanNames);
		myArgs.setDialect("org.hibernate.dialect.MySQL5Dialect");
		myArgs.setDriver("com.mysql.jdbc.Driver");
		myArgs.setDatabaseURL("jdbc:mysql://localhost:3306/hibernatepoc_b");
		myArgs.setUser("root");
		myArgs.setPassword("123456");
		File file = new File(System.getProperty("user.dir") + "/persistence_mysql.xml");
		myArgs.setFile(file);
		
		myArgs.setBeanNames(beanNames);
		new PersistenceGenerator().generate(myArgs);*/
		
		
		//mongodb
		/*PersistenceArguments myArgs = new PersistenceArguments();
		myArgs.setUnitName("mymongodbPU");
		myArgs.setDialect("mongodb");
		myArgs.setNodes("localhost");
		myArgs.setPort("27017");
		myArgs.setClient("MongoDB");
		myArgs.setKeyspace("mongodbtest");
		File file = new File(System.getProperty("user.dir") + "/persistence_mongodb.xml");
		myArgs.setFile(file);
		
		new PersistenceGenerator().generateMongoDB(myArgs);*/
		
		//parse
		/*PersistenceGenerator pg = new PersistenceGenerator();
		PersistenceArguments myArgs = new PersistenceArguments();
		
		myArgs.setPackageName("cn.edu.sjtu.se.dslab");
		myArgs.setXmlFile(new File(System.getProperty("user.dir") + "/CHARA_TEST1_mysql_1.xml"));
		//myArgs.setFile(new File(System.getProperty("user.dir") + "/CHARA_TEST1_mysql_1.java"));
		myArgs.setFile(new File(System.getProperty("user.dir") + "/persistence_mysql.xml"));
		pg.generateMySQL(myArgs);*/
		
		/*myArgs.setXmlFile(new File(System.getProperty("user.dir") + "/Lyrical_TEST1_mysql_1.xml"));
		myArgs.setFile(new File(System.getProperty("user.dir") + "/Lyrical_TEST1_mysql_1.java"));
		pg.generateMySQL(myArgs);*/
		
		//final
		PersistenceGenerator pg = new PersistenceGenerator();
		Set<File> files = new HashSet<File>();
		files.add(new File(System.getProperty("user.dir") + "/CHARA_TEST1_mysql_1.xml"));
		files.add(new File(System.getProperty("user.dir") + "/Lyrical_TEST1_mysql_1.xml"));
		Iterator<File> it = files.iterator();
		File outputDir = new File(System.getProperty("user.dir"));
		
		while(it.hasNext()){
			//pg.generateJAVA(it.next(),outputDir,outputDir);
		}
		pg.generateMySQL(new File(System.getProperty("user.dir") + "/mypersistence.xml"));
	}
}
