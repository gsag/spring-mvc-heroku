package repository.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AbstractRepository;

/**
 * Created by Guilherme on 15/08/2015.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public abstract class AbstractRepositoryService<T extends AbstractRepository> {

    private T concreteDao;

    public <TE> void saveEntity(TE entity) throws Exception {
        concreteDao.save(entity);
    }
}
