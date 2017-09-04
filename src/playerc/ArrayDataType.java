/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class ArrayDataType extends ConstructedDataType {
  private DataType elementType;

  public ArrayDataType(String name, DataType elementType, int line) throws SemanticException {
    super(name, DataType.ARRAY, line);
    this.elementType = elementType;
  }

  public DataType elementType() {
    return elementType;
  }
}
