/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generatoretags.data.generators;

/**
 *
 * @author gabri
 */
public class PlainStringRule implements Rule<String>{

    private String text;
    
    public PlainStringRule(String text){
        this.text = text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    @Override
    public String compute(RuleApplier generator) {
        return this.text;
    }

    @Override
    public RuleType getRuleType() {
        return RuleType.GENERIC_CONTENT_RULE;
    }
    
}
