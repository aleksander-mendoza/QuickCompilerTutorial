package my.compiler;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Compiler {

    public static void main(String[] args) throws IOException, InterruptedException {
        String source = "read x; read y; while(x!=y){if(x>y){x = x - y;}if(x<y){y = y - x;}} print x;";
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
        System.out.println("TRANSPILED TO C");
        StringBuilder sb = new StringBuilder();
        sb.append("#include <stdio.h>\n int main(){\n");
        for(String declaredVariable:listener.variablesRequired){
            sb.append("    int ").append(declaredVariable).append(" = 0;\n");
        }
        sb.append("    ");
        Statement.compileToC(output,sb);
        sb.append("\n}");
        System.out.println(sb.toString());
        ProcessBuilder gcc = new ProcessBuilder("gcc","-xc","-o",System.getProperty("user.dir")+"/exec","-");
        gcc.redirectError(ProcessBuilder.Redirect.INHERIT);
        System.out.println("RUNNING GCC COMPILER");
        Process gccProcess = gcc.start();
        OutputStream out = gccProcess.getOutputStream();
        out.write(sb.toString().getBytes());
        out.flush();
        out.close();
        gccProcess.waitFor();
        System.out.println("RUNNING EXECUTABLE IN "+System.getProperty("user.dir"));
        ProcessBuilder exec = new ProcessBuilder(System.getProperty("user.dir")+"/exec");
        exec.inheritIO();
        exec.start().waitFor();

    }
}
