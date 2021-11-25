package expression;

import java.util.ArrayList;
import java.util.List;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.ProgramContext;

public class AntlrToProgram extends ExprBaseVisitor<Program>{

  public List<String> semanticErrors;

  @Override
  public Program visitProgram(ProgramContext ctx){
    Program prog = new Program();
    System.out.println(1.1);

    semanticErrors = new ArrayList<>();
    AntlrToExpression exprVisitor = new AntlrToExpression(semanticErrors);
    

    
    
    
    for (int i = 0; i < ctx.getChildCount(); i++) {
//    	System.out.println(ctx.getText());
      if (i == ctx.getChildCount() - 1) {

      }
      else {
        prog.addExpression(exprVisitor.visit(ctx.getChild(i)));
      }
//      System.out.println(exprVisitor.visit(ctx.getChild(i)));
//      System.out.println(ctx.children);
    }
    return prog;
  }
}
