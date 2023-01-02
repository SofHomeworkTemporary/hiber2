package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Customer;

public class CustomerDao extends GenericDao<Customer> {
    public CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory, Customer.class);
    }
}
