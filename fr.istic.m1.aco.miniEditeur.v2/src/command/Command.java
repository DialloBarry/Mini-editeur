/**
 * @(#) Command.java
 */

package command;

import receiver.Buffer;
import invoker.IHM;

public abstract class Command
{
	protected static final boolean VERBOSE = true;
	
	protected Buffer buffer;
	protected IHM ihm;
	
	public abstract void execute();
	
	
}
