package evgeniy.html.analyzer.statistic.stat;

import org.jetbrains.annotations.NotNull;

public interface HtmlStatisticsFactory {

    @NotNull HtmlStatistics create(@NotNull String datasourceUrl);

}
