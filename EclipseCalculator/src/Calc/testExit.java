package Calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class testExit {

	@Test
	public void test() {
		String[] testval = { "e", "x", "i", "t" };
		String actual = Calculator1.process(testval);
		assertEquals("入力終了", actual);
	}

}
