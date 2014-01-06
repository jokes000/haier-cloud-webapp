package cn.edu.sjtu.se.dclab.metadata.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eobjects.metamodel.DataContext;
import org.eobjects.metamodel.DataContextFactory;
import org.eobjects.metamodel.UpdateCallback;
import org.eobjects.metamodel.UpdateScript;
import org.eobjects.metamodel.UpdateableDataContext;
import org.eobjects.metamodel.create.TableCreationBuilder;
import org.eobjects.metamodel.schema.Schema;
import org.eobjects.metamodel.schema.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import cn.edu.sjtu.se.dclab.metadata.dao.bean.ColumnMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Deploy;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.FKMeta;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.Server;
import cn.edu.sjtu.se.dclab.metadata.dao.bean.TableMeta;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.ColumnMetaDao;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.DeployDao;
import cn.edu.sjtu.se.dclab.metadata.dao.intf.FKMetaDao;
import cn.edu.sjtu.se.dclab.metadata.service.intf.DeployService;

@Service("deployService")
public class DeployServiceImpl implements DeployService {

	@Autowired
	private ColumnMetaDao columnMetaDao;

	@Autowired
	private FKMetaDao fKMetaDao;

	@Autowired
	private DeployDao deployDao;

	@Transactional
	public void createDeploy(Deploy deploy) {
		deployDao.creataDeploy(deploy);
	}

	@Transactional
	public List<Deploy> getDeployByUser(User user) {
		return deployDao.getDeployByUser(user);
	}

	@Transactional
	public Deploy getDeployByID(Long id) {
		return deployDao.getDeployByID(id);
	}

	@Transactional
	public void updateDeploy(Deploy deploy) {
		deployDao.updateDeploy(deploy);
	}

