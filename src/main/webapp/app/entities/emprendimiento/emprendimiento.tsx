import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './emprendimiento.reducer';
import { IEmprendimiento } from 'app/shared/model/emprendimiento.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Emprendimiento = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const emprendimientoList = useAppSelector(state => state.emprendimiento.entities);
  const loading = useAppSelector(state => state.emprendimiento.loading);
  const totalItems = useAppSelector(state => state.emprendimiento.totalItems);

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
      <h2 id="emprendimiento-heading" data-cy="EmprendimientoHeading">
        Emprendimientos
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Emprendimiento
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {emprendimientoList && emprendimientoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nombre')}>
                  Nombre <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('contacto')}>
                  Contacto <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaFinObra')}>
                  Fecha Fin Obra <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('codigoObra')}>
                  Codigo Obra <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('elementosDeRed')}>
                  Elementos De Red <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('clientesCatv')}>
                  Clientes Catv <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('clientesFibertel')}>
                  Clientes Fibertel <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('clientesFibertelLite')}>
                  Clientes Fibertel Lite <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('clientesFlow')}>
                  Clientes Flow <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('clientesCombo')}>
                  Clientes Combo <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lineasVoz')}>
                  Lineas Voz <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('mesesDeFinalizado')}>
                  Meses De Finalizado <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('altasBC')}>
                  Altas BC <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('penetracionVivLot')}>
                  Penetracion Viv Lot <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('penetracionBC')}>
                  Penetracion BC <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('demanda1')}>
                  Demanda 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('demanda2')}>
                  Demanda 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('demanda3')}>
                  Demanda 3 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('demanda4')}>
                  Demanda 4 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('demanda5')}>
                  Demanda 5 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lotes')}>
                  Lotes <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('viviendas')}>
                  Viviendas <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('comProf')}>
                  Com Prof <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('habitaciones')}>
                  Habitaciones <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('manzanas')}>
                  Manzanas <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('demanda')}>
                  Demanda <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaDeRelevamiento')}>
                  Fecha De Relevamiento <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('telefono')}>
                  Telefono <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('anoPriorizacion')}>
                  Ano Priorizacion <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('contratoOpen')}>
                  Contrato Open <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('negociacion')}>
                  Negociacion <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fecha')}>
                  Fecha <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('codigoDeFirma')}>
                  Codigo De Firma <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaFirma')}>
                  Fecha Firma <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('observaciones')}>
                  Observaciones <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('comentario')}>
                  Comentario <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('comenCan')}>
                  Comen Can <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estadoFirma')}>
                  Estado Firma <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estado')}>
                  Estado <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estadoBC')}>
                  Estado BC <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Grupo Emp <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Tipo Obra <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Tipo Emp <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Despliegue <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  N SE <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Segmento <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Tecnologia <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Ejec Cuentas <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Direccion <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Compentencia <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {emprendimientoList.map((emprendimiento, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${emprendimiento.id}`} color="link" size="sm">
                      {emprendimiento.id}
                    </Button>
                  </td>
                  <td>{emprendimiento.nombre}</td>
                  <td>{emprendimiento.contacto}</td>
                  <td>
                    {emprendimiento.fechaFinObra ? (
                      <TextFormat type="date" value={emprendimiento.fechaFinObra} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{emprendimiento.codigoObra}</td>
                  <td>{emprendimiento.elementosDeRed}</td>
                  <td>{emprendimiento.clientesCatv}</td>
                  <td>{emprendimiento.clientesFibertel}</td>
                  <td>{emprendimiento.clientesFibertelLite}</td>
                  <td>{emprendimiento.clientesFlow}</td>
                  <td>{emprendimiento.clientesCombo}</td>
                  <td>{emprendimiento.lineasVoz}</td>
                  <td>{emprendimiento.mesesDeFinalizado}</td>
                  <td>{emprendimiento.altasBC}</td>
                  <td>{emprendimiento.penetracionVivLot}</td>
                  <td>{emprendimiento.penetracionBC}</td>
                  <td>{emprendimiento.demanda1}</td>
                  <td>{emprendimiento.demanda2}</td>
                  <td>{emprendimiento.demanda3}</td>
                  <td>{emprendimiento.demanda4}</td>
                  <td>{emprendimiento.demanda5}</td>
                  <td>{emprendimiento.lotes}</td>
                  <td>{emprendimiento.viviendas}</td>
                  <td>{emprendimiento.comProf}</td>
                  <td>{emprendimiento.habitaciones}</td>
                  <td>{emprendimiento.manzanas}</td>
                  <td>{emprendimiento.demanda}</td>
                  <td>
                    {emprendimiento.fechaDeRelevamiento ? (
                      <TextFormat type="date" value={emprendimiento.fechaDeRelevamiento} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{emprendimiento.telefono}</td>
                  <td>
                    {emprendimiento.anoPriorizacion ? (
                      <TextFormat type="date" value={emprendimiento.anoPriorizacion} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{emprendimiento.contratoOpen}</td>
                  <td>{emprendimiento.negociacion ? 'true' : 'false'}</td>
                  <td>
                    {emprendimiento.fecha ? <TextFormat type="date" value={emprendimiento.fecha} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{emprendimiento.codigoDeFirma}</td>
                  <td>
                    {emprendimiento.fechaFirma ? (
                      <TextFormat type="date" value={emprendimiento.fechaFirma} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{emprendimiento.observaciones}</td>
                  <td>{emprendimiento.comentario}</td>
                  <td>{emprendimiento.comenCan}</td>
                  <td>{emprendimiento.estadoFirma}</td>
                  <td>{emprendimiento.estado}</td>
                  <td>{emprendimiento.estadoBC}</td>
                  <td>
                    {emprendimiento.grupoEmp ? (
                      <Link to={`grupo-emp/${emprendimiento.grupoEmp.id}`}>{emprendimiento.grupoEmp.descripcion}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {emprendimiento.tipoObra ? (
                      <Link to={`tipo-obra/${emprendimiento.tipoObra.id}`}>{emprendimiento.tipoObra.descripcion}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {emprendimiento.tipoEmp ? (
                      <Link to={`tipo-emp/${emprendimiento.tipoEmp.id}`}>{emprendimiento.tipoEmp.descripcion}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {emprendimiento.despliegue ? (
                      <Link to={`despliegue/${emprendimiento.despliegue.id}`}>{emprendimiento.despliegue.descripcion}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{emprendimiento.nSE ? <Link to={`nse/${emprendimiento.nSE.id}`}>{emprendimiento.nSE.descripcion}</Link> : ''}</td>
                  <td>
                    {emprendimiento.segmento ? (
                      <Link to={`segmento/${emprendimiento.segmento.id}`}>{emprendimiento.segmento.descripcion}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {emprendimiento.tecnologia ? (
                      <Link to={`tecnologia/${emprendimiento.tecnologia.id}`}>{emprendimiento.tecnologia.descripcion}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {emprendimiento.ejecCuentas ? (
                      <Link to={`ejec-cuentas/${emprendimiento.ejecCuentas.id}`}>{emprendimiento.ejecCuentas.nombre}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {emprendimiento.direccion ? (
                      <Link to={`direccion/${emprendimiento.direccion.id}`}>{emprendimiento.direccion.calle}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {emprendimiento.compentencia ? (
                      <Link to={`competencia/${emprendimiento.compentencia.id}`}>{emprendimiento.compentencia.descripcion}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${emprendimiento.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${emprendimiento.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${emprendimiento.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
          !loading && <div className="alert alert-warning">No Emprendimientos found</div>
        )}
      </div>
      {totalItems ? (
        <div className={emprendimientoList && emprendimientoList.length > 0 ? '' : 'd-none'}>
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

export default Emprendimiento;
