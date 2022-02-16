import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './demanda.reducer';
import { IDemanda } from 'app/shared/model/demanda.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Demanda = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const demandaList = useAppSelector(state => state.demanda.entities);
  const loading = useAppSelector(state => state.demanda.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="demanda-heading" data-cy="DemandaHeading">
        Demandas
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Demanda
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {demandaList && demandaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>A 1</th>
                <th>A 2</th>
                <th>A 3</th>
                <th>A 4</th>
                <th>A 5</th>
                <th>Tipo Emp</th>
                <th>Despliegue</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {demandaList.map((demanda, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${demanda.id}`} color="link" size="sm">
                      {demanda.id}
                    </Button>
                  </td>
                  <td>{demanda.a1}</td>
                  <td>{demanda.a2}</td>
                  <td>{demanda.a3}</td>
                  <td>{demanda.a4}</td>
                  <td>{demanda.a5}</td>
                  <td>{demanda.tipoEmp ? <Link to={`tipo-emp/${demanda.tipoEmp.id}`}>{demanda.tipoEmp.descripcion}</Link> : ''}</td>
                  <td>
                    {demanda.despliegue ? <Link to={`despliegue/${demanda.despliegue.id}`}>{demanda.despliegue.descripcion}</Link> : ''}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${demanda.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${demanda.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${demanda.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Demandas found</div>
        )}
      </div>
    </div>
  );
};

export default Demanda;
