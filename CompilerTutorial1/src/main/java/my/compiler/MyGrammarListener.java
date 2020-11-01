package my.compiler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MyGrammarListener implements GrammarListener{
    public void enterStart(GrammarParser.StartContext ctx) {
        System.out.println("Start entered");
    }

    public void exitStart(GrammarParser.StartContext ctx) {
        System.out.println("Start exited");
    }

    public void visitTerminal(TerminalNode terminalNode) {

    }

    public void visitErrorNode(ErrorNode errorNode) {

    }

    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
