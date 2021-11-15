package evgeniy.html.analyzer.statistic.stat;

import org.jetbrains.annotations.NotNull;

public class HashMapHtmlStatisticsFactory implements HtmlStatisticsFactory {

    public HashMapHtmlStatisticsFactory() {

    }

    @Override
    public @NotNull HtmlStatistics create(@NotNull final String datasourceUrl) {
        return new HashMapHtmlStatistics(datasourceUrl);
    }

}
