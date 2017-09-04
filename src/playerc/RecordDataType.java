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
public class RecordDataType extends ConstructedDataType {
  public RecordDataType(String name, int line) throws SemanticException {
    super(name, DataType.RECORD, line);
    // must store a collection of tuples: DataType x String
  }
}
