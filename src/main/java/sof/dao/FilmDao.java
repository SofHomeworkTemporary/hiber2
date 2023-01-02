package sof.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import sof.domain.Film;



public class FilmDao extends GenericDao<Film>{
    public FilmDao(SessionFactory sessionFactory) {
        super(sessionFactory, Film.class);
    }

    public Film getFirstAvailableFilmForRent() {
        Query<Film> query= getCurrentSession().createQuery(
                "select f from Film f where f.filmId not in(select distinct film.filmId " +
                        "from Inventory)",Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
