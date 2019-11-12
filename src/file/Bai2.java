package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * bai2
 */
public class Bai2 {

	public static Map<Integer, score> mapScore = new HashMap<Integer, score>();
	private static Scanner scan;

	public static void main(String[] args) {
		String textToWrite = "";
		List<Integer> maxPos = new ArrayList<>();
		try {
			File in = new File("input1.txt");
			scan = new Scanner(in);
			int n;
			n = scan.nextInt();
			System.out.println("\nn = " + n);
			while (n != 0) {
				mapScore.clear();
				for (int i = 1; i <= n; i++) {
					int num = 0;
					num = scan.nextInt();
					System.out.println("CHOICES = " + num);
					score sc = new score();
					for (int j = 1; j <= num; j++) {
						int tempNum = scan.nextInt();
						if (checkExist(tempNum)) {
							score tempScore = Bai2.mapScore.get(tempNum);
							tempScore.update(j);
							Bai2.mapScore.replace(tempNum, tempScore);
							System.out.println("NUM = " + tempNum + " is exist.");
						} else {
							System.out.println("NUM = " + tempNum + " is not exist.");
							score tempScore = new score();
							tempScore.update(j);
							Bai2.mapScore.put(tempNum, tempScore);
						}
						System.out.println("NUM[" + i + "][" + j + "]= " + tempNum);
					}
				}
				score scoreMax = new score();
				for (Map.Entry<Integer, score> entry : Bai2.mapScore.entrySet()) {
					System.out.println("INDEX = " + entry.getKey() + " ; SCORE = " + entry.getValue().getScore()
							+ " ; FIRST = " + entry.getValue().getFirst() + " ; SECOND = "
							+ entry.getValue().getSecond() + " ; THIRD = " + entry.getValue().getThird());
					if (checkMax(scoreMax, entry.getValue()) == -1) {
						scoreMax = entry.getValue();
					}
				}
				for (Map.Entry<Integer, score> entry : Bai2.mapScore.entrySet()) {
					if (checkMax(scoreMax, entry.getValue()) == 0) {
						maxPos.add(entry.getKey());
					}
				}
				System.out.println("POSITION MAX:");
				//// The text that will write to output.txt
				for (Integer pos : maxPos) {
					if (pos != maxPos.get(maxPos.size() - 1)) {
						textToWrite += pos + " ";
					} else {
						textToWrite += pos + "\n";
					}
					System.out.print(pos + "  ");
				}
				////
				maxPos.clear();
				n = scan.nextInt();
				System.out.println("\nn = " + n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//// Write to file
		System.out.println("TEXT WILL WRITE:\n" + textToWrite);
		try {
			File out = new File("output1.txt");
			FileWriter fw = new FileWriter(out);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(textToWrite);
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkExist(int pos) {
		try {
			score temp = Bai2.mapScore.get(pos);
			System.out.println("POS = " + pos + " = " + temp.getScore());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static int checkMax(score sc1, score sc2) {
		if (sc1.getScore() > sc2.getScore()) {
			return 1;
		} else if (sc1.getScore() < sc2.getScore()) {
			return -1;
		} else if (sc1.getFirst() > sc2.getFirst()) {
			return 1;
		} else if (sc1.getFirst() < sc2.getFirst()) {
			return -1;
		} else if (sc1.getSecond() > sc2.getSecond()) {
			return 1;
		} else if (sc1.getSecond() < sc2.getSecond()) {
			return -1;
		} else if (sc1.getThird() > sc2.getThird()) {
			return 1;
		} else if (sc1.getThird() < sc2.getThird()) {
			return -1;
		} else
			return 0;
	}
}

/**
 * Innerbai2
 */
class score {
	private int first, second, third, score;

	public score() {
		this.first = 0;
		this.second = 0;
		this.third = 0;
		this.score = 0;
	}

	public void update(int pos) {
		if (pos == 1) {
			this.first++;
		} else if (pos == 2) {
			this.second++;
		} else {
			this.third++;
		}
		this.score = this.first * 3 + this.second * 2 + this.third;
	}

	public int getScore() {
		return this.score;
	}

	public int getFirst() {
		return this.first;
	}

	public int getSecond() {
		return this.second;
	}

	public int getThird() {
		return this.third;
	}
}