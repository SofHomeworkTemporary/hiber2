package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Store;

public class StoreDao extends GenericDao<Store>{
    public StoreDao(SessionFactory sessionFactory) {
        super(sessionFactory, Store.class);
    }
}
