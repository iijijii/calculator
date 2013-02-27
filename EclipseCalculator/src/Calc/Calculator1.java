package Calc;

/*逆ポーランド記法電卓
 *  1. プログラムが起動すると入力待ちになる 
 2. 逆ポーランド記法の数式を受け取り、計算結果を出力する。入力が数式として解釈できない場合や計算できない式の場合エラーの旨を出力する。 
 3. 入力が数式ではなくexitだった場合、あるいは入力が終了した場合、プログラムを終了する
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Calculator1 {
	public static void main(String[] args) throws IOException {

		// ①入力を受け取る

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));

		PrintStream printStream = System.out;
		printStream.println("逆ポーランド記法の数式（値と値の間はスペースで区切る）を入力してください");

		// ③加工したものを出力する
		while (true) {
			String input = bufferedReader.readLine();
			if (input.equals("exit") || input == null) {
				printStream.println("入力終了");
				break;
			}
			printStream.println(process(parse(input)));
		}
		System.exit(0);
	}// main終わり

	public static String[] parse(String formula) {

		String[] s = formula.split("");
		List<String> tokens = new ArrayList<String>();
		int tSize = tokens.size();
		for (int i = 0; i < s.length; i++) {
			if (isInteger(s[i]) && isInteger(s[i - 1])) {
				tokens.set(tSize, tokens.get(tSize) + s[i]);
			} else if (isInteger(s[i]) || s[i].equals("+") || s[i].equals("-")
					|| s[i].equals("*") || s[i].equals("/")) {
				tokens.add(s[i]);
			} else if (s[i].equals(" ") || s[i].equals("")) {// 処理しない
			} else {// 入力が数字でも演算子でもexitでもスペースでもない場合
				String[] err = new String[1];
				err[0] = "e";
				return err;
			}
		}
		return tokens.toArray(new String[tokens.size()]);
	}

	// ②入力を加工する（計算結果を返す）
	public static String process(String[] array) {

		// 入力が数字でも演算子でもexitでもスペースでもない場合
		if (array[0].equals("e")) {
			return "エラー（数字か演算子（+,-,*,/）かexitを入力してください）";
		}

		int val1, val2;
		int ans = 0;
		Deque<Integer> stack = new ArrayDeque<Integer>();

		for (int i = 0; i < array.length; i++) {
			// 入力が整数の場合(→文字を数字にしてpush
			if (isInteger(array[i])) {
				stack.addFirst(Integer.parseInt(array[i]));
			}
			// 入力が演算子の場合(→数字をpopして計算結果をpush
			// popする前にstackObject.getLength>2を確認、否ならreturn"エラー（逆ポーランド記法の数式を入力してください。）"
			else if (array[i].equals("+") || array[i].equals("-")
					|| array[i].equals("*") || array[i].equals("/")) {

				int sSize = stack.size();
				if (sSize >= 2) {
					val1 = stack.removeFirst();
					val2 = stack.removeFirst();

					if (array[i].equals("+")) {
						stack.addFirst(val2 + val1);
					} else if (array[i].equals("-")) {
						stack.addFirst(val2 - val1);
					} else if (array[i].equals("*")) {
						stack.addFirst(val2 * val1);
					} else if (array[i].equals("/")) {
						if (val1 == 0) {
							return "エラー（０で割れません）";
						} else {
							stack.addFirst(val2 / val1);
						}
					}
				} else {
					return "エラー（逆ポーランド記法の数式を入力してください。）";
				}
			}
		}

		ans = stack.peekFirst();
		return Integer.toString(ans);
	}// process終了

	// 数字かどうか
	public static boolean isInteger(String num) {

		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
