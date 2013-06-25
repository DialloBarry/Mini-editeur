package command;

import invoker.IHM;
import receiver.Buffer;

/**
 * La commande qui ajoute un caractère dans le buffer.
 */
public class Taper extends Command {

	public Taper(Buffer buffer, IHM ihm) {
		this.buffer = buffer;
		this.ihm = ihm;
	}

	@Override
	public void execute() {
		char c = ihm.getCar();
		buffer.taper(c);

		if (VERBOSE) {
			System.out.println("caractère tapé: " + c);
		}
	}

}
