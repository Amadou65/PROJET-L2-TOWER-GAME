package game.choice;

public abstract class Choice<T> {

    public void displayChoice() {
		System.out.println("Running method of " + this);
	}

    public abstract T getChoice();
}
