package expression;


import java.util.Arrays;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.ExprBaseVisitor;
import antlr.ExprParser;
import antlr.ExprParser.Arg_listContext;

public class EvalVisitor extends ExprBaseVisitor<String> {
    private final SymbolTable st;

    public EvalVisitor() {
        st = new SymbolTable();
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
    	System.out.println("VarDeclStm");
        String id = ctx.ID().getText();
        String type = ctx.type().getText();

        st.put(id, type);
        return id;
    }

    @Override
    public String visitConstDeclStm(ExprParser.ConstDeclStmContext ctx) {
    	System.out.println("ConstDeclStm");
        String id = ctx.ID().getText();
        String type = ctx.type().getText();
        st.put(id, type);
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
    	System.out.println("MultIdArgRef");
        String type = "";
        if (ctx.ID() != null)
            type += st.get(ctx.ID().getText()) + "_";
        if (ctx.nemp_arg_list() != null)
            return type + visit(ctx.nemp_arg_list());
        return type;
    }

    @Override
    public String visitIdArgRef(ExprParser.IdArgRefContext ctx) {
    	System.out.println("IdArgRef");
        if (ctx.ID() != null)
            if (!st.contains(ctx.ID().getText())) {
                System.out.println("ERROR @ " + ctx.start.getLine());
                System.out.println("Variable \"" + ctx.ID() + "\" has not been instantiated.");
            }
        return st.get(ctx.ID().getText());
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
    	System.out.println("AssignStm");
        String id = ctx.ID().getText();
        String exp_type = visit(ctx.expression());

        boolean instantiated;
        instantiated = st.contains(id);

        if (!instantiated) {
            System.out.println("Error @ Line: " + ctx.start.getLine());
            System.out.println("Variable: \"" + id + "\" has not been instantiated.");
            System.exit(0);
        }

        if (exp_type != null && exp_type.contains("_")) {
            String returnType = exp_type.split("_")[0];
            String type = st.get(id);
            if (!type.equals(returnType)) {
                System.out.println("Error @ Line: " + ctx.start.getLine());
                System.out.println("The return value assigned to \"" + id + "\" does not match the defined type \"" + type + "\".");
                System.exit(0);
            }
        }

        return id;
    }

    @Override
    public String visitFragBinArithStm(ExprParser.FragBinArithStmContext ctx) {
    	System.out.println("FragBinArithStm");
        boolean isInt = true;

        if (ctx.frag(0) != null)
            isInt = isInt && visit(ctx.frag(0)).equals("integer");

        if (ctx.frag(1) != null)
            isInt = isInt && visit(ctx.frag(1)).equals("integer");

        if (!isInt) {
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
    	System.out.println("FuncCallStm");
        return funcHandler(ctx.ID(), ctx.arg_list(), ctx);
    }

    @Override
    public String visitFuncCallExpr(ExprParser.FuncCallExprContext ctx) {
    	System.out.println("FuncCallExpr");
        return funcHandler(ctx.ID(), ctx.arg_list(), ctx);
    }

    private String funcHandler(TerminalNode id2, Arg_listContext argument_listContext, ParserRuleContext ctx) {
    	System.out.println("funcHandle");
        String id = id2.getText();

        if (!st.contains(id)) {
            System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Function: \"" + id + "\" has not been defined.");
            System.exit(0);
        }

        String compositeType = st.get(id);
        String[] argTypesArray = compositeType.split("_");

        if (argTypesArray.length == 1)
            return st.get(id);

        String argTypes = String.join("_", Arrays.copyOfRange(argTypesArray, 1 , argTypesArray.length));

        if (!argTypes.equals(visit(argument_listContext))) {
            System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.print("Function: \"" + id + "\" requires " + (argTypesArray.length - 1) + " arguments of types ");
            for (String arg : Arrays.copyOfRange(argTypesArray, 1 , argTypesArray.length))
                System.out.print(arg + ", ");
            System.out.println();
            System.exit(0);
        }

        return st.get(id);
    }

    @Override
    public String visitIdFrag(ExprParser.IdFragContext ctx) {
    	System.out.println("IdFrag");
        String id = ctx.ID().getText();
        String type = st.get(id);

        if (type == null) {
            System.out.println("Error @ Line " + ctx.start.getLine());
            System.out.println("Variable \"" + id + "\" has not been declared");
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
    	System.out.println("NegationStm");
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
