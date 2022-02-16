import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './ejec-cuentas.reducer';
import { IEjecCuentas } from 'app/shared/model/ejec-cuentas.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const EjecCuentas = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const ejecCuentasList = useAppSelector(state => state.ejecCuentas.entities);
  const loading = useAppSelector(state => state.ejecCuentas.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="ejec-cuentas-heading" data-cy="EjecCuentasHeading">
        Ejec Cuentas
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Ejec Cuentas
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {ejecCuentasList && ejecCuentasList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Telefono</th>
                <th>Apellido</th>
                <th>Celular</th>
                <th>Mail</th>
                <th>Nombre</th>
                <th>Cod Vendedor</th>
                <th>Legajo</th>
                <th>Segmento</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ejecCuentasList.map((ejecCuentas, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${ejecCuentas.id}`} color="link" size="sm">
                      {ejecCuentas.id}
                    </Button>
                  </td>
                  <td>{ejecCuentas.telefono}</td>
                  <td>{ejecCuentas.apellido}</td>
                  <td>{ejecCuentas.celular}</td>
                  <td>{ejecCuentas.mail}</td>
                  <td>{ejecCuentas.nombre}</td>
                  <td>{ejecCuentas.codVendedor}</td>
                  <td>{ejecCuentas.legajo}</td>
                  <td>
                    {ejecCuentas.segmento ? <Link to={`segmento/${ejecCuentas.segmento.id}`}>{ejecCuentas.segmento.descripcion}</Link> : ''}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ejecCuentas.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ejecCuentas.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ejecCuentas.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Ejec Cuentas found</div>
        )}
      </div>
    </div>
  );
};

export default EjecCuentas;
