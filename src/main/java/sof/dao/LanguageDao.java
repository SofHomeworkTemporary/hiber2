package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Inventory;
import sof.domain.Language;

public class LanguageDao extends GenericDao<Language> {
    public LanguageDao(SessionFactory sessionFactory) {
        super(sessionFactory, Language.class);
    }
}
