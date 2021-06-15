package evgeniy.html.analyzer.splitter;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public interface StringSplitter {

    @NotNull Stream<String> apply(@NotNull String input);

}
