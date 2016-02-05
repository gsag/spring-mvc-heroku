package repository.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

/*
 * Created by guilherme on 05/02/16.
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class UserService extends AbstractRepositoryService<UserRepository> {
}