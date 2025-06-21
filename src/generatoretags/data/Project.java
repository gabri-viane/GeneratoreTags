/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data;

import generatoretags.data.generators.RuleApplier;
import generatoretags.data.ints.ResourceElement;
import generatoretags.data.ints.ResourceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabri
 */
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String projectName;
    private final Map<ResourceType, Map<String, ResourceElement>> resources;
    private final List<RuleApplier> ruleAppliers;

    public Project(String name) {
        this.projectName = name;
        this.resources = new HashMap<>();
        this.ruleAppliers = new ArrayList<>();

        resources.put(ResourceType.Function, new HashMap<>());
        resources.put(ResourceType.DataBlock, new HashMap<>());
        resources.put(ResourceType.FunctionBlock, new HashMap<>());
        resources.put(ResourceType.FunctionInstance, new HashMap<>());
    }

    public void addResource(ResourceElement re) {
        ResourceType rt = re.getType();
        resources.get(rt).put(re.getName(), re);
    }

    public ResourceElement getResource(ResourceType rt, String name) {
        ResourceElement toret = null;
        Map<String, ResourceElement> typeResources = resources.get(rt);
        if (typeResources != null) {
            toret = typeResources.get(name);
        }
        return toret;
    }

    public void removeResource(ResourceElement re) {
        if (re == null) {
            return;
        }
        Map<String, ResourceElement> get = resources.get(re.getType());
        if (get == null) {
            return;
        }
        get.remove(re.getName());
    }
    
    public void addRuleApplier(RuleApplier ra){
        this.ruleAppliers.add(ra);
    }

    public String getProjectName() {
        return projectName;
    }

}
