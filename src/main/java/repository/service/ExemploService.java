package repository.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ExemploRepository;

/**
 * Created by guilherme on 23/10/15.
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class ExemploService extends AbstractRepositoryService<ExemploRepository> {
}
