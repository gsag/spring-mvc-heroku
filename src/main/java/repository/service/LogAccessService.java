package repository.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.LogAccessRepository;

/**
 * Created by guilherme on 26/11/15.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LogAccessService extends AbstractRepositoryService<LogAccessRepository> {
}
