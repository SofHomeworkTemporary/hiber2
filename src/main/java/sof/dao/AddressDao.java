package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Address;

public class AddressDao extends GenericDao<Address>{
    public AddressDao(SessionFactory sessionFactory) {
        super(sessionFactory, Address.class);
    }
}
