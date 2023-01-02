package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Staff;

public class StaffDao extends GenericDao<Staff>{
    public StaffDao(SessionFactory sessionFactory) {
        super(sessionFactory, Staff.class);
    }
}
