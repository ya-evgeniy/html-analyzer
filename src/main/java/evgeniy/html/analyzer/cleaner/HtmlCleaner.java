package evgeniy.html.analyzer.cleaner;

import org.jetbrains.annotations.NotNull;

public interface HtmlCleaner {

    @NotNull String apply(@NotNull String input);

}
