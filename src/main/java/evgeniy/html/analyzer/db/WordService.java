package evgeniy.html.analyzer.db;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface WordService {

    void save(@NotNull WordEntity word);

    void saveAll(@NotNull Collection<WordEntity> word);

}
