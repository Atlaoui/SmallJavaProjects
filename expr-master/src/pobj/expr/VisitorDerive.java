package pobj.expr;

public class VisitorDerive implements  IVisitor<Expression>{

	private Var x;

	public VisitorDerive(Var x) {
		this.x=x;
	}

	@Override
	public Expression visit(Constant c) {
		// TODO Auto-generated method stub
		return new Constant(0);
	}

	@Override
	public Expression visit(Add e) {
		return new Add(e.fd.accept(this),e.fg.accept(this));
	}

	@Override
	public Expression visit(Mult e) {
		Expression left = new Mult(e.fd,e.fg.accept(this));
		Expression right =new Mult(e.fd.accept(this),e.fg);
		return new Add(left,right);
	}

	@Override
	public Expression visit(Var v) {
		if(v.equals(x))
			return new Constant(1);
		return new Constant(0);

	}

	private Expression visite(Expression e) {
		if(e instanceof Add)
			return visit((Add)e);
		
		if(e instanceof Mult)
			return visit((Mult)e);
		
		if(e instanceof Var)
			return visit((Var)e);
		
		
		return new Constant(0);

	}


}
