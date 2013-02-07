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

		// TODO
		// if,else ifの順番直す（大枠把握）（try catch使用検討
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
				stackObject.push(val2 + val1);
			} else if (args[i].equals("-")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				stackObject.push(val2 - val1);
			} else if (args[i].equals("*")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				stackObject.push(val2 * val1);
			} else if (args[i].equals("/")) {
				val1 = stackObject.pop();
				val2 = stackObject.pop();
				if (val1 == 0) {
					printStream.println("エラー（０で割れません）");
					break;// 入力待ちに戻る
				} else
					stackObject.push(val2 / val1);
			}

			// 入力がexitの場合
			else if (args[i].equals("exit")) {
				System.exit(0);
			}
			// TODO
			// 数式として読めない場合（→(!=「数字の数が演算子の数より１多い」)&&(？演算子と数式の順番？)
			else if (numOfFig() != numOfOpe() + 1 || order() == false) {
				printStream.println("エラー（逆ポーランド記法の数式を入力してください）");
				break;
			}

			// 入力が数字でも演算子でもexitでもない場合
			else {
				printStream.println("エラー（数字か演算子（+,-,*,/,）を入力してください）");
				break;// 入力待ちに戻る
			}
			// 入力が終了した場合
			ans = stackObject.pop();
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

	// 数字の数
	public static int numOfFig() {

	}

	// 演算子の数
	public static int numOfOpe() {

	}

	// 順番が正しい条件
	/*
	 * ・最初２つn,最後o,間は？ ・（（数字集合）＝（演算子集合）＋１）その後（数字集合）＝（演算子集合）？
	 */
	public boolean order() {

	}
}
