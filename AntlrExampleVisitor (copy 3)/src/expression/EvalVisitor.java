package expression;


import java.util.Arrays;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.ExprBaseVisitor;
import antlr.ExprParser;
import antlr.ExprParser.Arg_listContext;

public class EvalVisitor extends ExprBaseVisitor<String> {
    private final SymbolTable st;
    private final SymbolTable varConstInt;
    private final SymbolTable varConstBool;

    public EvalVisitor() {
        st = new SymbolTable();
        varConstInt = new SymbolTable();
        varConstBool = new SymbolTable();
    }

    @Override
    public String visitProgStm(ExprParser.ProgStmContext ctx) {
        st.put("BEGINBLOCK", "NULL");
        System.out.println("\n Prog");
        System.out.println(ctx.getText());
        
        if (ctx.decl_list() != null)
            visit(ctx.decl_list());
            
        if (ctx.function_list() != null)
            visit(ctx.function_list());
            
        if (ctx.main() != null)
            visit(ctx.main());
        st.put("ENDBLOCK", "NULL");
        return "";
    }



    @Override
    public String visitDeclListStm(ExprParser.DeclListStmContext ctx) {
        System.out.println("DeclList");
        if (ctx.decl() != null)
            visit(ctx.decl());
        if (ctx.decl_list() != null)
            visit(ctx.decl_list());
        return "";
    }

    @Override
    public String visitVarDeclRef(ExprParser.VarDeclRefContext ctx) {
    	System.out.println("VarDecl");
        return visit(ctx.var_decl());
    }

    @Override
    public String visitConstDeclRef(ExprParser.ConstDeclRefContext ctx) {
    	System.out.println("ConstDecl");
        return visit(ctx.const_decl());
    }



    @Override
    public String visitVarDeclStm(ExprParser.VarDeclStmContext ctx) {
    	System.out.println("\n--------------VarDeclStm---------------\n");
        String id = ctx.ID().getText();
        String type = ctx.type().getText();
        
        if (st.contains(id)) {
            System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("ID: \"" + id + "\" has already been declared.");
            System.exit(0);
        }
//////////////////////////////////////////////////////////////////
        st.put(id, type);
        
        if (type.equals("integer")) {
        	varConstBool.put(id, "variable");
        }
        else if (type.equals("boolean")) {
        	varConstBool.put(id, "variable////////////");

        }
        
        return id;
    }

    @Override
    public String visitConstDeclStm(ExprParser.ConstDeclStmContext ctx) {
    	System.out.println("ConstDeclStm");
        String id = ctx.ID().getText();
        String type = ctx.type().getText();
        
        if (st.contains(id)) {
            System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("ID: \"" + id + "\" has already been declared.");
            System.exit(0);
        }
        
        st.put(id, type);
        
        if (type.equals("integer")) {
        	varConstInt.put(id, "constant");
        }
        else if (type.equals("boolean")) {
        	varConstInt.put(id, "constant");

        }
        
        
        System.out.println("0000000"+varConstBool);
        return ctx.ID().getText();
    }

    @Override
    public String visitFuncListStm(ExprParser.FuncListStmContext ctx) {
    	System.out.println("FuncListStm");
        if (ctx.function() != null)
            visit(ctx.function());
        if (ctx.function_list() != null)
            visit(ctx.function_list());
        return "";
    }

    @Override
    public String visitFuncDeclStm(ExprParser.FuncDeclStmContext ctx) {
    	System.out.println("FuncDeclStm");

        String id = ctx.ID().getText();
        String type = ctx.type().getText();

        st.put(id, type);
        st.put("BEGINBLOCK", "NULL");

        type = type + "_" + visit(ctx.parameter_list());

        st.set(id, type);

        if (ctx.decl_list() != null)
            visit(ctx.decl_list());
        if (ctx.statement_block() != null)
            visit(ctx.statement_block());

        st.put("ENDBLOCK", "NULL");

        return id;
    }

