package Calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class test0Division {

	@Test
	public void test() {
		String[] testval = { "5", "0", "/" };
		String actual = Calculator1.process(testval);
		assertEquals("エラー（０で割れません）", actual);
	}

}
