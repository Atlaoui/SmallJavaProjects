package pobj.expr;

public class Var implements Expression {
    String nom;
    public Var(String nom){this.nom=nom;}

    public String getName(){return nom;}
    public <T> T accept(IVisitor<T> v){return v.visit(this);}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Var other = (Var) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + nom;
	}

	@Override
	public int eval() {
	 throw new UnsupportedOperationException();
	}
}