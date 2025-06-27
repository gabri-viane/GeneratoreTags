/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.gui;

import generatoretags.data.Project;
import generatoretags.commons.SharedDataChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class SharedData {

    private final List<SharedDataChangeListener<Project>> projectEventListeners;
    private Project p;

    public SharedData() {
        this.projectEventListeners = new ArrayList<>();
    }


    public void setOpenedProject(Project p) {
        this.p = p;
        projectEventListeners.forEach((t) -> {
            t.onChange(p);
        });
    }

    public void addProjectEventListener(SharedDataChangeListener<Project> listener) {
        this.projectEventListeners.add(listener);
    }

    public Project getProject() {
        return p;
    }
    
    

}
