package pobj.expr;

public abstract class BinOp implements Expression {
	 protected Expression fg, fd;
	    public Expression getLeft(){return fg;}
	   public Expression getRight(){return fd;}
}
