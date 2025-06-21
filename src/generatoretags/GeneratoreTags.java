/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generatoretags;

import generatoretags.data.scl.Network;
import generatoretags.data.scl.Variable;
import generatoretags.data.scl.VariableType;
import generatoretags.data.scl.impls.DataBlockInstanceElement;
import generatoretags.data.scl.impls.FunctionBlockElement;
import generatoretags.data.scl.impls.FunctionCallInstruction;
import generatoretags.data.scl.impls.FunctionElement;

/**
 *
 * @author gabri
 */
public class GeneratoreTags {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FunctionBlockElement fb = new FunctionBlockElement("@pc test 5000");

        Variable sample_t = new Variable("SAMPLE_T", VariableType.Real);
        sample_t.setDefaultValue(0.01d);
        fb.addInput(sample_t);

        Variable prova = new Variable("Prova", VariableType.Bool);
        fb.addInput(prova);

        fb.addOutput(new Variable("TestOUT", VariableType.String));

        FunctionElement fc = new FunctionElement("@pc temp");
        fc.addInput(prova);
        Network n = new Network("test");
        fc.addNetwork(n);

        DataBlockInstanceElement fbinstance = new DataBlockInstanceElement("SB 224", fb);
        FunctionCallInstruction functionCallInstruction = new FunctionCallInstruction(fb);
        functionCallInstruction.bindDBInstance(fbinstance);
        n.addInstruction(functionCallInstruction);
        
        System.out.println(fc);
    }

}
