/*逆ポーランド記法電卓
 *  1. プログラムが起動すると入力待ちになる 
   2. 逆ポーランド記法の数式を受け取り、計算結果を出力する。入力が数式として解釈できない場合や計算できない式の場合エラーの旨を出力する。 
   3. 入力が数式ではなくexitだった場合、あるいは入力が終了した場合、プログラムを終了する
 * */

import java.io.*;

import org.apache.log4j.Logger;

public class Calculator1 {private static final Logger logger =
java.util.logging.Logger.getLogger(Calculator1.class);

public static void main(String[]) args throws IOException{
InputStream inputStream = System.in; //標準入力
InputStreamReader inputStreamReader =
	new InputStreamReader (inputStream); //標準入力を読み込む
BufferedReader bufferedReader =
	new BufferedReader (inputStreamReader); //文字列取得

InputStream FileInputStream = new FileInputStream(new File("fileIn")); //fileInというファイルからデータ読み取り
InputStreamReader FileInputStreamReader =
	new InputStreamReader (FileInputStream); //ファイル入力を読み込む
BufferedReader FileBufferedReader =
	new BufferedReader (FileInputStreamReader); //ファイルから文字列取得

PrintStream printStream = System.out; //？？標準出力？？

OutputStream FileOutputStream = new FileOutputStream(new File("fileOut"));//fileOutファイルに出力



}
