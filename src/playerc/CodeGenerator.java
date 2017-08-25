package playerc;

import java.io.*;
import java.util.*;

import playerc.abstractsyntax.*;

/**
 * Author: sergei
 * Created: Sep 5, 2005
 */
public class CodeGenerator
{	
   private final String PROC_SUFFIX = "_instance";
   
   private StringBuffer buffer;
   private String outputPath;
   private int tabs;
   private String mainClassName;

   public CodeGenerator(String path)
   {
      outputPath = path;   
      buffer = new StringBuffer();
      tabs = 0;
      mainClassName = null;     
   }
   
   private void writeFile(String text, String fileName)
   {
      //create dir if not there  
      File dir = null;
      if (outputPath.length() > 0)
      {
         dir = new File(outputPath);
         if (!dir.exists())
            dir.mkdir();
      }
      File javaFile = new File(dir, fileName);
         
      try {
         BufferedWriter fileWriter = new BufferedWriter(new FileWriter(javaFile)); 
         fileWriter.write(text);
         fileWriter.flush();
      } catch (IOException e) { System.err.println(e.toString()); }      
   }
   
   private String tabs() 
   {
      StringBuffer b = new StringBuffer();
      for (int i=0; i<tabs; i++)
         b.append("\t");
      return b.toString();
   }
   
   public void generate(Program p) { p.accept(this); }
   
   public void visit(Program x) 
   {
      mainClassName = x.name().toString();
      buffer.append("public class " + mainClassName + "\n");
      buffer.append("{\n");
      tabs++;
      buffer.append(tabs() + "public static void main( String[] args )\n");
      buffer.append(tabs() + "{\n");
      tabs++;
      buffer.append(tabs() + mainClassName + " " + mainClassName + " = new " + mainClassName + "();\n");
      buffer.append(tabs() + mainClassName + ".run();\n");
      tabs--;
      buffer.append(tabs() + "}\n\n");     
      x.body().accept(this);             
      tabs--;
      buffer.append("}\n"); 
      
      writeFile(buffer.toString(), mainClassName.toString() + ".java");
   }
   public void visit(Body x) 
   {
      x.decls().accept(this);  
      buffer.append("\n");       
      buffer.append(tabs() + "public " + mainClassName + "() { }\n\n");     
      buffer.append(tabs() + "public void run()\n");
      buffer.append(tabs() + "{\n");       
      tabs++;
      x.stms().accept(this);
      tabs--;
      buffer.append(tabs() + "}\n");       
   }

   public void visit(VarDeclaration x) 
   {
      buffer.append(tabs() + "private ");
      
      //get typename (can't use typename of declaration - can be unspecified -must access ids)
      Iterator i = x.ids().iterator();
      Identifier id = (Identifier)i.next();
      
      TypeExpression type = id.type();
      String typename;
      
      if (x.expression() instanceof RecordInitsExpression) //record and not null
      {
         RecordInitsExpression exp = (RecordInitsExpression)x.expression();
         String tname = exp.typename().toString();
         buffer.append(tname + " ");
         x.ids().accept(this);
         buffer.append(" = new " + tname + "(");      
 
         Iterator inits = exp.list().iterator();
         while (inits.hasNext())
         {
            RecordInit ri = (RecordInit)inits.next();
            ri.expression().accept(this);
            buffer.append(", ");            
         }  
         buffer.delete(buffer.lastIndexOf(","), buffer.length());
         buffer.append(");\n");
      }
      else
      {         
         if (type instanceof ArrayTypeExpression)      
         {
            String temp1 = ( (ArrayTypeExpression)type ).elementType().typename();  
            typename = getJavaType(temp1) + "[]";        
         }
         else               
            typename = getJavaType(type.typename());      
         
         buffer.append(typename); 
         buffer.append(" ");
         x.ids().accept(this);            
         buffer.append(" = ");
         x.expression().accept(this);
         buffer.append(";\n");
      }
   }
  
