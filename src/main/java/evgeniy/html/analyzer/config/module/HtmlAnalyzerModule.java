package evgeniy.html.analyzer.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import evgeniy.html.analyzer.analyzer.BaseHtmlAnalyzer;
import evgeniy.html.analyzer.analyzer.HtmlAnalyzer;
import evgeniy.html.analyzer.cleaner.BaseHtmlCleaner;
import evgeniy.html.analyzer.cleaner.HtmlCleaner;
import evgeniy.html.analyzer.splitter.BaseStringSplitter;
import evgeniy.html.analyzer.splitter.StringSplitter;
import evgeniy.html.analyzer.statistic.EmptyHtmlStatistics;
import evgeniy.html.analyzer.statistic.stat.HashMapHtmlStatisticsFactory;
import evgeniy.html.analyzer.statistic.stat.HtmlStatistics;
import evgeniy.html.analyzer.statistic.stat.HtmlStatisticsFactory;
import org.jetbrains.annotations.NotNull;

public class HtmlAnalyzerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HtmlAnalyzer.class).to(BaseHtmlAnalyzer.class).asEagerSingleton();
        bind(HtmlCleaner.class).to(BaseHtmlCleaner.class).asEagerSingleton();
        bind(StringSplitter.class).to(BaseStringSplitter.class).asEagerSingleton();
        bind(HtmlStatisticsFactory.class).to(HashMapHtmlStatisticsFactory.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    @Named("empty")
    @NotNull HtmlStatistics provideEmptyHtmlStatistics() {
        return new EmptyHtmlStatistics();
    }

}
