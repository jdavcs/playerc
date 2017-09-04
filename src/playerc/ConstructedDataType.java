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
public abstract class ConstructedDataType extends DataType {
  private String name;

  public ConstructedDataType(String name, int type, int line) throws SemanticException {
    super(type, line);
    if (type < ARRAY)
      throw new SemanticException("invalid type enumeration value for constructed type: " + type, line);
    this.name = name;
  }

  public boolean equals(DataType dt) {
    return name == dt.name();
  }

  public String name() {
    return name;
  }
}
