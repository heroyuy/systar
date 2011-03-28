/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.listener;

import com.soyostar.model.map.Map;
import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface ProjectChangeListener extends EventListener {

    public void projectChanged(ProjectChangedEvent e);

    public void mapAdded(ProjectChangedEvent e, Map map);

    public void mapRemoved(ProjectChangedEvent e, int index);
}