   public void visit(TypeDeclaration x) 
   {
      NewType type = x.type();
      if (type instanceof ArrayType)
      {
         String temp = ( (ArrayType)type ).elementTypename().toString();
         String typename = getJavaType(temp);
         buffer.append(tabs() + "private " + typename + "[] " + x.typename().toString() + ";\n");               
      }      
      else //record type
      {
         RecordType rType = (RecordType)type;        
         String className = x.typename().toString();

         StringBuffer buffer2 = new StringBuffer();
         buffer2.append("public class " + className + "\n");
         buffer2.append("{\n");
         
         Iterator i3 = rType.members().iterator();
         while (i3.hasNext())
         {
            Member m = (Member)i3.next();
            buffer2.append("\tpublic " + getJavaType(m.typename().toString()) + " " + m.id().toString() + ";\n");
         }
         
         buffer2.append("\n\tpublic " + className + "(");
         
         Iterator i1 = rType.members().iterator();
         while (i1.hasNext())
         {
            Member m = (Member)i1.next();
            buffer2.append(getJavaType(m.typename().toString()) + " " + m.id().toString() + ", ");
         }    
         buffer2.delete(buffer2.lastIndexOf(","), buffer2.length());
         buffer2.append(")\n");
         
         buffer2.append("\t{\n");
         
         Iterator i2 = rType.members().iterator();
         while (i2.hasNext())
         {
            Member m = (Member)i2.next();
            buffer2.append("\t\tthis." + m.id().toString() + " = " + m.id().toString() + ";\n");
         }
         
         buffer2.append("\t}\n");
         buffer2.append("}\n"); 
         
         writeFile(buffer2.toString(), className.toString() + ".java");         
      }
   }

   public void visit(ProcDeclaration x) //nested procedures with local var declarations may be faulty in some cases. I'm tired..
   {
      String procName = x.id().toString();
      buffer.append("\n" + tabs() + "private " + procName + " " + procName + PROC_SUFFIX + " = new ");
      buffer.append(procName + "();\n"); //create reference
      
      buffer.append("\n" + tabs() + "private class " + x.id() + "\n");
      buffer.append(tabs() + "{\n"); 
      tabs++;      
      
      DeclarationList decls = x.body().decls();
      decls.accept(this);
       
      String typename;
      if (x.type() instanceof ArrayTypeExpression)
      {
         ArrayTypeExpression ae = (ArrayTypeExpression)x.type();
         typename = getJavaType(ae.elementType().typename()) + "[]";        
      }
      else
         typename = getJavaType(x.type().typename());

      buffer.append(tabs() + typename + " run(");	      
      
      if (x.params() != null)
      {
         Iterator params = x.params().iterator();
         while (params.hasNext())
         {
            FpSection fp = (FpSection)params.next();            
            TypeExpression typeExp = fp.type();
            if (typeExp instanceof ArrayTypeExpression)
            {                 
               String temp1 = ( (ArrayTypeExpression)typeExp ).elementType().typename();  
               buffer.append(getJavaType(temp1) + "[]");  
            }
            else    
               fp.typename().accept(this);
            
            buffer.append(" ");         
            buffer.append(fp.id().toString() + ", ");
         }
         buffer.delete(buffer.lastIndexOf(","), buffer.length());
      }      
      buffer.append(")\n");
      buffer.append(tabs() + "{\n");
      tabs++;
      
      StatementList stms = x.body().stms();
      stms.accept(this);
      
      tabs--;
      buffer.append(tabs() + "}\n");      
      
      tabs--;
      buffer.append(tabs() + "}\n");   
   }
      
   public void visit(PositiveExpression x) 
   {
      buffer.append("+");
      x.exp().accept(this);      
   }
   
   public void visit(NegativeExpression x)
   {
      buffer.append("-");
      x.exp().accept(this);      
   }
   
   public void visit(NotExpression x)
   {
      buffer.append("!");
      x.exp().accept(this);      
   }   

   public void visit(PlusExpression x)
   {
      x.left().accept(this);
      buffer.append(" + ");
      x.right().accept(this);
   }
   
   public void visit(MinusExpression x)
   {
      x.left().accept(this);
      buffer.append(" - ");
      x.right().accept(this);      
   }
   
   public void visit(MultExpression x)
   {
      x.left().accept(this);
      buffer.append(" * ");
      x.right().accept(this);      
   }
   public void visit(DivExpression x)
   {
      x.left().accept(this);
      buffer.append(" / ");
      x.right().accept(this);
   }
   
   public void visit(AndExpression x)
   {
      x.left().accept(this);
      buffer.append(" && ");
      x.right().accept(this);
   }
   
   public void visit(OrExpression x)
   {
      x.left().accept(this);
      buffer.append(" || ");
      x.right().accept(this);
   }
   
   public void visit(GreaterExpression x)
   {
      x.left().accept(this);
      buffer.append(" > ");
      x.right().accept(this);
   }
   
   public void visit(GreaterEqlExpression x)
   {
      x.left().accept(this);
      buffer.append(" >= ");
      x.right().accept(this);
   }
   
   public void visit(LessExpression x)
   {
      x.left().accept(this);
      buffer.append(" < ");
      x.right().accept(this);
   }
   
   public void visit(LessEqlExpression x)
   {
      x.left().accept(this);
      buffer.append(" <= ");
      x.right().accept(this);
   }
   
