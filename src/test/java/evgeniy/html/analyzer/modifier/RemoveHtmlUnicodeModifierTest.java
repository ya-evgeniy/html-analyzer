package evgeniy.html.analyzer.modifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RemoveHtmlUnicodeModifierTest {

    @Test
    void nullableArgument() {
        StringModifier modifier = new RemoveHtmlUnicodeModifier();

        assertThrows(NullPointerException.class, () -> {
            modifier.apply(null);
        });
    }

    @Test
    void unicode() {
        StringModifier modifier = new RemoveHtmlUnicodeModifier();

        assertEquals(
                """
                        <p></p>
                        <div></div>
                        <script></script>
                        """,
                modifier.apply("""
                        <p>&spades;</p>
                        <div>&#9824;</div>
                        <script>&#x2660;</script>
                        """)
        );
    }

}