package evgeniy.html.analyzer.modifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RemoveHtmlTagModifierTest {

    @Test
    void nullableArgument() {
        StringModifier modifier = new RemoveHtmlTagModifier();
        assertThrows(NullPointerException.class, () -> {
            modifier.apply(null);
        });
    }

    @Test
    void allTags() {
        StringModifier modifier = new RemoveHtmlTagModifier();

        assertEquals(
                """
                        \s
                            123
                            \s321\s
                            \svar a = 123\s
                        \s
                        """,
                modifier.apply("""
                        <div id = "123"
                             class = "123">
                            123
                            <h1>321</h1>
                            <script>var a = 123</script>
                        </div>
                        """)
        );
    }

    @Test
    void divTags() {
        StringModifier modifier = new RemoveHtmlTagModifier("div");

        assertEquals(
                """
                        \s
                            123
                            <h1>321</h1>
                            <script>var a = 123</script>
                        \s
                        """,
                modifier.apply("""
                        <div id = "123"
                             class = "123">
                            123
                            <h1>321</h1>
                            <script>var a = 123</script>
                        </div>
                        """)
        );
    }

    @Test
    void bodyTags() {
        StringModifier modifier = new RemoveHtmlTagModifier("script", true);

        assertEquals(
                """
                        <div id = "123"
                             class = "123">
                            123
                            <h1>321</h1>
                        \s\s\s\s\s
                        </div>
                        """,
                modifier.apply("""
                        <div id = "123"
                             class = "123">
                            123
                            <h1>321</h1>
                            <script>var a = 123</script>
                        </div>
                        """)
        );
    }
}