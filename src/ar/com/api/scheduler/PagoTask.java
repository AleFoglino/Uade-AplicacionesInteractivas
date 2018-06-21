/**
 * 
 */
package ar.com.api.scheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Alejandro Foglino
 *
 */
public class PagoTask {

	Timer timer;
	int count;

	/**
	 * 
	 */
	public PagoTask() {
		// TODO Auto-generated constructor stub
	}

	public void IniciarTemporizador() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("Cantidad de segundos :" + new Date().getSeconds());
				count++;
				stop();
			}
		}, 2, 6000);
	}

	public void stop() {
		if (count == 10) {
			if (timer != null) {
				timer.cancel();
			}
		}
	}

	public static void main(String[] arg) {
		PagoTask pagoTask = new PagoTask();
		pagoTask.IniciarTemporizador();

	}
}
