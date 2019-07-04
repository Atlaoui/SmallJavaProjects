package pobj.expr;

public class VisitorEval implements IVisitor<Integer> {

	@Override
	public Integer visit(Constant c) {
		return c.getValue();
	}

	@Override
	public Integer visit(Add e) {
		if(e.fd instanceof Var)
			throw new UnsupportedOperationException();
		if(e.fg instanceof Var)
			throw new UnsupportedOperationException();
		
		Integer s1 = e.getRight().accept(this);
		Integer s2 = e.getLeft().accept(this);
		return (s1)+(s2);
	}

	@Override
	public Integer visit(Mult e) {
		
		if(e.fd instanceof Var)
			throw new UnsupportedOperationException();
		if(e.fg instanceof Var)
			throw new UnsupportedOperationException();
		
		
		Integer s1 = e.getRight().accept(this);
		Integer s2 = e.getLeft().accept(this);
		return (s1)*(s2);
	}

	@Override
	public Integer visit(Var v) {
		throw new UnsupportedOperationException();
	}

}
