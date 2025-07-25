/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data.scl.impls;

import generatoretags.data.scl.ints.FunctionResource;
import generatoretags.data.ints.ResourceType;
import generatoretags.data.scl.Variable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gabri
 */
public class FunctionBlockElement extends FunctionResource {

    private static final long serialVersionUID = 1L;

    public FunctionBlockElement(String name) {
        super(name, ResourceType.FunctionBlock);
    }

    @Override
    public String getDefinition() {
        StringBuilder sb = new StringBuilder("BEGIN\n");
        this.networks.forEach(netw -> {
            sb.append(netw.toString()).append("\n");
        });
        sb.append("END_FUNCTION_BLOCK");
        this.definition = sb.toString();
        return this.definition;
    }

    @Override
    public String getDeclaration() {
        if (declaration == null) {
            this.reload();
        }
        String declInit = "FUNCTION_BLOCK \"" + super.name + "\"\n";
        return declInit + this.declaration;
    }

    @Override
    public Map<String, Variable> getVariables() {
        Map<String, Variable> variables = new HashMap<>();
        variables.putAll(this.inputs);
        variables.putAll(this.outputs);
        variables.putAll(this.outputs);
        variables.putAll(this.statics);
        return variables;
    }

}
