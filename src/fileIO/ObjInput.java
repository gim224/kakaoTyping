package fileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.Vector;

import kakaoTyping.User;

public class ObjInput {
	private FileInputStream fis;
	private ObjectInputStream ois;
	private Vector<User> user = new Vector<User>();

	@SuppressWarnings("unchecked")
	public ObjInput() {

		try {
			fis = new FileInputStream("txt/user.dat");
			ois = new ObjectInputStream(fis);
			
			user = (Vector<User>) ois.readObject();
			
			ois.close();
			
			fis.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Vector<User> getUserVector() {
		return user;
	}
}
