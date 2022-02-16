import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './usu-gemp.reducer';
import { IUsuGemp } from 'app/shared/model/usu-gemp.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const UsuGemp = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const usuGempList = useAppSelector(state => state.usuGemp.entities);
  const loading = useAppSelector(state => state.usuGemp.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="usu-gemp-heading" data-cy="UsuGempHeading">
        Usu Gemps
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Usu Gemp
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {usuGempList && usuGempList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Usu</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Email</th>
                <th>Perfiles</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {usuGempList.map((usuGemp, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${usuGemp.id}`} color="link" size="sm">
                      {usuGemp.id}
                    </Button>
                  </td>
                  <td>{usuGemp.usu}</td>
                  <td>{usuGemp.nombre}</td>
                  <td>{usuGemp.apellido}</td>
                  <td>{usuGemp.email}</td>
                  <td>{usuGemp.perfiles}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${usuGemp.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${usuGemp.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${usuGemp.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Usu Gemps found</div>
        )}
      </div>
    </div>
  );
};

export default UsuGemp;
