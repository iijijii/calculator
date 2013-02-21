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
import java.util.ArrayList;
import java.util.List;

public class Calculator1 {
	public static void main(String[] args) throws IOException {

		// ①入力を受け取る

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));

		PrintStream printStream = System.out;
		printStream.println("逆ポーランド記法の数式（値と値の間はスペースで区切る）を入力してください");

		String input = bufferedReader.readLine();

		// ③加工したものを出力する

		while (input != null) {
			if (input == "exit") {
				printStream.println("入力終了");
				break;
			}
			printStream.println(process(split(input)));
			input = bufferedReader.readLine();
		}
		System.exit(0);
	}// main終わり

	// 入力を値ごとに分解して配列を作り直す
	public static String[] split(String regex) {

		String[] s = regex.split("");

		int point = 0;// ずらした数
		for (int i = 0; i < s.length; i++) {
			// (1)文字が数字続きなら一つのインデックスにいれて、(２)左にずらし、(３)最後に空文字

			while (isInteger(s[i]) && isInteger(s[i + 1])) {
				s[i] = s[i] + s[i + 1]; // (1
				System.arraycopy(s, i + 2, s, i + 1, s.length - (i + 2));// (2
				point++;
				s[s.length - (point)] = ""; // 3
			}
		}
		return s;
	}

	// ②入力を加工する（計算結果を返す）
	public static String process(String[] array) {

		int val1, val2;
		int ans = 0;

		List<Integer> stackObject = new ArrayList<Integer>();

		for (int i = 0; i < array.length; i++) {
			// 入力が整数の場合(→文字を数字にしてpush
			if (isInteger(array[i])) {
				stackObject.add(Integer.parseInt(array[i]));
			}
			// 入力が演算子の場合(→数字をpopして計算結果をpush
			// popする前にstackObject.getLength>2を確認、否ならreturn"エラー（逆ポーランド記法の数式を入力してください。）"
			else if (array[i].equals("+") || array[i].equals("-")
					|| array[i].equals("*") || array[i].equals("/")) {
				int size = stackObject.size();
				if (size >= 2) {
					val1 = stackObject.get(size - 1);
					val2 = stackObject.get(size - 2);
					stackObject.remove(size - 1);
					stackObject.remove(size - 2);

					if (array[i].equals("+")) {
						stackObject.add(val2 + val1);
					} else if (array[i].equals("-")) {
						stackObject.add(val2 - val1);
					} else if (array[i].equals("*")) {
						stackObject.add(val2 * val1);
					} else if (array[i].equals("/")) {
						if (val1 == 0) {
							return "エラー（０で割れません）";
						} else {
							stackObject.add(val2 / val1);
						}
					}
				} else {
					return "エラー（逆ポーランド記法の数式を入力してください。）";
				}
			}

			// 入力がスペース
			else if (array[i].equals(" ") || array[i].equals("")) {
			}
			// 入力が数字でも演算子でもexitでもスペースでもない場合
			else {
				return "エラー（数字か演算子（+,-,*,/）かexitを入力してください）";
			}
		}
		ans = stackObject.get(0);
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
