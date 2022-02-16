import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './direccion.reducer';
import { IDireccion } from 'app/shared/model/direccion.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Direccion = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const direccionList = useAppSelector(state => state.direccion.entities);
  const loading = useAppSelector(state => state.direccion.loading);
  const totalItems = useAppSelector(state => state.direccion.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const { match } = props;

  return (
    <div>
      <h2 id="direccion-heading" data-cy="DireccionHeading">
        Direccions
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Direccion
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {direccionList && direccionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('identification')}>
                  Identification <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('pais')}>
                  Pais <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('provincia')}>
                  Provincia <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('partido')}>
                  Partido <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('localidad')}>
                  Localidad <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('calle')}>
                  Calle <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('altura')}>
                  Altura <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('region')}>
                  Region <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subregion')}>
                  Subregion <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hub')}>
                  Hub <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('barriosEspeciales')}>
                  Barrios Especiales <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('codigoPostal')}>
                  Codigo Postal <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tipoCalle')}>
                  Tipo Calle <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('zonaCompetencia')}>
                  Zona Competencia <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('intersectionLeft')}>
                  Intersection Left <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('intersectionRight')}>
                  Intersection Right <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('streetType')}>
                  Street Type <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('latitud')}>
                  Latitud <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('longitud')}>
                  Longitud <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('elementosDeRed')}>
                  Elementos De Red <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {direccionList.map((direccion, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${direccion.id}`} color="link" size="sm">
                      {direccion.id}
                    </Button>
                  </td>
                  <td>{direccion.identification}</td>
                  <td>{direccion.pais}</td>
                  <td>{direccion.provincia}</td>
                  <td>{direccion.partido}</td>
                  <td>{direccion.localidad}</td>
                  <td>{direccion.calle}</td>
                  <td>{direccion.altura}</td>
                  <td>{direccion.region}</td>
                  <td>{direccion.subregion}</td>
                  <td>{direccion.hub}</td>
                  <td>{direccion.barriosEspeciales}</td>
                  <td>{direccion.codigoPostal}</td>
                  <td>{direccion.tipoCalle}</td>
                  <td>{direccion.zonaCompetencia}</td>
                  <td>{direccion.intersectionLeft}</td>
                  <td>{direccion.intersectionRight}</td>
                  <td>{direccion.streetType}</td>
                  <td>{direccion.latitud}</td>
                  <td>{direccion.longitud}</td>
                  <td>{direccion.elementosDeRed}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${direccion.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${direccion.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${direccion.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Direccions found</div>
        )}
      </div>
      {totalItems ? (
        <div className={direccionList && direccionList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default Direccion;
