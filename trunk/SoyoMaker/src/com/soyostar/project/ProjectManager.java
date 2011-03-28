/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.project;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.Attribute;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ProjectManager {

    private ProjectManager() {
    }

    /**
     *
     * @return
     */
    public static boolean create() {
        boolean returnValue = false;
        Document doc = DocumentHelper.createDocument();
        Element project = doc.addElement("Project");
        project.addElement("softVersion").addText(Project.getInstance().getSoftVersion());
        project.addElement("projectName").addText(Project.getInstance().getName());

        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter(Project.getInstance().getPath() + "\\Project.xml"), format);
            xmlw.write(doc);
            xmlw.close();
//            System.out.println(doc.asXML());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    /**
     *
     * @return
     */
    public static boolean get() {
        boolean returnValue = false;//操作结果
        SAXReader sax = new SAXReader();
        try {
            System.out.println("************************************");
            System.out.println("读取项目文件...");
            System.out.println("项目路径:" + Project.getInstance().getPath());
            Document document = sax.read(new File(Project.getInstance().getPath() + "\\Project.xml"));
            // 得到根元素
            Element root = document.getRootElement();
            // 枚举节点
            for (Iterator i = root.elementIterator("projectName"); i.hasNext();) {
                Element name = (Element) i.next();
                Project.getInstance().setName(name.getText());
            }
            for (Iterator i = root.elementIterator("softVersion"); i.hasNext();) {
                Element version = (Element) i.next();
                Project.getInstance().setSoftVersion(version.getText());
            }
            returnValue = true;
            System.out.println("项目名:" + Project.getInstance().getName());
            System.out.println("创建版本:" + Project.getInstance().getSoftVersion());
            System.out.println("读取项目文件成功!");
            System.out.println("************************************");
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return returnValue;
    }
}
