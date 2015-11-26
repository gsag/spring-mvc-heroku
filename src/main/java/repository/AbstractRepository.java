package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by guilherme on 25/11/15.
 */
@Repository
public abstract class AbstractRepository<T,ID extends Serializable> implements RepositoryInterface<T, ID> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class givenClass;

    public AbstractRepository() {
        this.givenClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) throws Exception {
        getCurrentSession().save(entity);
    }
}
