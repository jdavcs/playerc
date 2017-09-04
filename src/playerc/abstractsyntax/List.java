package playerc.abstractsyntax;

import java.util.*;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 07 2005
 * modified Sep 04 2017
 * Comment: a make-list object will continuosly pop the semantic stack and accum ASTs 
 * 			until it pops an AST of the wrong type
 */
public abstract class List extends AbstractSyntaxTree
{
   private Vector list;
   
   public List(int lineNumber) 
   { 
      super(lineNumber);
      list = new Vector(); 
   }   
   
   public int size() { return list.size(); }
   
   public Iterator iterator() { return new ListIterator(list); }
   
   public void add(AbstractSyntaxTree element) { list.addElement(element); } 
   
   public boolean isEmpty() { return list.isEmpty(); }
   
   private class ListIterator implements Iterator
   {
      private int cursor;
      private Vector list;
      
      private ListIterator(Vector list) 
      {
         this.list = list;
         cursor = list.size(); 
      }
      
      public boolean hasNext() { return cursor > 0; }
     
      public Object next() 
      { 
         cursor--;
         return list.elementAt(cursor); 
      }
      
      public void remove() {}      
   }
}
