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
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class Calculator1 {
	public static void main(String[] args) throws IOException {

		// ①入力を受け取る
		InputStream inputStream = System.in;
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		InputStream FileInputStream = new FileInputStream(new File("fileIn"));
		InputStreamReader FileInputStreamReader = new InputStreamReader(
				FileInputStream);
		BufferedReader FileBufferedReader = new BufferedReader(
				FileInputStreamReader);

		PrintStream printStream = System.out;

		OutputStream FileOutputStream = new FileOutputStream(
				new File("fileOut"));

		String input = bufferedReader.readLine();
		String inputFile = FileBufferedReader.readLine();

		// ③加工したものを出力する

		while (input != null) {
			printStream.println(process(split(input), classify(input)));
			input = bufferedReader.readLine();
		}
		System.exit(0);

	}// main終わり

	public static EnumSet<CalcOption> classify(String in){
		
		List<String> argArray = Arrays.asList();
		EnumSet<CalcOption> calcOption = EnumSet.noneOf(CalcOption.class);
		while (in =="exit") {
			calcOption.add(CalcOption.EXIT);
		}
		while () {
			calcOption.add(CalcOption.);
		}
		return calcOption;
	}

	/*
	 * 1.入力が数式として解釈できる→(「数字の数が演算子の数より１多い」)かつ(演算子と数式の順番が正しい) 2.入力が数字、演算子→計算できる 3.
	 * 順番が正しい条件 /* ・最初２つn,最後o,間は？ ・（（数字集合）＝（演算子集合）＋１）その後（数字集合）＝（演算子集合）？
	 */

	// 入力を１文字ずつに分解して配列にする
	public static String[] split(String regex) {
		String[] s = regex.split("");
		return s;
	}

	// ②入力を加工する（計算結果を返す）
	public static String process(String[] array , EnumSet<CalcOption> options) {
		
		 
		// （１）入力が数式として解釈できない場合や計算できない式の 場合エラーの旨を出力す
			while () {
				return "エラー（逆ポーランド記法の数式を入力してください。）";
			}
			//（２） 入力が数式として解釈できない場合
			while () {
				return "エラー（数字か演算子（+,-,*,/,）を入力してください）";
			}

			// （３）入力がexitの場合
			while () {
				System.exit(0);
			}

			//（４）入力が計算できる場合
	while() {
		
		int val1, val2;
		int ans = 0;
		
		 int numOfFig = 0;
		 int numOfOpe = 0;
				
		// スタックを作る（１行分(readline分)の長さのスタック）
		stack stackObject = new stack(array.length);

		for (int i = 0; i < array.length; i++) {
			
			if(isInteger(array[i])){
				numOfFig ++;
			}
			if(array[i].equals("+")||array[i].equals("-")||array[i].equals("*")||array[i].equals("/")){
				numOfOpe ++;
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
		}
		ans = stackObject.pop();
		return Integer.toString(ans);
	}
	}//process終了

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
