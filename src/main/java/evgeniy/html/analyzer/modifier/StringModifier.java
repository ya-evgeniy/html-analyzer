package evgeniy.html.analyzer.modifier;

import org.jetbrains.annotations.NotNull;

public interface StringModifier {

    @NotNull String apply(@NotNull String input);

}
