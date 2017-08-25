package playerc;

import java.util.*;

/**
 * Author: sergei
 * Created: Sep 2, 2005
 */
public class RecordSymbolTables
{
   private Vector tables;
   
   public RecordSymbolTables()
   {
      tables = new Vector();
   }
   
   public void insert(String recordName, String s, TypeExpression t, int line) throws SemanticException
   {
      RecordSymbolTable table = getTable(recordName);
      table.insert(s, t, line);     
   }
   
   public TypeExpression lookup(String recordName, String s, int line) throws SemanticException
   {
      System.out.println("RecordSymbolTables lookup called");
      //TODO implement!
      return null;
//      throw new SemanticException("symbol " + s + " not found", line);      
   }
   
   private RecordSymbolTable getTable(String name)
   {
      Iterator i = tables.iterator();
      while (i.hasNext()) 
      {
         RecordSymbolTable t = (RecordSymbolTable)i.next();
         if (t.recordName.compareTo(name) == 0)
            return t;
      }
      RecordSymbolTable t = new RecordSymbolTable(name);
      tables.addElement(t);      
      return t;
   }
   
   private class RecordSymbolTable
   {
      private String recordName;
      private HashMap symbols;
      
      private RecordSymbolTable(String recordName)
      {
         symbols = new HashMap();
         this.recordName = recordName;
      }
      
      private void insert(String s, TypeExpression t, int line) throws SemanticException
      {
         if (symbols.containsKey(s))
            throw new SemanticException
            	("Symbol " + s + " already has been declared within record " + recordName, line);     
         else
            symbols.put(s, t);
      }
      
      private TypeExpression lookup(String s, int line) throws SemanticException
      {
         if (symbols.containsKey(s))         
            return (TypeExpression)symbols.get(s);
         else            
            throw new SemanticException("symbol " + s + " not found", line);      
      }      
   }
}
