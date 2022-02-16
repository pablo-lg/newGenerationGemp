package ar.teco.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.teco.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UsuGempTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsuGemp.class);
        UsuGemp usuGemp1 = new UsuGemp();
        usuGemp1.setId(1L);
        UsuGemp usuGemp2 = new UsuGemp();
        usuGemp2.setId(usuGemp1.getId());
        assertThat(usuGemp1).isEqualTo(usuGemp2);
        usuGemp2.setId(2L);
        assertThat(usuGemp1).isNotEqualTo(usuGemp2);
        usuGemp1.setId(null);
        assertThat(usuGemp1).isNotEqualTo(usuGemp2);
    }
}
