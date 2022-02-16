import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Demanda from './demanda';
import DemandaDetail from './demanda-detail';
import DemandaUpdate from './demanda-update';
import DemandaDeleteDialog from './demanda-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DemandaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DemandaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DemandaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Demanda} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DemandaDeleteDialog} />
  </>
);

export default Routes;
