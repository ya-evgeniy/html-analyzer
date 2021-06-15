package evgeniy.html.analyzer.statistic;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public interface HtmlStatistics {

    void add(@NotNull String word);

    @NotNull String getUrl();

    @NotNull Stream<WordStatistics> words();

    @NotNull Optional<WordStatistics> getFor(@NotNull String word);

}
