/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.project;

import com.soyomaker.AppData;
import com.soyomaker.infomation.SoftInformation;
import com.soyomaker.log.LogPrinter;
import com.soyomaker.log.LogPrinterFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import org.dom4j.Document;
import org.dom4j.DocumentException;
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
     * @throws IOException 
     */
    public static void create() throws IOException {

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

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter xmlw = new XMLWriter(new FileWriter(AppData.getInstance().getCurProject().getPath() + File.separator + AppData.getInstance().getCurProject().getName() + ".smproj"), format);
        xmlw.write(doc);
        xmlw.close();
    }

    /**
     *
     * @throws IOException
     */
    public static void update() throws IOException {
        Document doc = DocumentHelper.createDocument();
        Element project = doc.addElement("Project");
        project.addAttribute("name", AppData.getInstance().getCurProject().getName());
        Element softVersion = project.addElement("SoftVersion");
        softVersion.addAttribute("create", AppData.getInstance().getCurProject().getCreateSoftVersion());
        softVersion.addAttribute("save", SoftInformation.getVersion());
        Element time = project.addElement("Time");
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = tempDate.format(new java.util.Date());
        time.addAttribute("create", AppData.getInstance().getCurProject().getCreateTime());
        time.addAttribute("save", datetime);

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter xmlw = new XMLWriter(new FileWriter(AppData.getInstance().getCurProject().getPath() + File.separator + AppData.getInstance().getCurProject().getName() + ".smproj"), format);
        xmlw.write(doc);
        xmlw.close();
    }
    private static LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    /**
     *
     * @throws DocumentException 
     * @throws MalformedURLException 
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void get() throws DocumentException, MalformedURLException, UnsupportedEncodingException, FileNotFoundException, IOException {
        SAXReader sax = new SAXReader();
        printer.d("读取项目文件...");
        printer.d("项目路径:" + AppData.getInstance().getCurProject().getPath());
        String filename = AppData.getInstance().getCurProject().getPath().substring(AppData.getInstance().getCurProject().getPath().lastIndexOf(File.separator));
        File prjFile = new File(AppData.getInstance().getCurProject().getPath() + File.separator + filename + ".smproj");
        InputStream ifile = new FileInputStream(prjFile);
        InputStreamReader ir = new InputStreamReader(ifile, "UTF-8");
        Document document = sax.read(prjFile);
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
        ifile.close();
        ir.close();
//            System.out.println("项目名:" + AppData.getInstance().getCurProject().getName());
        printer.d("项目名:" + AppData.getInstance().getCurProject().getName());
//            System.out.println("创建项目软件版本:" + AppData.getInstance().getCurProject().getCreateSoftVersion());
        printer.d("创建项目软件版本:" + AppData.getInstance().getCurProject().getCreateSoftVersion());
//            System.out.println("创建项目时间:" + AppData.getInstance().getCurProject().getCreateTime());
        printer.d("创建项目时间:" + AppData.getInstance().getCurProject().getCreateTime());
//            System.out.println("读取项目文件成功!");
        printer.d("读取项目文件成功!");
    }
}
