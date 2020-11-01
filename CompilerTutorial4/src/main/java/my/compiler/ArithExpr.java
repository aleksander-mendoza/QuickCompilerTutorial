package my.compiler;

public interface ArithExpr {

    void compileToC(StringBuilder sb);

    public static class Plus implements ArithExpr{
        final ArithExpr lhs,rhs;

        public Plus(ArithExpr lhs, ArithExpr rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }

        @Override
        public String toString() {
            return lhs+" + "+rhs;
        }

        @Override
        public void compileToC(StringBuilder sb) {
            lhs.compileToC(sb);
            sb.append(" + ");
            rhs.compileToC(sb);
        }
    }

    public static class Minus implements ArithExpr{
        final ArithExpr lhs,rhs;

        public Minus(ArithExpr lhs, ArithExpr rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }
        @Override
        public String toString() {
            return lhs+" - "+rhs;
        }
        @Override
        public void compileToC(StringBuilder sb) {
            lhs.compileToC(sb);
            sb.append(" - ");
            rhs.compileToC(sb);
        }
    }

    public static class Times implements ArithExpr{
        final ArithExpr lhs,rhs;

        public Times(ArithExpr lhs, ArithExpr rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }
        @Override
        public String toString() {
            return (lhs instanceof Plus || lhs instanceof  Minus ? "("+lhs+")" : lhs)
                    +" * "+
                    (rhs instanceof Plus || rhs instanceof  Minus ? "("+rhs+")" : rhs);
        }
        @Override
        public void compileToC(StringBuilder sb) {
            if(lhs instanceof Plus || lhs instanceof  Minus){
                sb.append("(");
                lhs.compileToC(sb);
                sb.append(")");
            }else {
                lhs.compileToC(sb);
            }
            sb.append(" * ");
            if(rhs instanceof Plus || rhs instanceof  Minus){
                sb.append("(");
                rhs.compileToC(sb);
                sb.append(")");
            }else {
                rhs.compileToC(sb);
            }
        }
    }

    public static class Div implements ArithExpr{
        final ArithExpr lhs,rhs;

        public Div(ArithExpr lhs, ArithExpr rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }
        @Override
        public String toString() {
            return (lhs instanceof Plus || lhs instanceof  Minus ? "("+lhs+")" : lhs)
                    +" / "+
                    (rhs instanceof Plus || rhs instanceof  Minus
                            || rhs instanceof  Div || rhs instanceof Times ? "("+rhs+")" : rhs);
        }
        @Override
        public void compileToC(StringBuilder sb) {
            if(lhs instanceof Plus || lhs instanceof  Minus){
                sb.append("(");
                lhs.compileToC(sb);
                sb.append(")");
            }else {
                lhs.compileToC(sb);
            }
            sb.append(" / ");
            if(rhs instanceof Var || rhs instanceof  Int){
                rhs.compileToC(sb);
            }else {
                sb.append("(");
                rhs.compileToC(sb);
                sb.append(")");
            }
        }
    }

    public static class UnaryMinus  implements ArithExpr{
        final ArithExpr child;

        public UnaryMinus(ArithExpr child) {
            this.child = child;
        }
        @Override
        public String toString() {
            return "-" + (child instanceof Var || child instanceof  Int ? child : "("+child+")");
        }
        @Override
        public void compileToC(StringBuilder sb) {
            sb.append(" - ");
            if(child instanceof Var || child instanceof  Int){
                child.compileToC(sb);
            }else {
                sb.append("(");
                child.compileToC(sb);
                sb.append(")");
            }
        }
    }

    public static class Var implements ArithExpr{
        final String id;

        public Var(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
        @Override
        public void compileToC(StringBuilder sb) {
            sb.append(id);
        }
    }

    public static class Int implements ArithExpr{
        final int val;

        public Int(int val) {
            this.val = val;
        }
        @Override
        public String toString() {
            return Integer.toString(val);
        }
        @Override
        public void compileToC(StringBuilder sb) {
            sb.append(val);
        }
    }


}
