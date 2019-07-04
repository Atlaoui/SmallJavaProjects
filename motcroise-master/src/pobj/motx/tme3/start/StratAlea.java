package pobj.motx.tme3.start;


import java.util.Collections;
import java.util.List;

import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.IVariable;

public class StratAlea implements IChoixValeur {

	@Override
	public List<String> order_Values(ICSP problem, IVariable v) {
		List<IVariable> listDico = problem.getVars();
		Collections.shuffle(listDico);
		problem.setVar(listDico);
		return null;
	}

}
