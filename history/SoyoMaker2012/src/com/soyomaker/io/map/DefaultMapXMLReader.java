/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.Map;
import java.io.File;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Administrator
 */
public class DefaultMapXMLReader implements IMapReader {

    public Map readMap(String mapFile) throws Exception {
        Map map = new Map();
        SAXReader sax = new SAXReader();
        try {
            Document document = sax.read(new File(mapFile));
            // 得到根元素
            Element root = document.getRootElement();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return map;
    }
}
