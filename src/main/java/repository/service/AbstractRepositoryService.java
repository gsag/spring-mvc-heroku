package repository.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AbstractRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 * Created by Guilherme on 15/08/2015.
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
@SuppressWarnings("unchecked")
public abstract class AbstractRepositoryService<T extends AbstractRepository> {

    @Autowired
    private T concreteRepository;

    private static final Logger logger = Logger.getLogger(AbstractRepositoryService.class);

    @Transactional(readOnly = false)
    public <TE> void saveEntity(TE entity){
        try {
            concreteRepository.save(entity);
        } catch (Exception e) {
            logger.error("Exception caught on saveEntity: "+ e.getMessage(),e);
        }
    }

    @Transactional(readOnly = false)
    public <TE> void updateEntity(TE entity){
        try {
            concreteRepository.update(entity);
        } catch (Exception e) {
            logger.error("Exception caught on updateEntity: "+ e.getMessage(),e);
        }
    }

    @Transactional(readOnly = false)
    public <TE> void deleteEntity(TE entity){
        try {
            concreteRepository.delete(entity);
        } catch (Exception e) {
            logger.error("Exception caught on deleteEntity: "+ e.getMessage(),e);
        }
    }

    @Transactional
    public <TE, ID extends Serializable> Optional<TE> findEntityById(ID primaryKey){
        Optional<TE> entityFound = null;
        try {
            entityFound = concreteRepository.findOne(primaryKey);
        } catch (Exception e) {
            logger.error("Exception caught on findEntityById: "+ e.getMessage(),e);
        }
        return entityFound;
    }

    @Transactional
    public <TE> List<TE> findAllEntities(){
        List<TE> entityList = null;
        try {
            entityList = concreteRepository.findAll();
        } catch (Exception e) {
            logger.error("Exception caught on findAllEntities: "+ e.getMessage(),e);
        }
        return entityList;
    }

    @Transactional
    public Long countEntities(){
        Long count = 0L;
        try {
            count = concreteRepository.count();
        } catch (Exception e) {
            logger.error("Exception caught on countEntities: "+ e.getMessage(),e);
        }
        return count;
    }

    @Transactional
    public <ID extends Serializable> Boolean hasEntity(ID primaryKey){
        Boolean exists = Boolean.FALSE;
        try {
            exists = concreteRepository.exists(primaryKey);
        } catch (Exception e) {
            logger.error("Exception caught on hasEntity: "+ e.getMessage(),e);
        }
        return exists;
    }
}