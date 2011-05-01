/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.File;
import java.io.FileWriter;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.Attribute;
import org.dom4j.io.SAXReader;
import java.util.Iterator;
import org.dom4j.XPath;

/**
 *
 * @author Administrator
 */
public class XmlUtil {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        ChangeAndroidManifest("aa", "bb");
//        ChangeStrings("cc");
    }

    /**
     *
     * @param name
     * @return
     */
    public static boolean ChangeStrings(String path, String name) {
        boolean returnValue = false;//操作结果
        SAXReader sax = new SAXReader();
        try {
            System.out.println("************************************");
            System.out.println("读取strings.xml文件...");
            Document document = sax.read(new File(path));
            // 得到根元素
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator("string"); i.hasNext();) {
                Element e = (Element) i.next();
                Iterator it = e.attributeIterator();
                while (it.hasNext()) {
                    Attribute att = (Attribute) it.next();
                    if (att.getValue().equals("app_name")) {
                        e.setText(name);
                    }
                }
            }
            System.out.println("修改strings.xml文件成功!");
            System.out.println("************************************");
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter(path), format);
            xmlw.write(document);
            xmlw.close();
            returnValue = true;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return returnValue;
    }

    /**
     *
     * @param name
     * @param screen
     * @return
     */
    public static boolean ChangeAndroidManifest(String projectName, String name, String screen) {
        boolean returnValue = false;//操作结果
        SAXReader sax = new SAXReader();
        try {
            System.out.println("************************************");
            System.out.println("读取AndroidManifest.xml文件...");
            Document document = sax.read(new File(projectName + File.separatorChar + "AndroidManifest.xml"));
            XPath xpath = document.createXPath("manifest/application/activity");
            Element app = (Element) xpath.evaluate(document);
            app.setAttributeValue("screenOrientation", screen);
            app.setAttributeValue("name", "." + name);
            System.out.println("修改AndroidManifest.xml文件成功!");
            System.out.println("************************************");
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlw = new XMLWriter(new FileWriter(projectName + File.separatorChar + "AndroidManifest.xml"), format);
            xmlw.write(document);
            xmlw.close();
            returnValue = true;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return returnValue;
    }
}
