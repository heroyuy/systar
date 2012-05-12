/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Map;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Administrator
 */
public class DefaultSoftMapXMLReader implements IMapReader {

    public Map readMap(String mapFile) throws Exception {
        Map map = new Map();
        SAXReader sax = new SAXReader();
        try {
            InputStream ifile = new FileInputStream(mapFile);
            InputStreamReader ir = new InputStreamReader(ifile, "UTF-8");
            Document document = sax.read(ir);
            // 得到根元素
            Element root = document.getRootElement();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return map;
    }
}
