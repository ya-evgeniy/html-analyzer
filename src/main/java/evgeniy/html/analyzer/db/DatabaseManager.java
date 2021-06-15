package evgeniy.html.analyzer.db;

import evgeniy.html.analyzer.statistic.HtmlStatistics;
import evgeniy.html.analyzer.statistic.WordStatistics;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DatabaseManager {

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS `words_statistics` (
                `id` BIGINT NOT NULL AUTO INCREMENT,
                `source_url` VARCHAR(256) NOT NULL,
                `word` VARCHAR(256) NOT NULL,
                `count` INT NOT NULL
            ) COLLATE utf8mb4_unicode_ci;
            """;

    private static final String INSERT_WORD = """
            INSERT INTO `words_statistics` (`source_url`, `word`, `count`) VALUES ('%s', '%s', '%s');
            """;

    private static final Logger LOG = Logger.getLogger(DatabaseManager.class.getName());

    private final @NotNull DatabaseConfig config;

    public DatabaseManager(@NotNull DatabaseConfig config) {
        this.config = Objects.requireNonNull(config, "config must not be null");
    }

    public void upload(HtmlStatistics statistics) {
        LOG.info("Saving '%s' unique words to database".formatted(statistics.words().count()));

        if (!checkParameters()) {
            LOG.severe("One or more parameters in the database config file are missing");
            return;
        }

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            LOG.log(Level.SEVERE, e.getMessage(), e);
//            return;
//        }

        LOG.info("Trying to connect to database");

        final String connectionUrl = this.config.getUrl().formatted(
                this.config.getHost(),
                this.config.getPort(),
                this.config.getDb()
        );

        try (Connection connection = DriverManager.getConnection(connectionUrl, this.config.getLogin(), this.config.getPassword())) {
            final List<WordStatistics> words = statistics.words().collect(Collectors.toList());
            for (WordStatistics word : words) {
                try (Statement statement = connection.createStatement()) {
                    statement.execute(INSERT_WORD.formatted(
                            statistics.getUrl(),
                            word.getWord(),
                            word.getCount()
                    ));
                }
                catch (SQLException e) {
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return;
        }

        LOG.info("Completed saving to database");
    }

    public boolean checkParameters() {
        return this.config.getUrl() != null && this.config.getHost() != null && this.config.getDb() != null && this.config.getLogin() != null && this.config.getPassword() != null;
    }

    public DatabaseConfig getConfig() {
        return config;
    }

}
