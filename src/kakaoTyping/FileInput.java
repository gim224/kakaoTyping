package kakaoTyping;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class FileInput {
	private File file;
	private Scanner sc;
	private Vector<String> m_vector = new Vector<String>();

	public FileInput(String fileName) {
		file = new File(fileName);
		try {
			sc = new Scanner(file);
			while (sc.hasNext()) {
				m_vector.add(sc.next());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public String getOneWordRandom() {
		int random = (int) (Math.random() * (m_vector.size() + 1) + 0);
		return m_vector.get(random);
	}
	
	
	
	public int size(){
		return m_vector.size();
	}
	public String getOneWord(int index) {
		return m_vector.get(index);
	}

}
