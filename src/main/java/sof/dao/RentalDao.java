package sof.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import sof.domain.Rental;



public class RentalDao extends GenericDao<Rental> {
    public RentalDao(SessionFactory sessionFactory) {
        super(sessionFactory, Rental.class);
    }

    public Rental getAnyNonReturnedRental() {
        Query<Rental> query=getCurrentSession().createQuery("select r from Rental r where r.rentalDate is null",Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
