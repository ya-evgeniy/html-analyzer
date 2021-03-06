package evgeniy.html.analyzer.statistic;

import org.jetbrains.annotations.NotNull;

public class BaseCountedWord implements CountedWord {

    private final String word;
    private final int count;

    public BaseCountedWord(@NotNull String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public @NotNull String getWord() {
        return this.word;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public int compareTo(@NotNull CountedWord o) {
        return o.getCount() - this.getCount();
    }

}
