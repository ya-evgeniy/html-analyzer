package evgeniy.html.analyzer.db;

import com.google.inject.Provider;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class BaseWordService implements WordService {

    private final @NotNull Provider<Session> sessionProvider;

    public BaseWordService(@NotNull Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    @Override
    public void save(@NotNull final WordEntity word) {
        try (Session session = sessionProvider.get()) {
            final var transaction = session.beginTransaction();
            session.saveOrUpdate(word);
            transaction.commit();
        }
    }

    @Override
    public void saveAll(@NotNull final Collection<WordEntity> words) {
        try (Session session = sessionProvider.get()) {
            final var transaction = session.beginTransaction();

            words.forEach(session::save);

            transaction.commit();
        }
    }

}
