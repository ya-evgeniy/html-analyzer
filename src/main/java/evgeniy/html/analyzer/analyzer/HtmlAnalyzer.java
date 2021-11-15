package evgeniy.html.analyzer.analyzer;

import evgeniy.html.analyzer.statistic.stat.HtmlStatistics;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

public interface HtmlAnalyzer {

    @NotNull HtmlStatistics getStatisticsFor(@NotNull String siteUrl);

    @NotNull HtmlStatistics getStatisticsFor(@NotNull URL url);

}
