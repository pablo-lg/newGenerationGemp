package ar.teco.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.teco.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DemandaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demanda.class);
        Demanda demanda1 = new Demanda();
        demanda1.setId(1L);
        Demanda demanda2 = new Demanda();
        demanda2.setId(demanda1.getId());
        assertThat(demanda1).isEqualTo(demanda2);
        demanda2.setId(2L);
        assertThat(demanda1).isNotEqualTo(demanda2);
        demanda1.setId(null);
        assertThat(demanda1).isNotEqualTo(demanda2);
    }
}
