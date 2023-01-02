package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.FilmText;

public class FilmTextDao extends GenericDao<FilmText> {
    public FilmTextDao(SessionFactory sessionFactory) {
        super(sessionFactory, FilmText.class);
    }
}
