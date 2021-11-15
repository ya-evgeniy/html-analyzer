package evgeniy.html.analyzer.config.module;

import com.google.inject.AbstractModule;
import evgeniy.html.analyzer.db.BaseWordService;
import evgeniy.html.analyzer.db.WordEntity;
import evgeniy.html.analyzer.db.WordService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseModule extends AbstractModule {

    private final SessionFactory sessionFactory;

    public DatabaseModule() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.tld")
                .addAnnotatedClass(WordEntity.class)
                .buildSessionFactory();
    }

    @Override
    protected void configure() {
        bind(WordService.class).toInstance(new BaseWordService(sessionFactory::openSession));
    }



}
