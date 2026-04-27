package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import game.listchooser.InteractiveListChooser;

public class InteractiveListChooserTest {

    @Test
    @DisplayName("InteractiveListChooser : une saisie invalide ne bloque pas le choix")
    public void testInvalidInputThenValidChoice() {
        InputStream originalIn = System.in;
        String input = "abc\n9\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        try {
            InteractiveListChooser<String> chooser = new InteractiveListChooser<>();
            List<String> choices = Arrays.asList("premier", "deuxieme");

            String selected = chooser.choose("Choisir une valeur", choices);

            assertEquals("deuxieme", selected);
        } finally {
            System.setIn(originalIn);
        }
    }
}
