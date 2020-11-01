package my.compiler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Stack;

public class MyGrammarListener implements GrammarListener{


    public void visitTerminal(TerminalNode terminalNode) {

    }

    public void visitErrorNode(ErrorNode errorNode) {

    }

    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
    final Stack<ArrayList<Statement>> statements = new Stack<>();
    final Stack<LogicExpr> logicExpr = new Stack<>();
    final Stack<ArithExpr> arith = new Stack<>();

    public void enterStart(GrammarParser.StartContext ctx) {

    }

    public void exitStart(GrammarParser.StartContext ctx) {

    }

    public void enterStatements(GrammarParser.StatementsContext ctx) {

    }

    public void exitStatements(GrammarParser.StatementsContext ctx) {

    }

    public void enterIfStatement(GrammarParser.IfStatementContext ctx) {
        statements.push(new ArrayList<Statement>());
        System.out.println("Push blank statements nested in if "+statements);
    }

    public void exitIfStatement(GrammarParser.IfStatementContext ctx) {
        ArrayList<Statement> whileInnerStatements = statements.pop();
        statements.peek().add(new Statement.If(logicExpr.pop(),whileInnerStatements));
        System.out.println("Add if "+statements);
        System.out.println("Pop logic "+logicExpr);
    }

    public void enterWhileStatement(GrammarParser.WhileStatementContext ctx) {
        statements.push(new ArrayList<Statement>());
        System.out.println("Push blank statements nested in while "+statements);
    }

    public void exitWhileStatement(GrammarParser.WhileStatementContext ctx) {
        ArrayList<Statement> whileInnerStatements = statements.pop();
        statements.peek().add(new Statement.While(logicExpr.pop(),whileInnerStatements));
        System.out.println("Add while "+statements);
        System.out.println("Pop logic "+logicExpr);
    }

    public void enterAssignStatement(GrammarParser.AssignStatementContext ctx) {

    }

    public void exitAssignStatement(GrammarParser.AssignStatementContext ctx) {
        ArithExpr expr = arith.pop();
        String varID = ctx.ID().getText();
        statements.peek().add(new Statement.Assign(varID,expr));
        System.out.println("Add assign "+statements);
        System.out.println("Pop arith "+ arith);
    }

    public void enterReadStatement(GrammarParser.ReadStatementContext ctx) {

    }

    public void exitReadStatement(GrammarParser.ReadStatementContext ctx) {
        String varID = ctx.ID().getText();
        statements.peek().add(new Statement.Read(varID));
        System.out.println("Add read "+statements);
    }

    public void enterPrintStatement(GrammarParser.PrintStatementContext ctx) {

    }

    public void exitPrintStatement(GrammarParser.PrintStatementContext ctx) {
        statements.peek().add(new Statement.Print(arith.pop()));
        System.out.println("Add print "+statements);
        System.out.println("Pop arith "+ arith);
    }

    public void enterLogicLt(GrammarParser.LogicLtContext ctx) {

    }

    public void exitLogicLt(GrammarParser.LogicLtContext ctx) {
        ArithExpr rhs = arith.pop();
        ArithExpr lhs = arith.pop();//The order of popping it very important!
        logicExpr.push(new LogicExpr(lhs,rhs,LogicExpr.LT));
        System.out.println("Push logic LT "+logicExpr);
        System.out.println("Pop arith twice "+ arith);
    }

    public void enterLogicGt(GrammarParser.LogicGtContext ctx) {

    }

    public void exitLogicGt(GrammarParser.LogicGtContext ctx) {
        ArithExpr rhs = arith.pop();
        ArithExpr lhs = arith.pop();//The order of popping it very important!
        logicExpr.push(new LogicExpr(lhs,rhs,LogicExpr.GT));
        System.out.println("Push logic GT "+logicExpr);
        System.out.println("Pop arith twice "+ arith);
    }

    public void enterLogicEq(GrammarParser.LogicEqContext ctx) {

    }

    public void exitLogicEq(GrammarParser.LogicEqContext ctx) {
        ArithExpr rhs = arith.pop();
        ArithExpr lhs = arith.pop();//The order of popping it very important!
        logicExpr.push(new LogicExpr(lhs,rhs,LogicExpr.EQ));
        System.out.println("Push logic EQ "+logicExpr);
        System.out.println("Pop arith twice "+ arith);
    }

