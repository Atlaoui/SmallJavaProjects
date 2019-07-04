package pobj.expr;

public class VisitorConstant implements IVisitor<Boolean>{
	private VisitorEval visitor=new VisitorEval();	

	@Override
	public Boolean visit(Constant c) {
		return true;
	}

	@Override
	public Boolean visit(Add e) {
		try {
			visitor.visit(e);
		}catch(UnsupportedOperationException e1) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean visit(Mult e) {
		try {
			visitor.visit(e);
		}catch(UnsupportedOperationException e1) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean visit(Var v) {
		return false;
	}

}
