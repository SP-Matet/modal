package GestionnaireMort;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

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
			RelationsPubliques.BroadcastAll.broadcastTCP(Message.IS_DEAD + " " + m.ipAdresse + " " + m.port + " #", Donnees.getAllServeurs());
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
			clientSocket.write(Utilitaires.stringToBuffer(Message.VERIFY_DEATH));
			clientSocket.socket().setSoTimeout(Global.DEATH_TIMEOUT);
			if (clientSocket.read(ByteBuffer.allocateDirect(1000)) > 0)
				mort = false;
			clientSocket.close();
		} catch (IOException e) {
			// Il est mort.
		}
		return mort;
	}
}