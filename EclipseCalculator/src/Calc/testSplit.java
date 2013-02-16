package Calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class testSplit {

	@Test
	public void testSplit() {
		String testval = "1 1 +";
		String[] actual = Calculator1.split(testval);
		String[] expected = { "", "1", " ", "1", " ", "+" };
		assertArrayEquals(expected, actual);
	}

}
