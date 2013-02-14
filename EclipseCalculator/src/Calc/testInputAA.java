package Calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class testInputAA {

	@Test
	public void test() {
		String[] testval = { "A", "A" };
		String actual = Calculator1.process(testval);
		assertEquals("エラー（数字か演算子（+,-,*,/）かexitを入力してください）", actual);
	}

}
