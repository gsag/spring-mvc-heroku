package repository;

import entity.Exemplo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by guilherme on 23/10/15.
 */
@Repository
public class ExemploRepository extends AbstractRepository<Exemplo,Long> {
}
