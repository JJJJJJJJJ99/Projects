import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import util.DoNotUseThis;

public class Part2Test {

	private DFAGenerator dfa;
	
	@Before
	public void setUp() {
		dfa = new DFAGenerator();
	}

	@Test
	public void test1() {
		String[] whitelist = {"luka", "ryan", "grady", "ge", "ben", "zach"};
		String rep = dfa.generate(new HashSet<String>(Arrays.asList(whitelist)));
		DoNotUseThis graph = new DoNotUseThis(rep);
		
		for (String s : whitelist) {
			assertTrue(graph.testString(s));
		}
		
		assertFalse(graph.testString("brionne"));
		assertFalse(graph.testString("kiki"));
		assertFalse(graph.testString("nikos"));
		assertFalse(graph.testString("nick"));
		assertFalse(graph.testString("wenbin"));
		
		
		assertTrue(graph.unreachableStates().isEmpty());
		
		
		assertEquals(16, graph.getNumStates());

	}
	
	@Test
	public void test2() {
		Random r = new Random(42);
		
		Set<String> testSet = new HashSet<String>();
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		while (testSet.size() != 100) {
			int length = r.nextInt(9) + 1;
			StringBuilder sb = new StringBuilder();
			while (sb.length() != length) {
				sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
			}
			testSet.add(sb.toString());
		}
		
		String rep = dfa.generate(testSet);
		DoNotUseThis graph = new DoNotUseThis(rep);
		
		for (String s : testSet) {
			assertTrue(graph.testString(s));
		}
		
		assertFalse(graph.testString("ab1"));

		for (String s : testSet) {
			String sub = s.substring(0, s.length() - 1);
			assertEquals(testSet.contains(sub), graph.testString(sub));
		}
		
		assertTrue(graph.unreachableStates().isEmpty());
		
		assertEquals(248, graph.getNumStates());
		
	}
	
	@Test
	public void test3() {
		String[] whitelist = {"test"};
		String rep = dfa.generate(new HashSet<String>(Arrays.asList(whitelist)));
		DoNotUseThis graph = new DoNotUseThis(rep);
				
		for (String s : whitelist) {
			assertTrue(graph.testString(s));
		}
		
		assertFalse(graph.testString(""));
		assertFalse(graph.testString("tes"));
		assertFalse(graph.testString("te"));
		assertFalse(graph.testString("test "));
		assertFalse(graph.testString("t"));
		
		assertTrue(graph.unreachableStates().isEmpty());
		
		assertEquals(5, graph.getNumStates());
		

	}
	
	@Test
	public void test4() {
		Random r = new Random(42);
		
		Set<String> testSet = new HashSet<String>();
		
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		while (testSet.size() != 50) {
			int length = r.nextInt(19) + 1;
			StringBuilder sb = new StringBuilder();
			while (sb.length() != length) {
				sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
			}
			testSet.add(sb.toString());
		}
		
		String rep = dfa.generate(testSet);
		DoNotUseThis graph = new DoNotUseThis(rep);
		
		for (String s : testSet) {
			assertTrue(graph.testString(s));
		}
		
		assertFalse(graph.testString("ab1"));

		for (String s : testSet) {
			String sub = s.substring(0, s.length() - 1);
			assertEquals(testSet.contains(sub), graph.testString(sub));
		}
		
		
		assertTrue(graph.unreachableStates().isEmpty());

	;
		
		assertEquals(465, graph.getNumStates());
		
	}
	
	@Test
	public void test5() {
		String[] whitelist = {"user1gmailcom", "user2gmailcom", "user1yahoocom", "user2hotmailcom", "someotherother.info"};
		String rep = dfa.generate(new HashSet<String>(Arrays.asList(whitelist)));
		DoNotUseThis graph = new DoNotUseThis(rep);
		
		for (String s : whitelist) {
			assertTrue(graph.testString(s));
		}
		
		assertFalse(graph.testString("brionne"));
		assertFalse(graph.testString("kiki"));
		assertFalse(graph.testString("nikos"));
		assertFalse(graph.testString("nick"));
		assertFalse(graph.testString("wenbin"));
		assertFalse(graph.testString("user3gmailcom"));

		
		
		assertTrue(graph.unreachableStates().isEmpty());
		
	
		
		assertEquals(39, graph.getNumStates());

	}
	
	@Test
	public void cyclicTest() {
		Set<String> whitelist = new HashSet<String>();
		
		whitelist.add("");
		whitelist.add("aac");
		whitelist.add("aabc");
		whitelist.add("bbc");
		whitelist.add("bac");
		whitelist.add("ccac");
		whitelist.add("ccabc");
		whitelist.add("dbc");
		whitelist.add("dac");
		DFAGenerator dfg = new DFAGenerator();
		DoNotUseThis rep = new DoNotUseThis(dfg.generate(whitelist));

		for (String s : whitelist) {
			assertTrue(rep.testString(s));
		}
		
		assertFalse(rep.testString("ccacb"));
		assertFalse(rep.testString("aacb"));
		assertFalse(rep.testString("caa"));
		assertFalse(rep.testString("cacc"));
		
		assertTrue(rep.unreachableStates().isEmpty());

		
		assertEquals(7, rep.getNumStates());

	}
}
