package evgeniy.html.analyzer.statistic.stat;

import evgeniy.html.analyzer.statistic.CountedWord;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public interface HtmlStatistics {

    void add(@NotNull String word);

    @NotNull String getDatasourceUrl();

    @NotNull Stream<CountedWord> words();

    @NotNull Optional<CountedWord> getFor(@NotNull String word);

}
