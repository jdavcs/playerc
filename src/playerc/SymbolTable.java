package playerc;

import java.util.*;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  May 23 2006
 * modified Sep 04 2017
 */
public class SymbolTable
{
	private final String scopeMarker = "";
	private final int SIZE = 256;
	private Stack scopeStack;
	private Bucket[] table;
	   
	public SymbolTable()
	{
		scopeStack = new Stack();
		table = new Bucket[SIZE];
		setScope();
	}
	
	public void setScope() { scopeStack.push(scopeMarker); }
	
	   public void removeScope()
	   { 
	      while (true)
	      {
	         String s = (String)scopeStack.pop();
	         if (s.equals(scopeMarker))
	            break;
	         else
	         {
	        	Bucket b = findBucket(s);
	        	Bucket parent = b.scopeParent;
	        	
	        	if (parent != null)
	        		parent.scopeChild = null;
	        	else //remove bucket alltogether: there's no additional scope here
	        	{
	        		Bucket hashParent = b.hashParent;
	        		Bucket hashChild = b.hashChild;
	        		if (hashParent != null)
	        			hashParent.hashChild = hashChild;
	        		else	        		
	        			table[hash(s)] = hashChild;	        		
	        	}           
	         }
	      }
	   }
	
	public void insert(String s, DataType type, int line) throws SemanticException
	{		
		checkNotInScope(s, line);
		
		Bucket b = findBucket(s);
		if (b != null) //found bucket containing same symbol -> add scope link		
		{
			Bucket temp = new Bucket(s, type, b.hashParent, b.hashChild, b, null);
			b.scopeChild = temp;
		}
		else
		{
			int i = hash(s);		
			if (table[i] == null) //bucket is empty -> create new bucket for symbol
				table[i] = new Bucket(s, type, null, null, null, null);		
			else //bucket contains other symbol -> add hash link (we KNOW that this symbol has not been added yet - so it's not hidden by first link)			
				table[i] = new Bucket(s, type, table[i].hashParent, table[i], null, null);			
		}
		
		scopeStack.push(s);		
	}
	
	public DataType lookup(String s, int line) throws SemanticException
	{
		Bucket b = findBucket(s);
		if (b == null)
			throw new SemanticException("SymbolTable lookup error: symbol " + s + " not found", line);
		return b.type;
	}

	public void update(String s, DataType type, int line) throws SemanticException
	{
		checkInScope(s, line);
		Bucket b = findBucket(s);
		b.type = type;
	}
	
	private void checkNotInScope(String s, int line) throws SemanticException
	{
		for (int i=scopeStack.size()-1; i>-1; i--)
		{
			String test = (String)scopeStack.elementAt(i);
			if (test.equals(scopeMarker))
				return;
			else
				if (test.compareTo(s) == 0)
					throw new SemanticException("SymbolTable insert error: symbol " + s + " is declared within the current scope", line);	         
		}		
	}
	
	private void checkInScope(String s, int line) throws SemanticException
	{
		for (int i=scopeStack.size()-1; i>-1; i--)
		{
			String test = (String)scopeStack.elementAt(i);
			if (test.compareTo(s) == 0)
				return;
			if (test.equals(scopeMarker))
				throw new SemanticException("SymbolTable update error: symbol " + s + " is not declared within the current scope", line);				
		}
	}
	
	private int hash(String s) 
	{
		int h = 0;
		for (int i=0; i<s.length(); i++)
			h = h * 65599 + s.charAt(i);
		return Math.abs(h % SIZE);
	}
	
	private Bucket findBucket(String s)
	{
		int i = hash(s);
		Bucket b = table[i];
		while (b != null)		
			if (b.key == s) 
			{
				while (b.scopeChild != null)  //find the latest scopelink!
					b = b.scopeChild;				
				return b;
			}
			else
				b = b.hashChild;		
		return null;
	}
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();   
		for (int i=0; i< SIZE; i++)
		{
			if (table[i] != null)
			{
				buffer.append("Position " + i + '\n');
				for (Bucket b = table[i]; b != null; b = b.hashChild)
				{
					buffer.append("    key=" + b.key + "; type=" + b.type.name() + '\n');
					for (Bucket s = b; s != null; s = s.scopeChild)
						buffer.append("      key=" + s.key + "; type=" + s.type.name() + '\n');			
				}		
			}
		}
		return buffer.toString();
	}
	
	private class Bucket
	{
		private String key;
		private DataType type;
		private Bucket hashParent; //chains different symbols with the same hash value
		private Bucket hashChild;
		private Bucket scopeParent; //chains same symbols with different scopes
		private Bucket scopeChild;
		
		
		private Bucket(String key, DataType type, Bucket hashParent, Bucket hashChild, Bucket scopeParent, Bucket scopeChild)
		{
			this.key = key;
			this.type = type;
			this.hashParent = hashParent;
			this.hashChild = hashChild;
			this.scopeParent = scopeParent;
			this.scopeChild = scopeChild;			
		}		
	}
}
