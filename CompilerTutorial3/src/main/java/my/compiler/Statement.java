package my.compiler;

import java.util.ArrayList;

public interface Statement {

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
    }


}
