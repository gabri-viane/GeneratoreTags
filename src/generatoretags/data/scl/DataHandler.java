package generatoretags.data.scl;

import static generatoretags.data.scl.VariableType.Byte;
import static generatoretags.data.scl.VariableType.Word;

/**
 *
 * @author gabri
 */
public class DataHandler {

    public static boolean hasDefault(Variable v){
        return v.getDefaultValue() != null;
    }
    
    public static Object computeDefault(Variable v) {
        if (v.getDefaultValue() != null) {
            return v.getDefaultValue();
        }
        switch (v.getType()) {
            case Bool -> {
                return "FALSE";
            }
            case DInt, DWord, Int, Sint, UDInt, UInt, USInt, Word, Byte -> {
                return 0;
            }
            case Char, String -> {
                return "''";
            }
            case LReal, Real -> {
                return 0.0d;
            }
            default -> {
                return null;
            }
        }
    }
}
