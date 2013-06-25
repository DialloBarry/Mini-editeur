package command;

import receiver.Buffer;

/**
 * La commande qui supprime un caractère du buffer.
 */
public class Supprimer extends Command {

	public Supprimer(Buffer buf) {
		buffer = buf;
	}

	@Override
	public void execute() {
		buffer.supprimer();
	}

}
