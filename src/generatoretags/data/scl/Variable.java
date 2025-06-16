/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data.scl;

import java.io.Serializable;

/**
 *
 * @author gabri
 */
public class Variable implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final VariableType type;
    private Object defaultValue;
    private String comment;
    //se >0 allora è un array
    private int length = 0;

    public Variable(String name, VariableType varType) {
        this.name = name;
        this.type = varType;
        this.comment = "";
        this.defaultValue = null;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public String getName() {
        return name;
    }

    public VariableType getType() {
        return type;
    }

}
