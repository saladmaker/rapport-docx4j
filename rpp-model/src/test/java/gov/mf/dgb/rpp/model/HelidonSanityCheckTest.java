package gov.mf.dgb.rpp.model;

import io.helidon.common.Errors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HelidonSanityCheckTest {
    @Test
    void test_required_options(){
        assertThrows(Errors.ErrorMessagesException.class, ()-> AuSujet.builder().build());
    }
}
