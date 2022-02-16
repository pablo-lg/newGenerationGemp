import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './usu-gemp.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const UsuGempDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const usuGempEntity = useAppSelector(state => state.usuGemp.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="usuGempDetailsHeading">UsuGemp</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{usuGempEntity.id}</dd>
          <dt>
            <span id="usu">Usu</span>
          </dt>
          <dd>{usuGempEntity.usu}</dd>
          <dt>
            <span id="nombre">Nombre</span>
          </dt>
          <dd>{usuGempEntity.nombre}</dd>
          <dt>
            <span id="apellido">Apellido</span>
          </dt>
          <dd>{usuGempEntity.apellido}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{usuGempEntity.email}</dd>
          <dt>
            <span id="perfiles">Perfiles</span>
          </dt>
          <dd>{usuGempEntity.perfiles}</dd>
        </dl>
        <Button tag={Link} to="/usu-gemp" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/usu-gemp/${usuGempEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default UsuGempDetail;
