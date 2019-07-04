package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval{

	private Map<String, Integer> varMap;



	public VisitorEvalVar(Map<String, Integer> env) {
		this.varMap = env;
	}

	
	@Override
	public Integer visit(Add e) {
		Integer s1,s2;
		if(e.fd instanceof Var)
			s1=	this.visit((Var)e.fd);
		else
			s1=e.getRight().accept(this);
		
		if(e.fg instanceof Var)
			s2=	this.visit((Var)e.fg);
		else
			s2 = e.getLeft().accept(this);
		
		return (s1)+(s2);
	}

	@Override
	public Integer visit(Mult e) {
		
		Integer s1,s2;
		if(e.fd instanceof Var)
			s1=	this.visit((Var)e.fd);
		else
			s1=e.getRight().accept(this);
		
		if(e.fg instanceof Var)
			s2=	this.visit((Var)e.fg);
		else
			s2 = e.getLeft().accept(this);
	
		return (s1)*(s2);
	}
	


	@Override
	public Integer visit(Var v) {
		return varMap.get(v.toString());
	}

}
