import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './filtro-rep.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FiltroRepDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const filtroRepEntity = useAppSelector(state => state.filtroRep.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="filtroRepDetailsHeading">FiltroRep</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{filtroRepEntity.id}</dd>
          <dt>
            <span id="nombre">Nombre</span>
          </dt>
          <dd>{filtroRepEntity.nombre}</dd>
          <dt>
            <span id="filtro">Filtro</span>
          </dt>
          <dd>{filtroRepEntity.filtro}</dd>
        </dl>
        <Button tag={Link} to="/filtro-rep" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/filtro-rep/${filtroRepEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default FiltroRepDetail;
