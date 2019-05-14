import java.io.File;
import java.util.Scanner;

public class Hacking {
	public static void mian(String[] args) throws Exception {
		File file = new File(".");
		String[] dirList = file.list();
		
		for (String fileName : dirList) {
			System.out.println(fileName);
		}
		
		Scanner scan = new Scanner(System.in);
		scan.next(); // 프로그램 일시정지됨.
	}

}
