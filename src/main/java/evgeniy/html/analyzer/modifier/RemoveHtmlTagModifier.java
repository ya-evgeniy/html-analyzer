package evgeniy.html.analyzer.modifier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.regex.Pattern;

public class RemoveHtmlTagModifier implements StringModifier {

    private final String tag;
    private final boolean cleanBody;

    private @Nullable("lazy initialization") Pattern pattern;

    /**
     *
     */
    public RemoveHtmlTagModifier() {
        this(false);
    }

    /**
     *
     * @param cleanBody
     */
    public RemoveHtmlTagModifier(boolean cleanBody) {
        this("", cleanBody);
    }

    /**
     *
     * @param tag
     */
    public RemoveHtmlTagModifier(@NotNull String tag) {
        this(tag, false);
    }

    /**
     *
     * @param tag
     * @param cleanBody
     */
    public RemoveHtmlTagModifier(@NotNull String tag, boolean cleanBody) {
        this.tag = Objects.requireNonNull(tag, "tag must not be null");
        this.cleanBody = cleanBody;
    }

    @Override
    public @NotNull String apply(@NotNull String input) {
        Objects.requireNonNull(input, "input must not be null");

        if (pattern == null) {
            if (cleanBody) {
                pattern = Pattern.compile("<%s.*?>.*?</%s>".formatted(tag, tag), Pattern.DOTALL);
            }
            else {
                pattern = Pattern.compile("</?%s.*?>".formatted(tag), Pattern.DOTALL);
            }
        }

        return pattern.matcher(input).replaceAll("");
    }

    public String getTag() {
        return tag;
    }

    public boolean isCleanBody() {
        return cleanBody;
    }

}
