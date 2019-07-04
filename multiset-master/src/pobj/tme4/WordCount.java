
package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import pobj.util.Chrono;
import pobj.util.InvalidCountException;
import pobj.util.InvalidMultiSetFormat;

public class WordCount {
	
	public static void main(String[] args) {
		HashMultiSet<String> hms = new HashMultiSet<String>();
		MultiSetDecorator<String> deco1 = new MultiSetDecorator<String>(hms);
		System.out.println( "wordcount sur HashMultiSet : ");
		Chrono chrono = new Chrono(); 
		wordcount(deco1);
		chrono.stop();
		
		
		/*System.out.println("\n wordcount sur NaiveMultiSet : ");
		NaiveMultiSet<String> nms = new NaiveMultiSet<String>();
		MultiSetDecorator<String> deco2 = new MultiSetDecorator<String>(nms);
		
		chrono = new Chrono();
		wordcount(deco2);
		chrono.stop();*/
		
		
		
		//teste Parsage 
		
		System.out.println("Parsage du fichier :");
		MultiSet<String> m = null;
		try {
			m = MultiSetParser.parse("data/dataParaser.txt");
		} catch (InvalidMultiSetFormat | InvalidCountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(m.toString());
		
		
	}

	
	public static void wordcount(MultiSet<String> ms) {
		String file = "data/WarAndPeace.txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine())!=null) {
				for (String word : line.split("\\P{L}+")) {
					// TODO: traitement � faire pour le mot word
					if (!word.equals("") && !word.equals(" ")) {		
						ms.add(word);
					}
				}
			}

			br.close();
			Comparator<String> comparator = ms.comparator();
			
			List<String> lis=ms.elements();
			
			lis.sort(comparator);
					
			
			System.out.println(ms.toString());
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
