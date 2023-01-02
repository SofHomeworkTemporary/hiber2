package sof.dao;

import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@RequiredArgsConstructor
public abstract class GenericDao<T> {
    private final SessionFactory sessionFactory;
    private final Class<T> clazz;
    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public T getById(final int id) {
        return getCurrentSession().get(clazz,id);
    }
    public List<T> getItems(int offset, int count){
        Query query = getCurrentSession().createQuery("From "+clazz.getName(),clazz);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }
    public List<T> findAll(){
        return getCurrentSession().createQuery("From "+clazz.getName(),clazz).list();
    }
    public T save(final T entity){
         getCurrentSession().saveOrUpdate(entity);
         return entity;
    }
    public T update(final T entity){
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity){
        getCurrentSession().delete(entity);
    }

    public void deleteById(final int id){
        final T entity=getById(id);
        getCurrentSession().delete(entity);
    }

}
