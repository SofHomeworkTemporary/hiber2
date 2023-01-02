package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Inventory;

public class InventoryDao extends GenericDao<Inventory>{
    public InventoryDao(SessionFactory sessionFactory) {
        super(sessionFactory, Inventory.class);
    }
}
