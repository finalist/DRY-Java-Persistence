package nl.finalist.dry.persistence.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import nl.finalist.dry.persistence.DomainClassRepository;
import nl.finalist.dry.persistence.NotFoundException;
import nl.finalist.dry.persistence.Repository;

/**
 * Abstract class that can be used for a entity manager{@link Repository}.
 * 
 * @author Finalist
 * @since 1.0
 *
 * @param <T> the type of the domain class that is handled by the repository
 */
public class EntityManagerRepository<T> extends DomainClassRepository<T> {

    private final EntityManager entityManager;
    
    /**
     * Overriding constructor that initializes the domain class.
     */
    public EntityManagerRepository(EntityManager entityManager) {
        super();
        if (entityManager == null) {
            throw new IllegalArgumentException("Argument 'entityManager' is mandatory.");
        }
        
        this.entityManager = entityManager;
    }

    /**
     * @see Repository#get(Serializable)
     */
    public T get(Serializable id) {
        T t = (T) entityManager.find(getDomainClass(), id);

        if (t == null) {
            throw new NotFoundException("Entity of type " + getDomainClass().getName() 
                    + " with id " + id + " could not be found");
        }

        return t;
    }

    /**
     * @see Repository#merge(Object)
     */
    public T merge(T obj) {
        return (T) entityManager.merge(obj);
    }

    /**
     * @see Repository#delete(Object)
     */
    public void delete(T obj) {
        entityManager.remove(obj);
    }
    
    public void flush() {
        entityManager.flush();
    }

    public void refresh(T entity) {
        entityManager.refresh(entity);
    }

    public List<T> all() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDomainClass());
        
        TypedQuery<T> typedQuery = entityManager.createQuery(
                criteriaQuery.select(criteriaQuery.from(getDomainClass())));
        
        return typedQuery.getResultList();
    }

}