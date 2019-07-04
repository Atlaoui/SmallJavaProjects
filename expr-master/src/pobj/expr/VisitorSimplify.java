package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression>{

	@Override
	public Expression visit(Constant c) {
		return c;
	}

	@Override
	public Expression visit(Add e) {
		if(e.fd instanceof Var && e.fg instanceof Var)
			return e;
		if(e.fd instanceof Constant)
			if(e.fd.eval()==0)
				return e.fg;
		if(e.fg instanceof Constant)
			if(e.fg.eval()==0)
				return e.fd;
		if(e.fd instanceof Var)
			return e;
		if(e.fg instanceof Var)
			return e;
		if(Question10.isConstant(e.fd))
			if(!Question10.isConstant(e.fg))
			return new Add(new Constant(e.fd.eval()),e.fg);	
		if(!Question10.isConstant(e.fd))
			if(Question10.isConstant(e.fg))
			return new Add(new Constant(e.fg.eval()),e.fd);	
		if(!Question10.isConstant(e.fd))
			if(!Question10.isConstant(e.fg))
			return new Add(e.fg,e.fd);	
	
		return new Constant(e.eval());
	}

	@Override
	public Expression visit(Mult e) {
		if(e.fd instanceof Var && e.fg instanceof Var)
			return e;
		if(e.fd instanceof Constant)
			if(e.fd.eval()==1)
				return e.fg;
		if(e.fg instanceof Constant)
			if(e.fg.eval()==1)
				return e.fd;
		if(e.fd instanceof Constant)
			if(e.fd.eval()==0)
				return new Constant(0);
		if(e.fg instanceof Constant)
			if(e.fg.eval()==0)
				return new Constant(0);
		if(e.fd instanceof Var)
			return e;
		if(e.fg instanceof Var)
			return e;	
		if(Question10.isConstant(e.fd))
			if(!Question10.isConstant(e.fg))
			return new Mult(new Constant(e.fd.eval()),e.fg);	
		if(!Question10.isConstant(e.fd))
			if(Question10.isConstant(e.fg))
			return new Mult(e.fd,new Constant(e.fg.eval()));	
		
		if(!Question10.isConstant(e.fd))
			if(!Question10.isConstant(e.fg))
			return new Mult(e.fg,e.fd);	
		
		
		return new Constant(e.eval());
	
	}

	@Override
	public Expression visit(Var v) {
		return v;
	}
	
}
