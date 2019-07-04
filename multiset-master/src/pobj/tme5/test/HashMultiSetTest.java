package pobj.tme5.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pobj.tme4.HashMultiSet;
import pobj.tme4.MultiSet;

public class HashMultiSetTest {
	
	private MultiSet<String> m;
	
	@Before
	public void before() {
		m = new HashMultiSet<>();
	}
	
	@Test 
	public void testAdd1() {
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testAdd2() {
		m.add("a");
		m.add("a",-1);
	}
	
	
	@Test
	public void testRemove1() {
		m.add("a");
		m.add("a",2);
		m.remove("a",1);
		assertEquals(2, m.count("a"));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testRemove2() {
		m.add("a");
		m.remove("a",-1);
	}
	
	@Test 
	public void testSize() {
		assertEquals(0, m.size());
		m.add("a",6);
		m.add("j",6);
		assertEquals(12, m.size());
	}


	
	@Test 
	public void testClear() {
		m.add("a");
		m.add("a",2);
		m.remove("a",1);
		m.clear();
		assertEquals(0 , m.size());
		
		
	}
	
	@Test
	public void testComplexe() {
		assertEquals(0, m.size());
		m.add("a",6);
		m.add("h",12);
		m.add("a",6);
		assertEquals(false ,m.remove("m",20));
		m.add("hello",15);
		m.remove("a",2);
		m.remove("a",10);
		assertEquals(0 ,m.count("a"));
		m.remove("hello",14);
		assertEquals(1 ,m.count("hello"));
		assertEquals(13 ,m.size());
	}
	
}
