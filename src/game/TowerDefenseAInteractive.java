package game;

/**
 * Final delivery entry point:
 * board A (left-start random path) + interactive choices.
 */
public class TowerDefenseAInteractive {
    public static void main(String[] args) throws Exception {
        Livrable6.run(
                "game.TowerDefenseAInteractive",
                Livrable6.BoardMode.A,
                Livrable6.ChoiceMode.INTERACTIVE,
                args);
    }
}
