package expression;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.AdditionContext;
import antlr.ExprParser.DeclarationContext;
import antlr.ExprParser.MultiplicationContext;
import antlr.ExprParser.NumberContext;
import antlr.ExprParser.VariableContext;

public class AntlrToExpression extends ExprBaseVisitor<Expression>{

  private List<String> vars;
  private List<String> semanticErrors;

  public AntlrToExpression(List<String> semanticErrors) {
    vars = new ArrayList<>();
    this.semanticErrors = semanticErrors;
  }



  @Override
  public Expression visitDeclaration(DeclarationContext ctx){
    Token idToken = ctx.ID().getSymbol();
    int line = idToken.getLine();
    int column = idToken.getCharPositionInLine() + 1;


    String id = ctx.getChild(0).getText();
    if (vars.contains(id)){
      semanticErrors.add("Error: variable " + id + "already declared (" + line + ", " + column + ")" );
    }
    else {
      vars.add(id);
    }
    String type = ctx.getChild(2).getText();
    int value = Integer.parseInt(ctx.NUM().getText());
    return new VariableDeclaration(id, type, value);
  }



  @Override
  public Expression visitMultiplication(MultiplicationContext ctx){
    Expression left = visit(ctx.getChild(0));
    Expression right = visit(ctx.getChild(2));
    return new Multiplication(left, right);
  }



  @Override
  public Expression visitAddition(AdditionContext ctx){
    Expression left = visit(ctx.getChild(0));
    Expression right = visit(ctx.getChild(2));
    return new Addition(left, right);
  }



  @Override
  public Expression visitVariable(VariableContext ctx){
    Token idToken = ctx.ID().getSymbol();
    int line = idToken.getLine();
    int column = idToken.getCharPositionInLine() + 1;

    String id = ctx.getChild(0).getText();
    if(!vars.contains(id)) {
      semanticErrors.add("Error: variable " + id + " not declared (" + line + ", " + column + ")");
    }
    return new Variable(id);
  }



  @Override
  public Expression visitNumber(NumberContext ctx){
    String numText = ctx.getChild(0).getText();
    int num = Integer.parseInt(numText);
    return new Number(num);
  }


}
