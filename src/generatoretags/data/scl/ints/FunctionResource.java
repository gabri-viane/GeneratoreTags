/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package generatoretags.data.scl.ints;

import generatoretags.data.ints.Reloadable;
import generatoretags.data.ints.ResourceElement;
import generatoretags.data.ints.ResourceType;
import generatoretags.data.scl.Network;
import generatoretags.data.scl.Variable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author gabri
 */
public abstract class FunctionResource extends ResourceElement implements Reloadable {

    protected final List<Variable> inputs;
    protected final List<Variable> outputs;
    protected final List<Variable> inout;
    protected final List<Variable> statics;
    private final List<Variable> temp;

    protected String declaration;
    protected String definition;
    protected final List<Network> networks;

    public FunctionResource(String name, ResourceType rt) {
        super(rt);
        super.name = name;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        inout = new ArrayList<>();
        statics = new ArrayList<>();
        temp = new ArrayList<>();
        networks = new ArrayList<>();
    }

    public void addInput(Variable v) {
        this.inputs.add(v);
    }

    public void removeInput(Variable v) {
        this.inputs.remove(v);
    }

    public void addOutput(Variable v) {
        this.outputs.add(v);
    }

    public void removeOutput(Variable v) {
        this.outputs.remove(v);
    }

    public void addInOut(Variable v) {
        this.inout.add(v);
    }

    public void removeInOut(Variable v) {
        this.inout.remove(v);
    }

    public void addStatic(Variable v) {
        this.statics.add(v);
    }

    public void removeStatic(Variable v) {
        this.statics.remove(v);
    }

    public void addTemp(Variable v) {
        this.temp.add(v);
    }

    public void removeTemp(Variable v) {
        this.temp.remove(v);
    }

    public List<Variable> getInputs() {
        return List.copyOf(inputs);
    }

    public List<Variable> getOutputs() {
        return List.copyOf(outputs);
    }

    public List<Variable> getInout() {
        return List.copyOf(inout);
    }

    public List<Variable> getStatics() {
        return List.copyOf(statics);
    }

    @Override
    public void reload() {
        StringBuilder sb = new StringBuilder("TITLE = " + super.name + "\n"
                + "{ S7_Optimized_Access := 'TRUE'}\n"
                + "VERSION : 0.1\n"
        );
        sb.append("VAR_INPUT\n");
        inputs.forEach(v -> {
            sb.append("\t").append(v.getName()).append(" : ").append(v.getType().toString());
            if (v.getDefaultValue() != null) {
                sb.append(" : ").append(v.toString());
            }
            sb.append(";");
            String comment = v.getComment();
            if (comment != null && !comment.isBlank()) {
                sb.append("//").append(comment);
            }
            sb.append("\n");
        });
        sb.append("END_VAR\n");

        sb.append("VAR_OUTPUT\n");
        outputs.forEach(v -> {
            sb.append("\t").append(v.getName()).append(" : ").append(v.getType().toString()).append(";");
            String comment = v.getComment();
            if (comment != null && !comment.isBlank()) {
                sb.append("//").append(comment);
            }
            sb.append("\n");
        });
        sb.append("END_VAR\n");

        sb.append("VAR_IN_OUT\n");
        inout.forEach(v -> {
            sb.append("\t").append(v.getName()).append(" : ").append(v.getType().toString());
            if (v.getDefaultValue() != null) {
                sb.append(" : ").append(v.toString());
            }
            sb.append(";");
            String comment = v.getComment();
            if (comment != null && !comment.isBlank()) {
                sb.append("//").append(comment);
            }
            sb.append("\n");
        });
        sb.append("END_VAR\n");

        sb.append("VAR_TEMP\n");
        temp.forEach(v -> {
            sb.append("\t").append(v.getName()).append(" : ").append(v.getType().toString());
            if (v.getDefaultValue() != null) {
                sb.append(" : ").append(v.toString());
            }
            sb.append(";");
            String comment = v.getComment();
            if (comment != null && !comment.isBlank()) {
                sb.append("//").append(comment);
            }
            sb.append("\n");
        });
        sb.append("END_VAR\n");

        sb.append("VAR CONSTANT\n");
        statics.forEach(v -> {
            sb.append("\t").append(v.getName()).append(" : ").append(v.getType().toString());
            if (v.getDefaultValue() != null) {
                sb.append(" : ").append(v.toString());
            }
            sb.append(";");
            String comment = v.getComment();
            if (comment != null && !comment.isBlank()) {
                sb.append("//").append(comment);
            }
            sb.append("\n");
        });
        sb.append("END_VAR\n");
        declaration = sb.toString();
    }

}
