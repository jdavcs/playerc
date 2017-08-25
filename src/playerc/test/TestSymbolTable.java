package playerc.test;

import java.util.*;
import playerc.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 23, 2006 9:00:56 PM
 */
public class TestSymbolTable extends BaseTestCase
{
	public void testSimpleInsertLookup() throws SemanticException
	{
		SymbolTable table = new SymbolTable();
		
		PrimitiveDataType typeA = new PrimitiveDataType(DataType.INTEGER, -1);
		PrimitiveDataType typeB = new PrimitiveDataType(DataType.REAL, -1);
		PrimitiveDataType typeC = new PrimitiveDataType(DataType.BOOLEAN, -1);
		PrimitiveDataType typeD = new PrimitiveDataType(DataType.STRING, -1);
		PrimitiveDataType typeE = new PrimitiveDataType(DataType.NULL, -1);
		PrimitiveDataType typeF = new PrimitiveDataType(DataType.VOID, -1);
		PrimitiveDataType typeG = new PrimitiveDataType(DataType.ERROR, -1);
		ArrayDataType typeH = new ArrayDataType("arr1", typeA, -1);
		ArrayDataType typeI = new ArrayDataType("arr2", typeB, -1);
		RecordDataType typeJ = new RecordDataType("rec1", -1);
		RecordDataType typeK = new RecordDataType("rec2", -1);
		
		table.insert("varA", typeA, -1);
		table.insert("varB", typeB, -1);
		table.insert("varC", typeC, -1);
		table.insert("varD", typeD, -1);
		table.insert("varE", typeE, -1);
		table.insert("varF", typeF, -1);
		table.insert("varG", typeG, -1);
		table.insert("varH", typeH, -1);
		table.insert("varI", typeI, -1);
		table.insert("varJ", typeJ, -1);
		table.insert("varK", typeK, -1);
		
		DataType checkA = table.lookup("varA", -1);
		DataType checkB = table.lookup("varB", -1);
		DataType checkC = table.lookup("varC", -1);
		DataType checkD = table.lookup("varD", -1);
		DataType checkE = table.lookup("varE", -1);
		DataType checkF = table.lookup("varF", -1);
		DataType checkG = table.lookup("varG", -1);
		DataType checkH = table.lookup("varH", -1);
		DataType checkI = table.lookup("varI", -1);
		DataType checkJ = table.lookup("varJ", -1);
		DataType checkK = table.lookup("varK", -1);
		
		assertTrue(typeA.equals(checkA));
		assertTrue(typeB.equals(checkB));
		assertTrue(typeC.equals(checkC));
		assertTrue(typeD.equals(checkD));
		assertTrue(typeE.equals(checkE));
		assertTrue(typeF.equals(checkF));
		assertTrue(typeG.equals(checkG));
		assertTrue(typeH.equals(checkH));
		assertTrue(typeI.equals(checkI));
		assertTrue(typeJ.equals(checkJ));
		assertTrue(typeK.equals(checkK));
		
		assertFalse(typeH.equals(checkI));
		assertFalse(typeI.equals(checkH));
		assertFalse(typeJ.equals(checkK));
		assertFalse(typeK.equals(checkJ));
		
		assertEquals(DataType.INTEGER_NAME, checkA.name());
		assertEquals(DataType.REAL_NAME, checkB.name());
		assertEquals(DataType.BOOLEAN_NAME, checkC.name());
		assertEquals(DataType.STRING_NAME, checkD.name());
		assertEquals(DataType.NULL_NAME, checkE.name());
		assertEquals(DataType.VOID_NAME, checkF.name());
		assertEquals(DataType.ERROR_NAME, checkG.name());
		assertEquals("arr1", checkH.name());	
		assertEquals("arr2", checkI.name());	
		assertEquals("rec1", checkJ.name());	
		assertEquals("rec2", checkK.name());	
	}
	
	public void testInsertDuplicate() throws SemanticException
	{
		SymbolTable table = new SymbolTable();
		PrimitiveDataType typeA = new PrimitiveDataType(DataType.INTEGER, -1);
		PrimitiveDataType typeB = new PrimitiveDataType(DataType.REAL, -1);
		
		table.insert("varA", typeA, -1);
		try {
			table.insert("varA", typeA, -1);
			fail();
		}
		catch (Exception e) { System.out.println(e.toString()); }	
		
		try {
			table.insert("varA", typeB, -1);
			fail();
		}
		catch (Exception e) { System.out.println(e.toString()); }	
	}
	
