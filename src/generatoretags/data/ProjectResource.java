/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data;

import generatoretags.data.ints.ResourceType;

/**
 *
 * @author gabri
 */
public class ProjectResource {
    
    private ResourceType type;
    private String resourceName;
    
    public ProjectResource(String resourceName,ResourceType type){
        this.resourceName=resourceName;
        this.type = type;
    }

    public String getResourceName() {
        return resourceName+" ["+type.toString()+"]";
    }

    public ResourceType getType() {
        return type;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }
    
}
