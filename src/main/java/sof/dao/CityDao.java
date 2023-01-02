package sof.dao;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import sof.domain.City;


public class CityDao extends GenericDao<City>{
    public CityDao(SessionFactory sessionFactory) {
        super(sessionFactory, City.class);
    }

    public City getByName(String cityName) {
        Query<City> query = getCurrentSession().createQuery(
                "select c from City c where c.city= : NAME",City.class);
        query.setParameter("NAME",cityName);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
