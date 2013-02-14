package Calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class Calculator1Test {

	String[] testval = Calculator1.split("1 1 +");

	@Test
	public void testProcess() {
		String actual = Calculator1.process(testval);
		assertEquals("2", actual);
	}

}
