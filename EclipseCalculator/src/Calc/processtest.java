package Calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class processtest {

	@Test
	public void testProcess() {
		String[] testval = { "2", "10", "+", "3", "*" };
		String actual = Calculator1.process(testval);
		assertEquals("36", actual);
	}

}
