/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.map;

import com.soyostar.app.Layer;
import com.soyostar.app.Painter;
import game.impl.model.Map;

/**
 *
 * @author Administrator
 */
public class MapLayer extends Layer {

    private Map curMap = null;

    public Map getCurMap() {
        return curMap;
    }

    public void setCurMap(Map curMap) {
        this.curMap = curMap;
    }

    @Override
    public void paint(Painter painter) {
        super.paint(painter);
        painter.drawImage(curMap.background, 0, 0, Painter.LT);
        painter.drawImage(curMap.foreground, 0, 0, Painter.LT);
    }
}
