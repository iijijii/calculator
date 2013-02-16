package Calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class Calculator1Test {

	@Test
	public void testProcess() {
		String actual = Calculator1.process(Calculator1.split("100 24 +"));
		assertEquals("124", actual);
	}

}
