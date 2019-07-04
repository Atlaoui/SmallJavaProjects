package pobj.tme5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pobj.tme4.HashMultiSet;
import pobj.tme4.MultiSet;
import pobj.tme4.MultiSetDecorator;
import pobj.util.InvalidCountException;

public class HashMultiSetTest2 {
	
	
	private MultiSet<String> ms;
	private MultiSetDecorator<String> m ;
	
	@Before
	public void before() {
		ms = new HashMultiSet<>();
		m =new MultiSetDecorator<>(ms);
	}
	
	
	@Test
	public void testAdd() {
		m.add("a", 0);
		assertEquals(m.count("a"), 0);
		m.add("a");
		m.add("a", 0);
		assertEquals(m.count("a"), 1);
		System.out.println("Succ�s test ADD");
		
	}
	
	@Test
	public void testAdd1() throws InvalidCountException {
	MultiSet<String> m = new HashMultiSet<>();
	m.add("a");
	m.add("a",5);
	assertEquals(m.count("a"), 6);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testAdd2() throws InvalidCountException {
	MultiSet<String> m = new HashMultiSet<>();
	m.add("a");
	m.add("a",-1);
	}

	
	@Test
	public void testRemove1() throws InvalidCountException {
		m.add("a", 7);
		m.remove("a");
		m.remove("a", 2);
		assertEquals(m.count("a"), 4);
		System.out.println("Succ�s test Remove 1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestRemove2() {
		m.add("a", 11);
		m.remove("a",-1);
		System.out.println("Succ�s test Remove 2");
	}
	
	@Test
	public void TestSize(){
		m.add("a", 900);
		m.remove("a");
		m.remove("a", 500);
		m.add("a");
		assertEquals(m.size(), 400);
		System.out.println("Succ�s test size");
	}
	
	@Test
	public void testToString() {
		m.add("a",1);
		m.add("b",2);
		m.add("c",99);
		System.out.println(m.toString());
		assertEquals(m.toString(),"[a:1; b:2; c:99]");
		
	}
	
	@Test
	public void testDeco() {
		MultiSetDecorator<String> deco = new MultiSetDecorator<String>(m);
		deco.add("a");
		deco.add("BLABLA",500);
		deco.clear();
		
	}
	
	
	@Test
	public void TestClear(){
		m.add("a", 1560);
		m.add("AD", 654);
		m.remove("a");
		m.clear();
		assertEquals(m.count("a"), 0);
		assertEquals(m.count("AD"), 0);
		assertEquals(m.size(), 0);
	}
}
