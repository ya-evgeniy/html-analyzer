package evgeniy.html.analyzer.statistic;

import org.jetbrains.annotations.NotNull;

public interface CountedWord extends Comparable<CountedWord> {

    @NotNull String getWord();

    int getCount();

}
