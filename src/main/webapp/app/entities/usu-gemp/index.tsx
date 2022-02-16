import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UsuGemp from './usu-gemp';
import UsuGempDetail from './usu-gemp-detail';
import UsuGempUpdate from './usu-gemp-update';
import UsuGempDeleteDialog from './usu-gemp-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UsuGempUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UsuGempUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UsuGempDetail} />
      <ErrorBoundaryRoute path={match.url} component={UsuGemp} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UsuGempDeleteDialog} />
  </>
);

export default Routes;
