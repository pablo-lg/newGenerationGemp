import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './tipo-emp.reducer';
import { ITipoEmp } from 'app/shared/model/tipo-emp.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TipoEmp = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const tipoEmpList = useAppSelector(state => state.tipoEmp.entities);
  const loading = useAppSelector(state => state.tipoEmp.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="tipo-emp-heading" data-cy="TipoEmpHeading">
        Tipo Emps
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Tipo Emp
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {tipoEmpList && tipoEmpList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Demanda</th>
                <th>Descripcion</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tipoEmpList.map((tipoEmp, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${tipoEmp.id}`} color="link" size="sm">
                      {tipoEmp.id}
                    </Button>
                  </td>
                  <td>{tipoEmp.demanda ? 'true' : 'false'}</td>
                  <td>{tipoEmp.descripcion}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tipoEmp.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tipoEmp.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tipoEmp.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Tipo Emps found</div>
        )}
      </div>
    </div>
  );
};

export default TipoEmp;
