package pobj.expr;

public class Mult extends BinOp {
    public Mult(Expression e1, Expression e2) { fg=e1; fd=e2; }
    
    public <T> T accept(IVisitor<T> v) {return v.visit(this);}

	@Override
	public String toString() {
		return fg.toString() + " * " + fd.toString();
	}

	@Override
	public int eval() {
		return fg.eval()*fd.eval();
	}
}
