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
public class TypeExpression {
  public static final TypeExpression Boolean = new TypeExpression("boolean");
  public static final TypeExpression Integer = new TypeExpression("integer");
  public static final TypeExpression Real = new TypeExpression("real");
  public static final TypeExpression StringType = new TypeExpression("string");
  public static final TypeExpression VoidType = new TypeExpression("void");
  public static final TypeExpression ErrorType = new TypeExpression("error");
  public static final TypeExpression Program = new TypeExpression("program");
  public static final TypeExpression NullType = new TypeExpression("null");
  public static final TypeExpression TemporaryType = new TypeExpression("temp"); // used
                                                                                 // during
                                                                                 // 1st
                                                                                 // pass
                                                                                 // in
                                                                                 // type
                                                                                 // checking

  private String typename;

  public static TypeExpression makeTypeExpression(String s) {
    if (s.compareTo("boolean") == 0)
      return TypeExpression.Boolean;
    else if (s.compareTo("integer") == 0)
      return TypeExpression.Integer;
    else if (s.compareTo("real") == 0)
      return TypeExpression.Real;
    else if (s.compareTo("string") == 0)
      return TypeExpression.StringType;
    else if (s.compareTo("program") == 0)
      return TypeExpression.Program;
    else if (s.compareTo("error") == 0)
      return TypeExpression.ErrorType;
    else if (s.compareTo("void") == 0)
      return TypeExpression.VoidType;
    else if (s.compareTo("null") == 0)
      return TypeExpression.NullType;
    else
      return new TypeExpression(s);
  }

  public boolean equals(TypeExpression te) {
    return te.typename().compareTo(typename) == 0;
  }

  public int hashcode() {
    return typename.hashCode();
  }

  public String typename() {
    return typename;
  }

  protected TypeExpression(String typename) {
    this.typename = typename;
  }
}