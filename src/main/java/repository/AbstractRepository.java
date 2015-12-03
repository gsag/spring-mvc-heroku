package repository;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/*
 * Created by guilherme on 25/11/15.
 */
@Repository
@SuppressWarnings("unchecked")
public abstract class AbstractRepository<T,ID extends Serializable> implements RepositoryInterface<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class entityClass;

    public AbstractRepository() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession(){
        return entityManager.unwrap(Session.class);
    }

    protected Criteria getCriteria(){
        return getSession().createCriteria(entityClass);
    }

    @Override
    public Long count() throws Exception {
        Criteria criteria = getCriteria().setProjection(Projections.rowCount());
        Long resultCount = (Long) criteria.uniqueResult();
        return resultCount;
    }

    @Override
    public void save(T entity) throws Exception {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) throws Exception {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public Optional<T> findOne(ID primaryKey) throws Exception {
        Criteria criteria = getCriteria().add(Restrictions.idEq(primaryKey));
        T object = (T) criteria.uniqueResult();
        return Optional.ofNullable(object);
    }

    @Override
    public List<T> findAll() throws Exception {
        Criteria criteria = getCriteria().setFetchMode("select", FetchMode.SELECT);
        List<T> objectList = criteria.list();
        return objectList;
    }

    @Override
    public boolean exists(ID primaryKey) throws Exception {
        return (findOne(primaryKey).isPresent())?Boolean.TRUE:Boolean.FALSE;
    }
}