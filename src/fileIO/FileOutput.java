package fileIO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileOutput {
	public RandomAccessFile raf;

	public FileOutput(String fileName, String word) {
		try {
			raf = new RandomAccessFile(fileName, "rw");
			raf.seek(raf.length());
			raf.write((word.trim() + "@").getBytes());
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}