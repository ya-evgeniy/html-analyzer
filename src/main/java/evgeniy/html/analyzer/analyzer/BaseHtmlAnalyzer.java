package evgeniy.html.analyzer.analyzer;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import evgeniy.html.analyzer.cleaner.HtmlCleaner;
import evgeniy.html.analyzer.splitter.StringSplitter;
import evgeniy.html.analyzer.statistic.stat.HtmlStatistics;
import evgeniy.html.analyzer.statistic.stat.HtmlStatisticsFactory;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class BaseHtmlAnalyzer implements HtmlAnalyzer {

    private static final Logger LOG = Logger.getLogger(BaseHtmlAnalyzer.class.getName());

    private final HtmlCleaner htmlCleaner;
    private final StringSplitter stringSplitter;

    private final HtmlStatisticsFactory htmlStatisticsFactory;
    private final HtmlStatistics emptyHtmlStatistics;

    @Inject
    public BaseHtmlAnalyzer(@NonNull HtmlCleaner htmlCleaner,
                            @NonNull StringSplitter stringSplitter,
                            @NonNull HtmlStatisticsFactory htmlStatisticsFactory,
                            @NonNull @Named("empty") HtmlStatistics emptyHtmlStatistics) {
        this.htmlCleaner = htmlCleaner;
        this.stringSplitter = stringSplitter;

        this.htmlStatisticsFactory = htmlStatisticsFactory;
        this.emptyHtmlStatistics = emptyHtmlStatistics;
    }

    @Override
    public @NotNull HtmlStatistics getStatisticsFor(@NotNull String siteUrl) {
        try {
            LOG.info("Checking the site url '%s'".formatted(siteUrl));
            final URL url = new URL(siteUrl);
            return getStatisticsFor(url);
        } catch (MalformedURLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return this.emptyHtmlStatistics;
        }
    }

    @Override
    public @NotNull HtmlStatistics getStatisticsFor(@NotNull URL url) {
        LOG.info("Trying to get html page at '%s'".formatted(url.toString()));
        try (final InputStream urlStream = url.openStream();
             final BufferedInputStream bufferedUrlStream = new BufferedInputStream(urlStream)) {

            LOG.info("Downloading html page at '%s'".formatted(url.toString()));
            final byte[] urlBytes = bufferedUrlStream.readAllBytes();
            final String html = new String(urlBytes, StandardCharsets.UTF_8);

            LOG.info("Cleaning up the html page");
            final String clean = this.htmlCleaner.apply(html);

            LOG.info("Splitting a page into words");
            final Stream<String> words = this.stringSplitter.apply(clean);

            LOG.info("Counting word statistics");
            final HtmlStatistics statistics = this.htmlStatisticsFactory.create(url.toString());
            words.filter(s -> !s.isBlank())
                    .map(String::toUpperCase)
                    .forEach(statistics::add);

            return statistics;
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return this.emptyHtmlStatistics;
        }
    }

}
