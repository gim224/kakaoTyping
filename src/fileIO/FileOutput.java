package fileIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class FileOutput {
	public RandomAccessFile raf;
	public FileOutput(String name, String string) {
		try {
			raf = new RandomAccessFile(name, "rw");
			raf.seek(raf.length());
			raf.write((string+"@").getBytes());
		} catch (IOException e) {
		}
		try {
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FileOutput(String name) {
		try {
			raf = new RandomAccessFile(name, "rw");
			raf.seek(raf.length());
		} catch (IOException e) {
		}
	}
}