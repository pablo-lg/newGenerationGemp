import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './tipo-emp.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TipoEmpDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const tipoEmpEntity = useAppSelector(state => state.tipoEmp.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tipoEmpDetailsHeading">TipoEmp</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{tipoEmpEntity.id}</dd>
          <dt>
            <span id="demanda">Demanda</span>
          </dt>
          <dd>{tipoEmpEntity.demanda ? 'true' : 'false'}</dd>
          <dt>
            <span id="descripcion">Descripcion</span>
          </dt>
          <dd>{tipoEmpEntity.descripcion}</dd>
        </dl>
        <Button tag={Link} to="/tipo-emp" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tipo-emp/${tipoEmpEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default TipoEmpDetail;
