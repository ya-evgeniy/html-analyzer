package evgeniy.html.analyzer.modifier;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

public class CleanupWhitespaceModifier implements StringModifier {

    private final @NotNull Pattern pattern = Pattern.compile("\\s+");

    @Override
    public @NotNull String apply(@NotNull String input) {
        Objects.requireNonNull(input, "input must not be null");

        return pattern.matcher(input).replaceAll(" ");
    }

}
