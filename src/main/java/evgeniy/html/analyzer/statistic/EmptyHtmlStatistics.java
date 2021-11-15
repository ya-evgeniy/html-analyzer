package evgeniy.html.analyzer.statistic;

import evgeniy.html.analyzer.statistic.stat.HtmlStatistics;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public class EmptyHtmlStatistics implements HtmlStatistics {

    @Override
    public void add(@NotNull String word) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull String getDatasourceUrl() {
        return "";
    }

    @Override
    public @NotNull Stream<CountedWord> words() {
        return Stream.empty();
    }

    @Override
    public @NotNull Optional<CountedWord> getFor(@NotNull String word) {
        return Optional.empty();
    }

}
