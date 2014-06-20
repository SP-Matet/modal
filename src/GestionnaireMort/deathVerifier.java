package GestionnaireMort;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

import Stockage.Donnees;
import Utilitaires.*;

public class deathVerifier implements Runnable {
	Stockage.Machine m;

	public deathVerifier (Stockage.Machine m) {
		this.m = m;
	}

	public void run () {
		Boolean mort = verifyDeath(m);
		if (mort) {
			broadcastDeath(m);
		}
	}
	
	// Vérifie la mort et renvoie oui ou non
	public static boolean verifyDeath (Stockage.Machine m) {
		Boolean mort = true;
		try (SocketChannel clientSocket = SocketChannel.open()) { 
			InetSocketAddress local = new InetSocketAddress(0); 
			clientSocket.bind(local); 
			InetSocketAddress remote = new InetSocketAddress(m.ipAdresse, m.port); 
			clientSocket.connect(remote);
			clientSocket.write(Utilitaires.stringToBuffer(Global.VERIFY_DEATH));
			clientSocket.socket().setSoTimeout(Global.DEATH_TIMEOUT);
			if (clientSocket.read(ByteBuffer.allocateDirect(1000)) > 0)
				mort = false;
		} catch (IOException e) {
			// Il est bel et bien mort (ou je suis déconnecté et il faut relancer l'appli)
		}
		return mort;
	}
	
	public static void broadcastDeath(Stockage.Machine m) {
		LinkedList<Stockage.Machine> buff = Donnees.getAllServeurs();
		for (Stockage.Machine dest : buff) {
			
		}
	}
}