package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Payment;

public class PaymentDao extends GenericDao<Payment>{
    public PaymentDao(SessionFactory sessionFactory) {
        super(sessionFactory, Payment.class);
    }
}
