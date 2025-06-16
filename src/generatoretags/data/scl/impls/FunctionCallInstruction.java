/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data.scl.impls;

import generatoretags.data.ints.Resource;
import generatoretags.data.ints.ResourceType;
import generatoretags.data.scl.ints.SCLInstruction;
import generatoretags.data.scl.Variable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class FunctionCallInstruction implements SCLInstruction {
    
    private static final long serialVersionUID = 1L;

    private Resource callingFunction;
    private boolean hasDBInstance;

    private Resource bindedDB;
    private List<Variable> callParameters;

    public FunctionCallInstruction(Resource FunctionInstance) {
        if (!FunctionInstance.getType().equals(ResourceType.Function)
                || !FunctionInstance.getType().equals(ResourceType.FunctionBlock)) {
            throw new RuntimeException("Expected Function or FunctionBlock call. Found : " + FunctionInstance.getType().name());
        }
        this.callingFunction = FunctionInstance;
        this.callParameters = new ArrayList<>();
    }

    public void bindDBInstance(Resource DB) {
        if (DB == null || !DB.getType().equals(ResourceType.FunctionInterfaceInstance)) {
            this.bindedDB = null;
            this.hasDBInstance = false;
            return;
        }
        this.bindedDB = DB;
        this.hasDBInstance = true;
    }

    @Override
    public String getName() {
        return "CALL";
    }

    @Override
    public String getInstruction() {
        StringBuilder sb = new StringBuilder();

        sb.append("CALL \"").append(this.callingFunction.getName()).append("\"");
        if (this.hasDBInstance) {
            sb.append(", \"").append(this.bindedDB.getName()).append("\"");
        }
        sb.append("\t( ");
        this.callParameters.forEach(v -> {
            Object value = v.getDefaultValue();
            if (value != null) {
                String name = v.getName();
                if (name.contains(" ") || name.contains("\t")) {
                    sb.append("\"").append(name).append("\"");
                } else {
                    sb.append(name);
                }
                sb.append(" := ").append(value.toString()).append(",\n");
            }
        });
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(");");
        return sb.toString();
    }

}
