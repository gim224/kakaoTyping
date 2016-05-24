package ThirdScene;

import java.io.FileWriter;
import java.io.IOException;

import fileIO.FileInput;

public class WordOutputDel {
	String str = "";

	public WordOutputDel(String s, String seperate) {
		FileInput input = new FileInput("txt/word.txt", seperate);

		for (int i = 0; i < input.size(); i++) {
			if (input.getOneWord(i).equals(s)) {
				input.remove(i);
				i--;
				continue;
			}
			str += input.getOneWord(i) + seperate;
			// System.out.println(input.getOneWord(i));
		}

		try {
			FileWriter m_writer = new FileWriter("txt/word.txt");
			m_writer.write(str);
			m_writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
