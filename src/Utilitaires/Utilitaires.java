package Utilitaires;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Utilitaires {
	/**
	 * 
	 * @param s
	 *            string to convert
	 * @return FLIPPED buffer
	 */
	public static ByteBuffer stringToBuffer(String s) {
		ByteBuffer buff = ByteBuffer.allocateDirect(Global.BUFFER_LENGTH);
		for (char c : s.toCharArray()) {
			buff.putChar(c);
		}
		buff.flip();
		return buff;
	}

	/**
	 * 
	 * @param b     buffer to convert (already flipped !)
	 * @return resulting string 
	 * Le buffer est flippe
	 */
	public static String buffToString(ByteBuffer b) {

		String s = new String();

		while (b.hasRemaining())
			s += b.getChar();
		b.flip();
		return s;
	}

	public static ByteBuffer stringToBuffer(int id) {
		String s = Integer.valueOf(id).toString();
		return stringToBuffer(s);
	}

	public static String getAFullMessage(String finalWord, SocketChannel s) throws IOException {
		String[] arg = new String[1];
		arg[0] = finalWord;
		return getAFullMessage(arg, s);

	}

	public static String getAFullMessage(String[] finalWords, SocketChannel s) throws IOException {
		ByteBuffer b = ByteBuffer.allocateDirect(Global.BUFFER_LENGTH);
		String retour = "";
		String m;
		String token;
		int i = 0;
		boolean continuer = true;
		while (continuer && i < Global.BUFFER_LENGTH) {
			
			if (s.read(b) == -1)
			{ 
				Utilitaires.out("Fréquence 2",2,true);
				continuer = false;
			}
			if (s.socket().isClosed()) {
				Utilitaires.out("Socket fermée !");
				throw new IOException ();
			}
			i++;
			//Utilitaires.out("Test 31",6,true);
			//Utilitaires.out(finalWords[0],6,true);
			//Utilitaires.out("Test 31",6,true);
			//s.read(b);
			b.flip();
			m = buffToString(b);

			retour += m;
			b.clear();

			Scanner sc = new Scanner(m);
			while (sc.hasNext() && continuer) {
				token = sc.next();
				for (String w : finalWords) {
					if (token.equals(w)) {
						continuer = false;
						// Utilitaires.out(i) ;
						break;
					}
				}
			}
			//Utilitaires.out(Boolean.valueOf(continuer).toString(),6,true);
			sc.close();
			//Utilitaires.out(Boolean.valueOf(continuer).toString(),6,true);
		}
		
		return retour;
	}
	
	public static void out(String s){
		System.out.println("Serveur "+ Global.TCP_PORT+ " : " + s);
	}
	
	public static void out(String s,int color,boolean bold){
		String sortie ="";
		sortie+="\033[";
		if(bold)
			sortie+="1;";
		switch(color){
			case 0:
				sortie+="30";
				break;
			case 1:
				sortie+="31";
				break;
			case 2:
				sortie+="32";
				break;
			case 3:
				sortie+="33";
				break;
			case 4:
				sortie+="34";
				break;
			case 5:
				sortie+="35";
				break;
			case 6:
				sortie+="36";
				break;
			case 7:
				sortie+="37";
				break;
				
				
		}
		sortie+="mServeur "+ Global.TCP_PORT+ " : " + s +"\033[0m\n";
		
			
		try {
			Thread.sleep((int) Math.abs(Math.random()*20));
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(sortie);
	}
	
	public void outDem (String s, int color, boolean bold) {
		String sortie ="";
		sortie+="\033[";
		if(bold)
			sortie+="1;";
		switch(color){
			case 0:
				sortie+="30";
				break;
			case 1:
				sortie+="31";
				break;
			case 2:
				sortie+="32";
				break;
			case 3:
				sortie+="33";
				break;
			case 4:
				sortie+="34";
				break;
			case 5:
				sortie+="35";
				break;
			case 6:
				sortie+="36";
				break;
			case 7:
				sortie+="37";
				break;
				
				
		}
		sortie+="mServeur "+ Global.TCP_PORT+ " : " + s +"\033[0m\n";
		
			
		try {
			Thread.sleep((int) Math.abs(Math.random()*20));
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(sortie);
	}
}