   public void visit(EqlExpression x)   
   {
      x.left().accept(this);
      buffer.append(" == ");
      x.right().accept(this);
   }
   
   public void visit(NotEqlExpression x)
   {
      x.left().accept(this);
      buffer.append(" != ");
      x.right().accept(this);
   }
		
   public void visit(TrueExpression x) { buffer.append("true"); }
   
   public void visit(FalseExpression x) { buffer.append("false"); }
   
   public void visit(IntegerExpression x) { buffer.append(x.value()); }
   
   public void visit(RealExpression x) { buffer.append(x.value()); }
   
   public void visit(StringExpression x) { buffer.append("\"" + x.value() + "\""); }
  
   public void visit(ArrayInitsExpression x) 
   {      
      Iterator i = x.list().iterator();
      buffer.append("{");
      while (i.hasNext())
      {
         ArrayInit ai = (ArrayInit)i.next();
         int numberOf = ai.numberOf().value();
         Expression exp = ai.expression();
         while (numberOf > 0)
         {
            exp.accept(this);
            buffer.append(", ");
            numberOf--;
         }
      }
      buffer.delete(buffer.lastIndexOf(","), buffer.length());
      buffer.append("}");
   }
   
   public void visit(RecordInitsExpression x) { /* i am never called :-) */ }  
		
   public void visit(CallExpression x)
   {
      buffer.append(x.id().toString() + PROC_SUFFIX + ".run");
      buffer.append("(");
      ExpressionList params = x.params();
      if (params != null)
      {
         Iterator i = params.iterator();
         while (i.hasNext())
         {
            Expression exp = (Expression)i.next();
            exp.accept(this);
            buffer.append(", ");
         }
         buffer.delete(buffer.lastIndexOf(","), buffer.length());
      }
      buffer.append(")");      
   }
   
   public void visit(LookupExpression x)
   {
      x.lvalue().accept(this);
      buffer.append("[");
      x.expression().accept(this);
      buffer.append("]"); 
   }
   
   public void visit(DerefExpression x)
   {
      x.lvalue().accept(this);
      buffer.append("." + x.id().toString());
   }
   
   public void visit(IdExpression x) 
   { 
      buffer.append(x.id().toString()); 
   }     

   public void visit(AssignmentStatement x)
   {
      buffer.append(tabs());
      x.lvalue().accept(this);
      buffer.append(" = ");
      x.expression().accept(this);
      buffer.append(";\n"); 
   }
   
   public void visit(CallStatement x)
   {
      buffer.append(tabs());      
      buffer.append(x.id().toString() + PROC_SUFFIX + ".run");
      
      buffer.append("(");      
      ExpressionList params = x.params();
      if (params != null)
      {
         Iterator i = params.iterator();
         while (i.hasNext())
         {
            Expression exp = (Expression)i.next();
            exp.accept(this);
            buffer.append(", ");
         }
         buffer.delete(buffer.lastIndexOf(","), buffer.length());
      }
      buffer.append(");\n");       
   }
   
   public void visit(ExitStatement x) 
   { 
      buffer.append(tabs());
      buffer.append("break;\n"); 
   }
   
   public void visit(ForStatement x)
   {
      buffer.append(tabs() + "for (int " + x.id().toString() + " = ");
      x.expFrom().accept(this);      
      buffer.append("; " + x.id().toString());
      
      //temporary solution: we will know if expBy > 0 or not only at runtime!
      if (x.expBy() instanceof NegativeExpression)
         buffer.append(" > ");
      else
         buffer.append(" < ");   

      x.expTo().accept(this);
      buffer.append("; " + x.id().toString() + "+=");
      x.expBy().accept(this);      
      buffer.append(")\n");
      buffer.append(tabs() + "{\n");
      tabs++;
      x.list().accept(this);
      tabs--;
      buffer.append(tabs() + "}\n");      
   }
   
   public void visit(IfStatement x)
   {
      Iterator i1 = x.expStms().iterator();
      boolean isElse = false;
      while (i1.hasNext())
      {
         buffer.append(tabs());
         ExpThenStatements expstms = (ExpThenStatements)i1.next();
         Expression exp = expstms.expression();
         if (isElse)
            buffer.append("else ");
         
         buffer.append("if (");
         exp.accept(this);
         buffer.append(")\n");         
         buffer.append(tabs() + "{\n");         
         tabs++;
         
         Iterator stms = expstms.statements().iterator();
         while (stms.hasNext())
         {
            Statement stm = (Statement)stms.next();
            stm.accept(this);
         }
         tabs--;
         buffer.append(tabs() + "}\n");     
         isElse = true;         
      }

      StatementList elseStms = x.elseStms();
      if (elseStms != null)
      {
         buffer.append(tabs() + "else\n");
         buffer.append(tabs() + "{\n");         
         tabs++;         
         elseStms.accept(this);
         tabs--;
         buffer.append(tabs() + "}\n");      
      }      
   }
   
