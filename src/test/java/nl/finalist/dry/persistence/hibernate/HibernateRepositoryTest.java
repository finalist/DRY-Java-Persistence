package nl.finalist.dry.persistence.hibernate;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.finalist.dry.persistence.NotFoundException;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HibernateRepositoryTest {

    @Mock private SessionFactory sessionFactory;
    @Mock private Session session;
    
    private static class TestEntityHibernateRepository extends HibernateRepository<TestEntity> {

        public TestEntityHibernateRepository(SessionFactory sessionFactory) {
            super(sessionFactory);
        }
        
    }
    
    @InjectMocks TestEntityHibernateRepository testSubject;
    
    @Before
    public void beforeEachTest() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }
    
    @Test
    public void all() {
        // given
        final Criteria mockCriteria = Mockito.mock(Criteria.class);
        when(session.createCriteria(TestEntity.class)).thenReturn(mockCriteria);
        
        // when
        testSubject.all();
        
        // then
        verify(session).createCriteria(TestEntity.class);
        
        verify(mockCriteria).list();
    }
    
    @Test
    public void delete() {
        // given
        final TestEntity entity = new TestEntity();
        
        // when
        testSubject.delete(entity);
        
        // then
        verify(session).delete(entity);
    }

    @Test
    public void evict() {
        // given
        final TestEntity entity = new TestEntity();
        
        // when
        testSubject.evict(entity);
        
        // then
        verify(session).evict(entity);
    }
    
    @Test
    public void flush() {
        // given
        
        // when
        testSubject.flush();
        
        // then
        verify(session).flush();
    }
    
    @Test
    public void merge() {
        // given
        final TestEntity entity = new TestEntity();
        
        // when
        testSubject.merge(entity);
        
        // then
        verify(session).merge(entity);
    }
    
    @Test
    public void refresh() {
        // given
        final TestEntity entity = new TestEntity();
        
        // when
        testSubject.refresh(entity);
        
        // then
        verify(session).refresh(entity);
    }
    
    @Test
    public void getExisting() {
        // given
        final String id = "id";
        final TestEntity entity = new TestEntity();
        when(session.get(eq(TestEntity.class), eq(id))).thenReturn(entity);
        
        // when
        testSubject.get(id);
        
        // then
        verify(session).get(eq(TestEntity.class), eq(id));
    }
    
    @Test(expected = NotFoundException.class)
    public void getNonExisting() {
        // given
        final String id = "id";
        when(session.get(eq(TestEntity.class), eq(id))).thenReturn(null);
        
        // when
        testSubject.get(id);
        
        // then exception expected
    }
    
}
