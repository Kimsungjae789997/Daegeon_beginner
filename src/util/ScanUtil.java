package util;

import java.util.Scanner;

public class ScanUtil   {
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음 // static 객체 안만들어도 불러올 수 있음
	// 추후 나중에 double 등 넣고싶으면 수정 가능
	static Scanner sc = new Scanner(System.in);
	
	public static int menu() {
		return nextInt("메뉴 : ");
	}
	
	public static String nextLine(String print) {
		System.out.print(print);
		return nextLine();
	}
	
	private static String nextLine() {
		return sc.nextLine();
	}
	
	public static int nextInt(String print) {
		// 출력문구 강제로 넣도록 만듦
		System.out.print(print);
		return nextInt();
	}
	
	// 숫자 아닌 것들을 입력했을 때
	private static int nextInt() {
		while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("잘못 입력!!");
			}
		}
	}
}
