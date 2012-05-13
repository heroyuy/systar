/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.listener;

import com.soyomaker.project.Project;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class ProjectAnimationChangedEvent extends EventObject {

    /**
     * 
     * @param pro
     */
    public ProjectAnimationChangedEvent(Project pro) {
        super(pro);
    }

    /**
     *
     * @return
     */
    public Project getProject() {
        return (Project) getSource();
    }
}
