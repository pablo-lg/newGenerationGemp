import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HistoWF from './histo-wf';
import HistoWFDetail from './histo-wf-detail';
import HistoWFUpdate from './histo-wf-update';
import HistoWFDeleteDialog from './histo-wf-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HistoWFUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HistoWFUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HistoWFDetail} />
      <ErrorBoundaryRoute path={match.url} component={HistoWF} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={HistoWFDeleteDialog} />
  </>
);

export default Routes;
