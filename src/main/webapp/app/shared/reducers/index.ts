import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication from './authentication';
import applicationProfile from './application-profile';

import administration from 'app/modules/administration/administration.reducer';
import userManagement from 'app/modules/administration/user-management/user-management.reducer';
import register from 'app/modules/account/register/register.reducer';
import activate from 'app/modules/account/activate/activate.reducer';
import password from 'app/modules/account/password/password.reducer';
import settings from 'app/modules/account/settings/settings.reducer';
import passwordReset from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import despliegue from 'app/entities/despliegue/despliegue.reducer';
// prettier-ignore
import segmento from 'app/entities/segmento/segmento.reducer';
// prettier-ignore
import competencia from 'app/entities/competencia/competencia.reducer';
// prettier-ignore
import tecnologia from 'app/entities/tecnologia/tecnologia.reducer';
// prettier-ignore
import nSE from 'app/entities/nse/nse.reducer';
// prettier-ignore
import tipoObra from 'app/entities/tipo-obra/tipo-obra.reducer';
// prettier-ignore
import tipoEmp from 'app/entities/tipo-emp/tipo-emp.reducer';
// prettier-ignore
import ejecCuentas from 'app/entities/ejec-cuentas/ejec-cuentas.reducer';
// prettier-ignore
import direccion from 'app/entities/direccion/direccion.reducer';
// prettier-ignore
import emprendimiento from 'app/entities/emprendimiento/emprendimiento.reducer';
// prettier-ignore
import demanda from 'app/entities/demanda/demanda.reducer';
// prettier-ignore
import histoWF from 'app/entities/histo-wf/histo-wf.reducer';
// prettier-ignore
import parametros from 'app/entities/parametros/parametros.reducer';
// prettier-ignore
import grupoEmp from 'app/entities/grupo-emp/grupo-emp.reducer';
// prettier-ignore
import filtroRep from 'app/entities/filtro-rep/filtro-rep.reducer';
// prettier-ignore
import usuGemp from 'app/entities/usu-gemp/usu-gemp.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  despliegue,
  segmento,
  competencia,
  tecnologia,
  nSE,
  tipoObra,
  tipoEmp,
  ejecCuentas,
  direccion,
  emprendimiento,
  demanda,
  histoWF,
  parametros,
  grupoEmp,
  filtroRep,
  usuGemp,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
