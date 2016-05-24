package fileIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

import kakaoTyping.User;

public class ObjOutput {
	private FileOutputStream fos;
	private ObjectOutputStream oos;

	public ObjOutput(Vector<User> wantSave) {

		try {
			fos = new FileOutputStream("txt/user.txt");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(wantSave);
			fos.flush();
			oos.flush();
			oos.close();
			fos.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
