package evgeniy.html.analyzer.statistic;

import org.jetbrains.annotations.NotNull;

public interface WordStatistics extends Comparable<WordStatistics> {

    @NotNull String getWord();

    int getCount();

}