    @Override
    public String visitNonEmptyParamRef(ExprParser.NonEmptyParamRefContext ctx) {
    	System.out.println("NonEmptyParamRef");
        if (ctx.nemp_parameter_list() != null)
            return "" + visit(ctx.nemp_parameter_list());
        return "";
    }

    @Override
    public String visitSingleParamStm(ExprParser.SingleParamStmContext ctx) {
    	System.out.println("SingleParamStm");
        String id = ctx.ID().getText();
        String type = ctx.type().getText();

        st.put(id, type);
        return "_" + type;
    }

    @Override
    public String visitMultipleParamStm(ExprParser.MultipleParamStmContext ctx) {
    	System.out.println("MultipleParamStm");
        String type = "";
        if (ctx.ID() != null && ctx.type() != null) {
            st.put(ctx.ID().getText(), ctx.type().getText());
            return ctx.type().getText() + visit(ctx.nemp_parameter_list());
        }
        if (ctx.nemp_parameter_list() != null)
            return type + visit(ctx.nemp_parameter_list());
        return type;
    }

    @Override
    public String visitMultIdArgRef(ExprParser.MultIdArgRefContext ctx) {
    	System.out.println("--------------MultIdArgRef-------------");
    	
    	String id = ctx.ID().getText();
        
        if (!st.contains(id)) {
        	System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Variable \"" + id + "\" has not been declared in scope.");
            System.exit(0);
        }
    	
    	
        String type = "";
        if (ctx.ID() != null)
            type += st.get(ctx.ID().getText()) + "_";
        if (ctx.nemp_arg_list() != null)
            return type + visit(ctx.nemp_arg_list());
        return type;
    }

    @Override
    public String visitIdArgRef(ExprParser.IdArgRefContext ctx) {
    	System.out.println("------------IdArgRef-------------");
    	
    	String id = ctx.ID().getText();
        
        if (!st.contains(id)) {
        	System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Variable \"" + id + "\" has not been declared in scope.");
            System.exit(0);
        }
    	
