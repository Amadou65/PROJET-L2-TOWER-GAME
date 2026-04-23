package game;

/**
 * Final delivery entry point:
 * board A (left-start random path) + random choices.
 */
public class TowerDefenseARandom {
    public static void main(String[] args) throws Exception {
        Livrable6.run(
                "game.TowerDefenseARandom",
                Livrable6.BoardMode.A,
                Livrable6.ChoiceMode.RANDOM,
                args);
    }
}
