package evgeniy.html.analyzer;

import evgeniy.html.analyzer.statistic.HtmlStatistics;
import evgeniy.html.analyzer.statistic.WordStatistics;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HtmlAnalyzerTest {

    private HtmlAnalyzer analyzer = new HtmlAnalyzer();

    @Test
    void test1() throws MalformedURLException {
        final URL url = new File("src/test/resources/test1.html").toURI().toURL();
        final HtmlStatistics statistics = analyzer.getStatisticsFor(url);

        final Optional<WordStatistics> optValue1 = statistics.getFor("123");
        assertTrue(optValue1.isPresent());
        assertEquals(1, optValue1.get().getCount());
    }

}