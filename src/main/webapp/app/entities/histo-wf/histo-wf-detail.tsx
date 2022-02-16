import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './histo-wf.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const HistoWFDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const histoWFEntity = useAppSelector(state => state.histoWF.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="histoWFDetailsHeading">HistoWF</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{histoWFEntity.id}</dd>
          <dt>
            <span id="estadoAnterior">Estado Anterior</span>
          </dt>
          <dd>{histoWFEntity.estadoAnterior}</dd>
          <dt>
            <span id="estadoActual">Estado Actual</span>
          </dt>
          <dd>{histoWFEntity.estadoActual}</dd>
          <dt>Emprendimiento</dt>
          <dd>{histoWFEntity.emprendimiento ? histoWFEntity.emprendimiento.nombre : ''}</dd>
        </dl>
        <Button tag={Link} to="/histo-wf" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/histo-wf/${histoWFEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default HistoWFDetail;
