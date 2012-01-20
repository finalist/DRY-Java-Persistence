package nl.finalist.dry.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Repository interface that manages the entity of type <T>.
 * 
 * <p>
 * Methods working with an id should throw a {@link NotFoundException}. Finders should not.
 * </p>
 * 
 * @author Finalist
 * @since 1.0
 *
 * @param <T> the type of the entity that it managed by this repository
 */
public interface Repository<T> {
    
    /**
     * Get an entity of type <T> witht the given id
     * 
     * @param id the id to search for
     * @return the entity with the given id
     * @throws NotFoundException if the object is not found
     */
    T get(Serializable id);
    
    /**
     * Get a list of all entities in the repository.
     * Use caution when calling this function, because it can
     * slowndown the performance theoretically.
     * 
     * @return a list of all entities in the repository
     */
    List<T> all();

    /**
     * Save the given entity. Note that the entity itself
     * is not attached to a Hibernate session.
     * 
     * @param entity the entity to save
     * @return the persisted, attached entity
     */
    T merge(T entity);

    /**
     * Delete the given entity.
     * 
     * @param entity the entity to delete
     */
    void delete(T entity);
    
    /**
     * Refresh the entity.
     * @param entity
     */
    void refresh(T entity);
    
    /**
     * Flush.
     */
    void flush();

}