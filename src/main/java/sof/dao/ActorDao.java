package sof.dao;

import org.hibernate.SessionFactory;
import sof.domain.Actor;

public class ActorDao extends GenericDao<Actor>{

    public ActorDao(SessionFactory sessionFactory) {
        super(sessionFactory, Actor.class);
    }
}
