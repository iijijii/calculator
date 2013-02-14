package Calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class test11plus {

	@Test
	public void test() {
		String[] testval = { "1", "1", "+" };
		String actual = Calculator1.process(testval);
		assertEquals("2", actual);
	}

}
