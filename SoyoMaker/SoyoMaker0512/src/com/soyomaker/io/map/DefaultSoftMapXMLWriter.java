/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Map;
import java.io.FileOutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author Administrator
 */
public class DefaultSoftMapXMLWriter implements IMapWriter {

    public void writeMap(Map map, String filename) throws Exception {
        Document doc = DocumentHelper.createDocument();
        Element mapElement = doc.addElement("map");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter xmlw = new XMLWriter(new FileOutputStream(filename), format);
        xmlw.write(doc);
        xmlw.close();
    }
}
