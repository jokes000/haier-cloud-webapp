package cn.edu.sjtu.se.dclab.codegen.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import cn.edu.sjtu.se.dclab.codegen.myparsing.PersistenceGenerator;

//import com.sun.xml.internal.fastinfoset.sax.Properties;

public class Servicegenerator {
	// public static String fileBase = "./src/cn/edu/sjtu/dclab/datatemplate/";

	public void generate(String fileBase, String pack, String name)
			throws IOException {
		// String name = "User";

		try {
			Velocity.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		VelocityContext cont = new VelocityContext();
		cont.put("package", pack);
		cont.put("classname", name);

		Template temp = new Template();

		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource resource = appContext.getResource("classpath:codetemplate");

		try {
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, resource
					.getURI().getPath());
			temp = ve.getTemplate("Dataaccess.vm");
		} catch (ResourceNotFoundException e) {
			// TODO: handle exception
			System.out.println("File not found!");
		} catch (ParseErrorException e) {
			System.out.println("Parse error!");
		} catch (MethodInvocationException e) {
			System.out.println("Method Invocation Error!");
		} catch (Exception e) {
			System.out.println("Something wrong!");
		}

		String dest = fileBase;
		String filename = name + "Service.java";
		dest = dest + filename;
		File f = new File(dest);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		temp.merge(cont, bw);
		bw.flush();
		bw.close();

	}
}
