/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.project;

import com.soyomaker.AppData;
import com.soyomaker.infomation.SoftInformation;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.SAXReader;
import java.util.Iterator;

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
        project.addAttribute("name", AppData.getInstance().getCurProject().getName());
        Element softVersion = project.addElement("SoftVersion");
        //创建项目时，保存软件版本和时间和创建时的相同
        softVersion.addAttribute("create", AppData.getInstance().getCurProject().getCreateSoftVersion());
        softVersion.addAttribute("save", AppData.getInstance().getCurProject().getCreateSoftVersion());
        Element time = project.addElement("Time");
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new java.util.Date());
        AppData.getInstance().getCurProject().setCreateTime(datetime);
        AppData.getInstance().getCurProject().setLastSaveTime(datetime);
        time.addAttribute("create", datetime);
        time.addAttribute("save", datetime);
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter(AppData.getInstance().getCurProject().getPath() + File.separator + "Project.xml"), format);
            xmlw.write(doc);
            xmlw.close();
            returnValue = true;
        } catch (IOException e) {
            returnValue = false;
            e.printStackTrace();
        }
        return returnValue;
    }

    /**
     *
     * @return
     */
    public static boolean update() {
        boolean returnValue = false;//操作结果
        Document doc = DocumentHelper.createDocument();
        Element project = doc.addElement("Project");
        project.addAttribute("name", AppData.getInstance().getCurProject().getName());
        Element softVersion = project.addElement("SoftVersion");
        softVersion.addAttribute("create", AppData.getInstance().getCurProject().getCreateSoftVersion());
        softVersion.addAttribute("save", SoftInformation.majorVersion + "." + SoftInformation.minorVersion + "." + SoftInformation.lastVersion);
        Element time = project.addElement("Time");
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new java.util.Date());
        time.addAttribute("create", AppData.getInstance().getCurProject().getCreateTime());
        time.addAttribute("save", datetime);
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter(AppData.getInstance().getCurProject().getPath() + File.separator + "Project.xml"), format);
            xmlw.write(doc);
            xmlw.close();
            returnValue = true;
        } catch (IOException e) {
            returnValue = false;
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
//            System.out.println();
//            AppData.getInstance().getLogger().d("");
//            System.out.println("读取项目文件...");
            AppData.getInstance().getLogger().d("读取项目文件...");
//            System.out.println("项目路径:" + AppData.getInstance().getCurProject().getPath());
            AppData.getInstance().getLogger().d("项目路径:" + AppData.getInstance().getCurProject().getPath());
            Document document = sax.read(new File(AppData.getInstance().getCurProject().getPath() + File.separator + "Project.xml"));
            // 得到根元素
            Element root = document.getRootElement();
            if (root.attributeValue("name") != null) {
                AppData.getInstance().getCurProject().setName(root.attributeValue("name"));
            }
            for (Iterator i = root.elementIterator("SoftVersion"); i.hasNext();) {
                Element version = (Element) i.next();
                if (version.attributeValue("create") != null) {
                    AppData.getInstance().getCurProject().setCreateSoftVersion(version.attributeValue("create"));
                }
                if (version.attributeValue("save") != null) {
                    AppData.getInstance().getCurProject().setLastSaveSoftVersion(version.attributeValue("save"));
                }
            }
            for (Iterator i = root.elementIterator("Time"); i.hasNext();) {
                Element version = (Element) i.next();
                if (version.attributeValue("create") != null) {
                    AppData.getInstance().getCurProject().setCreateTime(version.attributeValue("create"));
                }
                if (version.attributeValue("save") != null) {
                    AppData.getInstance().getCurProject().setLastSaveTime(version.attributeValue("save"));
                }
            }
            returnValue = true;
//            System.out.println("项目名:" + AppData.getInstance().getCurProject().getName());
            AppData.getInstance().getLogger().d("项目名:" + AppData.getInstance().getCurProject().getName());
//            System.out.println("创建项目软件版本:" + AppData.getInstance().getCurProject().getCreateSoftVersion());
            AppData.getInstance().getLogger().d("创建项目软件版本:" + AppData.getInstance().getCurProject().getCreateSoftVersion());
//            System.out.println("创建项目时间:" + AppData.getInstance().getCurProject().getCreateTime());
            AppData.getInstance().getLogger().d("创建项目时间:" + AppData.getInstance().getCurProject().getCreateTime());
//            System.out.println("读取项目文件成功!");
            AppData.getInstance().getLogger().d("读取项目文件成功!");
//            System.out.println();
        } catch (Exception e) {
            returnValue = false;
            e.printStackTrace();
            // TODO: handle exception
        }
        return returnValue;
    }
}
