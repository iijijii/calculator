package Calc;

public class stack {
	int stacksize;
	int stack[];
	int point; // スタックのプッシュするとこ

	// コンストラクタ（スタックオブジェクトが呼び出された時実行）
	public stack(int size) {
		stacksize = size;
		stack = new int[stacksize];
		point = 0;
	}

	// プッシュメソッド
	public void push(int val) {
		stack[point] = val;
		point += 1;
	}

	// ポップメソッド
	public int pop() {
		point -= 1;
		return stack[point];
	}

	public int getLength() {
		return point;
	}

}
