package cn.edu.sjtu.se.dclab.codegen.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import cn.edu.sjtu.se.dclab.codegen.generator.Servicegenerator;
import cn.edu.sjtu.se.dclab.codegen.myparsing.PersistenceGenerator;
import cn.edu.sjtu.se.dclab.haiercloud.web.util.StaticLoader;

public class IntegrateAll {

	private String xmlDirPath = null;

	// war for data access
	// 生成的war包的位置
	private String warDirPath = null;

	// jar for developer
	private String jarDirPath = null;
	private String tomcatMachineUser = null;
	private String tomcatMachineIP = null;
	private String tomcatMachinePassword = null;
	private String tomcatPath = null;
	private String shellPath = null;

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
					e.printStackTrace();
				} finally {
					try {
						input.close();
					} catch (IOException e) {
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
					String str;
					while ((str = errorReader.readLine()) != null)
						System.out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						error.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private void readProperties() {
		Properties prop = new Properties();
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource resource = appContext
				.getResource("classpath:integrateInfos.properties");
		InputStream in = null;
		try {
			in = resource.getInputStream();
			prop.load(in);
			xmlDirPath = prop.getProperty("xmlDirPath");
			warDirPath = prop.getProperty("warDirPath");
			jarDirPath = prop.getProperty("jarDirPath");
			tomcatMachineUser = prop.getProperty("tomcatMachineUser");
			tomcatMachineIP = prop.getProperty("tomcatMachineIP");
			tomcatMachinePassword = prop.getProperty("tomcatMachinePassword");
			tomcatPath = prop.getProperty("tomcatPath");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void integrate() {
		readProperties();
		File xmlDir = new File(xmlDirPath);
		File srcDir = new File(warDirPath);

		if (!xmlDir.isDirectory() || !srcDir.isDirectory()) {
			System.out.println("input or output isn't dir!");
			return;
		} else {

			PersistenceGenerator pg = new PersistenceGenerator();
			Servicegenerator sg = new Servicegenerator();

			// [0]:path [1]:packageName [2]:className
			// String jarProjectPath = System.getProperty("user.dir");
			File vjavaFile = new File(jarDirPath + "/src/main/java");
			String[] javaFileInfos = new String[3];
			javaFileInfos[0] = vjavaFile.getAbsolutePath();

			String[] fileList = xmlDir.list();
			for (int i = 0; i < fileList.length; i++) {
				pg.generateJAVA(new File(xmlDir.getAbsolutePath() + "/"
						+ fileList[i]), new File(srcDir.getAbsolutePath()
						+ "/src/main/java"), javaFileInfos);

				try {
					sg.generate(
							javaFileInfos[0] + "/"
									+ javaFileInfos[1].replace(".", "/") + "/",
							javaFileInfos[1], javaFileInfos[2]);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			File persistenceFile = new File(srcDir.getAbsolutePath()
					+ "/src/main/resources/META-INF/persistence.xml");
			pg.generateMySQL(persistenceFile);

			// shellPath =
			// IntegrateAll.class.getClass().getResource("").getPath() +
			// "/scripts";
			shellPath = StaticLoader.getPathByClasspath("scripts/package");
			System.out.println(shellPath);

			try {
//				Process p = Runtime.getRuntime()
//						.exec(shellPath + "maven.sh" + " "
//								+ srcDir.getAbsolutePath());
//
//				readBuffer(p.getInputStream(), p.getErrorStream());
//				p.waitFor();
//
//				Process p2 = Runtime.getRuntime().exec(
//						shellPath + "maven.sh" + " " + jarDirPath);
//
//				readBuffer(p2.getInputStream(), p2.getErrorStream());
//				p2.waitFor();

				/*Process pp = Runtime.getRuntime().exec(
						shellPath + "pushToTomcat.sh" + " " + tomcatMachineUser
								+ " " + tomcatMachineIP + " "
								+ tomcatMachinePassword + " " + tomcatPath
								+ " " + warDirPath+"/target/datanucleus-samples-jpa-tutorial-3.2.war");
				readBuffer(pp.getInputStream(), pp.getErrorStream());
				pp.waitFor();*/
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		IntegrateAll ia = new IntegrateAll();
		File xmlDir = new File(System.getProperty("user.dir") + "/xmlFiles");
		File srcDir = new File("/home/hadoop/mongo_test");
		ia.integrate();
		/*
		 * Process p; try { p = Runtime.getRuntime().exec(
		 * System.getProperty("user.dir") + "/lib/maven.sh" + " " +
		 * "/home/hadoop/datanucleus-samples-jpa-tutorial-3.3");
		 * 
		 * readBuffer(p.getInputStream(), p.getErrorStream()); p.waitFor(); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */

	}
}
