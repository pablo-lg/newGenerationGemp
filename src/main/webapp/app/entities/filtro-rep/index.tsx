import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FiltroRep from './filtro-rep';
import FiltroRepDetail from './filtro-rep-detail';
import FiltroRepUpdate from './filtro-rep-update';
import FiltroRepDeleteDialog from './filtro-rep-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FiltroRepUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FiltroRepUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FiltroRepDetail} />
      <ErrorBoundaryRoute path={match.url} component={FiltroRep} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FiltroRepDeleteDialog} />
  </>
);

export default Routes;
