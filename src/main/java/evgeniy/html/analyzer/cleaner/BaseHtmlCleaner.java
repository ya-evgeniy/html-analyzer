package evgeniy.html.analyzer.cleaner;

import evgeniy.html.analyzer.modifier.CleanupWhitespaceModifier;
import evgeniy.html.analyzer.modifier.RemoveHtmlTagModifier;
import evgeniy.html.analyzer.modifier.RemoveHtmlUnicodeModifier;
import evgeniy.html.analyzer.modifier.StringModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BaseHtmlCleaner implements HtmlCleaner {

    private final List<StringModifier> modifiers = Arrays.asList(
            new RemoveHtmlTagModifier("script", true),
            new RemoveHtmlTagModifier("noscript", true),
            new RemoveHtmlTagModifier("style", true),
            new RemoveHtmlTagModifier("svg", true),
            new RemoveHtmlTagModifier(),

            new RemoveHtmlUnicodeModifier(),
            new CleanupWhitespaceModifier()
    );

    @Override
    public @NotNull String apply(@NotNull String input) {
        @NotNull String result = Objects.requireNonNull(input, "input must not be null");

        for (StringModifier modifier : modifiers) {
            result = modifier.apply(result);
        }

        return result;
    }

}
