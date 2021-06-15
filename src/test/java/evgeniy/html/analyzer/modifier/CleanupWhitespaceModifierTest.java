package evgeniy.html.analyzer.modifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CleanupWhitespaceModifierTest {

    private StringModifier modifier = new CleanupWhitespaceModifier();

    @Test
    void nullableArgument() {
        assertThrows(NullPointerException.class, () -> {
            modifier.apply(null);
        });
    }

    @Test
    void emptyString() {
        assertEquals(
                "",
                modifier.apply("")
        );
    }

    @Test
    void singleWhitespace() {
        assertEquals(
                " ",
                modifier.apply(" ")
        );
    }

    @Test
    void doubleWhitespace() {
        assertEquals(
                " ",
                modifier.apply("  ")
        );
    }

    @Test
    void newline() {
        assertEquals(
                " ",
                modifier.apply(" \n\n ")
        );
    }

    @Test
    void specialCharacter() {
        assertEquals(
                " ",
                modifier.apply(" \n\t\t\n ")
        );
    }

    @Test
    void text() {
        assertEquals(" testing text ",
                modifier.apply("""
                        
                        testing
                                text
                        
                        """)
        );
    }

}