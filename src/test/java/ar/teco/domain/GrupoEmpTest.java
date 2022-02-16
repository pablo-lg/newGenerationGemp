package ar.teco.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.teco.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrupoEmpTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrupoEmp.class);
        GrupoEmp grupoEmp1 = new GrupoEmp();
        grupoEmp1.setId(1L);
        GrupoEmp grupoEmp2 = new GrupoEmp();
        grupoEmp2.setId(grupoEmp1.getId());
        assertThat(grupoEmp1).isEqualTo(grupoEmp2);
        grupoEmp2.setId(2L);
        assertThat(grupoEmp1).isNotEqualTo(grupoEmp2);
        grupoEmp1.setId(null);
        assertThat(grupoEmp1).isNotEqualTo(grupoEmp2);
    }
}
