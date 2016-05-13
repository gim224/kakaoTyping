package kakaoTyping;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class FileIO {
	public static void main(String[] args) throws FileNotFoundException {
		// File file = new File("txt/text.txt"); // IOException &
		// FileNotFoundException
		File file = new File(args[0]);	//args[0] -> txt/text.txt
		Scanner sc = new Scanner(file);
		// while(sc.hasNext()){
		// String first = sc.next();
		// System.out.println(first);
		// }
		Vector<String> m_vector = new Vector<String>();
		
		while (sc.hasNext()) {
			m_vector.add(sc.next());
		}

		for (int i = 0; i < m_vector.size(); i++) {
			System.out.println(m_vector.get(i));
		}

		// try{
		// FileReader m_reader = new FileReader("txt/text.txt");
		// int c;
		// while(true){
		// c = m_reader.read();
		// if(c== -1)
		// break;
		// System.out.print(String.valueOf((char)c));
		// }
		//
		// m_reader.close();
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}
