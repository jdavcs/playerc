package playerc;

import java.util.HashMap;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 26 2005
 * modified Sep 04 2017
 */
public class RecordTypeExpression extends TypeExpression
{
   private HashMap members;
   
   public RecordTypeExpression(String typename, HashMap members)
   {
      super(typename);
      this.members = members;
   }
   
   public TypeExpression memberType(String memberId, int lineNumber) throws SemanticException
   {
      if (members.containsKey(memberId))
         return (TypeExpression)members.get(memberId);
      else
         throw new SemanticException("member " + memberId + " of record " + typename() + " not found", lineNumber);
   }
   
   public HashMap members() { return members; }
}
