package repository;

import entity.LogAccess;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by guilherme on 25/11/15.
 */
@Repository
public class LogAccessRepository extends AbstractRepository<LogAccess,Long> {}
