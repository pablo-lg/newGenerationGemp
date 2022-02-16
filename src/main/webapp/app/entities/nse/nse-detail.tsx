import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './nse.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const NSEDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const nSEEntity = useAppSelector(state => state.nSE.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="nSEDetailsHeading">NSE</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{nSEEntity.id}</dd>
          <dt>
            <span id="descripcion">Descripcion</span>
          </dt>
          <dd>{nSEEntity.descripcion}</dd>
          <dt>
            <span id="activo">Activo</span>
          </dt>
          <dd>{nSEEntity.activo ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/nse" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/nse/${nSEEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default NSEDetail;
