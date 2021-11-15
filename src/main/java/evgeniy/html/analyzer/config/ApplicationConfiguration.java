package evgeniy.html.analyzer.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import evgeniy.html.analyzer.config.module.DatabaseModule;
import evgeniy.html.analyzer.config.module.HtmlAnalyzerModule;
import org.jetbrains.annotations.NotNull;

public class ApplicationConfiguration {

    public @NotNull Injector configure() {
        return Guice.createInjector(
                new HtmlAnalyzerModule(),
                new DatabaseModule()
        );
    }

}
