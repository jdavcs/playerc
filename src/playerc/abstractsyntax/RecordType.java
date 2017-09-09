/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class RecordType extends NewType {
  private MemberList members;

  public RecordType(MemberList members, int lineNumber) {
    super(lineNumber);
    this.members = members;
  }

  public MemberList members() {
    return members;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
