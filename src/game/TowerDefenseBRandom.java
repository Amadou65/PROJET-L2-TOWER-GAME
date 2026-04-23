package game;

/**
 * Final delivery entry point:
 * board B (multiple straight paths) + random choices.
 */
public class TowerDefenseBRandom {
    public static void main(String[] args) throws Exception {
        Livrable6.run(
                "game.TowerDefenseBRandom",
                Livrable6.BoardMode.B,
                Livrable6.ChoiceMode.RANDOM,
                args);
    }
}
