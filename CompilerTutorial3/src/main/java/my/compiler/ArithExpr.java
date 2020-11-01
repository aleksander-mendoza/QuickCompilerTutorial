package my.compiler;

public interface ArithExpr {

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
    }


}
