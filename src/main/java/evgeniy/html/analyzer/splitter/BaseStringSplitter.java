package evgeniy.html.analyzer.splitter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class BaseStringSplitter implements StringSplitter {

    private final @NotNull Pattern pattern = Pattern.compile("[/ <>{}()\\[\\],.!?'\";:]+");

    @Override
    public @NotNull Stream<String> apply(@NotNull String input) {
        Objects.requireNonNull(input, "input must not be null");

        return Arrays.stream(pattern.split(input));
    }

}
