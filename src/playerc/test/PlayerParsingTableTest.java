/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;
import playerc.Grammar;
import playerc.ParseAction;
import playerc.ParsingException;
import playerc.ParsingTable;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class PlayerParsingTableTest extends TestCase {
  public void test1() throws ParsingException {
    ParsingTable table = makeTable();
    // System.out.println(table.generateTestCode());

    ParseAction a0 = table.lookup("program", "'program'");
    assertEquals(" 'program'  identifier  make-id  body  make-program ", a0.toString());

    ParseAction a1 = table.lookup("actual-params", "'('");
    assertEquals(" '('  actual-params-rest ", a1.toString());

    ParseAction a2 = table.lookup("actual-params-rest", "identifier");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a2.toString());

    ParseAction a3 = table.lookup("actual-params-rest", "'('");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a3.toString());

    ParseAction a4 = table.lookup("actual-params-rest", "')'");
    assertEquals(" ')' ", a4.toString());

    ParseAction a5 = table.lookup("actual-params-rest", "'+'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a5.toString());

    ParseAction a6 = table.lookup("actual-params-rest", "'-'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a6.toString());

    ParseAction a7 = table.lookup("actual-params-rest", "'true'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a7.toString());

    ParseAction a8 = table.lookup("actual-params-rest", "'false'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a8.toString());

    ParseAction a9 = table.lookup("actual-params-rest", "string");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a9.toString());

    ParseAction a10 = table.lookup("actual-params-rest", "integer");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a10.toString());

    ParseAction a11 = table.lookup("actual-params-rest", "real");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a11.toString());

    ParseAction a12 = table.lookup("actual-params-rest", "'null'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a12.toString());

    ParseAction a13 = table.lookup("actual-params-rest", "'boolean'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a13.toString());

    ParseAction a14 = table.lookup("actual-params-rest", "'integer'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a14.toString());

    ParseAction a15 = table.lookup("actual-params-rest", "'real'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a15.toString());

    ParseAction a16 = table.lookup("actual-params-rest", "'string'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a16.toString());

    ParseAction a17 = table.lookup("actual-params-rest", "'not'");
    assertEquals(" expression  expressions-more-opt  make-expression-list  ')' ", a17.toString());

    ParseAction a18 = table.lookup("array-init", "identifier");
    assertEquals(" expression  array-init-tail  make-array-init ", a18.toString());

    ParseAction a19 = table.lookup("array-init", "'('");
    assertEquals(" expression  array-init-tail  make-array-init ", a19.toString());

    ParseAction a20 = table.lookup("array-init", "'+'");
    assertEquals(" expression  array-init-tail  make-array-init ", a20.toString());

    ParseAction a21 = table.lookup("array-init", "'-'");
    assertEquals(" expression  array-init-tail  make-array-init ", a21.toString());

    ParseAction a22 = table.lookup("array-init", "'true'");
    assertEquals(" expression  array-init-tail  make-array-init ", a22.toString());

    ParseAction a23 = table.lookup("array-init", "'false'");
    assertEquals(" expression  array-init-tail  make-array-init ", a23.toString());

    ParseAction a24 = table.lookup("array-init", "string");
    assertEquals(" expression  array-init-tail  make-array-init ", a24.toString());

    ParseAction a25 = table.lookup("array-init", "integer");
    assertEquals(" expression  array-init-tail  make-array-init ", a25.toString());

    ParseAction a26 = table.lookup("array-init", "real");
    assertEquals(" expression  array-init-tail  make-array-init ", a26.toString());

    ParseAction a27 = table.lookup("array-init", "'null'");
    assertEquals(" expression  array-init-tail  make-array-init ", a27.toString());

    ParseAction a28 = table.lookup("array-init", "'boolean'");
    assertEquals(" expression  array-init-tail  make-array-init ", a28.toString());

    ParseAction a29 = table.lookup("array-init", "'integer'");
    assertEquals(" expression  array-init-tail  make-array-init ", a29.toString());

    ParseAction a30 = table.lookup("array-init", "'real'");
    assertEquals(" expression  array-init-tail  make-array-init ", a30.toString());

    ParseAction a31 = table.lookup("array-init", "'string'");
    assertEquals(" expression  array-init-tail  make-array-init ", a31.toString());

    ParseAction a32 = table.lookup("array-init", "'not'");
    assertEquals(" expression  array-init-tail  make-array-init ", a32.toString());

    ParseAction a33 = table.lookup("array-init-tail", "'of'");
    assertEquals(" 'of'  expression ", a33.toString());

    ParseAction a34 = table.lookup("array-init-tail", "�");
    assertEquals("(empty) ", a34.toString());

    ParseAction a35 = table.lookup("array-init-tail", "'>]'");
    assertEquals("(empty) ", a35.toString());

    ParseAction a36 = table.lookup("array-init-tail", "','");
    assertEquals("(empty) ", a36.toString());

    ParseAction a37 = table.lookup("array-inits", "'[<'");
    assertEquals(" '[<'  array-init  array-inits-more-opt  '>]'  make-array-init-list ", a37.toString());

    ParseAction a38 = table.lookup("array-inits-more-opt", "�");
    assertEquals("(empty) ", a38.toString());

    ParseAction a39 = table.lookup("array-inits-more-opt", "'>]'");
    assertEquals("(empty) ", a39.toString());

    ParseAction a40 = table.lookup("array-inits-more-opt", "','");
    assertEquals(" ','  array-init  array-inits-more-opt ", a40.toString());

    ParseAction a41 = table.lookup("binary-op", "'+'");
    assertEquals(" '+' ", a41.toString());

    ParseAction a42 = table.lookup("binary-op", "'-'");
    assertEquals(" '-' ", a42.toString());

    ParseAction a43 = table.lookup("binary-op", "'*'");
    assertEquals(" '*' ", a43.toString());

    ParseAction a44 = table.lookup("binary-op", "'/'");
    assertEquals(" '/' ", a44.toString());

    ParseAction a45 = table.lookup("binary-op", "'and'");
    assertEquals(" 'and' ", a45.toString());

    ParseAction a46 = table.lookup("binary-op", "'or'");
    assertEquals(" 'or' ", a46.toString());

    ParseAction a47 = table.lookup("binary-op", "'>'");
    assertEquals(" '>' ", a47.toString());

    ParseAction a48 = table.lookup("binary-op", "'<'");
    assertEquals(" '<' ", a48.toString());

    ParseAction a49 = table.lookup("binary-op", "'='");
    assertEquals(" '=' ", a49.toString());

    ParseAction a50 = table.lookup("binary-op", "'>='");
    assertEquals(" '>=' ", a50.toString());

    ParseAction a51 = table.lookup("binary-op", "'<='");
    assertEquals(" '<=' ", a51.toString());

    ParseAction a52 = table.lookup("binary-op", "'<>'");
    assertEquals(" '<>' ", a52.toString());

    ParseAction a53 = table.lookup("body", "'begin'");
    assertEquals(
        " make-body-marker  declarations-opt  make-declaration-list  'begin'  statements-opt  make-statement-list  'end'  make-body ",
        a53.toString());

    ParseAction a54 = table.lookup("body", "'var'");
    assertEquals(
        " make-body-marker  declarations-opt  make-declaration-list  'begin'  statements-opt  make-statement-list  'end'  make-body ",
        a54.toString());

    ParseAction a55 = table.lookup("body", "'type'");
    assertEquals(
        " make-body-marker  declarations-opt  make-declaration-list  'begin'  statements-opt  make-statement-list  'end'  make-body ",
        a55.toString());

    ParseAction a56 = table.lookup("body", "'procedure'");
    assertEquals(
        " make-body-marker  declarations-opt  make-declaration-list  'begin'  statements-opt  make-statement-list  'end'  make-body ",
        a56.toString());

    ParseAction a57 = table.lookup("by-expression-opt", "�");
    assertEquals("(empty) ", a57.toString());

    ParseAction a58 = table.lookup("by-expression-opt", "'by'");
    assertEquals(" 'by'  expression  make-by-expression-opt ", a58.toString());

    ParseAction a59 = table.lookup("by-expression-opt", "'do'");
    assertEquals("(empty) ", a59.toString());

    ParseAction a60 = table.lookup("declaration", "'var'");
    assertEquals(" 'var'  var-decls-opt ", a60.toString());

    ParseAction a61 = table.lookup("declaration", "'type'");
    assertEquals(" 'type'  type-decls-opt ", a61.toString());

    ParseAction a62 = table.lookup("declaration", "'procedure'");
    assertEquals(" 'procedure'  procedure-decls-opt ", a62.toString());

    ParseAction a63 = table.lookup("declarations-opt", "�");
    assertEquals("(empty) ", a63.toString());

    ParseAction a64 = table.lookup("declarations-opt", "'begin'");
    assertEquals("(empty) ", a64.toString());

    ParseAction a65 = table.lookup("declarations-opt", "'var'");
    assertEquals(" declaration  declarations-opt ", a65.toString());

    ParseAction a66 = table.lookup("declarations-opt", "'type'");
    assertEquals(" declaration  declarations-opt ", a66.toString());

    ParseAction a67 = table.lookup("declarations-opt", "'procedure'");
    assertEquals(" declaration  declarations-opt ", a67.toString());

    ParseAction a68 = table.lookup("decl-typename-opt", "�");
    assertEquals("(empty) ", a68.toString());

    ParseAction a69 = table.lookup("decl-typename-opt", "'begin'");
    assertEquals("(empty) ", a69.toString());

    ParseAction a70 = table.lookup("decl-typename-opt", "'var'");
    assertEquals("(empty) ", a70.toString());

    ParseAction a71 = table.lookup("decl-typename-opt", "'type'");
    assertEquals("(empty) ", a71.toString());

    ParseAction a72 = table.lookup("decl-typename-opt", "'procedure'");
    assertEquals("(empty) ", a72.toString());

    ParseAction a73 = table.lookup("decl-typename-opt", "':'");
    assertEquals(" ':'  typename ", a73.toString());

    ParseAction a74 = table.lookup("decl-typename-opt", "':='");
    assertEquals("(empty) ", a74.toString());

    ParseAction a75 = table.lookup("else-opt", "�");
    assertEquals("(empty) ", a75.toString());

    ParseAction a76 = table.lookup("else-opt", "'end'");
    assertEquals("(empty) ", a76.toString());

    ParseAction a77 = table.lookup("else-opt", "'else'");
    assertEquals(" 'else'  statement  statements-opt  make-statement-list ", a77.toString());

    ParseAction a78 = table.lookup("elseif", "'elseif'");
    assertEquals(" 'elseif'  exp-then-stms ", a78.toString());

    ParseAction a79 = table.lookup("elseifs-opt", "�");
    assertEquals("(empty) ", a79.toString());

    ParseAction a80 = table.lookup("elseifs-opt", "'end'");
    assertEquals("(empty) ", a80.toString());

    ParseAction a81 = table.lookup("elseifs-opt", "'else'");
    assertEquals("(empty) ", a81.toString());

    ParseAction a82 = table.lookup("elseifs-opt", "'elseif'");
    assertEquals(" elseif  elseifs-opt ", a82.toString());

    ParseAction a83 = table.lookup("exp-then-stms", "identifier");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a83.toString());

    ParseAction a84 = table.lookup("exp-then-stms", "'('");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a84.toString());

    ParseAction a85 = table.lookup("exp-then-stms", "'+'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a85.toString());

    ParseAction a86 = table.lookup("exp-then-stms", "'-'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a86.toString());

    ParseAction a87 = table.lookup("exp-then-stms", "'true'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a87.toString());

    ParseAction a88 = table.lookup("exp-then-stms", "'false'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a88.toString());

    ParseAction a89 = table.lookup("exp-then-stms", "string");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a89.toString());

    ParseAction a90 = table.lookup("exp-then-stms", "integer");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a90.toString());

    ParseAction a91 = table.lookup("exp-then-stms", "real");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a91.toString());

    ParseAction a92 = table.lookup("exp-then-stms", "'null'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a92.toString());

    ParseAction a93 = table.lookup("exp-then-stms", "'boolean'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a93.toString());

    ParseAction a94 = table.lookup("exp-then-stms", "'integer'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a94.toString());

    ParseAction a95 = table.lookup("exp-then-stms", "'real'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a95.toString());

    ParseAction a96 = table.lookup("exp-then-stms", "'string'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a96.toString());

    ParseAction a97 = table.lookup("exp-then-stms", "'not'");
    assertEquals(" expression  'then'  statement  statements-opt  make-statement-list  make-exp-then-statements ",
        a97.toString());

    ParseAction a98 = table.lookup("expression", "identifier");
    assertEquals(" identifier  make-id  expression-id-rest ", a98.toString());

    ParseAction a99 = table.lookup("expression", "'('");
    assertEquals(" paren-expression ", a99.toString());

    ParseAction a100 = table.lookup("expression", "'+'");
    assertEquals(" unary-op  make-operator  expression  make-unary-op-expression  expression-tail ", a100.toString());

    ParseAction a101 = table.lookup("expression", "'-'");
    assertEquals(" unary-op  make-operator  expression  make-unary-op-expression  expression-tail ", a101.toString());

    ParseAction a102 = table.lookup("expression", "'true'");
    assertEquals(" 'true'  make-true-expression  expression-tail ", a102.toString());

    ParseAction a103 = table.lookup("expression", "'false'");
    assertEquals(" 'false'  make-false-expression  expression-tail ", a103.toString());

    ParseAction a104 = table.lookup("expression", "string");
    assertEquals(" string  make-string-expression  expression-tail ", a104.toString());

    ParseAction a105 = table.lookup("expression", "integer");
    assertEquals(" integer  make-integer-expression  expression-tail ", a105.toString());

    ParseAction a106 = table.lookup("expression", "real");
    assertEquals(" real  make-real-expression  expression-tail ", a106.toString());

    ParseAction a107 = table.lookup("expression", "'null'");
    assertEquals(" 'null'  make-null-expression ", a107.toString());

    ParseAction a108 = table.lookup("expression", "'boolean'");
    assertEquals(" prim-typename  array-inits  make-array-inits-expression  expression-tail ", a108.toString());

    ParseAction a109 = table.lookup("expression", "'integer'");
    assertEquals(" prim-typename  array-inits  make-array-inits-expression  expression-tail ", a109.toString());

    ParseAction a110 = table.lookup("expression", "'real'");
    assertEquals(" prim-typename  array-inits  make-array-inits-expression  expression-tail ", a110.toString());

    ParseAction a111 = table.lookup("expression", "'string'");
    assertEquals(" prim-typename  array-inits  make-array-inits-expression  expression-tail ", a111.toString());

    ParseAction a112 = table.lookup("expression", "'not'");
    assertEquals(" unary-op  make-operator  expression  make-unary-op-expression  expression-tail ", a112.toString());

    ParseAction a113 = table.lookup("paren-expression", "'('");
    assertEquals(" '('  expression  ')'  make-paren-expression  expression-tail ", a113.toString());

    ParseAction a114 = table.lookup("expression-id-rest", "'('");
    assertEquals(" actual-params  make-call-expression  expression-tail ", a114.toString());

    ParseAction a115 = table.lookup("expression-id-rest", "')'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a115.toString());

    ParseAction a116 = table.lookup("expression-id-rest", "'of'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a116.toString());

    ParseAction a117 = table.lookup("expression-id-rest", "�");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a117.toString());

    ParseAction a118 = table.lookup("expression-id-rest", "'[<'");
    assertEquals(" make-new-typename  array-inits  make-array-inits-expression  expression-tail ", a118.toString());

    ParseAction a119 = table.lookup("expression-id-rest", "'>]'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a119.toString());

    ParseAction a120 = table.lookup("expression-id-rest", "','");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a120.toString());

    ParseAction a121 = table.lookup("expression-id-rest", "'+'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a121.toString());

    ParseAction a122 = table.lookup("expression-id-rest", "'-'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a122.toString());

    ParseAction a123 = table.lookup("expression-id-rest", "'*'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a123.toString());

    ParseAction a124 = table.lookup("expression-id-rest", "'/'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a124.toString());

    ParseAction a125 = table.lookup("expression-id-rest", "'and'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a125.toString());

    ParseAction a126 = table.lookup("expression-id-rest", "'or'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a126.toString());

    ParseAction a127 = table.lookup("expression-id-rest", "'>'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a127.toString());

    ParseAction a128 = table.lookup("expression-id-rest", "'<'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a128.toString());

    ParseAction a129 = table.lookup("expression-id-rest", "'='");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a129.toString());

    ParseAction a130 = table.lookup("expression-id-rest", "'>='");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a130.toString());

    ParseAction a131 = table.lookup("expression-id-rest", "'<='");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a131.toString());

    ParseAction a132 = table.lookup("expression-id-rest", "'<>'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a132.toString());

    ParseAction a133 = table.lookup("expression-id-rest", "'by'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a133.toString());

    ParseAction a134 = table.lookup("expression-id-rest", "'then'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a134.toString());

    ParseAction a135 = table.lookup("expression-id-rest", "';'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a135.toString());

    ParseAction a136 = table.lookup("expression-id-rest", "'['");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a136.toString());

    ParseAction a137 = table.lookup("expression-id-rest", "']'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a137.toString());

    ParseAction a138 = table.lookup("expression-id-rest", "'.'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a138.toString());

    ParseAction a139 = table.lookup("expression-id-rest", "'{'");
    assertEquals(" make-new-typename  record-inits  make-record-inits-expression  expression-tail ", a139.toString());

    ParseAction a140 = table.lookup("expression-id-rest", "'}'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a140.toString());

    ParseAction a141 = table.lookup("expression-id-rest", "'to'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a141.toString());

    ParseAction a142 = table.lookup("expression-id-rest", "'do'");
    assertEquals(" make-id-lvalue  lvalue-tail  make-lvalue-expression  expression-tail ", a142.toString());

    ParseAction a143 = table.lookup("expression-tail", "')'");
    assertEquals("(empty) ", a143.toString());

    ParseAction a144 = table.lookup("expression-tail", "'of'");
    assertEquals("(empty) ", a144.toString());

    ParseAction a145 = table.lookup("expression-tail", "�");
    assertEquals("(empty) ", a145.toString());

    ParseAction a146 = table.lookup("expression-tail", "'>]'");
    assertEquals("(empty) ", a146.toString());

    ParseAction a147 = table.lookup("expression-tail", "','");
    assertEquals("(empty) ", a147.toString());

    ParseAction a148 = table.lookup("expression-tail", "'+'");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a148.toString());

    ParseAction a149 = table.lookup("expression-tail", "'-'");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a149.toString());

    ParseAction a150 = table.lookup("expression-tail", "'*'");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a150.toString());

    ParseAction a151 = table.lookup("expression-tail", "'/'");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a151.toString());

    ParseAction a152 = table.lookup("expression-tail", "'and'");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a152.toString());

    ParseAction a153 = table.lookup("expression-tail", "'or'");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a153.toString());

    ParseAction a154 = table.lookup("expression-tail", "'>'");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a154.toString());

    ParseAction a155 = table.lookup("expression-tail", "'<'");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a155.toString());

    ParseAction a156 = table.lookup("expression-tail", "'='");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a156.toString());

    ParseAction a157 = table.lookup("expression-tail", "'>='");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a157.toString());

    ParseAction a158 = table.lookup("expression-tail", "'<='");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a158.toString());

    ParseAction a159 = table.lookup("expression-tail", "'<>'");
    assertEquals(" binary-op  make-operator  expression  make-binary-op-expression  expression-tail ", a159.toString());

    ParseAction a160 = table.lookup("expression-tail", "'by'");
    assertEquals("(empty) ", a160.toString());

    ParseAction a161 = table.lookup("expression-tail", "'then'");
    assertEquals("(empty) ", a161.toString());

    ParseAction a162 = table.lookup("expression-tail", "';'");
    assertEquals("(empty) ", a162.toString());

    ParseAction a163 = table.lookup("expression-tail", "']'");
    assertEquals("(empty) ", a163.toString());

    ParseAction a164 = table.lookup("expression-tail", "'}'");
    assertEquals("(empty) ", a164.toString());

    ParseAction a165 = table.lookup("expression-tail", "'to'");
    assertEquals("(empty) ", a165.toString());

    ParseAction a166 = table.lookup("expression-tail", "'do'");
    assertEquals("(empty) ", a166.toString());

    ParseAction a167 = table.lookup("expression-opt", "identifier");
    assertEquals(" expression ", a167.toString());

    ParseAction a168 = table.lookup("expression-opt", "'('");
    assertEquals(" expression ", a168.toString());

    ParseAction a169 = table.lookup("expression-opt", "�");
    assertEquals("(empty) ", a169.toString());

    ParseAction a170 = table.lookup("expression-opt", "'+'");
    assertEquals(" expression ", a170.toString());

    ParseAction a171 = table.lookup("expression-opt", "'-'");
    assertEquals(" expression ", a171.toString());

    ParseAction a172 = table.lookup("expression-opt", "'true'");
    assertEquals(" expression ", a172.toString());

    ParseAction a173 = table.lookup("expression-opt", "'false'");
    assertEquals(" expression ", a173.toString());

    ParseAction a174 = table.lookup("expression-opt", "string");
    assertEquals(" expression ", a174.toString());

    ParseAction a175 = table.lookup("expression-opt", "integer");
    assertEquals(" expression ", a175.toString());

    ParseAction a176 = table.lookup("expression-opt", "real");
    assertEquals(" expression ", a176.toString());

    ParseAction a177 = table.lookup("expression-opt", "'null'");
    assertEquals(" expression ", a177.toString());

    ParseAction a178 = table.lookup("expression-opt", "';'");
    assertEquals("(empty) ", a178.toString());

    ParseAction a179 = table.lookup("expression-opt", "'boolean'");
    assertEquals(" expression ", a179.toString());

    ParseAction a180 = table.lookup("expression-opt", "'integer'");
    assertEquals(" expression ", a180.toString());

    ParseAction a181 = table.lookup("expression-opt", "'real'");
    assertEquals(" expression ", a181.toString());

    ParseAction a182 = table.lookup("expression-opt", "'string'");
    assertEquals(" expression ", a182.toString());

    ParseAction a183 = table.lookup("expression-opt", "'not'");
    assertEquals(" expression ", a183.toString());

    ParseAction a184 = table.lookup("expressions-more-opt", "')'");
    assertEquals("(empty) ", a184.toString());

    ParseAction a185 = table.lookup("expressions-more-opt", "�");
    assertEquals("(empty) ", a185.toString());

    ParseAction a186 = table.lookup("expressions-more-opt", "','");
    assertEquals(" ','  expression  expressions-more-opt ", a186.toString());

    ParseAction a187 = table.lookup("formal-params", "'('");
    assertEquals(" '('  formal-params-rest ", a187.toString());

    ParseAction a188 = table.lookup("formal-params-rest", "identifier");
    assertEquals(" fp-section  fp-sections-more-opt  ')'  make-fp-section-list ", a188.toString());

    ParseAction a189 = table.lookup("formal-params-rest", "')'");
    assertEquals(" ')' ", a189.toString());

    ParseAction a190 = table.lookup("fp-section", "identifier");
    assertEquals(" identifier  make-id  identifiers-more-opt  make-id-list  ':'  typename  make-fp-section ",
        a190.toString());

    ParseAction a191 = table.lookup("fp-sections-more-opt", "')'");
    assertEquals("(empty) ", a191.toString());

    ParseAction a192 = table.lookup("fp-sections-more-opt", "�");
    assertEquals("(empty) ", a192.toString());

    ParseAction a193 = table.lookup("fp-sections-more-opt", "';'");
    assertEquals(" ';'  fp-section  fp-sections-more-opt ", a193.toString());

    ParseAction a194 = table.lookup("identifiers-more-opt", "�");
    assertEquals("(empty) ", a194.toString());

    ParseAction a195 = table.lookup("identifiers-more-opt", "','");
    assertEquals(" ','  identifier  make-id  identifiers-more-opt ", a195.toString());

    ParseAction a196 = table.lookup("identifiers-more-opt", "':'");
    assertEquals("(empty) ", a196.toString());

    ParseAction a197 = table.lookup("identifiers-more-opt", "':='");
    assertEquals("(empty) ", a197.toString());

    ParseAction a198 = table.lookup("lvalue", "identifier");
    assertEquals(" identifier  make-id  make-id-lvalue  lvalue-tail ", a198.toString());

    ParseAction a199 = table.lookup("lvalue-tail", "')'");
    assertEquals("(empty) ", a199.toString());

    ParseAction a200 = table.lookup("lvalue-tail", "'of'");
    assertEquals("(empty) ", a200.toString());

    ParseAction a201 = table.lookup("lvalue-tail", "�");
    assertEquals("(empty) ", a201.toString());

    ParseAction a202 = table.lookup("lvalue-tail", "'>]'");
    assertEquals("(empty) ", a202.toString());

    ParseAction a203 = table.lookup("lvalue-tail", "','");
    assertEquals("(empty) ", a203.toString());

    ParseAction a204 = table.lookup("lvalue-tail", "'+'");
    assertEquals("(empty) ", a204.toString());

    ParseAction a205 = table.lookup("lvalue-tail", "'-'");
    assertEquals("(empty) ", a205.toString());

    ParseAction a206 = table.lookup("lvalue-tail", "'*'");
    assertEquals("(empty) ", a206.toString());

    ParseAction a207 = table.lookup("lvalue-tail", "'/'");
    assertEquals("(empty) ", a207.toString());

    ParseAction a208 = table.lookup("lvalue-tail", "'and'");
    assertEquals("(empty) ", a208.toString());

    ParseAction a209 = table.lookup("lvalue-tail", "'or'");
    assertEquals("(empty) ", a209.toString());

    ParseAction a210 = table.lookup("lvalue-tail", "'>'");
    assertEquals("(empty) ", a210.toString());

    ParseAction a211 = table.lookup("lvalue-tail", "'<'");
    assertEquals("(empty) ", a211.toString());

    ParseAction a212 = table.lookup("lvalue-tail", "'='");
    assertEquals("(empty) ", a212.toString());

    ParseAction a213 = table.lookup("lvalue-tail", "'>='");
    assertEquals("(empty) ", a213.toString());

    ParseAction a214 = table.lookup("lvalue-tail", "'<='");
    assertEquals("(empty) ", a214.toString());

    ParseAction a215 = table.lookup("lvalue-tail", "'<>'");
    assertEquals("(empty) ", a215.toString());

    ParseAction a216 = table.lookup("lvalue-tail", "'by'");
    assertEquals("(empty) ", a216.toString());

    ParseAction a217 = table.lookup("lvalue-tail", "'then'");
    assertEquals("(empty) ", a217.toString());

    ParseAction a218 = table.lookup("lvalue-tail", "';'");
    assertEquals("(empty) ", a218.toString());

    ParseAction a219 = table.lookup("lvalue-tail", "'['");
    assertEquals(" '['  expression  ']'  make-lookup-lvalue  lvalue-tail ", a219.toString());

    ParseAction a220 = table.lookup("lvalue-tail", "']'");
    assertEquals("(empty) ", a220.toString());

    ParseAction a221 = table.lookup("lvalue-tail", "'.'");
    assertEquals(" '.'  identifier  make-id  make-deref-lvalue  lvalue-tail ", a221.toString());

    ParseAction a222 = table.lookup("lvalue-tail", "':='");
    assertEquals("(empty) ", a222.toString());

    ParseAction a223 = table.lookup("lvalue-tail", "'}'");
    assertEquals("(empty) ", a223.toString());

    ParseAction a224 = table.lookup("lvalue-tail", "'to'");
    assertEquals("(empty) ", a224.toString());

    ParseAction a225 = table.lookup("lvalue-tail", "'do'");
    assertEquals("(empty) ", a225.toString());

    ParseAction a226 = table.lookup("lvalues-more-opt", "')'");
    assertEquals("(empty) ", a226.toString());

    ParseAction a227 = table.lookup("lvalues-more-opt", "�");
    assertEquals("(empty) ", a227.toString());

    ParseAction a228 = table.lookup("lvalues-more-opt", "','");
    assertEquals(" ','  lvalue  lvalues-more-opt ", a228.toString());

    ParseAction a229 = table.lookup("member", "identifier");
    assertEquals(" identifier  make-id  ':'  typename  ';'  make-member ", a229.toString());

    ParseAction a230 = table.lookup("members-opt", "identifier");
    assertEquals(" member  members-opt ", a230.toString());

    ParseAction a231 = table.lookup("members-opt", "�");
    assertEquals("(empty) ", a231.toString());

    ParseAction a232 = table.lookup("members-opt", "'end'");
    assertEquals("(empty) ", a232.toString());

    ParseAction a233 = table.lookup("new-typename", "identifier");
    assertEquals(" identifier  make-id  make-new-typename ", a233.toString());

    ParseAction a234 = table.lookup("prim-typename", "'boolean'");
    assertEquals(" 'boolean'  make-prim-typename ", a234.toString());

    ParseAction a235 = table.lookup("prim-typename", "'integer'");
    assertEquals(" 'integer'  make-prim-typename ", a235.toString());

    ParseAction a236 = table.lookup("prim-typename", "'real'");
    assertEquals(" 'real'  make-prim-typename ", a236.toString());

    ParseAction a237 = table.lookup("prim-typename", "'string'");
    assertEquals(" 'string'  make-prim-typename ", a237.toString());

    ParseAction a238 = table.lookup("procedure-decl", "identifier");
    assertEquals(
        " identifier  make-id  make-formal-params-marker  formal-params  decl-typename-opt  body  ';'  make-proc-declaration ",
        a238.toString());

    ParseAction a239 = table.lookup("procedure-decls-opt", "identifier");
    assertEquals(" procedure-decl  procedure-decls-opt ", a239.toString());

    ParseAction a240 = table.lookup("procedure-decls-opt", "�");
    assertEquals("(empty) ", a240.toString());

    ParseAction a241 = table.lookup("procedure-decls-opt", "'begin'");
    assertEquals("(empty) ", a241.toString());

    ParseAction a242 = table.lookup("procedure-decls-opt", "'var'");
    assertEquals("(empty) ", a242.toString());

    ParseAction a243 = table.lookup("procedure-decls-opt", "'type'");
    assertEquals("(empty) ", a243.toString());

    ParseAction a244 = table.lookup("procedure-decls-opt", "'procedure'");
    assertEquals("(empty) ", a244.toString());

    ParseAction a245 = table.lookup("record-init", "identifier");
    assertEquals(" identifier  make-id  ':='  expression  make-record-init ", a245.toString());

    ParseAction a246 = table.lookup("record-inits", "'{'");
    assertEquals(" '{'  record-init  record-inits-more-opt  '}'  make-record-init-list ", a246.toString());

    ParseAction a247 = table.lookup("record-inits-more-opt", "�");
    assertEquals("(empty) ", a247.toString());

    ParseAction a248 = table.lookup("record-inits-more-opt", "';'");
    assertEquals(" ';'  record-init  record-inits-more-opt ", a248.toString());

    ParseAction a249 = table.lookup("record-inits-more-opt", "'}'");
    assertEquals("(empty) ", a249.toString());

    ParseAction a250 = table.lookup("statement", "identifier");
    assertEquals(" identifier  make-id  statement-id-rest ", a250.toString());

    ParseAction a251 = table.lookup("statement", "'write'");
    assertEquals(" 'write'  make-write-marker  write-params  ';'  make-write-statement ", a251.toString());

    ParseAction a252 = table.lookup("statement", "'read'");
    assertEquals(
        " 'read'  make-read-marker  '('  lvalue  lvalues-more-opt  make-lvalue-list  ')'  ';'  make-read-statement ",
        a252.toString());

    ParseAction a253 = table.lookup("statement", "'exit'");
    assertEquals(" 'exit'  ';'  make-exit-statement ", a253.toString());

    ParseAction a254 = table.lookup("statement", "'return'");
    assertEquals(" 'return'  make-return-marker  expression-opt  ';'  make-return-statement ", a254.toString());

    ParseAction a255 = table.lookup("statement", "'loop'");
    assertEquals(
        " 'loop'  make-loop-marker  statement  statements-opt  make-statement-list  'end'  ';'  make-loop-statement ",
        a255.toString());

    ParseAction a256 = table.lookup("statement", "'for'");
    assertEquals(
        " 'for'  identifier  make-id  ':='  expression  'to'  expression  by-expression-opt  'do'  statements-opt  make-statement-list  'end'  ';'  make-for-statement ",
        a256.toString());

    ParseAction a257 = table.lookup("statement", "'if'");
    assertEquals(
        " 'if'  exp-then-stms  elseifs-opt  make-exp-then-statements-list  else-opt  'end'  ';'  make-if-statement ",
        a257.toString());

    ParseAction a258 = table.lookup("statement-id-rest", "'('");
    assertEquals(" actual-params  ';'  make-call-statement ", a258.toString());

    ParseAction a259 = table.lookup("statement-id-rest", "'['");
    assertEquals(" make-id-lvalue  lvalue-tail  ':='  expression  ';'  make-assignment-statement ", a259.toString());

    ParseAction a260 = table.lookup("statement-id-rest", "'.'");
    assertEquals(" make-id-lvalue  lvalue-tail  ':='  expression  ';'  make-assignment-statement ", a260.toString());

    ParseAction a261 = table.lookup("statement-id-rest", "':='");
    assertEquals(" make-id-lvalue  lvalue-tail  ':='  expression  ';'  make-assignment-statement ", a261.toString());

    ParseAction a262 = table.lookup("statements-opt", "identifier");
    assertEquals(" statement  statements-opt ", a262.toString());

    ParseAction a263 = table.lookup("statements-opt", "�");
    assertEquals("(empty) ", a263.toString());

    ParseAction a264 = table.lookup("statements-opt", "'end'");
    assertEquals("(empty) ", a264.toString());

    ParseAction a265 = table.lookup("statements-opt", "'else'");
    assertEquals("(empty) ", a265.toString());

    ParseAction a266 = table.lookup("statements-opt", "'elseif'");
    assertEquals("(empty) ", a266.toString());

    ParseAction a267 = table.lookup("statements-opt", "'write'");
    assertEquals(" statement  statements-opt ", a267.toString());

    ParseAction a268 = table.lookup("statements-opt", "'read'");
    assertEquals(" statement  statements-opt ", a268.toString());

    ParseAction a269 = table.lookup("statements-opt", "'exit'");
    assertEquals(" statement  statements-opt ", a269.toString());

    ParseAction a270 = table.lookup("statements-opt", "'return'");
    assertEquals(" statement  statements-opt ", a270.toString());

    ParseAction a271 = table.lookup("statements-opt", "'loop'");
    assertEquals(" statement  statements-opt ", a271.toString());

    ParseAction a272 = table.lookup("statements-opt", "'for'");
    assertEquals(" statement  statements-opt ", a272.toString());

    ParseAction a273 = table.lookup("statements-opt", "'if'");
    assertEquals(" statement  statements-opt ", a273.toString());

    ParseAction a274 = table.lookup("type", "'array'");
    assertEquals(" 'array'  'of'  typename  make-array-type ", a274.toString());

    ParseAction a275 = table.lookup("type", "'record'");
    assertEquals(" 'record'  member  members-opt  make-member-list  'end'  make-record-type ", a275.toString());

    ParseAction a276 = table.lookup("type-decl", "identifier");
    assertEquals(" new-typename  '='  type  ';'  make-type-declaration ", a276.toString());

    ParseAction a277 = table.lookup("type-decls-opt", "identifier");
    assertEquals(" type-decl  type-decls-opt ", a277.toString());

    ParseAction a278 = table.lookup("type-decls-opt", "�");
    assertEquals("(empty) ", a278.toString());

    ParseAction a279 = table.lookup("type-decls-opt", "'begin'");
    assertEquals("(empty) ", a279.toString());

    ParseAction a280 = table.lookup("type-decls-opt", "'var'");
    assertEquals("(empty) ", a280.toString());

    ParseAction a281 = table.lookup("type-decls-opt", "'type'");
    assertEquals("(empty) ", a281.toString());

    ParseAction a282 = table.lookup("type-decls-opt", "'procedure'");
    assertEquals("(empty) ", a282.toString());

    ParseAction a283 = table.lookup("typename", "identifier");
    assertEquals(" new-typename ", a283.toString());

    ParseAction a284 = table.lookup("typename", "'boolean'");
    assertEquals(" prim-typename ", a284.toString());

    ParseAction a285 = table.lookup("typename", "'integer'");
    assertEquals(" prim-typename ", a285.toString());

    ParseAction a286 = table.lookup("typename", "'real'");
    assertEquals(" prim-typename ", a286.toString());

    ParseAction a287 = table.lookup("typename", "'string'");
    assertEquals(" prim-typename ", a287.toString());

    ParseAction a288 = table.lookup("unary-op", "'+'");
    assertEquals(" '+' ", a288.toString());

    ParseAction a289 = table.lookup("unary-op", "'-'");
    assertEquals(" '-' ", a289.toString());

    ParseAction a290 = table.lookup("unary-op", "'not'");
    assertEquals(" 'not' ", a290.toString());

    ParseAction a291 = table.lookup("var-decls-opt", "identifier");
    assertEquals(" var-decl  var-decls-opt ", a291.toString());

    ParseAction a292 = table.lookup("var-decls-opt", "�");
    assertEquals("(empty) ", a292.toString());

    ParseAction a293 = table.lookup("var-decls-opt", "'begin'");
    assertEquals("(empty) ", a293.toString());

    ParseAction a294 = table.lookup("var-decls-opt", "'var'");
    assertEquals("(empty) ", a294.toString());

    ParseAction a295 = table.lookup("var-decls-opt", "'type'");
    assertEquals("(empty) ", a295.toString());

    ParseAction a296 = table.lookup("var-decls-opt", "'procedure'");
    assertEquals("(empty) ", a296.toString());

    ParseAction a297 = table.lookup("var-decl", "identifier");
    assertEquals(
        " identifier  make-id  identifiers-more-opt  make-id-list  decl-typename-opt  ':='  expression  ';'  make-var-declaration ",
        a297.toString());

    ParseAction a298 = table.lookup("write-params", "'('");
    assertEquals(" '('  write-params-rest ", a298.toString());

    ParseAction a299 = table.lookup("write-params-rest", "identifier");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a299.toString());

    ParseAction a300 = table.lookup("write-params-rest", "'('");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a300.toString());

    ParseAction a301 = table.lookup("write-params-rest", "')'");
    assertEquals(" ')' ", a301.toString());

    ParseAction a302 = table.lookup("write-params-rest", "'+'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a302.toString());

    ParseAction a303 = table.lookup("write-params-rest", "'-'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a303.toString());

    ParseAction a304 = table.lookup("write-params-rest", "'true'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a304.toString());

    ParseAction a305 = table.lookup("write-params-rest", "'false'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a305.toString());

    ParseAction a306 = table.lookup("write-params-rest", "string");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a306.toString());

    ParseAction a307 = table.lookup("write-params-rest", "integer");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a307.toString());

    ParseAction a308 = table.lookup("write-params-rest", "real");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a308.toString());

    ParseAction a309 = table.lookup("write-params-rest", "'null'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a309.toString());

    ParseAction a310 = table.lookup("write-params-rest", "'boolean'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a310.toString());

    ParseAction a311 = table.lookup("write-params-rest", "'integer'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a311.toString());

    ParseAction a312 = table.lookup("write-params-rest", "'real'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a312.toString());

    ParseAction a313 = table.lookup("write-params-rest", "'string'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a313.toString());

    ParseAction a314 = table.lookup("write-params-rest", "'not'");
    assertEquals(" expression  expressions-more-opt  ')'  make-expression-list ", a314.toString());

  }

  protected void writeOutput(String source, String outputPath) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
      writer.write(source.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private ParsingTable makeTable() {
    Grammar g = new Grammar("input/playerGrammar.txt", "�", "->");
    return g.getParsingTable();
  }
}
