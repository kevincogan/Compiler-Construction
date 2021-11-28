package expression;

import java.util.ArrayList;
import java.util.List;

import antlr.ExprBaseVisitor;
import antlr.ExprParser;


public class IRGeneratorVisitor extends ExprBaseVisitor<String> {
    private int paramaterNum = 1;
    private int jmpIdx = 1;
    private final QuadrupleQueue quadQueue = new QuadrupleQueue();

    @Override
    public String visitProgStm(ExprParser.ProgStmContext ctx) {
        String output = "";

        if (ctx.decl_list() != null)
            output = output + visit(ctx.decl_list()) + "\n";
        if (ctx.function_list() != null)
            output = output + visit(ctx.function_list()) + "\n";
        if (ctx.main() != null)
            output = output + visit(ctx.main());

        return output;
    }

    @Override
    public String visitDeclListStm(ExprParser.DeclListStmContext ctx) {
        String output = "";

        if (!(ctx.decl() == null))
            output = output + visit(ctx.decl());
        if (ctx.decl_list() != null)
            output = output + visit(ctx.decl_list());

        return output;
    }

    @Override
    public String visitType(ExprParser.TypeContext ctx) {////
    	
    	System.out.println(ctx.getText()+"------------------------------------");
        return ctx.getText();
    }

//    @Override
//    public String visitEmptyStm(ExprParser.EmptyStmContext ctx) {
//        return "";
//    }

    @Override
    public String visitVarDeclRef(ExprParser.VarDeclRefContext ctx) {
        return "";
    }

    @Override
    public String visitVarDeclStm(ExprParser.VarDeclStmContext ctx) {
        return "";
    }

    @Override
    public String visitConstDeclRef(ExprParser.ConstDeclRefContext ctx) {
        return visit(ctx.const_decl());
    }

    @Override
    public String visitConstDeclStm(ExprParser.ConstDeclStmContext ctx) {
        return "\t" + ctx.ID().getText() + " = " + visit(ctx.expression()) + "\n";
    }

    @Override
    public String visitFuncListStm(ExprParser.FuncListStmContext ctx) {
        String output = "";

        if (!(ctx.function() == null))
            output = output + visit(ctx.function());
        
        if (!(ctx.function_list() == null))
            output = output + visit(ctx.function_list());
        
        return output;
    }

    @Override
    public String visitFuncDeclStm(ExprParser.FuncDeclStmContext ctx) {
        String output = "";
        this.paramaterNum = 1; ///might not need to be there...

        output = output + ctx.ID().getText() + ":\n";

        if (!(ctx.parameter_list() == null))
            output = output + visit(ctx.parameter_list());
        if (!(ctx.decl_list() == null))
            output = output + visit(ctx.decl_list());
        if (!(ctx.statement_block() == null))
            output = output + visit(ctx.statement_block());
        if (!(ctx.expression() == null))
            output = output + "\treturn " + visit(ctx.expression());

        return output;
    }

    @Override
    public String visitNonEmptyParamRef(ExprParser.NonEmptyParamRefContext ctx) {
        String output = "";
        if (ctx.nemp_parameter_list() != null)
            output = output + visit(ctx.nemp_parameter_list());
        return output;
    }

    @Override
    public String visitSingleParamStm(ExprParser.SingleParamStmContext ctx) {
        String line = "\t" + ctx.ID().getText() + " = getparam " + paramaterNum + "\n";
        this.paramaterNum++;
        return line;
    }

    @Override
    public String visitMultipleParamStm(ExprParser.MultipleParamStmContext ctx) {
        String output = "";
        if (ctx.ID() != null) {
            output = output + "\t" + ctx.ID().getText() + " = getparam " + paramaterNum + "\n";
            paramaterNum++;
        } if (ctx.nemp_parameter_list() != null)
            output = output + visit(ctx.nemp_parameter_list());
        return output;
    }

    @Override
    public String visitMainStm(ExprParser.MainStmContext ctx) {
        String output = "main:\n";
        if (ctx.decl_list() != null)
            output = output + visit(ctx.decl_list());
        output = output + "\n";
        if (ctx.statement_block() != null)
            output = output + visit(ctx.statement_block());
        output = output + "\tcall _exit, 0";
        return output;
    }

