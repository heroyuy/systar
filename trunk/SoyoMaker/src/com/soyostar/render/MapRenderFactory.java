/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.render;

import com.soyostar.model.map.Map;

/**
 *
 * @author 地图绘制器工厂，未完成
 */
public class MapRenderFactory {

    /**
     * 先检查有没有以插件扩展的地图view，没有则默认用orthomapview
     * @return
     */
    public static MapRender createMapRender(Map map) {
        return new OrthoMapRender(map);
    }
}
