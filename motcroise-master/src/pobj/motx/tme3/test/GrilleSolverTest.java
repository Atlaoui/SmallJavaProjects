package pobj.motx.tme3.test;


import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.MotX;
import pobj.motx.tme3.start.StratMin;

public class GrilleSolverTest {

	@Test
	public void testMedium() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

	
		 //System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX mot=new MotX(gp);
		
		CSPSolver cp= new CSPSolver();
		//cp.setChoixVarStrat(new StratMin());
		cp.solve(mot);

		
		
		System.out.println("Succès test GrillePotentiel : medium.");
	
	}
	@Test
	public void testHard() {


		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large4.grl");

	
		 System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX mot=new MotX(gp);
		
		CSPSolver cp= new CSPSolver();
		cp.setChoixVarStrat(new StratMin());
		cp.solve(mot);

		System.out.println("Succès test GrillePotentiel : hard.");
	}
	
}
