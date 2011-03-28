/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.listener;

import com.soyostar.project.Project;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class ProjectChangedEvent extends EventObject {

    public ProjectChangedEvent(Project pro) {
        super(pro);
    }

    public Project getProject() {
        return (Project) getSource();
    }
}
