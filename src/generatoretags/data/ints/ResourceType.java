/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package generatoretags.data.ints;

/**
 *
 * @author gabri
 */
public enum ResourceType {
    
    FunctionBlock("FB"), Function("FC"), DataBlock("DB"), FunctionInstance("DBI");
    
    
    private final String shortName;
    
    private ResourceType(String shrtName){
        this.shortName = shrtName;
    }

    @Override
    public String toString() {
        return this.shortName; 
    }
    
    
}
