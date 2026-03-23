/**
 * Cette classe abstraite représente une option de choix dans le jeu. Elle est générique et peut être utilisée pour différents types de choix, tels que les choix d'action ou d'évolution.
 * 
 * Chaque classe concrète qui étend cette classe doit implémenter la méthode getChoice()
 */


package game.choice;

public abstract class Choice<T> {

    /**
     * La méthode displayChoice() affiche une description de l'option de choix. 
     * Elle peut être utilisée pour fournir des informations supplémentaires sur le choix à l'utilisateur.
     */
    public void displayChoice() {
		System.out.println("Running method of " + this);
	}


    /**
     * Le methode getChoice() retourne le choix spécifique de l'option. Chaque classe concrète qui étend Choice doit implémenter cette méthode pour fournir le choix approprié.
     * @return le choix pour l'utiliser dans le jeu
     */
    public abstract T getChoice();
}