    @Override
    public String visitStmBlockRef(ExprParser.StmBlockRefContext ctx) {
        String output = "";
        if (ctx.statement() != null)
            output = output + visit(ctx.statement()) + "\n";
        if (ctx.statement_block() != null)
            output = output + visit(ctx.statement_block());
        return output;
    }

    @Override
    public String visitEmptyStatment(ExprParser.EmptyStatmentContext ctx) {
        return "";
    }

    @Override
    public String visitAssignStm(ExprParser.AssignStmContext ctx) {
        String output = visit(ctx.expression());
        quadQueue.clear();
        String[] lines = output.split("\n");
        lines[lines.length - 1] = "\t" + ctx.ID().getText() + " = " + lines[lines.length - 1] + "\n";

        if (output.contains("param"))
            return "\n" + String.join("\n", lines);

        output = "";
        quadQueue.add("=", visit(ctx.expression()), ctx.ID().getText());
        output = output + quadQueue.genIRCode();
        return output;

    }

    @Override
    public String visitFuncCallStm(ExprParser.FuncCallStmContext ctx) {
        String output = "";
        if (ctx.arg_list() != null)
            output = output + visit(ctx.arg_list());
        output = output + "\tcall " + ctx.ID().getText() + ", " + (paramaterNum - 1);
        return output;
    }

    @Override
    public String visitFuncCallExpr(ExprParser.FuncCallExprContext ctx) {

        String output = "";

        if (ctx.arg_list() != null)
            output = output + visit(ctx.arg_list());

        output = output + "call " + ctx.ID().getText() + ", " + (paramaterNum - 1) + "\n";

        return output;
    }

    @Override
    public String visitBeginStm(ExprParser.BeginStmContext ctx) {
        return "\t" + visit(ctx.statement_block()) + "\n";
    }

    @Override
    public String visitConditionalStm(ExprParser.ConditionalStmContext ctx) {
        String output = "";

        int jmpTo;
        boolean isAnd = false;

        String condition = visit(ctx.condition());
        String[] boolExpressions;

        if (condition.contains("&")) {
            isAnd = true;
            boolExpressions = condition.split("&");
        } else if (condition.contains("||"))
            boolExpressions = condition.split("||");
        else
            boolExpressions = new String[] {condition};

        jmpTo = jmpIdx + boolExpressions.length;

        if (isAnd) {
            for (String _if : boolExpressions) {
                output = output + "\t" + "ifz " + _if + " goto l" + jmpIdx + "\n";
                output = output + "\tgoto exit" + jmpTo + "\n";
                output = output + "l" + jmpIdx + ":\n";
                jmpIdx++;
            }
            jmpIdx = jmpTo;

            if (ctx.statement_block(0) != null)
                output = output + visit(ctx.statement_block(0));

            output = output + "\tgoto exit" + (jmpIdx + 1) + "\n";
            output = output + "exit" + jmpIdx + ":\n";
            jmpIdx++;

        } else {
            for (String _if : boolExpressions) {
                output = output + "\t" + "if " + _if + " goto l" + jmpIdx + "\n";
                output = output + "\tgoto exit" + (jmpIdx + 1) + "\n";
                output = output + "l" + jmpIdx + ":\n";
            }

            jmpIdx++;
            if (ctx.statement_block(0) != null)
                output = output + visit(ctx.statement_block(0));

            output = output + "\tgoto exit" + jmpIdx + "\n";
        }

        if (ctx.statement_block(1) != null)
            output = output + visit(ctx.statement_block(1));

        output = output + "exit" + jmpIdx + ":\n";

        return output;
    }

    @Override
    public String visitWhileStm(ExprParser.WhileStmContext ctx) {
        String output = "l" + jmpIdx + ":\n";
        if (ctx.condition() != null)
            output = output + "\t" + "ifz " + visit(ctx.condition()) + " goto exit" + jmpIdx + "\n";
        if (ctx.statement_block() != null)
            output = output + visit(ctx.statement_block());
        output = output + "\tgoto l" + jmpIdx + "\n";
        output = output + "exit" + jmpIdx + ":\n";
        jmpIdx++;
        return output;
    }

    @Override
    public String visitSkipStm(ExprParser.SkipStmContext ctx) {
        return "";
    }

