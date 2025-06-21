/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data.generators;

/**
 *
 * @author gabri
 */
public class EmptyRule implements Rule<Void>{

    @Override
    public RuleType getRuleType() {
        return RuleType.GENERIC_CONTENT_RULE;
    }

    @Override
    public Void compute(RuleApplier generator) {
        return null;
    }
    
}
