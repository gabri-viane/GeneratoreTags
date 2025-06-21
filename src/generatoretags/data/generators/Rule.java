/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package generatoretags.data.generators;

import java.io.Serializable;

/**
 *
 * @author gabri
 * @param <T> Valore restituito dalla regola
 */
public interface Rule<T> extends Serializable{
    
    public RuleType getRuleType();

    public T compute(RuleApplier generator);
}
