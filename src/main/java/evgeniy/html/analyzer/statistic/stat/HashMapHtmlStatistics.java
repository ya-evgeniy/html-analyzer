package evgeniy.html.analyzer.statistic.stat;

import evgeniy.html.analyzer.statistic.BaseCountedWord;
import evgeniy.html.analyzer.statistic.CountedWord;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class HashMapHtmlStatistics implements HtmlStatistics {

    private final Map<String, CountedWord> words = new HashMap<>();
    private final @NotNull String datasourceUrl;

    public HashMapHtmlStatistics(@NotNull String datasourceUrl) {
        this.datasourceUrl = Objects.requireNonNull(datasourceUrl, "datasourceUrl must not be null");
    }

    @Override
    public void add(@NotNull String word) {
        final CountedWord statistics = this.words.get(word);
        final int count = statistics == null ? 0 : statistics.getCount();

        words.put(word, new BaseCountedWord(word, count + 1));
    }

    @Override
    public @NotNull String getDatasourceUrl() {
        return this.datasourceUrl;
    }

    @Override
    public @NotNull Stream<CountedWord> words() {
        return this.words.values().stream();
    }

    @Override
    public @NotNull Optional<CountedWord> getFor(@NotNull String word) {
        return Optional.ofNullable(words.get(word));
    }

}
