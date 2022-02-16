import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './parametros.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ParametrosDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const parametrosEntity = useAppSelector(state => state.parametros.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="parametrosDetailsHeading">Parametros</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{parametrosEntity.id}</dd>
          <dt>
            <span id="clave">Clave</span>
          </dt>
          <dd>{parametrosEntity.clave}</dd>
          <dt>
            <span id="valor">Valor</span>
          </dt>
          <dd>{parametrosEntity.valor}</dd>
        </dl>
        <Button tag={Link} to="/parametros" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/parametros/${parametrosEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ParametrosDetail;
