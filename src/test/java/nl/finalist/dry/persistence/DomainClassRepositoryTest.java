package nl.finalist.dry.persistence;

import java.io.Serializable;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DomainClassRepositoryTest {

    @Test
    public void shouldReturnTheCorrectClass() {
        // given
        Class<String> expectedClass = String.class;
        
        // when
        final DomainClassRepository<String> testSubject = new DomainClassRepository<String>() {

            public String get(Serializable id) {
                // TODO Auto-generated method stub
                return null;
            }

            public List<String> all() {
                // TODO Auto-generated method stub
                return null;
            }

            public String merge(String entity) {
                // TODO Auto-generated method stub
                return null;
            }

            public void delete(String entity) {
                // TODO Auto-generated method stub
                
            }

            public void refresh(String entity) {
                // TODO Auto-generated method stub
                
            }

            public void flush() {
                // TODO Auto-generated method stub
                
            }
            
        };
        
        // then
        Assert.assertEquals(expectedClass, testSubject.getDomainClass());
    }

}
