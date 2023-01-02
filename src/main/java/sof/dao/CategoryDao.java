package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Category;

public class CategoryDao extends GenericDao<Category> {
    public CategoryDao(SessionFactory sessionFactory) {
        super(sessionFactory, Category.class);
    }
}
