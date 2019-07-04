package pobj.expr;

public class Constant implements Expression {
    private int value;

    public Constant(){this.value=0;}
    public Constant(int value){this.value=value;}

    public int getValue(){return value;}
    public <T> T accept(IVisitor<T> v){return v.visit(this);}
    
    @Override
    public String toString() {
    	return value+"";
    }
	public int eval() {
		return value ;
	}
}