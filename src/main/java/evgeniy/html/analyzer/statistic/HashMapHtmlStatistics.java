package evgeniy.html.analyzer.statistic;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class HashMapHtmlStatistics implements HtmlStatistics {

    private final Map<String, WordStatistics> words = new HashMap<>();
    private final @NotNull String url;

    public HashMapHtmlStatistics(@NotNull String url) {
        this.url = Objects.requireNonNull(url, "url must not be null");
    }

    @Override
    public void add(@NotNull String word) {
        final WordStatistics statistics = this.words.get(word);
        final int count = statistics == null ? 0 : statistics.getCount();

        words.put(word, new BaseWordStatistics(word, count + 1));
    }

    @Override
    public @NotNull String getUrl() {
        return this.url;
    }

    @Override
    public @NotNull Stream<WordStatistics> words() {
        return this.words.values().stream();
    }

    @Override
    public @NotNull Optional<WordStatistics> getFor(@NotNull String word) {
        return Optional.ofNullable(words.get(word));
    }

}