    @Override
    public String visitFragBinArithStm(ExprParser.FragBinArithStmContext ctx) {
        int tmpN = quadQueue.getTmpCounter();
        quadQueue.add(visit(ctx.binary_arith_op()), visit(ctx.frag(0)), visit(ctx.frag(1)), "t" + tmpN); //Might need to change frag to expression...
        return "t" + tmpN;
    }

    @Override
    public String visitParenExpreStm(ExprParser.ParenExpreStmContext ctx) {
        return "";
    }

    @Override
    public String visitFragRef(ExprParser.FragRefContext ctx) {
        return visit(ctx.frag());
    }

    @Override
    public String visitBoolNegStm(ExprParser.BoolNegStmContext ctx) {
        return ctx.NEGATION() + " " + visit(ctx.condition());
    }

    @Override
    public String visitParenConditionalStm(ExprParser.ParenConditionalStmContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public String visitBoolEvalStm(ExprParser.BoolEvalStmContext ctx) {
        String op;
        if (ctx.AND() != null)
            op = "&&";
        else
            op = "||";

        return visit(ctx.condition(0)) + op + visit(ctx.condition(1));
    }

    @Override
    public String visitBoolArithStm(ExprParser.BoolArithStmContext ctx) {
        return visit(ctx.expression(0)) + " " + visit(ctx.comp_op()) + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitIdFrag(ExprParser.IdFragContext ctx) {
        return ctx.ID().getText();
    }

    @Override
    public String visitFragUnaArithStm(ExprParser.FragUnaArithStmContext ctx) {
        int tmpN = quadQueue.getTmpCounter();
        quadQueue.add(visit(ctx.binary_arith_op()), visit(ctx.expression()), "t" + tmpN);
        return "t" + tmpN;
    }

    @Override
    public String visitNegationStm(ExprParser.NegationStmContext ctx) {
        return ctx.ID().getText();
    }

    @Override
    public String visitIntStm(ExprParser.IntStmContext ctx) {
        String num = ctx.NUMBER().getText();

        if (Integer.parseInt(num) < 0)
            return "0 - " + num.substring(1);

        return num;
    }

    @Override
    public String visitTrueStm(ExprParser.TrueStmContext ctx) {
        return ctx.True().getText();
    }

    @Override
    public String visitFalseStm(ExprParser.FalseStmContext ctx) {
        return ctx.False().getText();
    }

    @Override
    public String visitNonEmptyArgListRef(ExprParser.NonEmptyArgListRefContext ctx) {
        String output = "";
        if (ctx.nemp_arg_list() != null)
            output = output + visit(ctx.nemp_arg_list());

        return output;
    }

    @Override
    public String visitIdArgRef(ExprParser.IdArgRefContext ctx) {
        String output = "";
        if (ctx.ID() != null)
            output = output + "\tparam " + ctx.ID().getText() + "\n";
        return output;
    }

    @Override
    public String visitMultIdArgRef(ExprParser.MultIdArgRefContext ctx) {
        String output = "";
        if (ctx.ID() != null)
            output = output + "\tparam " + ctx.ID().getText() + "\n";
        if (ctx.nemp_arg_list() != null)
            output = output + visit(ctx.nemp_arg_list());
        return output;
    }

    @Override
    public String visitAdditionStm(ExprParser.AdditionStmContext ctx) {
        return "+";
    }

    @Override
    public String visitSubtractionStm(ExprParser.SubtractionStmContext ctx) {
        return "-";
    }

    @Override
    public String visitLogOr(ExprParser.LogOrContext ctx) {
        return ctx.OR().getText();
    }

    @Override
    public String visitLogEq(ExprParser.LogEqContext ctx) {
        return ctx.EQUAL().getText();
    }

    @Override
    public String visitLogNEq(ExprParser.LogNEqContext ctx) {
        return ctx.NOTEQUAL().getText();
    }

    @Override
    public String visitLogLT(ExprParser.LogLTContext ctx) {
        return ctx.LT().getText();
    }

    @Override
    public String visitLogLTE(ExprParser.LogLTEContext ctx) {
        return ctx.LTE().getText();
    }

    @Override
    public String visitLogGT(ExprParser.LogGTContext ctx) {
        return ctx.GT().getText();
    }

    @Override
    public String visitLogGTE(ExprParser.LogGTEContext ctx) {
        return ctx.GTE().getText();
    }
}