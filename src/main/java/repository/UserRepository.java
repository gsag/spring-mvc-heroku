package repository;

import entity.user.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
 * Created by guilherme on 05/02/16.
 */
@Repository
public class UserRepository extends AbstractRepository<User,Long>{

    @PersistenceContext
    private EntityManager entityManager;

    public UserDetails findUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = entityManager.unwrap(Session.class)
                .createCriteria(User.class)
                .add(Restrictions.eq("username",username)).list();

        if(users.isEmpty()){
            throw new UsernameNotFoundException("O usuário "+ username+" não foi encontrado.");
        }
        return users.get(0);
    }
}