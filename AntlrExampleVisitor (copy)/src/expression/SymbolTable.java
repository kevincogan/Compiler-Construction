package expression;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class SymbolTable {
  List<Expression> list;
  public Map<String, String> ScopeStack;
  public Map<String, Integer> IntegerStack;
  public Stack<String> undoStack;

  
  //Code ENTERS HERE FIRST...
  public SymbolTable(List<Expression> list) {
    this.list = list; // This is a list of all the input from text file...
    System.out.println(this.list);
    
    ScopeStack = new HashMap<>(); // HashMap where we store variable value, type, and symbol...
    IntegerStack = new HashMap<>(); // This HashMap is for dealing with integer values...
    undoStack = new Stack<>();
  }

  //Code flow then goes here afterwards...
  public List<String> getEvaluationResults() {
    List<String> evaluations = new ArrayList<>();

    //This assigns the variables to the hash table...
    for (Expression e: list){
      if (e instanceof VariableDeclaration){
        VariableDeclaration decl = (VariableDeclaration) e;
        IntegerStack.put(decl.id, decl.value);
//        System.out.println(decl.id + decl.value);
//        System.out.println(decl.type);
        
      }
      else{
        String input = e.toString();
        int result = getEvalResult(e);
        evaluations.add(input + " is " + result);

      }
    }
    
    ////////// Test Stack + Scope Stac/////////
    put("BEGINBLOCK", null);
    undoStack.push("1");
    undoStack.push("2");
    undoStack.push("3");
    undoStack.push("4");
    System.out.println(undoStack);
    System.out.println("Hash Table: " + IntegerStack);
    System.out.println("Scope Table: " + ScopeStack);
    
    purgeStack();
    System.out.println(undoStack);
    
    System.out.println("Hash Table: " + IntegerStack);
    System.out.println("Scope Table: " + ScopeStack);
    return evaluations;
  }
  /////////////////////////////////////////////////////
  
  /////////////// Code for Stack.../////////////////
  
  // Add value to the Stack...
  public void put(String id, String type) {
	  if (id.equals("ENDBLOCK")) {
          purgeStack();
      } else {
          ScopeStack.put(id, type);
          undoStack.push(id);
      }
  }
  
  // Take value off the Stack...
  public String pop() {
      return undoStack.pop();
  }
  
  public void purgeStack() throws EmptyStackException {
      String stackTop;
      do {
          stackTop = undoStack.pop();
          ScopeStack.remove(stackTop);
          if (undoStack.isEmpty())
              return;
      } while (!stackTop.equals("BEGINBLOCK"));
  }
  
  /////////////////////////////////////////////////

  private int getEvalResult(Expression e){
    int result = 0;

    if (e instanceof Number) {
      Number num = (Number) e;
      result = num.num;
    }
    else if ( e instanceof Variable) {
      Variable var = (Variable) e;
      result = IntegerStack.get(var.id);
    }
    else if (e instanceof Addition){
      Addition add = (Addition) e;
      int left = getEvalResult(add.left);
      int right = getEvalResult(add.right);
      result = left + right;
    }
    else {
      Multiplication add = (Multiplication) e;
      int left = getEvalResult(add.left);
      int right = getEvalResult(add.right);
      result = left * right;
    }
    
//    System.out.println("Hash Table: " + ScopeStack);

    return result;
  }
}
