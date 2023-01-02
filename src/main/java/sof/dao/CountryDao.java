package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Country;

public class CountryDao extends GenericDao<Country> {
    public CountryDao(SessionFactory sessionFactory) {
        super(sessionFactory, Country.class);
    }
}
