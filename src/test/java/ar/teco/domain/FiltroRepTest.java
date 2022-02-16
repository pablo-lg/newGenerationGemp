package ar.teco.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.teco.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FiltroRepTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FiltroRep.class);
        FiltroRep filtroRep1 = new FiltroRep();
        filtroRep1.setId(1L);
        FiltroRep filtroRep2 = new FiltroRep();
        filtroRep2.setId(filtroRep1.getId());
        assertThat(filtroRep1).isEqualTo(filtroRep2);
        filtroRep2.setId(2L);
        assertThat(filtroRep1).isNotEqualTo(filtroRep2);
        filtroRep1.setId(null);
        assertThat(filtroRep1).isNotEqualTo(filtroRep2);
    }
}
