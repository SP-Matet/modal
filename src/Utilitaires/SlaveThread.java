package Utilitaires;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SlaveThread extends Thread{
	private LinkedBlockingQueue<Runnable> tasks;	// Vérifier si l'attente sur une file vide ne prend pas trop de temps
	private ConcurrentLinkedQueue<Integer> loads;
	private int estimatedLoad;

	public void run () {
		Runnable r = null;

		while (true) {
			try {
				r = tasks.take();
				r.run();
			} catch (Exception e) {
				Utilitaires.out("Exception in SlaverThread. Task was " + r.getClass());
				e.printStackTrace();
				// We don't want to crash the slave if the runnable is wrong.
			}
			estimatedLoad -= loads.poll();
		}
	}

	public boolean doThat(Runnable r, int estimatedLoad) {
		if (tasks.add(r)) {	// return false if couldnt add task (should not happen normally)
			this.estimatedLoad += estimatedLoad;
			this.loads.add(estimatedLoad);
			return true;
		}
		return false;
	}

	public int getLoad() {
		return estimatedLoad;
	}

	public SlaveThread () {
		tasks = new LinkedBlockingQueue<Runnable>();
		loads = new ConcurrentLinkedQueue<Integer>();
		estimatedLoad = 0;
	}
}
