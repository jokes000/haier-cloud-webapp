package cn.edu.sjtu.se.dclab.codegen.myparsing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.activation.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.edu.sjtu.se.dclab.codegen.integration.IntegrateAll;
import cn.edu.sjtu.se.dclab.haiercloud.web.util.StaticLoader;

public class PersistenceGenerator {

	private String unitName;
	private Vector<String> beanNames = new Vector<String>();
	private Vector<String> vbeanNames = new Vector<String>();
	private Vector<Bean> beans = new Vector<Bean>();
	private String namespace;
	private Vector<Datasource> datasources = new Vector<Datasource>();
	// private Hashtable<String,Vector<Bean>> dbs = new
	// Hashtable<String,Vector<Bean>>();//datasourceName:bean
	private String dialect;
	private String driver;
	private String datasourceName;
	private String databaseName;
	private String databaseURL;
	private String databaseType;
	private String user;
	private String host;
	private String password;

	private String nodes;
	private String port;
	private String keyspace;
	private String client;

	// inputXMLFile is a .xml file.
	// outputJAVAFile is a dir where .java file will be located.
	public void generateJAVA(File inputXMLFile, File outputJAVAFile,
			String[] javaFileInfos) {
		Bean bean = new Bean();
		Bean vbean = new Bean();
		String tableName = null;
		String packageName = null;
		List<Property> properties = new ArrayList<Property>();
		List<Association> associations = new ArrayList<Association>();
		String packageDir = null;
		File xmlFile = null;

		String outputPath = null;

		namespace = inputXMLFile.getName();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			xmlFile = inputXMLFile;
			Document doc = builder.parse(xmlFile);

			// get tableName and className for bean
			// default classname is same with tablename
			Element table = doc.getDocumentElement();
			tableName = table.getElementsByTagName("name").item(0)
					.getFirstChild().getNodeValue();
			bean.setClassName(tableName);
			bean.setTableName(tableName);

			// produce the package's dir
			String str = xmlFile.getName();
			int index = str.indexOf("_");
			int index2 = str.indexOf("_", index + 1);
			packageName = new String(str.substring(index + 1, index2)
					.toLowerCase()
					+ "."
					+ str.substring(index2 + 1, str.lastIndexOf("_"))
							.toLowerCase());
			packageDir = outputJAVAFile.getAbsolutePath()
					+ "/"
					+ str.substring(index + 1, index2).toLowerCase()
					+ "/"
					+ str.substring(index2 + 1, str.lastIndexOf("_"))
							.toLowerCase();
			bean.setPackageName(packageName);

			outputPath = System.getProperty("user.dir") + "/"
					+ str.substring(index + 1, index2);

			// parse name and type for every column
			NodeList columns = doc.getElementsByTagName("column");
			for (int i = 0; i < columns.getLength(); i++) {
				Property p = new Property();
				String propertyName = null;
				String propertyType = null;
				String referenceName = null;
				String cascadeType = null;
				boolean isRef = false;

				NodeList columnProperties = columns.item(i).getChildNodes();
				for (int j = 0; j < columnProperties.getLength(); j++) {
					isRef = false;
					Node node = columnProperties.item(j);
					String nodeName = node.getNodeName();
					if (nodeName.equals("name"))
						propertyName = node.getFirstChild().getNodeValue();
					else if (nodeName.equals("type")) {
						propertyType = node.getFirstChild().getNodeValue();
					} else if (nodeName.equals("reference")) {
						isRef = true;
						NodeList refNodeList = node.getChildNodes();
						for (int k = 0; k < refNodeList.getLength(); k++) {
							Node refNode = refNodeList.item(k);
							String refNodeName = refNode.getNodeName();
							if (refNodeName.equals("tablename"))
								referenceName = refNode.getFirstChild()
										.getNodeValue();
							else if (refNodeName.equals("cascade")
									&& refNode.getFirstChild() != null)
								cascadeType = refNode.getFirstChild()
										.getNodeValue();

						}
					}

				}
				// String propertyName =
				// doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();
				// String propertyType =
				// doc.getElementsByTagName("type").item(i).getFirstChild().getNodeValue();

				p.setColumn(propertyName);
				// default columnName is same with the propertyName,care about
				// upper lower
				p.setName(propertyName);
				if (propertyType.equals("BIGINT"))
					p.setType("long");
				else if (propertyType.equals("INT"))
					p.setType("int");
				else if (propertyType.equals("VARCHAR"))
					p.setType("String");
				else if (propertyType.equals("DATATIME")) {
					p.setType("java.sql.Timestamp");
				} else if (propertyType.equals("FLOAT")) {
					p.setType("float");
				} else if (propertyType.equals("DOUBLE")) {
					p.setType("double");
				} else if (propertyType.equals("TIMESTAMP")) {
					p.setType("java.sql.Timestamp");
				}

				if (!isRef)
					properties.add(p);
				else {
					Association association = new Association();
					association.setName(propertyName);
					association.setType(propertyName);
					association.setJoinColumn(referenceName);
					association.setCascadeType(cascadeType);
					associations.add(association);
				}
			}
			bean.setProperties(properties);
			bean.setAssociations(associations);

			NodeList serverNodeList = doc.getElementsByTagName("server");
			NodeList serverInfosNodeList = serverNodeList.item(0)
					.getChildNodes();
			for (int i = 0; i < serverInfosNodeList.getLength(); i++) {
				Node node = serverInfosNodeList.item(i);
				String nodeName = node.getNodeName();
				if (nodeName.equals("name")) {
					datasourceName = node.getFirstChild().getNodeValue();
				}
				if (nodeName.equals("type") && node.hasChildNodes()) {
						databaseType = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("database") && node.hasChildNodes()) {
					databaseName = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("host") && node.hasChildNodes()) {
					host = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("port") && node.hasChildNodes()) {
					port = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("user") && node.hasChildNodes()) {
					user = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("password") && node.hasChildNodes()) {
					password = node.getFirstChild().getNodeValue();
				}
			}

			String datasourceId = host + port + databaseName;

			Iterator<Datasource> it = datasources.iterator();
			while (it.hasNext()) {
				Datasource ds = it.next();
				if (datasourceId.equals(ds.getHost() + ds.getPort()
						+ ds.getDatabaseName())) {
					ds.getVbs().add(bean);
					break;
				} else {
					Datasource newDs = new Datasource();
					newDs.setName(datasourceName);
					newDs.setIndex(String.valueOf(datasources.size()));
					newDs.setHost(host);
					newDs.setPort(port);
					newDs.setType(databaseType);
					newDs.setUrl(host + ":" + port);
					newDs.setDatabaseName(databaseName);
					newDs.setUser(user);
					newDs.setPassword(password);

					newDs.getVbs().add(bean);

					datasources.add(newDs);
				}
			}

			if (datasources.size() == 0) {
				Datasource ds = new Datasource();
				ds.setName(datasourceName);
				ds.setIndex(String.valueOf(datasources.size()));
				ds.setHost(host);
				ds.setPort(port);
				ds.setType(databaseType);
				ds.setUrl(host + ":" + port);
				ds.setDatabaseName(databaseName);
				ds.setUser(user);
				ds.setPassword(password);

				ds.setVbs(new Vector<Bean>());
				ds.getVbs().add(bean);

				datasources.add(ds);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// make package dirs
		File f = new File(packageDir);
		f.mkdirs();
		File ff = new File(javaFileInfos[0] + "/"
				+ packageName.replace('.', '/') + "/vulgaris");
		ff.mkdirs();

		File file = new File(packageDir + "/" + bean.getClassName() + ".java");
		File vfile = new File(ff.getAbsolutePath() + "/" + bean.getClassName()
				+ ".java");
		beanNames.add(packageName + "." + bean.getClassName());

		vbean.setPackageName(new String(packageName + ".vulgaris"));
		vbean.setClassName(bean.getClassName());
		vbean.setTableName(bean.getTableName());
		vbean.setProperties(bean.getProperties());
		vbean.setAssociations(bean.getAssociations());

		javaFileInfos[1] = packageName + ".vulgaris";
		javaFileInfos[2] = vbean.getClassName();

		VelocityEngine ve = new VelocityEngine();

		// String path = System.getProperty("user.dir");
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource resource = appContext.getResource("classpath:codetemplate");
		String wodepath = null;
		try {
			wodepath = resource.getURI().getPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, wodepath);

		ve.setProperty(Velocity.INPUT_ENCODING, "GBK");
		ve.setProperty(Velocity.OUTPUT_ENCODING, "GBK");
		try {

			ve.init();

			Template template = ve.getTemplate("bean.vm");
			VelocityContext vc = new VelocityContext();

			vc.put("bean", bean);
			Writer writer = new PrintWriter(new FileOutputStream(file));
			template.merge(vc, writer);
			writer.flush();
			writer.close();

			template = ve.getTemplate("vbean.vm");
			vc.put("vbean", vbean);
			Writer vwriter = new PrintWriter(new FileOutputStream(vfile));
			template.merge(vc, vwriter);
			vwriter.flush();
			vwriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// getConnInformation(inputXMLFile);

		// args.getFile().renameTo(file);
	}

	private void getConnInformation(File file) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			NodeList serverNodeList = doc.getElementsByTagName("server");
			NodeList serverInfosNodeList = serverNodeList.item(0)
					.getChildNodes();
			for (int i = 0; i < serverInfosNodeList.getLength(); i++) {
				Node node = serverInfosNodeList.item(i);
				String nodeName = node.getNodeName();
				if (nodeName.equals("type")) {
					databaseType = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("database")) {
					databaseName = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("host")) {
					host = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("port")) {
					port = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("user")) {
					user = node.getFirstChild().getNodeValue();
				} else if (nodeName.equals("password")) {
					password = node.getFirstChild().getNodeValue();
				}
			}

			unitName = databaseType + "PU";

			if (databaseType.equals("mysql"))
				databaseURL = "jdbc:" + databaseType + ":" + "//" + host + ":"
						+ port + "/" + databaseName;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateMySQL(File outputFile) {

		// parse xml file and produce bean

		VelocityEngine ve = new VelocityEngine();

		// String path =
		// PersistenceGenerator.class.getClass().getResource("").getPath() +
		// "/codetemplate";
		String path = StaticLoader.getPathByClasspath("codetemplate");

		ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);

		ve.setProperty(Velocity.INPUT_ENCODING, "GBK");
		ve.setProperty(Velocity.OUTPUT_ENCODING, "GBK");
		try {
			ve.init();

			Template template = ve.getTemplate("persistence.vm");
			VelocityContext vc = new VelocityContext();

			// vc.put("unitName", unitName);
			// vc.put("beanNames", beanNames);
			// vc.put("databaseURL", databaseURL);
			// vc.put("user", userName);
			// vc.put("password", password);

			vc.put("datasources", datasources);
			// vc.put("namespace", namespace);
			// vc.put("dbs", dbs);

			Writer writer = new PrintWriter(new FileOutputStream(outputFile));
			template.merge(vc, writer);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateMongoDB(PersistenceArguments args) {
		VelocityEngine ve = new VelocityEngine();

		String path = StaticLoader.getPathByClasspath("codetemplate");

		ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);

		ve.setProperty(Velocity.INPUT_ENCODING, "GBK");
		ve.setProperty(Velocity.OUTPUT_ENCODING, "GBK");
		try {
			ve.init();

			Template template = ve.getTemplate("persistence_mongodb.vm");
			VelocityContext vc = new VelocityContext();

			vc.put("unitName", args.getUnitName());
			vc.put("nodes", args.getNodes());
			vc.put("port", args.getPort());
			vc.put("keyspace", args.getKeyspace());
			vc.put("dialect", args.getDialect());
			vc.put("client", args.getClient());
			Writer writer = new PrintWriter(
					new FileOutputStream(args.getFile()));
			template.merge(vc, writer);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
