package game;

/**
 * Final delivery entry point:
 * board B (multiple straight paths) + interactive choices.
 */
public class TowerDefenseBInteractive {
    public static void main(String[] args) throws Exception {
        Livrable6.run(
                "game.TowerDefenseBInteractive",
                Livrable6.BoardMode.B,
                Livrable6.ChoiceMode.INTERACTIVE,
                args);
    }
}
