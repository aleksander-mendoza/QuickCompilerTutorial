package my.compiler;

import java.util.ArrayList;

public interface Statement {

    public static void compileToC(ArrayList<Statement> statements, StringBuilder sb){
        for(Statement stmt:statements){
           stmt.compileToC(sb);
        }
    }

    void compileToC(StringBuilder sb);

    public static class If implements Statement {
        final LogicExpr test;
        final ArrayList<Statement> statements;

        public If(LogicExpr test, ArrayList<Statement> statements) {
            this.test = test;
            this.statements = statements;
        }

        @Override
        public String toString() {
            return "if("+test+")"+statements;
        }

        @Override
        public void compileToC(StringBuilder sb) {
            sb.append("if(");
            test.compileToC(sb);
            sb.append("){");
            Statement.compileToC(statements,sb);
            sb.append("}");
        }
    }

    public static class While implements Statement {
        final LogicExpr test;
        final ArrayList<Statement> statements;

        public While(LogicExpr test, ArrayList<Statement> statements) {
            this.test = test;
            this.statements = statements;
        }
        @Override
        public String toString() {
            return "while("+test+")"+statements;
        }

        @Override
        public void compileToC(StringBuilder sb) {
            sb.append("while(");
            test.compileToC(sb);
            sb.append("){");
            Statement.compileToC(statements,sb);
            sb.append("}");
        }
    }

    public static class Assign implements Statement {
        final String var;
        final ArithExpr expr;

        public Assign(String var, ArithExpr expr) {
            this.var = var;
            this.expr = expr;
        }
        @Override
        public String toString() {
            return var+" = "+expr;
        }

        @Override
        public void compileToC(StringBuilder sb) {
            sb.append(var);
            sb.append("=");
            expr.compileToC(sb);
            sb.append(";");
        }
    }

    public static class Read implements Statement {
        final String variable;

        public Read(String variable) {
            this.variable = variable;
        }
        @Override
        public String toString() {
            return "read "+variable;
        }

        @Override
        public void compileToC(StringBuilder sb) {
            sb.append("printf(\"What is ").append(variable).append("=\");fflush(stdout);");
            sb.append("scanf(\"%d\", &").append(variable).append(");");
        }
    }

    public static class Print implements Statement {
        final ArithExpr out;

        public Print(ArithExpr out) {
            this.out = out;
        }
        @Override
        public String toString() {
            return "print "+out;
        }
        @Override
        public void compileToC(StringBuilder sb) {
            sb.append("printf(\"").append(out).append("=%d\\n\", ");
            out.compileToC(sb);
            sb.append(");");
        }
    }


}
