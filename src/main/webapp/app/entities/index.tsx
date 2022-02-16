import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Despliegue from './despliegue';
import Segmento from './segmento';
import Competencia from './competencia';
import Tecnologia from './tecnologia';
import NSE from './nse';
import TipoObra from './tipo-obra';
import TipoEmp from './tipo-emp';
import EjecCuentas from './ejec-cuentas';
import Direccion from './direccion';
import Emprendimiento from './emprendimiento';
import Demanda from './demanda';
import HistoWF from './histo-wf';
import Parametros from './parametros';
import GrupoEmp from './grupo-emp';
import FiltroRep from './filtro-rep';
import UsuGemp from './usu-gemp';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}despliegue`} component={Despliegue} />
      <ErrorBoundaryRoute path={`${match.url}segmento`} component={Segmento} />
      <ErrorBoundaryRoute path={`${match.url}competencia`} component={Competencia} />
      <ErrorBoundaryRoute path={`${match.url}tecnologia`} component={Tecnologia} />
      <ErrorBoundaryRoute path={`${match.url}nse`} component={NSE} />
      <ErrorBoundaryRoute path={`${match.url}tipo-obra`} component={TipoObra} />
      <ErrorBoundaryRoute path={`${match.url}tipo-emp`} component={TipoEmp} />
      <ErrorBoundaryRoute path={`${match.url}ejec-cuentas`} component={EjecCuentas} />
      <ErrorBoundaryRoute path={`${match.url}direccion`} component={Direccion} />
      <ErrorBoundaryRoute path={`${match.url}emprendimiento`} component={Emprendimiento} />
      <ErrorBoundaryRoute path={`${match.url}demanda`} component={Demanda} />
      <ErrorBoundaryRoute path={`${match.url}histo-wf`} component={HistoWF} />
      <ErrorBoundaryRoute path={`${match.url}parametros`} component={Parametros} />
      <ErrorBoundaryRoute path={`${match.url}grupo-emp`} component={GrupoEmp} />
      <ErrorBoundaryRoute path={`${match.url}filtro-rep`} component={FiltroRep} />
      <ErrorBoundaryRoute path={`${match.url}usu-gemp`} component={UsuGemp} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
