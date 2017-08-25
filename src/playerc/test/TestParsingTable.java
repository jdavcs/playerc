package playerc.test;

import playerc.Grammar;
import playerc.ParseAction;
import playerc.ParsingException;
import playerc.ParsingTable;
import playerc.PushSymbol;
import junit.framework.TestCase;

/**
 * Author: sergei
 * Created: Jul 19, 2005
 */
public class TestParsingTable extends TestCase
{
   public void testAdd() throws ParsingException
   {
	   Grammar g = new Grammar("test/source/TestGrammar1.txt", "null", "->", "|");   
	   ParsingTable t = new ParsingTable(g.terminals(), g.nonterminals());
	   t.add("A", "b", new PushSymbol(""));
      
	   try { 
		   t.add("no such symbol", "b", new PushSymbol(""));
         fail();
      } catch (ParsingException e) {}
   }
   
   public void testLookUp() throws ParsingException
   {
      Grammar g = new Grammar("test/source/TestGrammar1.txt", "null", "->", "|"); 
      ParsingTable t = new ParsingTable(g.terminals(), g.nonterminals());   
      ParseAction pa1 = new PushSymbol("");
      ParseAction pa2 = new PushSymbol("");
      t.add("A", "b", pa1);
      t.add("B", "b", pa2);
      
      assertTrue(pa1.equals(t.lookup("A", "b")));
      assertTrue(pa2.equals(t.lookup("B", "b")));
      assertFalse(pa1.equals(t.lookup("B", "b")));
      assertFalse(pa2.equals(t.lookup("A", "b")));      
            
      try { 
         t.lookup("no such symbol", "b");
         fail();
      } catch (ParsingException e) {}      
   }
}
