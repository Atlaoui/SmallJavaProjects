package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pobj.util.InvalidCountException;
import pobj.util.InvalidMultiSetFormat;

public class MultiSetParser {

	public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat, InvalidCountException{
		MultiSet<String> multiSet = new HashMultiSet<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			String [] mor;
			while ((line = br.readLine())!=null) {
				mor =line.split(":");
				multiSet.add(mor[0], Integer.decode((mor[1].trim())));
			}
			br.close();
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new InvalidCountException(e.toString());
		}catch(ArrayIndexOutOfBoundsException e){
			throw new InvalidMultiSetFormat("Format pouris");
		}
			
		
		return multiSet;
		
		
	}
}
