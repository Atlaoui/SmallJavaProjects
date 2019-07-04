package pobj.expr;

public interface Expression {
	  public abstract <T> T accept(IVisitor<T> v);
	  
	  public abstract int eval();
}
