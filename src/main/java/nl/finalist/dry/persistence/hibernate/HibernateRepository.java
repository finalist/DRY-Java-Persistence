package nl.finalist.dry.persistence.hibernate;

import java.io.Serializable;
import java.util.List;

import nl.finalist.dry.persistence.DomainClassRepository;
import nl.finalist.dry.persistence.NotFoundException;
import nl.finalist.dry.persistence.Repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * Abstract class that can be used for a {@link Repository}.
 * 
 * @author Finalist
 * @since 1.0
 *
 * @param <T> the type of the domain class that is handled by the repository
 */
public class HibernateRepository<T> extends DomainClassRepository<T>  {

    private final SessionFactory sessionFactory;
    
    protected final Criteria createCriteria() {
        return getSession().createCriteria(getDomainClass());
    }
    
    protected final T cast(Object object) {
        return getDomainClass().cast(object);
    }
    
    /**
     * Overriding constructor that initializes the domain class.
     */
    public HibernateRepository(SessionFactory sessionFactory) {
        super();
        if (sessionFactory == null) {
            throw new IllegalArgumentException("Argument 'sessionFactory' is mandatory.");
        }
        
        this.sessionFactory = sessionFactory;
    }

    /**
     * @see Repository#get(Serializable)
     */
    @SuppressWarnings("unchecked")
    public T get(Serializable id) {
        final Session session = getSession();
        T t = (T) session.get(getDomainClass(), id);

        if (t == null) {
            throw new NotFoundException("Entity of type " + getDomainClass().getName() 
                    + " with id " + id + " could not be found");
        }

        return t;
    }

    /**
     * @see Repository#merge(Object)
     */
    @SuppressWarnings("unchecked")
    public T merge(T obj) {
        final Session session = getSession();
        return (T) session.merge(obj);
    }

    /**
     * @see Repository#delete(Object)
     */
    public void delete(T obj) {
        final Session session = getSession();
        session.delete(obj);
    }

    /** 
     * get the session with the NOT_DELETED filter enabled.
     * @return
     */
    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    public void flush() {
        getSession().flush();
    }

    public void refresh(T entity) {
        getSession().refresh(entity);
    }

    public void evict(T entity) {
        getSession().evict(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> all() {
        return getSession().createCriteria(getDomainClass()).list();
    }
}