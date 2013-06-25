/**
 * @(#) Copier.java
 */

package command;

import receiver.Buffer;

/**
 * La commande qui exécute l'action copier() du buffer.
 */
public class Copier extends Command {

	public Copier(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void execute() {
		buffer.copier();
	}

}
