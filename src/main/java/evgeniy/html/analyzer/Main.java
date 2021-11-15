package evgeniy.html.analyzer;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) {
        final boolean success = loadLoggingConfiguration();
        assert success: "An error occurred while loading the logging configuration";

        final var application = new Application();
        application.launch(args);
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

}
