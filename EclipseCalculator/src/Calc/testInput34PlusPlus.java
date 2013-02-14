package Calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class testInput34PlusPlus {

	@Test
	public void test() {
		String[] testval = { "3", "4", "+", "+" };
		String actual = Calculator1.process(testval);
		assertEquals("エラー（逆ポーランド記法の数式を入力してください。）", actual);
	}

}
