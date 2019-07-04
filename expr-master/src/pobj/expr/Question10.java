package pobj.expr;

public class Question10 {
	// rend vrai si e est un arbre d’expression constant
	public static boolean isConstant(Expression e) {
		return e.accept(new VisitorConstant());
	}
	// rend la valeur d’une expression constante
	// signale une exception si l’expression n’est pas constante
	public static int evalConstantExpression (Expression e) {
		return e.eval();
	}
	}
