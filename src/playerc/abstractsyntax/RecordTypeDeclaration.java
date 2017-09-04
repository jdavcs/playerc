/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class RecordTypeDeclaration extends Declaration {
  private Identifier id;
  private MemberList members;

  public RecordTypeDeclaration(Identifier id, MemberList members, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.members = members;
  }

  public Identifier id() {
    return id;
  }

  public MemberList members() {
    return members;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
