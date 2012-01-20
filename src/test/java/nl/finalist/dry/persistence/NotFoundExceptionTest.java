package nl.finalist.dry.persistence;

import nl.finalist.dry.junit.commons.ExtendingExceptionTest;

public class NotFoundExceptionTest extends ExtendingExceptionTest<NotFoundException>{

    @Override
    protected Class<NotFoundException> getExceptionClass() {
        return NotFoundException.class;
    }

}
