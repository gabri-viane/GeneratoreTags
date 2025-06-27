/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generatoretags;

import generatoretags.data.Project;
import generatoretags.data.rules.ResourceRuleApplier;
import generatoretags.data.rules.ResultHolder;
import generatoretags.data.rules.RuleApplier;
import generatoretags.data.rules.ints.ResourceRule;
import generatoretags.data.rules.ints.RuleType;
import generatoretags.data.ints.ResourceElement;
import generatoretags.data.ints.ResourceType;
import generatoretags.data.scl.Network;
import generatoretags.data.scl.Variable;
import generatoretags.data.scl.VariableType;
import generatoretags.data.scl.impls.DataBlockInstanceElement;
import generatoretags.data.scl.impls.FunctionBlockElement;
import generatoretags.data.scl.impls.FunctionCallInstruction;
import generatoretags.data.scl.impls.FunctionElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

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

        Project p = new Project("progetto di test");
        p.addResource(fb);
        p.addResource(fc);
        p.addResource(fbinstance);

        FileOutputStream fos;
        try {
            fos = new FileOutputStream("prova.out");
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(p);
                oos.flush();
            }
            fos.close();
        } catch (FileNotFoundException ex) {
            System.getLogger(GeneratoreTags.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(GeneratoreTags.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        Project loaded = null;

        FileInputStream fis;
        try {
            fis = new FileInputStream("prova.out");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object readObject = ois.readObject();
            loaded = (Project) readObject;
        } catch (FileNotFoundException ex) {
            System.getLogger(GeneratoreTags.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            System.getLogger(GeneratoreTags.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        if (loaded != null) {
            System.out.println(loaded.getProjectName());
        }

        RuleApplier nameRule = new RuleApplier();
        nameRule.addResource(fc);
        nameRule.addResource(fbinstance);

        ResourceRule nameGenerator = new ResourceRule() {
            @Override
            public RuleType getRuleType() {
                return RuleType.NAME_RULE;
            }

            @Override
            public Void compute(ResourceElement res, ResultHolder<ResourceElement> result) {
                String name = switch (res.getType()) {
                    case DataBlock, FunctionInstance ->
                        "DB " + res.getName() + ".db";
                    case Function ->
                        "FC " + res.getName() + ".awl";
                    case FunctionBlock ->
                        "FB " + res.getName() + ".awl";
                };
                result.addProperty("FileName", name);
                return null;
            }
        };

        ResourceRule fileGenerator = new ResourceRule() {
            @Override
            public RuleType getRuleType() {
                return RuleType.FILE_RULE;
            }

            @Override
            public Void compute(ResourceElement param1, ResultHolder<ResourceElement> param2) {
                param2.addProperty("FilePath", ".\\");
                return null;
            }
        };

        ResourceRuleApplier rra = new ResourceRuleApplier();
        rra.addRule(fileGenerator);
        rra.addRule(nameGenerator);

        Map<ResourceType, Map<String, ResourceElement>> resources = loaded.getResources();

        resources.forEach((rt, map) -> {
            map.forEach((name, element) -> {
                FileWriter fw;
                try {
                    ResultHolder<ResourceElement> appliedRules = rra.applyRules(element);
                    String pathToFile = ((String) appliedRules.get("FilePath")) + ((String) appliedRules.get("FileName"));
                    File f = new File(pathToFile);
                    fw = new FileWriter(f);
                    fw.write(element.toString());
                    fw.close();
                } catch (IOException ex) {
                    System.getLogger(GeneratoreTags.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            });
        });

    }

}
