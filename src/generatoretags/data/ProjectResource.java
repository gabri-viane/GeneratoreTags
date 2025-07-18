/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data;

import generatoretags.data.ints.ResourceElement;
import generatoretags.data.ints.ResourceType;

/**
 *
 * @author gabri
 */
public class ProjectResource {

    private ResourceElement re;

    public ProjectResource(ResourceElement re) {
        this.re = re;
    }

    public String getResourceName() {
        return re.getName() + " [" + re.getType().toString() + "]";
    }

    public ResourceType getType() {
        return re.getType();
    }
}
