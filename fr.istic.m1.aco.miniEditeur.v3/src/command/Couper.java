/**
 * @(#) Couper.java
 */

package command;

import receiver.Buffer;

/**
 * La commande qui exécute l'action couper() du buffer.
 */
public class Couper extends Command {

	public Couper(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void execute() {
		buffer.couper();
	}

}
