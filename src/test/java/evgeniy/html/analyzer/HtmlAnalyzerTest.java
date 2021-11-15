package evgeniy.html.analyzer;

import evgeniy.html.analyzer.analyzer.HtmlAnalyzer;
import evgeniy.html.analyzer.config.ApplicationConfiguration;
import evgeniy.html.analyzer.statistic.CountedWord;
import evgeniy.html.analyzer.statistic.stat.HtmlStatistics;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HtmlAnalyzerTest {

    private static HtmlAnalyzer analyzer;

    @BeforeAll
    public static void init() {
        final var injector = new ApplicationConfiguration().configure();

        analyzer = injector.getInstance(HtmlAnalyzer.class);
    }

    @Test
    void test1() throws MalformedURLException {
        final URL url = new File("src/test/resources/test1.html").toURI().toURL();
        final HtmlStatistics statistics = analyzer.getStatisticsFor(url);

        final Optional<CountedWord> optValue1 = statistics.getFor("123");
        assertTrue(optValue1.isPresent());
        assertEquals(1, optValue1.get().getCount());
    }

    @Test
    void test2() throws MalformedURLException {
        final URL url = new File("src/test/resources/test2.html").toURI().toURL();
        final HtmlStatistics statistics = analyzer.getStatisticsFor(url);

        final Optional<CountedWord> optValue1 = statistics.getFor("123");
        assertTrue(optValue1.isPresent());
        assertEquals(2, optValue1.get().getCount());
    }

}