//        if (ctx.ID() != null)
//            if (!st.contains(ctx.ID().getText())) {
//                System.out.println("ERROR @ " + ctx.start.getLine());
//                System.out.println("Variable \"" + ctx.ID() + "\" has not been instantiated.");
//            }
        return st.get(id);
    }

    @Override
    public String visitMainStm(ExprParser.MainStmContext ctx) {
    	System.out.println("MainStm");
        st.put("BEGINBLOCK", "NULL");

        if (ctx.decl_list() != null)
            visit(ctx.decl_list());
        if (ctx.statement_block() != null)
            visit(ctx.statement_block());
        st.put("ENDBLOCK", "NULL");
        return "";
    }

    @Override
    public String visitStmBlockRef(ExprParser.StmBlockRefContext ctx) {
    	System.out.println("StmBlockRef");
        if (ctx.statement() != null)
            visit(ctx.statement());
        if (ctx.statement_block() != null)
            visitChildren(ctx.statement_block());
        return "";
    }

    @Override
    public String visitAssignStm(ExprParser.AssignStmContext ctx) {
    	System.out.println("-----------AssignStm-------------");
        String id = ctx.ID().getText();
        String exp_type = visit(ctx.expression());
        System.out.println("\n"+exp_type + "111111111111111111111111");
        System.out.println(id + "111111111111111111111111");

        
        if (!st.contains(id)) {
            System.out.println("Error @ Line: " + ctx.start.getLine());
            System.out.println("AssignStm: \"" + id + "\" has not been declared in scope.");
            System.exit(0);
        }
        
        
        //If there is multiple declarations and check the most LHS assignment.
        if (exp_type != null && exp_type.contains("_")) {
            String returnType = exp_type.split("_")[0];
            String type = st.get(id);
            

            if (!type.equals(returnType)) {
                System.out.println("Error @ Line: " + ctx.start.getLine());
                System.out.println("Left-hand side of an assignment variable \"" + id + "\" does not match the defined type \"" + type + "\".");
                System.exit(0);
            }
        }
         
        //This checks all single LHS variables are assigned the correct type.
        else if (exp_type != null){
        	String returnType = exp_type;
            String type = st.get(id);
            
//            System.out.println("\n"+returnType);
//            System.out.println(!type.equals("boolean"));
//            System.out.println(type+"\n");
            
            //If the returnType is a True or False we will define returnType as "boolean".
            if (returnType.equals("True")|| returnType.equals("true") || returnType.equals("False") || returnType.equals("false")) {
            	returnType = "boolean";
            }
            
        	if (!type.equals(returnType)) {
                System.out.println("Error @ Line: " + ctx.start.getLine());
                System.out.println("Left-hand side of an assignment variable \"" + id + "\" does not match the defined type \"" + type + "\".");
                System.exit(0);
        	}
        }
        

        return id;
    }

    @Override
    public String visitFragBinArithStm(ExprParser.FragBinArithStmContext ctx) {
    	System.out.println("FragBinArithStm");
        boolean arg1Flag = true;
        boolean arg2Flag = true;
        
        String arg1 = ctx.frag(0).getText();
        String arg2 = ctx.frag(1).getText();
        String argType1 = varConstInt.get(arg1);
        String argType2 = varConstInt.get(arg2);
        
        System.out.println("-------"+argType1);
        System.out.println("-------"+argType2);
        System.out.println(visit(ctx.frag(0)).equals("integer"));
        
        // Check to see if the input is an number.
        if (argType1 == null) {
        	arg1Flag = visit(ctx.frag(0)).equals("integer");
        }
        //Check to see if the first argument is a variable.
        else if (argType1 == "constant") {
        	arg1Flag = true;
        }
        //Check to see if the first argument is a variable.
        else if (argType1 == "variable") {
        	arg1Flag = true;
        }
        
        // Check to see if the input is an number.
        if (argType2 == null) {
        	arg2Flag = visit(ctx.frag(1)).equals("integer");
        }
        //Check to see if the second argument is a variable.
        else if (argType2 == "constant") {
        	arg2Flag = true;
        }
        //Check to see if the second argument is a variable.
        else if (argType2 == "variable") {
        	arg2Flag = true;
        }
        

        if (!(arg1Flag && arg2Flag)) {
            System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("You are trying to preform an arithmetic operation using one or more non-integer values.");
            System.exit(0);
        }
        return "integer";
    }

    @Override
    public String visitBeginStm(ExprParser.BeginStmContext ctx) {
    	System.out.println("BeginStm");
        st.put("BEGIN", "NULL");
        visit(ctx.statement_block());
        st.put("BEGIN", "NULL");
        return "";
    }

    @Override
    public String visitConditionalStm(ExprParser.ConditionalStmContext ctx) {
    	System.out.println("ConditionalStm");
        if (ctx.condition() != null)
            visit(ctx.condition());
        if (ctx.statement_block() != null)
            visit(ctx.statement_block(0));
        if (ctx.statement_block() != null)
            visit(ctx.statement_block(1));
        return "";
    }

    @Override
    public String visitWhileStm(ExprParser.WhileStmContext ctx) {
    	System.out.println("WhileStm");
        if (ctx.condition() != null)
            visit(ctx.condition());
        if (ctx.statement_block() != null)
            visit(ctx.statement_block());
        return "";
    }

    @Override
    public String visitFuncCallStm(ExprParser.FuncCallStmContext ctx) {
    	System.out.println("-----------FuncCallStm---------");
    	
    	String id = ctx.ID().getText();
        
        if (!st.contains(id)) {
        	System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Variable \"" + id + "\" has not been declared in scope.");
            System.exit(0);
        }
    	
        return funcHandler(ctx.ID(), ctx.arg_list(), ctx);
    }

    @Override
    public String visitFuncCallExpr(ExprParser.FuncCallExprContext ctx) {
    	System.out.println("FuncCallExpr");
    	
    	String id = ctx.ID().getText();
        
        if (!st.contains(id)) {
        	System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Variable \"" + id + "\" has not been declared in scope.");
            System.exit(0);
        }
    	
        return funcHandler(ctx.ID(), ctx.arg_list(), ctx);
    }

    private String funcHandler(TerminalNode id2, Arg_listContext argument_listContext, ParserRuleContext ctx) {
    	System.out.println("\n -----------------funcHandle-------------------\n");
        String id = id2.getText(); // This is the function name...

        //Check to see if the function name is in the stack, if not then trigger an error...
        if (!st.contains(id)) {
        	System.out.println("Errror 1");
            System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Function: \"" + id + "\" has not been defined.");
            System.exit(0);
        }

        //Check to see the id of the function and arguments...
        String compositeType = st.get(id);  //This retrieves the string of id's eg. Integer_Integer_Integer.
        String[] argTypesArray = compositeType.split("_"); //This divides the string into a list of id's.
        