   public void visit(LoopStatement x)
   {
      buffer.append(tabs());
      buffer.append("while (true)\n");
      buffer.append(tabs() + "{\n");
      tabs++;
      x.list().accept(this);
      tabs--;
      buffer.append(tabs() + "}\n");
   }
   
   public void visit(ReadStatement x)
   {
      buffer.append(tabs() + "java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));\n");
      buffer.append(tabs() + "try {\n");
      tabs++;
            
      Iterator i= x.lvalueList().iterator();      
      while (i.hasNext())
      {
         buffer.append(tabs()); 
         Lvalue lval = (Lvalue)i.next();
         lval.accept(this);
         if (lval.type().equals(TypeExpression.Integer))
            buffer.append(" = Integer.parseInt(in.readLine());\n");
         else if (lval.type().equals(TypeExpression.Real))
            buffer.append(" = Double.parseDouble(in.readLine());\n");            
         else if (lval.type().equals(TypeExpression.StringType))         
            buffer.append(" = in.readLine();\n");
      }
      
      tabs--;
      buffer.append(tabs() + "} catch (Exception e) { e.printStackTrace(); }\n");   
   }
   
   public void visit(ReturnStatement x)
   {
      buffer.append(tabs() + "return ");
      x.expression().accept(this);
      buffer.append(";\n"); 
   }
   
   public void visit(WriteStatement x)
   {
      Iterator i = x.expressionList().iterator();
      while (i.hasNext())
      {
         buffer.append(tabs() + "System.out.print(");
         Expression exp = (Expression)i.next();
         exp.accept(this);
         buffer.append(");\n");  
      }      
      buffer.append(tabs() + "System.out.println();\n"); 
   }

   public void visit(DeclarationList x)
   {
      Iterator i = x.iterator();      
      while (i.hasNext())
         ( (Declaration)i.next() ).accept(this);         
   }
   
   public void visit(ExpressionList x)
   {
      Iterator i = x.iterator();      
      while (i.hasNext())
         ( (Expression)i.next() ).accept(this);         
   }   
   
   public void visit(StatementList x) 
   {
      Iterator i = x.iterator();      
      while (i.hasNext())
         ( (Statement)i.next() ).accept(this);         
   }
   
   public void visit(FpSectionList x)
   {
      Iterator i = x.iterator();      
      while (i.hasNext())
         ( (FpSection)i.next() ).accept(this);         
   }
   
   public void visit(IdentifierList x)
   {
      Iterator i = x.iterator();      
      while (i.hasNext())      
         buffer.append( ((Identifier)i.next()).toString() + ", ");

      buffer.delete(buffer.length()-2, buffer.length());
   }
   
   public void visit(LvalueList x)
   {
      Iterator i = x.iterator();      
      while (i.hasNext())
         ( (Lvalue)i.next() ).accept(this);         
   }   
   
   public void visit(RecordType x)
   {
      buffer.append(x.toString());
   }
      
   public void visit(NewTypename x)
   {
      buffer.append(x.toString());
   }
   
   public void visit(PrimTypename x)
   {
      String typename = getJavaType(x.toString());
      buffer.append(typename);    
   }

   public void visit(IdLvalue x)
   {
      buffer.append(x.id().toString());
   }
   
   public void visit(LookupLvalue x)
   {
      x.lvalue().accept(this);
      buffer.append("[");
      x.expression().accept(this);
      buffer.append("]");            
   }
   
   public void visit(DerefLvalue x)
   {
      x.lvalue().accept(this);
      buffer.append("." + x.id().toString());
   }

   public void visit(FpSection x)
   {
      x.typename().accept(this);
      buffer.append(" "); 
      buffer.append(x.id().toString());      
   }
   
   public void visit(ParenExpression x)
   {
      buffer.append("("); 
      x.exp().accept(this);
      buffer.append(")"); 
   }   

   public void visit(NullExpression x)
   {
      buffer.append("null"); 
   }     
      
   private String getJavaType(String typename)
   {
      if (typename.compareTo("integer") == 0)
         return "int";
      else if (typename.compareTo("real") == 0)
         return "double";   
      else if (typename.compareTo("boolean") == 0)
         return "boolean";   
      else if (typename.compareTo("string") == 0)
         return "String";         
      else
         return typename;     
   }
}
