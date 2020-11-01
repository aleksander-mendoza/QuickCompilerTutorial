package my.compiler;

public class LogicExpr {
    final ArithExpr lhs,rhs;
    final int type;

    public static final int EQ = 0;
    public static final int LT = 1;
    public static final int GT = 2;
    public static final int NEQ = 3;

    public LogicExpr(ArithExpr lhs, ArithExpr rhs, int type) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type){
            case  EQ:
                return lhs+" == "+rhs;
            case  LT:
                return lhs+" < "+rhs;
            case  GT:
                return lhs+" > "+rhs;
            case  NEQ:
                return lhs+" != "+rhs;
        }
        return null;//not possible
    }
}
