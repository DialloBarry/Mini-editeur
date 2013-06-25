/**
 * @(#) Observer.java
 */

package observer;

/**
 * Interface Observer du patron de conception du même nom. 
 */
public interface Observer
{
	/**
	 * Méthode appellée par le <i>subject</i> pour mettre à jour l'<i>observer</i>.
	 */
	void notifyMe();
	
	
}
