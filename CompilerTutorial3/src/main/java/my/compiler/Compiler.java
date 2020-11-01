package my.compiler;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;

public class Compiler {

    public static void main(String[] args) {
        String source = "while(x<y){x = 1 * (1 + 2); if(x>0){x=x-1;}}";
        final GrammarLexer lexer = new GrammarLexer(CharStreams.fromString(source));
        final GrammarParser parser = new GrammarParser(new CommonTokenStream(lexer));
        MyGrammarListener listener = new MyGrammarListener();
        listener.statements.push(new ArrayList<Statement>());
        System.out.println("Push initial blank statements "+listener.statements);
        ParseTreeWalker.DEFAULT.walk(listener, parser.start());
        ArrayList<Statement> output = listener.statements.pop();
        if(!listener.arith.isEmpty())throw new IllegalStateException("Arithmetic operation unpopped "+listener.logicExpr);
        if(!listener.logicExpr.isEmpty())throw new IllegalStateException("Arithmetic operation unpopped "+listener.arith);
        if(!listener.statements.isEmpty())throw new IllegalStateException("Statements unpopped "+listener.statements);
        System.out.println(output);
    }
}
