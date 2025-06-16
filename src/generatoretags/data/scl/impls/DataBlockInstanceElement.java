/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data.scl.impls;

import generatoretags.data.ints.Reloadable;
import generatoretags.data.ints.ResourceType;
import generatoretags.data.scl.Variable;
import generatoretags.data.scl.ints.DataBlockResource;
import generatoretags.data.scl.ints.FunctionResource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Rapparesenta un DataBlock d'istanza ed è associato a una Function [FC] o
 * FunctionBlock [FB].
 * <br>
 * Per modificare i valori delle variabili usare {@link #getVariables() } in
 * congiunta con {@link #setVariableDefaultValue(generatoretags.data.scl.Variable, java.lang.Object)
 * }.
 *
 *
 * @author gabri
 */
public class DataBlockInstanceElement extends DataBlockResource implements Reloadable {

    private final FunctionResource bindedFunction;

    public DataBlockInstanceElement(String name, FunctionResource function) {
        super(name, ResourceType.FunctionInstance);
        this.bindedFunction = function;
        reload();
    }

    @Override
    protected String getDeclarationEnd() {
        StringBuilder sb = new StringBuilder("\"");
        sb.append(this.bindedFunction.getName()).append("\"\n");
        return sb.toString();
    }

    @Override
    public List<Variable> getVariables() {
        return Collections.unmodifiableList(variables);
    }

    /**
     * Imposta il valore di default di una variabile già definita in questo DB
     * associato all'interfaccia di una Function o FunctionBlock
     *
     * @param v La variabile (deve essere presente nella lista) a cui impostare
     * il valore di default.
     * @param value Il valore da impostare.
     */
    public void setVariableDefaultValue(Variable v, Object value) {
        int indexOf = this.variables.indexOf(value);
        if (indexOf > -1) {
            this.variables.get(indexOf).setDefaultValue(value);
        }
    }

    @Override
    public final void reload() {
        super.variables = new ArrayList<>();
        variables.addAll(bindedFunction.getInputs());
        variables.addAll(bindedFunction.getOutputs());
        variables.addAll(bindedFunction.getInout());
        variables.addAll(bindedFunction.getStatics());
    }

}
