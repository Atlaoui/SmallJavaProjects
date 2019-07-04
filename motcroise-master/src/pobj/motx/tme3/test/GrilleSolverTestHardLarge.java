package pobj.motx.tme3.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.csp.CSPSolver;
//import pobj.motx.tme3.adapt.MotX;
//import pobj.motx.tme3.csp.CSPSolverCorrige;
import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.MotX;
import pobj.motx.tme3.start.StratAlea;
import pobj.motx.tme3.start.StratFirst;
import pobj.motx.tme3.start.StratFrequence;
import pobj.motx.tme3.start.StratMin;
import pobj.motx.tme3.start.StratMinContraint;

public class GrilleSolverTestHardLarge {

	/*
	@Test
	public void testHard() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/hard.grl");

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		// System.out.println(gp);
		 assertTrue(! gp.isDead());

		ICSP problem = new MotX(gp);
	//CSPSolverCorrige solver = new CSPSolverCorrige();

		// solver.setStrat(new StratFirst());
		 //solver.setStrat(new StratMin());
		
		long timestamp = System.currentTimeMillis();
		//ICSP solution = solver.solve(problem);

		//System.out.println("Solution \n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
	}
	*/
	@Test
	public void testLarg() {


	Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large.grl");
		
		// System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		assertTrue(!gp.isDead());
		
		ICSP mot=new MotX(gp);

		CSPSolver cp= new CSPSolver();
		//cp.setChoixVarStrat(new StratFirst());
		cp.setChoixVarStrat(new StratMin());
		//cp.setChoixVarStrat(new StratMinContraint());
		//cp.setChoixValeurStrat(new StratAlea());
		//cp.setChoixValeurStrat(new StratFrequence());
		cp.solve(mot);

		
		System.out.println("Succès test GrillePotentiel : medium.");
		

	}

}
