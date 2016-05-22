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

	/** fileName : 상대경로로 파일이름 */

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
		} catch (@SuppressWarnings("hiding") IOException e2) {
			e2.printStackTrace();
		} finally {
			sc.close();			
		}
	}

	/**
	 * 
	 * fileName: 상대경로로 파일이름 seperate: Scanner 의 구분자를 삽입
	 * 
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
		} catch (@SuppressWarnings("hiding") IOException e2) {
			e2.printStackTrace();
		} finally {
			sc.close();
		}
	}

	/** vector의 사이즈를 리턴 */
	public int size() {
		return m_vector.size();
	}

	/** vector의 요소를 인덱스로 리턴 */
	public String getOneWord(int index) {		
		return m_vector.get(index);

	}

	/** vector의 요소를 랜덤으로 리턴 */
	public String getOneWordRandom() {
		int random = (int) (Math.random() * (m_vector.size()));
		return m_vector.get(random);
	}

	public Vector<String> getVector() {
		return m_vector;
	}

}
