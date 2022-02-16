import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './demanda.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DemandaDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const demandaEntity = useAppSelector(state => state.demanda.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="demandaDetailsHeading">Demanda</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{demandaEntity.id}</dd>
          <dt>
            <span id="a1">A 1</span>
          </dt>
          <dd>{demandaEntity.a1}</dd>
          <dt>
            <span id="a2">A 2</span>
          </dt>
          <dd>{demandaEntity.a2}</dd>
          <dt>
            <span id="a3">A 3</span>
          </dt>
          <dd>{demandaEntity.a3}</dd>
          <dt>
            <span id="a4">A 4</span>
          </dt>
          <dd>{demandaEntity.a4}</dd>
          <dt>
            <span id="a5">A 5</span>
          </dt>
          <dd>{demandaEntity.a5}</dd>
          <dt>Tipo Emp</dt>
          <dd>{demandaEntity.tipoEmp ? demandaEntity.tipoEmp.descripcion : ''}</dd>
          <dt>Despliegue</dt>
          <dd>{demandaEntity.despliegue ? demandaEntity.despliegue.descripcion : ''}</dd>
        </dl>
        <Button tag={Link} to="/demanda" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/demanda/${demandaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default DemandaDetail;
