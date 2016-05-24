package fileIO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjOutputStream extends ObjectOutputStream {

	protected MyObjOutputStream(OutputStream out) throws IOException, SecurityException {
		super(out);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void writeStreamHeader() throws IOException{
		
	}
	
}
