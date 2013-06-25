/**
 * @(#) Command.java
 */

package command;

import receiver.Buffer;
import invoker.IHM;

public abstract class Command
{
	protected static final boolean VERBOSE = false;
	
	protected Buffer buffer;
	protected IHM ihm;
	
	public abstract void execute();
	
	
}
