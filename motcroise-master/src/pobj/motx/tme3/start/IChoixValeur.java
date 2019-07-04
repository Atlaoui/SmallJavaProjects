package pobj.motx.tme3.start;

import java.util.List;

import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.IVariable;

public interface IChoixValeur {
	public List<String> order_Values (ICSP problem, IVariable v);

}
