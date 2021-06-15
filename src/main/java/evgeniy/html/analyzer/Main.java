package evgeniy.html.analyzer;

import com.google.gson.Gson;
import evgeniy.html.analyzer.db.DatabaseConfig;
import evgeniy.html.analyzer.db.DatabaseManager;
import evgeniy.html.analyzer.statistic.HtmlStatistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    private static Logger LOG;

    private static DatabaseManager databaseManager;

    public static void main(String[] args) {
        final boolean success = loadLoggingConfiguration();
        if (!success) System.out.println("An error occurred while loading the logging configuration");

        LOG = Logger.getLogger(Main.class.getName());

        // FIXME: ADD RIVER TO JAR
        loadDatabaseConfiguration();
//        databaseManager = new DatabaseManager(new DatabaseConfig());

        analyzeHtml(args);
    }

    private static boolean loadLoggingConfiguration() {
        try (InputStream configStream = Main.class.getClassLoader().getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(configStream);
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void loadDatabaseConfiguration() {
        try (InputStream configStream = Main.class.getClassLoader().getResourceAsStream("db.json")) {
            if (configStream == null) {
                LOG.severe("File 'db.json' in jar not found");
                databaseManager = new DatabaseManager(new DatabaseConfig());
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(configStream));
            final Gson gson = new Gson();

            final DatabaseConfig databaseConfig = gson.fromJson(reader, DatabaseConfig.class);
            databaseManager = new DatabaseManager(databaseConfig);
        }
        catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private static void analyzeHtml(String[] args) {
        if (args.length == 0) {
            LOG.info("Not enough arguments. Run the program again with the parameter in the form of a link to the html page");
            return;
        }

        final String pageLink = args[0];

        final HtmlAnalyzer htmlAnalyzer = new HtmlAnalyzer();
        final HtmlStatistics statistics = htmlAnalyzer.getStatisticsFor(pageLink);

        LOG.info("Found '%s' unique words".formatted(statistics.words().count()));
        statistics.words()
                .sorted()
                .map(s -> "%s\t%s".formatted(s.getCount(), s.getWord()))
                .forEach(LOG::info);

        if (databaseManager.getConfig().isEnabled()) {
            databaseManager.upload(statistics);
        }
    }

}
