package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Bai1 {
	public static List<Integer> list = new ArrayList<>();
	private static Scanner scan;

	public static void main(String[] args) {
		try {
			File in = new File("input.txt");
			scan = new Scanner(in);
			int n = Integer.parseInt(scan.nextLine());
			System.out.println("n = " + n);
			for (int i = 0; i <= n; i++) {
				String str = "";
				str = scan.nextLine();
				System.out.println(str);
				getnumber(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LIST");
		Collections.sort(Bai1.list);
		for (int i : Bai1.list) {
			System.out.print(i + " - ");
		}
		try {
			File out = new File("output.txt");
			FileWriter fw = new FileWriter(out);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i : Bai1.list) {
				System.out.println(i);
				if (i != Bai1.list.get(Bai1.list.size() - 1))
					bw.write(Integer.toString(i) + "\n");
				else
					bw.write(Integer.toString(i));
			}
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getnumber(String str) {
		try {
			String parseStr = str.replaceAll("\\D+", " ");
			String numArr[] = parseStr.split(" ");
			int length = numArr.length;
			System.out.println(length);
			for (int i = 0; i < length; i++) {
				if (isNumeric(numArr[i])) {
					Bai1.list.add(Integer.parseInt(numArr[i]));
				}
			}
		} catch (Exception e) {
			System.out.print("Can't get");
		}
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
