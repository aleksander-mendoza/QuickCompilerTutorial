package my.compiler;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Compiler {

    public static void main(String[] args) {
        String source = "hello world";
        final GrammarLexer lexer = new GrammarLexer(CharStreams.fromString(source));
        final GrammarParser parser = new GrammarParser(new CommonTokenStream(lexer));
        ParseTreeWalker.DEFAULT.walk(new MyGrammarListener(), parser.start());
    }
}