	@Transactional
	public String createMysqlDB(String dbname, Server server,
			HttpServletRequest request) {
		String sql = "CREATE DATABASE " + dbname
				+ " DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ server.getHost() + ":" + server.getPort(),
					server.getUsername(), server.getPassword());
			Statement s = con.createStatement();
			s.execute(sql);
			con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
		return "success";
	}

	@Transactional
	public String createMysql(TableMeta tablemeta, String dbname,
			Server server, HttpServletRequest request) {

		String sql = "CREATE TABLE " + tablemeta.getT_name() + " (\n"
				+ "id BIGINT not null auto_increment, \n";

		List<ColumnMeta> columnList = columnMetaDao.getColumnMeta(tablemeta);
		Iterator<ColumnMeta> it = columnList.iterator();
		for (; it.hasNext();) {
			ColumnMeta cm = it.next();
			sql += cm.getC_name() + " " + cm.getType();
			if (cm.getType().equals("VARCHAR")
					|| cm.getType().equals("NUMERIC"))
				sql += "(" + cm.getLength() + ")";
			if (cm.isNotnull())
				sql += " NOT NULL";
			sql += ",\n";

			// create fk
			FKMeta fm = fKMetaDao.getFKMetaByCol(cm);
			if (fm != null) {
				sql += "foreign key (" + cm.getC_name() + ") references "
						+ fm.getTablemeta().getT_name() + "(id)";
				if (cm.getCascade().equals("ALL"))
					sql += " ON UPDATE CASCADE ON DELETE CASCADE";
				if (cm.getCascade().equals("UPDATE"))
					sql += " ON UPDATE CASCADE";
				if (cm.getCascade().equals("DELETE"))
					sql += " ON DELETE CASCADE";
				sql += ",\n";
			}
		}
		sql += "primary key (id)\n)";
		System.out.println(sql);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ server.getHost() + ":" + server.getPort() + "/" + dbname,
					server.getUsername(), server.getPassword());
			Statement s = con.createStatement();
			s.execute(sql);
			writeXML(tablemeta, dbname, server, request);
			con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
			return e.toString();
		}
		return "success";
	}

	@Transactional
	public String createMongo(final TableMeta tablemeta, String dbname,
			Server server, HttpServletRequest request) {

		try {
			DataContext dataContext = DataContextFactory
					.createMongoDbDataContext(server.getHost(), Integer
							.parseInt(server.getPort()), dbname, server
							.getUsername(),
							((server.getPassword() == null) ? "".toCharArray()
									: server.getPassword().toCharArray()));
			final Schema schema = dataContext.getDefaultSchema();
			((UpdateableDataContext) dataContext)
					.executeUpdate(new UpdateScript() {
						public void run(UpdateCallback callback) {
							TableCreationBuilder t = callback.createTable(
									schema, tablemeta.getT_name());
							List<ColumnMeta> columnList = columnMetaDao
									.getColumnMeta(tablemeta);
							Iterator<ColumnMeta> it = columnList.iterator();
							for (; it.hasNext();) {
								ColumnMeta cm = it.next();
								t.withColumn(cm.getC_name());
							}
							Table table = t.execute();

							callback.insertInto(table)
									.value(columnList.get(0).getC_name(), 0)
									.execute();
							callback.deleteFrom(table)
									.where(columnList.get(0).getC_name()).eq(0)
									.execute();
						}
					});
			writeXML(tablemeta, dbname, server, request);
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		return "success";
	}

	public void writeXML(TableMeta tablemeta, String _dbname, Server _server,
			HttpServletRequest request) throws Exception {

		// String path = StaticLoader.getPathByClasspath("xmlFilePath");
		// String path =
		// "D:\\Program Files\\Apache Software Foundation\\apache-tomcat-7.0.42\\webapps\\haier-cloud-webapp\\WEB-INF\\xml";
		String path = "/home/hadoop/tmp/dclab/xmlFiles";

		System.out.println(path);
		File file = new File(path + "/" + tablemeta.getT_name() + "_" + _dbname
				+ "_" + _server.getType() + "_"
				+ ((User) request.getSession().getAttribute("user")).getId()
				+ ".xml");
		if (!file.exists())
			file.createNewFile();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = factory.newDocumentBuilder();
		Document doc = db.newDocument();
		Element table = doc.createElement("table");
		doc.appendChild(table);
		List<ColumnMeta> columnList = columnMetaDao.getColumnMeta(tablemeta);
		Iterator<ColumnMeta> it = columnList.iterator();
		Element tname = doc.createElement("name");
		table.appendChild(tname);
		Text tname_text = doc.createTextNode(tablemeta.getT_name());
		tname.appendChild(tname_text);
		Element columnlist = doc.createElement("columnlist");
		table.appendChild(columnlist);
		for (; it.hasNext();) {
			ColumnMeta cm = it.next();
			Element column = doc.createElement("column");
			columnlist.appendChild(column);
			Element cname = doc.createElement("name");
			column.appendChild(cname);
			Text cname_text = doc.createTextNode(cm.getC_name());
			cname.appendChild(cname_text);
			Element ctype = doc.createElement("type");
			column.appendChild(ctype);
			Text ctype_text = doc.createTextNode(cm.getType());
			ctype.appendChild(ctype_text);
			Element clength = doc.createElement("length");
			column.appendChild(clength);
			Text clength_text = doc.createTextNode(cm.getLength());
			clength.appendChild(clength_text);
			Element notnull = doc.createElement("notnull");
			column.appendChild(notnull);
			Text notnull_text;
			if (cm.isNotnull())
				notnull_text = doc.createTextNode("true");
			else
				notnull_text = doc.createTextNode("false");
			notnull.appendChild(notnull_text);
			if (cm.getC_name().equals("id")) {
				Element primarykey = doc.createElement("primarykey");
				column.appendChild(primarykey);
				Text primarykey_text = doc.createTextNode("true");
				primarykey.appendChild(primarykey_text);
			}
			FKMeta fm = fKMetaDao.getFKMetaByCol(cm);
			if (fm != null) {
				Element reference = doc.createElement("reference");
				column.appendChild(reference);
				Element tablename = doc.createElement("tablename");
				reference.appendChild(tablename);
				Text tablename_text = doc.createTextNode(fm.getTablemeta()
						.getT_name());
				tablename.appendChild(tablename_text);
				Element cascade = doc.createElement("cascade");
				reference.appendChild(cascade);
				Text cascade_text = doc.createTextNode(fm.getColumnmeta_fk()
						.getCascade());
				cascade.appendChild(cascade_text);
			}
		}
		Element server = doc.createElement("server");
		table.appendChild(server);
		Element sname = doc.createElement("name");
		server.appendChild(sname);
		Text sname_text = doc.createTextNode(_server.getS_name());
		sname.appendChild(sname_text);
		Element stype = doc.createElement("type");
		server.appendChild(stype);
		Text stype_text = doc.createTextNode(_server.getType());
		stype.appendChild(stype_text);
		Element host = doc.createElement("host");
		server.appendChild(host);
		Text host_text = doc.createTextNode(_server.getHost());
		host.appendChild(host_text);
		Element port_ = doc.createElement("port");
		server.appendChild(port_);
		Text port_text = doc.createTextNode(_server.getPort());
		port_.appendChild(port_text);
		Element dbname = doc.createElement("database");
		server.appendChild(dbname);
		Text db_text = doc.createTextNode(_dbname);
		dbname.appendChild(db_text);
		Element user = doc.createElement("user");
		server.appendChild(user);
		Text user_text;
		if (_server.getPassword() == null)
			user_text = doc.createTextNode("");
		else
			user_text = doc.createTextNode(_server.getUsername());
		user.appendChild(user_text);
		Element pass = doc.createElement("password");
		server.appendChild(pass);
		Text pass_text;
		if (_server.getPassword() == null)
			pass_text = doc.createTextNode("");
		else
			pass_text = doc.createTextNode(_server.getPassword().toString());
		pass.appendChild(pass_text);

		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter outwriter = new OutputStreamWriter(fos);

		callWriteXmlFile(doc, outwriter, "utf-8");
		outwriter.close();
		fos.close();
	}

	public static void callWriteXmlFile(Document doc, Writer w, String encoding) {
		try {
			Source source = new DOMSource(doc);

			Result result = new StreamResult(w);

			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			xformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			xformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
