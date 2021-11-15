package evgeniy.html.analyzer;

import com.google.inject.Injector;
import evgeniy.html.analyzer.analyzer.HtmlAnalyzer;
import evgeniy.html.analyzer.config.ApplicationConfiguration;
import evgeniy.html.analyzer.db.WordEntity;
import evgeniy.html.analyzer.db.WordService;
import evgeniy.html.analyzer.statistic.stat.HtmlStatistics;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Application {

    private final @NotNull Logger logger = Logger.getLogger(Application.class.getName());
    private @NotNull Injector injector;

    public Application() {
        this.injector = new ApplicationConfiguration().configure();
    }

    public void launch(@NotNull String[] args) {
        if (args.length == 0) {
            logger.info("Not enough arguments. Run the program again with the parameter in the form of a link to the html page");
            return;
        }

        final String pageLink = args[0];

        final HtmlAnalyzer htmlAnalyzer = this.injector.getInstance(HtmlAnalyzer.class);
        final HtmlStatistics statistics = htmlAnalyzer.getStatisticsFor(pageLink);

        logger.info("Found '%s' unique words".formatted(statistics.words().count()));
        statistics.words()
                .sorted()
                .map(s -> "%s\t%s".formatted(s.getCount(), s.getWord()))
                .forEach(logger::info);

        final var wordService = this.injector.getInstance(WordService.class);
        final var toSave = statistics.words()
                .map(word -> WordEntity.builder()
                        .datasource(pageLink)
                        .word(word.getWord())
                        .count(word.getCount())
                        .createdAt(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
        wordService.saveAll(toSave);
    }

}
