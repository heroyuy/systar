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
public class ProjectChangedEvent extends EventObject {

    /**
     * 
     * @param pro
     */
    public ProjectChangedEvent(Project pro) {
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
