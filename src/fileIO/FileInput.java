package fileIO;

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

	/** fileName : 파일의 상대경로 */
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

	/**
	 * fileName: 파일의 상대경로 seperate: Scanner 구분단위
	 */
	public FileInput(String fileName, String seperate) {
		file = new File(fileName);
		try {
			sc = new Scanner(file);
			sc.useDelimiter(seperate);
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

	/** vector의 사이즈를 리턴 */
	public int size() {
		return m_vector.size();
	}

	/** vector에 있는 단어를 인덱스로 리턴 */
	public String getOneWord(int index) {
		return m_vector.get(index);
	}

	/** vector에 있는 단어를 랜덤으로 리턴 */
	public String getOneWordRandom() {
		int random = (int) (Math.random() * (m_vector.size() -1) + 0);
		return m_vector.get(random);
	}

	/** vector 레퍼런스를 리턴 */
	public Vector getVector() {
		return m_vector;
	}

}
