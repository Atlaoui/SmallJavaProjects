package pobj.motx.tme3.csp;

import pobj.motx.tme3.start.IChoixValeur;
import pobj.motx.tme3.start.IChoixVar;

public class CSPSolver {
	private IChoixVar stratVar=null;
	
	private IChoixValeur stratVal=null;
	
	public ICSP solve(ICSP problem) {
		System.out.println("Solve : \n" + problem);
		// Condition terminale : succès
		if (problem.getVars().isEmpty()) {
			System.out.println("Problème résolu.");
			return problem;
		}
		// condition terminale : échec sur cette branche
		if (!problem.isConsistent()) {
			System.out.println("Problème invalide.");
			return problem;
		} else {
			System.out.println("Problème valide.");
		}
		// On choisit une variable arbitraire, ici la première
		// On est garantis que ! getVars().isEmpty(), testé au dessus

		if(stratVal!=null)
			stratVal.order_Values(problem, null);
		
		IVariable vi;
		if(stratVar!=null)
			vi=stratVar.chooseVar(problem);
		else
			vi = problem.getVars().get(0);
		
		ICSP next = null;
		// On est garantis que toute variable a un domaine non nul
		for (String val : vi.getDomain()) {
			System.out.println("Fixe var :" + vi + " à " + val);
			next = problem.assign(vi, val);
			next = solve(next);
			if (next.isConsistent()) {
				return next;
			} else {
				System.out.println("Essai valeur suivante.");
			}
		}
		System.out.println("Backtrack sur variable "+ vi);
		return next;
	}

	public void setChoixVarStrat(IChoixVar strat) {
		this.stratVar=strat;
	}
	
	public void setChoixValeurStrat(IChoixValeur stratVal) {
		this.stratVal=stratVal;
	}
	
}