    public void enterLogicNeq(GrammarParser.LogicNeqContext ctx) {

    }

    public void exitLogicNeq(GrammarParser.LogicNeqContext ctx) {
        ArithExpr rhs = arith.pop();
        ArithExpr lhs = arith.pop();//The order of popping it very important!
        logicExpr.push(new LogicExpr(lhs,rhs,LogicExpr.NEQ));
        System.out.println("Push logic NEQ "+logicExpr);
        System.out.println("Pop arith twice "+ arith);
    }

    public void enterArithPlus(GrammarParser.ArithPlusContext ctx) {

    }

    public void exitArithPlus(GrammarParser.ArithPlusContext ctx) {
        ArithExpr rhs = arith.pop();
        ArithExpr lhs = arith.pop();//The order of popping it very important!
        arith.push(new ArithExpr.Plus(lhs,rhs));
        System.out.println("Pop arith twice, push plus "+ arith);
    }

    public void enterArithMinus(GrammarParser.ArithMinusContext ctx) {

    }

    public void exitArithMinus(GrammarParser.ArithMinusContext ctx) {
        ArithExpr rhs = arith.pop();
        ArithExpr lhs = arith.pop();//The order of popping it very important!
        arith.push(new ArithExpr.Minus(lhs,rhs));
        System.out.println("Pop arith twice, push minus "+ arith);
    }

    public void enterArithLvl0(GrammarParser.ArithLvl0Context ctx) {

    }

    public void exitArithLvl0(GrammarParser.ArithLvl0Context ctx) {
        //pass
        //because in this rule we are popping and
        //then pushing onto the stack the exact same element
        //which results in no operation
    }

    public void enterArithTimes(GrammarParser.ArithTimesContext ctx) {

    }

    public void exitArithTimes(GrammarParser.ArithTimesContext ctx) {
        ArithExpr rhs = arith.pop();
        ArithExpr lhs = arith.pop();//The order of popping it very important!
        arith.push(new ArithExpr.Times(lhs,rhs));
        System.out.println("Pop arith twice, push times "+ arith);
    }

    public void enterArithDiv(GrammarParser.ArithDivContext ctx) {

    }

    public void exitArithDiv(GrammarParser.ArithDivContext ctx) {
        ArithExpr rhs = arith.pop();
        ArithExpr lhs = arith.pop();//The order of popping it very important!
        arith.push(new ArithExpr.Div(lhs,rhs));
        System.out.println("Pop arith twice, push div "+ arith);
    }

    public void enterArithLvl1(GrammarParser.ArithLvl1Context ctx) {

    }

    public void exitArithLvl1(GrammarParser.ArithLvl1Context ctx) {
        //pass
        //because in this rule we are popping and
        //then pushing onto the stack the exact same element
        //which results in no operation
    }

    public void enterArithLvl2(GrammarParser.ArithLvl2Context ctx) {

    }

    public void exitArithLvl2(GrammarParser.ArithLvl2Context ctx) {
        //pass
        //because in this rule we are popping and
        //then pushing onto the stack the exact same element
        //which results in no operation
    }

    public void enterArithMinusUnary(GrammarParser.ArithMinusUnaryContext ctx) {

    }

    public void exitArithMinusUnary(GrammarParser.ArithMinusUnaryContext ctx) {
        ArithExpr child = arith.pop();
        arith.push(new ArithExpr.UnaryMinus(child));
        System.out.println("Push arith "+ arith);
    }

    public void enterArithVar(GrammarParser.ArithVarContext ctx) {

    }

    public void exitArithVar(GrammarParser.ArithVarContext ctx) {
        arith.push(new ArithExpr.Var(ctx.ID().getText()));
        System.out.println("Push arith var "+ arith);
    }

    public void enterArithInt(GrammarParser.ArithIntContext ctx) {

    }

    public void exitArithInt(GrammarParser.ArithIntContext ctx) {
        arith.push(new ArithExpr.Int(Integer.parseInt(ctx.INT().getText())));
        System.out.println("Push arith int "+ arith);
    }

    public void enterArithLvl3(GrammarParser.ArithLvl3Context ctx) {

    }

    public void exitArithLvl3(GrammarParser.ArithLvl3Context ctx) {
        //pass
        //because in this rule we are popping and
        //then pushing onto the stack the exact same element
        //which results in no operation
    }
}
