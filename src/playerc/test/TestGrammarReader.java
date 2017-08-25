package playerc.test;

import java.io.*;
import java.util.*;

import playerc.GrammarReader;
import playerc.Production;

/**
 * Author: sergei
 * Created: Jul 15, 2005
 */
public class TestGrammarReader extends BaseTestCase
{	
	public void testRemoveLHS()
	{
		   GrammarReader r = new GrammarReader("->", "|");	   
		   String method = "removeLHS";
		   Class[] formals = { String.class };	
		   
		   String[] params1 = { " abc -> x | y | z " };
		   String[] params2 = { " | 123 " };
		   
		   String expected1 = " x | y | z ";
		   String expected2 = " | 123 ";		   
		   
		   String result1 = (String)invokeMethod(r, method, formals, params1);
		   String result2 = (String)invokeMethod(r, method, formals, params2);		
		   
		   assertEquals(expected1, result1);
		   assertEquals(expected2, result2);
	}
	
	public void testMakeLHS()
	{
		   GrammarReader r = new GrammarReader("->", "|");	   
		   String method = "makeLHS";
		   Class[] formals = { String.class, String.class };		   
		   
		   String[] params1 = { null, "abc -> x" };
		   String[] params2 = { null, " 	abc 	-> x" };
		   String[] params3 = { "A", " | x" };
		   String[] params4 = { "A", "B -> x" };
		   
		   String expected1 = "abc";
		   String expected2 = "abc";
		   String expected3 = "A";
		   String expected4 = "B";
		   
		   String result1 = (String)invokeMethod(r, method, formals, params1);
		   String result2 = (String)invokeMethod(r, method, formals, params2);
		   String result3 = (String)invokeMethod(r, method, formals, params3);
		   String result4 = (String)invokeMethod(r, method, formals, params4);
		   
		   assertEquals(expected1, result1);
		   assertEquals(expected2, result2);
		   assertEquals(expected3, result3);
		   assertEquals(expected4, result4);
	}
	
	   public void testMakeRHS()
	   {		   
		   GrammarReader r = new GrammarReader("->", "|");	
		   String method = "makeRHS";		   
		   Class[] formals = { String.class, boolean.class };		 		
		   
		   Object[] params1 = { " ab MAKE-action cd ", Boolean.FALSE };
		   Object[] params2 = { " ab MAKE-action cd ", Boolean.TRUE }; 
		   
		   String expected1_1 = "ab";
		   String expected1_2 = "cd";		
		   String expected2_1 = "ab";
		   String expected2_2 = "MAKE-action";
		   String expected2_3 = "cd";   

		   Vector result1 = (Vector)invokeMethod(r, method, formals, params1);
		   Vector result2 = (Vector)invokeMethod(r, method, formals, params2);

		   assertEquals(2, result1.size());
	      assertEquals(3, result2.size());
	      	      
	      assertEquals(expected1_1, result1.elementAt(0));
	      assertEquals(expected1_2, result1.elementAt(1));
	      assertEquals(expected2_1, result2.elementAt(0));
	      assertEquals(expected2_2, result2.elementAt(1));
	      assertEquals(expected2_3, result2.elementAt(2)); 
	   }

	   public void testGetProductions() throws IOException, Exception
	   {	   
		   GrammarReader r = new GrammarReader("->", "|");	
		   String method = "getProductions";		   
		   Class[] formals = { String.class, boolean.class };		 		
		   
		   Object[] params1 = { "test/source/TestGrammarReader1.txt", Boolean.FALSE };  		   
		   Vector result1 = (Vector)invokeMethod(r, method, formals, params1);
	   
		   assertEquals(6, result1.size());
		   Production p1_1 = (Production)result1.elementAt(0);
		   Production p1_2 = (Production)result1.elementAt(1);
		   Production p1_3 = (Production)result1.elementAt(2);
		   Production p1_4 = (Production)result1.elementAt(3);
		   Production p1_5 = (Production)result1.elementAt(4);
		   Production p1_6 = (Production)result1.elementAt(5);
		   
		   assertEquals("A", p1_1.lhs());
		   assertEquals("A", p1_2.lhs());
		   assertEquals("A", p1_3.lhs());
		   assertEquals("B", p1_4.lhs());
		   assertEquals("C", p1_5.lhs());
		   assertEquals("C", p1_6.lhs());
		   
		   assertEquals(3, p1_1.rhs().size());
		   assertEquals(3, p1_2.rhs().size());
		   assertEquals(1, p1_3.rhs().size());
		   assertEquals(1, p1_4.rhs().size());
		   assertEquals(2, p1_5.rhs().size());
		   assertEquals(2, p1_6.rhs().size());
		   
		   assertEquals("x1", p1_1.rhs().elementAt(0));
		   assertEquals("y1", p1_1.rhs().elementAt(1));
		   assertEquals("z1", p1_1.rhs().elementAt(2));
		   assertEquals("1", p1_2.rhs().elementAt(0));
		   assertEquals("2", p1_2.rhs().elementAt(1));
		   assertEquals("3", p1_2.rhs().elementAt(2));
		   assertEquals("null", p1_3.rhs().elementAt(0));
		   assertEquals("a", p1_4.rhs().elementAt(0));
		   assertEquals("a1", p1_5.rhs().elementAt(0));
		   assertEquals("b1", p1_5.rhs().elementAt(1));
		   assertEquals("c1", p1_6.rhs().elementAt(0));
		   assertEquals("d1", p1_6.rhs().elementAt(1));
		  
		   Object[] params2 = { "test/source/TestGrammarReader1.txt", Boolean.TRUE };
		   Vector result2 = (Vector)invokeMethod(r, method, formals, params2);
		   
		   assertEquals(6, result2.size());
		   Production p2_1 = (Production)result2.elementAt(0);
		   Production p2_2 = (Production)result2.elementAt(1);
		   Production p2_3 = (Production)result2.elementAt(2);
		   Production p2_4 = (Production)result2.elementAt(3);
		   Production p2_5 = (Production)result2.elementAt(4);
		   Production p2_6 = (Production)result2.elementAt(5);
		   
		   assertEquals("A", p2_1.lhs());
		   assertEquals("A", p2_2.lhs());
		   assertEquals("A", p2_3.lhs());
		   assertEquals("B", p2_4.lhs());
		   assertEquals("C", p2_5.lhs());
		   assertEquals("C", p2_6.lhs());
		   
		   assertEquals(3, p2_1.rhs().size());
		   assertEquals(3, p2_2.rhs().size());
		   assertEquals(1, p2_3.rhs().size());
		   assertEquals(1, p2_4.rhs().size());
		   assertEquals(2, p2_5.rhs().size());
		   assertEquals(3, p2_6.rhs().size());
		   
		   assertEquals("x1", p2_1.rhs().elementAt(0));
		   assertEquals("y1", p2_1.rhs().elementAt(1));
		   assertEquals("z1", p2_1.rhs().elementAt(2));
		   assertEquals("1", p2_2.rhs().elementAt(0));
		   assertEquals("2", p2_2.rhs().elementAt(1));
		   assertEquals("3", p2_2.rhs().elementAt(2));
		   assertEquals("null", p2_3.rhs().elementAt(0));
		   assertEquals("a", p2_4.rhs().elementAt(0));
		   assertEquals("a1", p2_5.rhs().elementAt(0));
		   assertEquals("b1", p2_5.rhs().elementAt(1));
		   assertEquals("MAKE-action", p2_6.rhs().elementAt(0));
		   assertEquals("c1", p2_6.rhs().elementAt(1));		   
		   assertEquals("d1", p2_6.rhs().elementAt(2)); 
	   }
}