	public void testLookupWrongSymbol() throws SemanticException
	{
		SymbolTable table = new SymbolTable();
		PrimitiveDataType typeA = new PrimitiveDataType(DataType.INTEGER, -1);
		
		table.insert("varA", typeA, -1);
		try {
			table.lookup("varB", -1);
			fail();
		}
		catch (Exception e) { System.out.println(e.toString()); }	
	}
	
	public void testUpdate()throws SemanticException
	{
		SymbolTable table = new SymbolTable();
		
		PrimitiveDataType typeA = new PrimitiveDataType(DataType.INTEGER, -1);
		PrimitiveDataType typeB = new PrimitiveDataType(DataType.REAL, -1);	
		
		table.insert("varA", typeA, -1);		
		DataType checkA = table.lookup("varA", -1);
		assertTrue(typeA.equals(checkA));
		
		table.update("varA", typeB, -1);
		DataType checkB = table.lookup("varA", -1);
		assertTrue(typeB.equals(checkB));				
	}
	
	public void testUpdateWrongSymbol() throws SemanticException
	{
		SymbolTable table = new SymbolTable();
		PrimitiveDataType typeA = new PrimitiveDataType(DataType.INTEGER, -1);
		
		try {
			table.update("varA", typeA, -1);
			fail();
		}
		catch (Exception e) { System.out.println(e.toString()); }	
	}	
	
	public void testSameHash() throws SemanticException
	{
		SymbolTable table = new SymbolTable();
		PrimitiveDataType typeA = new PrimitiveDataType(DataType.INTEGER, -1);
		PrimitiveDataType typeB = new PrimitiveDataType(DataType.REAL, -1);

		String s1 = "abc";
		String s2 = "b"; //both hash to 98!
		
		table.insert(s1, typeA, -1);
		table.insert(s2, typeB, -1);
		
		//System.out.println(table.toString());
		
		DataType checkA = table.lookup(s1, -1);
		DataType checkB = table.lookup(s2, -1);
		
		assertTrue(typeA.equals(checkA));
		assertTrue(typeB.equals(checkB));
		
		table.update(s1, typeB, -1);
		table.update(s2, typeA, -1);
		
		checkA = table.lookup(s1, -1);
		checkB = table.lookup(s2, -1);
		
		assertTrue(typeA.equals(checkB));
		assertTrue(typeB.equals(checkA));
	}
	
	public void testScopes() throws SemanticException
	{
		SymbolTable table = new SymbolTable();
		PrimitiveDataType typeA = new PrimitiveDataType(DataType.INTEGER, -1);
		PrimitiveDataType typeB = new PrimitiveDataType(DataType.REAL, -1);
		PrimitiveDataType typeC = new PrimitiveDataType(DataType.BOOLEAN, -1);
		PrimitiveDataType typeD = new PrimitiveDataType(DataType.STRING, -1);
		String s1 = "abc";
		String s2 = "b";
		
		table.insert(s1, typeA, -1);
		assertTrue(typeA.equals(table.lookup(s1, -1)));
		
		table.insert(s2, typeA, -1);
		assertTrue(typeA.equals(table.lookup(s2, -1)));
		
		table.setScope();
		table.insert(s1, typeB, -1);
		table.insert("c", typeA, -1);
		assertTrue(typeB.equals(table.lookup(s1, -1)));
		
		table.setScope();
		table.insert(s1, typeC, -1);
		table.insert("d", typeA, -1);
		assertTrue(typeC.equals(table.lookup(s1, -1)));
		
		table.setScope();
		table.insert(s1, typeD, -1);
		table.insert("e", typeA, -1);
		assertTrue(typeD.equals(table.lookup(s1, -1)));
		
		table.removeScope();
		assertTrue(typeC.equals(table.lookup(s1, -1)));
		
		table.removeScope();
		assertTrue(typeB.equals(table.lookup(s1, -1)));
		
		table.removeScope();
		assertTrue(typeA.equals(table.lookup(s1, -1)));
	}	
}
