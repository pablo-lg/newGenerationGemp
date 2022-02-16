package ar.teco.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.teco.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HistoWFTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoWF.class);
        HistoWF histoWF1 = new HistoWF();
        histoWF1.setId(1L);
        HistoWF histoWF2 = new HistoWF();
        histoWF2.setId(histoWF1.getId());
        assertThat(histoWF1).isEqualTo(histoWF2);
        histoWF2.setId(2L);
        assertThat(histoWF1).isNotEqualTo(histoWF2);
        histoWF1.setId(null);
        assertThat(histoWF1).isNotEqualTo(histoWF2);
    }
}
