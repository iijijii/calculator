package Calc;

/*逆ポーランド記法電卓
 *  1. プログラムが起動すると入力待ちになる 
 2. 逆ポーランド記法の数式を受け取り、計算結果を出力する。入力が数式として解釈できない場合や計算できない式の場合エラーの旨を出力する。 
 3. 入力が数式ではなくexitだった場合、あるいは入力が終了した場合、プログラムを終了する
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class Calculator1 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in; // 標準入力
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // 標準入力を読み込む
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // 文字列取得

		InputStream FileInputStream = new FileInputStream(new File("fileIn")); // fileInというファイルからデータ読み取り
		InputStreamReader FileInputStreamReader = new InputStreamReader(
				FileInputStream); // ファイル入力を読み込む
		BufferedReader FileBufferedReader = new BufferedReader(
				FileInputStreamReader); // ファイルから文字列取得

		PrintStream printStream = System.out; // 標準出力

		OutputStream FileOutputStream = new FileOutputStream(
				new File("fileOut"));// fileOutファイルに出力

		String input = bufferedReader.readLine();// 入力を１行まとめて読み込む
		String inputFile = FileBufferedReader.readLine();// ファイル入力を１行まとめて読み込む

		/*
		 * 2. 逆ポーランド記法の数式を受け取り、計算結果を出力する。 入力が数式として解釈できない場合や計算できない式の
		 * 場合エラーの旨を出力する。
		 */
		int val1, val2;
		int ans = 0;

		// スタックを作る（コマンドライン引数の長さのスタック）
		stack stackObject = new stack(args.length);

		for (int i = 0; i < args.length; i++) {
			// 入力が整数の場合(→文字列を数字にしてpush
			if (isInteger(args[i])) {
				stackObject.push(Integer.parseInt(args[i]));
			}
			// 入力が演算子の場合(→数字をpopして計算結果をpush
			else if (args[i].equals("+")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				stackObject.push(ans = val1 + val2);
			} else if (args[i].equals("-")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				stackObject.push(ans = val1 - val2);
			} else if (args[i].equals("*")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				stackObject.push(ans = val1 * val2);
			} else if (args[i].equals("/")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				if (val1 == 0) {
					printStream.println("エラー（０で割れません）");
				}
				stackObject.push(ans = val1 / val2);
			}
			// 数字でも演算子でもない場合
			else {
				printStream.println("エラー（数字か演算子（+,-,*,/）を入力してください）");
			}
			printStream.print(ans);
		}
	}

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
