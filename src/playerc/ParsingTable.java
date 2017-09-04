/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc;

import java.util.Vector;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1 (terminals/nonterminals/sem actions) are implemented as strings
 */
public class ParsingTable {
  private ParseAction[][] table;
  private Vector terminals;
  private Vector nonterminals;
  private int[] cellWidths;
  private Vector displayProductions;

  public ParsingTable(Vector terminals, Vector nonterminals) {
    this.terminals = terminals;
    this.nonterminals = nonterminals;
    table = new ParseAction[nonterminals.size()][terminals.size()];
    cellWidths = new int[terminals.size() + 1];
    displayProductions = new Vector();
  }

  public void add(String nonterminal, String terminal, ParseAction action) throws ParsingException {
    int row = nonterminals.indexOf(nonterminal);
    int col = terminals.indexOf(terminal);
    if (row < 0)
      throw new ParsingException("add: noterminal " + nonterminal + " does not exist in grammar");
    if (col < 0)
      throw new ParsingException("add: terminal " + terminal + " does not exist in grammar");
    if (table[row][col] != null)
      throw new ParsingException("attempting to add an action to a non-empty cell");

    table[row][col] = action;
  }

  // returns a sequence of parse actions for a nonterminal + terminal
  // combination
  public ParseAction lookup(String nonterminal, String terminal) throws ParsingException {
    int row = nonterminals.indexOf(nonterminal);
    int col = terminals.indexOf(terminal);
    if (row < 0)
      throw new ParsingException("lookup: noterminal " + nonterminal + " does not exist in grammar");
    if (col < 0)
      throw new ParsingException("lookup: terminal " + terminal + " does not exist in grammar");

    ParseAction action = table[row][col];
    if (action == null)
      throw new ParsingException("no production for nonterminal " + nonterminal + " and terminal " + terminal);

    return action;
  }

  public String toString() {
    int tableWidth = getTableWidth();
    StringBuffer buffer = new StringBuffer();

    // make top horizontal line
    for (int i = 0; i < tableWidth - 1; i++)
      buffer.append("-");
    String hLine = buffer.toString();
    buffer.delete(0, buffer.length());

    // make middle horizontal line
    buffer.append(hLine + "\n");

    // make left title cell
    int leftCellWidth = getLeftCellWidth();
    cellWidths[0] = leftCellWidth;
    buffer.append(makeBlank(leftCellWidth));
    buffer.append("|");

    // make other title cells
    for (int i = 0; i < terminals.size(); i++)
      buffer.append(makeTitleCell((String) terminals.elementAt(i), i));
    buffer.append("\n" + hLine + "\n");

    // make table
    for (int i = 0; i < nonterminals.size(); i++) {
      String symbol = (String) nonterminals.elementAt(i);
      buffer.append(makeLeftCell(symbol));
      for (int j = 0; j < terminals.size(); j++)
        buffer.append(makeCell(symbol, (String) terminals.elementAt(j), j));

      buffer.append(" \n");
    }
    // make bottom horizontal line
    buffer.append(hLine + "\n");

    // display all productions
    for (int i = 0; i < displayProductions.size(); i++) {
      buffer.append(i + ". ");
      buffer.append(displayProductions.elementAt(i) + "\n");
    }
    return buffer.toString();
  }

  private String makeCell(String symbol, String token, int index) {
    int row = nonterminals.indexOf(symbol);
    int col = index;
    if (table[row][col] == null)
      return makeBlank(cellWidths[index]) + "|";
    else {
      ParseAction pa = table[row][col];
      displayProductions.addElement(symbol + " -> " + pa.toString());
      String text = "" + (displayProductions.size() - 1);
      return makeCellText(text, index);
    }
  }

  private String makeLeftCell(String symbol) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(symbol);
    buffer.append(makeBlank(getLeftCellWidth() - symbol.length()));
    buffer.append("|");
    return buffer.toString();
  }

  private int getTableWidth() {
    int width = getLeftCellWidth() + 1;
    for (int i = 0; i < terminals.size(); i++)
      width += getCellWidth((String) terminals.elementAt(i)) + 1;
    return width;
  }

  private int getLeftCellWidth() {
    int width = 0;
    for (int i = 0; i < nonterminals.size(); i++)
      width = Math.max(width, ((String) nonterminals.elementAt(i)).length());
    return width + 1;
  }

  private String makeTitleCell(String text, int index) {
    int minTextWidth = 3;
    if (text.length() < minTextWidth)
      text += makeBlank(minTextWidth - text.length());
    StringBuffer buffer = new StringBuffer();
    buffer.append(" " + text + " |");
    cellWidths[index] = buffer.length() - 1;
    return buffer.toString();
  }

  private String makeCellText(String text, int index) {
    int width = cellWidths[index];
    StringBuffer buffer = new StringBuffer(" ");
    buffer.append(text);
    buffer.append(" ");
    if (buffer.length() < width)
      buffer.append(makeBlank(width - buffer.length()));
    buffer.append("|");
    return buffer.toString();
  }

  private int getCellWidth(String text) {
    return Math.max(text.length(), 3) + 2;
  }

  private String makeBlank(int length) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < length; i++)
      buffer.append(" ");
    return buffer.toString();
  }

  public String generateTestCode() {
    StringBuffer buffer = new StringBuffer();

    int k = 0;
    for (int i = 0; i < nonterminals.size(); i++)
      for (int j = 0; j < terminals.size(); j++) {
        ParseAction a = null;
        String production = "";
        try {
          a = lookup((String) nonterminals.elementAt(i), (String) terminals.elementAt(j));
          production = a.toString();
          buffer.append("\nParseAction a" + k + " = table.lookup(\"");
          buffer.append(nonterminals.elementAt(i) + "\", \"" + terminals.elementAt(j) + "\");\n");
          buffer.append("assertEquals(\"" + production + "\", a" + k + ".toString());\n");
          k++;
        } catch (ParsingException e) {
        }
      }
    return buffer.toString();
  }
}
