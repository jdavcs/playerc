/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

import java.util.Stack;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class SymbolTable {
  private final int SIZE = 256;
  private final String scopeMarker = "";
  private Bucket table[];
  private Stack undoStack;

  public SymbolTable() {
    table = new Bucket[SIZE];
    undoStack = new Stack();
    setScope();
  }

  public void setScope() {
    undoStack.push(scopeMarker);
  }

  public void removeScope() {
    while (true) {
      String s = (String) undoStack.pop();
      if (s.equals(scopeMarker))
        break;
      else
        pop(s);
    }
  }

  public void insert(String s, TypeExpression t, int line) throws SemanticException {
    checkNotExistsInScope(s, line);
    int i = hash(s);
    table[i] = new Bucket(s, t, table[i]);
    undoStack.push(s);
  }

  public void update(String s, TypeExpression t, int line) throws SemanticException {
    // find within this scope and change TypeExpression
    checkExistsInScope(s, line);
    int i = hash(s);
    table[i] = new Bucket(s, t, table[i]);
    undoStack.push(s);
  }

  public TypeExpression lookup(String s, int line) throws SemanticException {

    int i = hash(s);
    for (Bucket b = table[i]; b != null; b = b.next)
      if (s.equals(b.key))
        return b.type;

    throw new SemanticException("symbol " + s + " not found", line);
  }

  public void pop(String s) {
    int i = hash(s);
    table[i] = table[i].next;
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < SIZE; i++) {
      if (table[i] != null)
        for (Bucket b = table[i]; b != null; b = b.next)
          buffer.append(b.key + " == " + b.type.typename().toString() + "\n");
    }
    return buffer.toString();
  }

  private void checkNotExistsInScope(String s, int line) throws SemanticException {
    int pos = undoStack.size() - 1;
    while (pos > -1) {
      String test = (String) undoStack.elementAt(pos);
      if (test.equals(scopeMarker))
        break;
      else if (test.compareTo(s) == 0)
        throw new SemanticException("symbol " + s + " is already declared within the current scope", line);
      pos--;
    }
  }

  private void checkExistsInScope(String s, int line) throws SemanticException {
    int pos = undoStack.size() - 1;
    while (pos > -1) {
      String test = (String) undoStack.elementAt(pos);
      if (test.compareTo(s) == 0)
        return;
      if (test.equals(scopeMarker))
        break;
      pos--;
    }
    throw new SemanticException("symbol " + s + " is already declared within the current scope", line);
  }

  private int hash(String s) {
    int h = 0;
    for (int i = 0; i < s.length(); i++)
      h = h * 65599 + s.charAt(i);
    return Math.abs(h % SIZE);
  }

  private class Bucket {
    private String key;
    private TypeExpression type;
    private Bucket next;

    private Bucket(String key, TypeExpression type, Bucket next) {
      this.key = key;
      this.type = type;
      this.next = next;
    }
  }
}