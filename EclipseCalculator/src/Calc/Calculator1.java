package Calc;

/*逆ポーランド記法電卓
 *  1. プログラムが起動すると入力待ちになる 
 2. 逆ポーランド記法の数式を受け取り、計算結果を出力する。入力が数式として解釈できない場合や計算できない式の場合エラーの旨を出力する。 
 3. 入力が数式ではなくexitだった場合、あるいは入力が終了した場合、プログラムを終了する
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Calculator1 {
	public static void main(String[] args) throws IOException {

		// ①入力を受け取る
		InputStream inputStream = System.in;
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		PrintStream printStream = System.out;
		printStream.println("逆ポーランド記法の数式（値と値の間、値と演算子の間はスペースで区切る）を入力してください");

		String input = bufferedReader.readLine();

		// ③加工したものを出力する

		while (input != null) {
			printStream.println(process(split(input)));
			if (process(split(input)) == "入力終了") {
				break;
			}
			input = bufferedReader.readLine();
		}
		System.exit(0);
	}// main終わり

	// 入力を値ごとに分解して配列を作り直す
	public static String[] split(String regex) {

		String[] s = regex.split("");

		for (int i = 0; i < s.length; i++) {
			if (isInteger(s[i]) && isInteger(s[i + 1])) {
				s[i] = s[i] + s[i + 1];
				for (int j = i + 1; j < s.length - 1; j++) {
					s[j] = s[j + 1];
					s[s.length - (i + 1)] = null;
				}
			}
			if (s[i] == " ") {
				for (int j = i + 1; j < s.length; j++) {
					s[j - 1] = s[j];
					s[s.length - (i + 1)] = null;
				}
			}

		}
		return s;
	}

	// ②入力を加工する（計算結果を返す）
	public static String process(String[] array) {

		int val1, val2;
		int ans = 0;

		// スタックを作る（１行分(readline分)の長さのスタック）
		stack stackObject = new stack(array.length);

		for (int i = 0; i < array.length; i++) {
			if (array[0] == "e" && array[1] == "x" && array[2] == "i"
					&& array[3] == "t") {
				return "入力終了";
			}
			// 入力が整数の場合(→文字を数字にしてpush
			if (isInteger(array[i])) {
				stackObject.push(Integer.parseInt(array[i]));
			}
			// 入力が演算子の場合(→数字をpopして計算結果をpush
			else if (array[i].equals("+")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				stackObject.push(val2 + val1);
			} else if (array[i].equals("-")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				stackObject.push(val2 - val1);
			} else if (array[i].equals("*")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				stackObject.push(val2 * val1);
			} else if (array[i].equals("/")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				if (val1 == 0) {
					return "エラー（０で割れません）";
				} else {
					stackObject.push(val2 / val1);
				}
			}
			// 入力が数字でも演算子でもexitでもない場合
			else {
				return "エラー（数字か演算子（+,-,*,/）かexitを入力してください）";
			}
		}
		ans = stackObject.pop();
		return Integer.toString(ans);
	}// process終了

	// 数字かどうか
	public static boolean isInteger(String num) {
		try {
			int n = Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
