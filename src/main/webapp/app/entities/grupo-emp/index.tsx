import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GrupoEmp from './grupo-emp';
import GrupoEmpDetail from './grupo-emp-detail';
import GrupoEmpUpdate from './grupo-emp-update';
import GrupoEmpDeleteDialog from './grupo-emp-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={GrupoEmpUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={GrupoEmpUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={GrupoEmpDetail} />
      <ErrorBoundaryRoute path={match.url} component={GrupoEmp} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={GrupoEmpDeleteDialog} />
  </>
);

export default Routes;
