/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data.generators;

import generatoretags.data.ints.ResourceElement;

/**
 *
 * @author gabri
 */
public class ResourceGeneratorRule extends RuleApplier {

    protected ResourceElement re;
    protected String elementName;

    public void setResurce(ResourceElement re) {
        this.re = re;
    }

    public void setElementName(String name) {
        this.elementName = name;
    }

}
