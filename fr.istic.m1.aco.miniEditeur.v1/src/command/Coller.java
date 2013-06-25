/**
 * @(#) Coller.java
 */

package command;

import receiver.Buffer;

/**
 * La commande qui exécute l'action coller() du buffer.
 */
public class Coller extends Command {

	public Coller(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void execute() {
		buffer.coller();
	}

}