//        System.out.println(compositeType);
//        System.out.println(argTypesArray.length);
        
        //If the function has no arguments then return the id...
        if (argTypesArray.length == 1) {
        	System.out.println("Error 2");
            return st.get(id);
        }
        
        //This pick the two argument types in array format.
        String[] argTypesList = Arrays.copyOfRange(argTypesArray, 1 , argTypesArray.length); // https://www.geeksforgeeks.org/java-util-arrays-copyofrange-java/
        String argTypes = "";
        
        //This joins the types together.
        for (int i = 0; i < argTypesList.length - 1; i++) {
       
        	if (i == argTypesList.length - 2){
        		argTypes = argTypes + argTypesList[i];
        	}
        	argTypes = argTypes + "_" +argTypesList[i];
        }
        
//        System.out.println(argTypes);
//        System.out.println("Errror 3");
        
        //If the arguments in the function are not equal to the value passed.
        if (!argTypes.equals(visit(argument_listContext))) {
        	
        	
        	//Formatting missing type output to the user.
            String missingArgumentMessage = "";
            int i = 0;
            for (String arg : Arrays.copyOfRange(argTypesArray, 1 , argTypesArray.length)) {
            	if (i == Arrays.copyOfRange(argTypesArray, 1 , argTypesArray.length).length -1){
            		missingArgumentMessage = missingArgumentMessage + arg;
            	}
            	else {
            		missingArgumentMessage = missingArgumentMessage + arg +", ";
            		i = i + 1;
            		}
            	} 
            
            //Error message being print out to commandline.
            System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Function: \"" + id + "\" requires " + (argTypesArray.length - 1) + " arguments of types "+ missingArgumentMessage);
            
            //Exit program because of error.
            System.exit(0);
        }
            
      return st.get(id);
    }
    
    
    

    @Override
    public String visitIdFrag(ExprParser.IdFragContext ctx) {
    	System.out.println("---------------IdFrag------------");
        String id = ctx.ID().getText();
        String type = st.get(id);
        
        if (!st.contains(id) && type == null) {
        	System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Variable \"" + id + "\" has not been declared in scope.");
            System.exit(0);
        }

        return type;
    }

    @Override
    public String visitIntStm(ExprParser.IntStmContext ctx) {
    	System.out.println("IntStm");
        return "integer";
    }

    @Override
    public String visitNegationStm(ExprParser.NegationStmContext ctx) {
    	System.out.println("-------NegationStm---------");
    	String id = ctx.ID().getText();
        
        if (!st.contains(id)) {
        	System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Variable \"" + id + "\" has not been declared in scope.");
            System.exit(0);
        }
        return "integer";
    }

    @Override
    public String visitType(ExprParser.TypeContext ctx) {
    	System.out.println("Type");
        return ctx.getText();
    }

    @Override
    public String visitTrueStm(ExprParser.TrueStmContext ctx) {
    	System.out.println("TrueStm");
        return ctx.True().getText();
    }

    @Override
    public String visitFalseStm(ExprParser.FalseStmContext ctx) {
    	System.out.println("FalseStm");
        return ctx.False().getText();
    }
}
