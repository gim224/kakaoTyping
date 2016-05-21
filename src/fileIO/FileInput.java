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

	/** fileName : ������ ����� */
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
		}
	}

	/**
	 * fileName: ������ ����� seperate: Scanner ���д���
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
		}
	}

	/** vector�� ����� ���� */
	public int size() {
		return m_vector.size();
	}

	/** vector�� �ִ� �ܾ �ε����� ���� */
	public String getOneWord(int index) {
		return m_vector.get(index);
	}

	/** vector�� �ִ� �ܾ �������� ���� */
	public String getOneWordRandom() {
		int random = (int) (Math.random() * (m_vector.size()));
		return m_vector.get(random);
	}

	/** vector ���۷����� ���� */
	public Vector<String> getVector() {
		return m_vector;
	}

}
