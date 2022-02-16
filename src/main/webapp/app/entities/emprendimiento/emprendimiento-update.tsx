import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IGrupoEmp } from 'app/shared/model/grupo-emp.model';
import { getEntities as getGrupoEmps } from 'app/entities/grupo-emp/grupo-emp.reducer';
import { ITipoObra } from 'app/shared/model/tipo-obra.model';
import { getEntities as getTipoObras } from 'app/entities/tipo-obra/tipo-obra.reducer';
import { ITipoEmp } from 'app/shared/model/tipo-emp.model';
import { getEntities as getTipoEmps } from 'app/entities/tipo-emp/tipo-emp.reducer';
import { IDespliegue } from 'app/shared/model/despliegue.model';
import { getEntities as getDespliegues } from 'app/entities/despliegue/despliegue.reducer';
import { INSE } from 'app/shared/model/nse.model';
import { getEntities as getNSes } from 'app/entities/nse/nse.reducer';
import { ISegmento } from 'app/shared/model/segmento.model';
import { getEntities as getSegmentos } from 'app/entities/segmento/segmento.reducer';
import { ITecnologia } from 'app/shared/model/tecnologia.model';
import { getEntities as getTecnologias } from 'app/entities/tecnologia/tecnologia.reducer';
import { IEjecCuentas } from 'app/shared/model/ejec-cuentas.model';
import { getEntities as getEjecCuentas } from 'app/entities/ejec-cuentas/ejec-cuentas.reducer';
import { IDireccion } from 'app/shared/model/direccion.model';
import { getEntities as getDireccions } from 'app/entities/direccion/direccion.reducer';
import { ICompetencia } from 'app/shared/model/competencia.model';
import { getEntities as getCompetencias } from 'app/entities/competencia/competencia.reducer';
import { getEntity, updateEntity, createEntity, reset } from './emprendimiento.reducer';
import { IEmprendimiento } from 'app/shared/model/emprendimiento.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { EstadoFirma } from 'app/shared/model/enumerations/estado-firma.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';
import { EstadoBC } from 'app/shared/model/enumerations/estado-bc.model';

export const EmprendimientoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const grupoEmps = useAppSelector(state => state.grupoEmp.entities);
  const tipoObras = useAppSelector(state => state.tipoObra.entities);
  const tipoEmps = useAppSelector(state => state.tipoEmp.entities);
  const despliegues = useAppSelector(state => state.despliegue.entities);
  const nSES = useAppSelector(state => state.nSE.entities);
  const segmentos = useAppSelector(state => state.segmento.entities);
  const tecnologias = useAppSelector(state => state.tecnologia.entities);
  const ejecCuentas = useAppSelector(state => state.ejecCuentas.entities);
  const direccions = useAppSelector(state => state.direccion.entities);
  const competencias = useAppSelector(state => state.competencia.entities);
  const emprendimientoEntity = useAppSelector(state => state.emprendimiento.entity);
  const loading = useAppSelector(state => state.emprendimiento.loading);
  const updating = useAppSelector(state => state.emprendimiento.updating);
  const updateSuccess = useAppSelector(state => state.emprendimiento.updateSuccess);
  const estadoFirmaValues = Object.keys(EstadoFirma);
  const estadoValues = Object.keys(Estado);
  const estadoBCValues = Object.keys(EstadoBC);
  const handleClose = () => {
    props.history.push('/emprendimiento' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getGrupoEmps({}));
    dispatch(getTipoObras({}));
    dispatch(getTipoEmps({}));
    dispatch(getDespliegues({}));
    dispatch(getNSes({}));
    dispatch(getSegmentos({}));
    dispatch(getTecnologias({}));
    dispatch(getEjecCuentas({}));
    dispatch(getDireccions({}));
    dispatch(getCompetencias({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...emprendimientoEntity,
      ...values,
      grupoEmp: grupoEmps.find(it => it.id.toString() === values.grupoEmp.toString()),
      tipoObra: tipoObras.find(it => it.id.toString() === values.tipoObra.toString()),
      tipoEmp: tipoEmps.find(it => it.id.toString() === values.tipoEmp.toString()),
      despliegue: despliegues.find(it => it.id.toString() === values.despliegue.toString()),
      nSE: nSES.find(it => it.id.toString() === values.nSE.toString()),
      segmento: segmentos.find(it => it.id.toString() === values.segmento.toString()),
      tecnologia: tecnologias.find(it => it.id.toString() === values.tecnologia.toString()),
      ejecCuentas: ejecCuentas.find(it => it.id.toString() === values.ejecCuentas.toString()),
      direccion: direccions.find(it => it.id.toString() === values.direccion.toString()),
      compentencia: competencias.find(it => it.id.toString() === values.compentencia.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          estadoFirma: 'FIRMADO',
          estado: 'SIN_ESTADO',
          estadoBC: 'APROBADO',
          ...emprendimientoEntity,
          grupoEmp: emprendimientoEntity?.grupoEmp?.id,
          tipoObra: emprendimientoEntity?.tipoObra?.id,
          tipoEmp: emprendimientoEntity?.tipoEmp?.id,
          despliegue: emprendimientoEntity?.despliegue?.id,
          nSE: emprendimientoEntity?.nSE?.id,
          segmento: emprendimientoEntity?.segmento?.id,
          tecnologia: emprendimientoEntity?.tecnologia?.id,
          ejecCuentas: emprendimientoEntity?.ejecCuentas?.id,
          direccion: emprendimientoEntity?.direccion?.id,
          compentencia: emprendimientoEntity?.compentencia?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newGenerationGempApp.emprendimiento.home.createOrEditLabel" data-cy="EmprendimientoCreateUpdateHeading">
            Create or edit a Emprendimiento
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="emprendimiento-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField label="Nombre" id="emprendimiento-nombre" name="nombre" data-cy="nombre" type="text" />
              <ValidatedField label="Contacto" id="emprendimiento-contacto" name="contacto" data-cy="contacto" type="text" />
              <ValidatedField
                label="Fecha Fin Obra"
                id="emprendimiento-fechaFinObra"
                name="fechaFinObra"
                data-cy="fechaFinObra"
                type="date"
              />
              <ValidatedField label="Codigo Obra" id="emprendimiento-codigoObra" name="codigoObra" data-cy="codigoObra" type="text" />
              <ValidatedField
                label="Elementos De Red"
                id="emprendimiento-elementosDeRed"
                name="elementosDeRed"
                data-cy="elementosDeRed"
                type="text"
              />
              <ValidatedField
                label="Clientes Catv"
                id="emprendimiento-clientesCatv"
                name="clientesCatv"
                data-cy="clientesCatv"
                type="text"
              />
              <ValidatedField
                label="Clientes Fibertel"
                id="emprendimiento-clientesFibertel"
                name="clientesFibertel"
                data-cy="clientesFibertel"
                type="text"
              />
              <ValidatedField
                label="Clientes Fibertel Lite"
                id="emprendimiento-clientesFibertelLite"
                name="clientesFibertelLite"
                data-cy="clientesFibertelLite"
                type="text"
              />
              <ValidatedField
                label="Clientes Flow"
                id="emprendimiento-clientesFlow"
                name="clientesFlow"
                data-cy="clientesFlow"
                type="text"
              />
              <ValidatedField
                label="Clientes Combo"
                id="emprendimiento-clientesCombo"
                name="clientesCombo"
                data-cy="clientesCombo"
                type="text"
              />
              <ValidatedField label="Lineas Voz" id="emprendimiento-lineasVoz" name="lineasVoz" data-cy="lineasVoz" type="text" />
              <ValidatedField
                label="Meses De Finalizado"
                id="emprendimiento-mesesDeFinalizado"
                name="mesesDeFinalizado"
                data-cy="mesesDeFinalizado"
                type="text"
              />
              <ValidatedField label="Altas BC" id="emprendimiento-altasBC" name="altasBC" data-cy="altasBC" type="text" />
              <ValidatedField
                label="Penetracion Viv Lot"
                id="emprendimiento-penetracionVivLot"
                name="penetracionVivLot"
                data-cy="penetracionVivLot"
                type="text"
              />
              <ValidatedField
                label="Penetracion BC"
                id="emprendimiento-penetracionBC"
                name="penetracionBC"
                data-cy="penetracionBC"
                type="text"
              />
              <ValidatedField label="Demanda 1" id="emprendimiento-demanda1" name="demanda1" data-cy="demanda1" type="text" />
              <ValidatedField label="Demanda 2" id="emprendimiento-demanda2" name="demanda2" data-cy="demanda2" type="text" />
              <ValidatedField label="Demanda 3" id="emprendimiento-demanda3" name="demanda3" data-cy="demanda3" type="text" />
              <ValidatedField label="Demanda 4" id="emprendimiento-demanda4" name="demanda4" data-cy="demanda4" type="text" />
              <ValidatedField label="Demanda 5" id="emprendimiento-demanda5" name="demanda5" data-cy="demanda5" type="text" />
              <ValidatedField label="Lotes" id="emprendimiento-lotes" name="lotes" data-cy="lotes" type="text" />
              <ValidatedField label="Viviendas" id="emprendimiento-viviendas" name="viviendas" data-cy="viviendas" type="text" />
              <ValidatedField label="Com Prof" id="emprendimiento-comProf" name="comProf" data-cy="comProf" type="text" />
              <ValidatedField
                label="Habitaciones"
                id="emprendimiento-habitaciones"
                name="habitaciones"
                data-cy="habitaciones"
                type="text"
              />
              <ValidatedField label="Manzanas" id="emprendimiento-manzanas" name="manzanas" data-cy="manzanas" type="text" />
              <ValidatedField label="Demanda" id="emprendimiento-demanda" name="demanda" data-cy="demanda" type="text" />
              <ValidatedField
                label="Fecha De Relevamiento"
                id="emprendimiento-fechaDeRelevamiento"
                name="fechaDeRelevamiento"
                data-cy="fechaDeRelevamiento"
                type="date"
              />
              <ValidatedField label="Telefono" id="emprendimiento-telefono" name="telefono" data-cy="telefono" type="text" />
              <ValidatedField
                label="Ano Priorizacion"
                id="emprendimiento-anoPriorizacion"
                name="anoPriorizacion"
                data-cy="anoPriorizacion"
                type="date"
              />
              <ValidatedField
                label="Contrato Open"
                id="emprendimiento-contratoOpen"
                name="contratoOpen"
                data-cy="contratoOpen"
                type="text"
              />
              <ValidatedField
                label="Negociacion"
                id="emprendimiento-negociacion"
                name="negociacion"
                data-cy="negociacion"
                check
                type="checkbox"
              />
              <ValidatedField label="Fecha" id="emprendimiento-fecha" name="fecha" data-cy="fecha" type="date" />
              <ValidatedField
                label="Codigo De Firma"
                id="emprendimiento-codigoDeFirma"
                name="codigoDeFirma"
                data-cy="codigoDeFirma"
                type="text"
              />
              <ValidatedField label="Fecha Firma" id="emprendimiento-fechaFirma" name="fechaFirma" data-cy="fechaFirma" type="date" />
              <ValidatedField
                label="Observaciones"
                id="emprendimiento-observaciones"
                name="observaciones"
                data-cy="observaciones"
                type="text"
              />
              <ValidatedField label="Comentario" id="emprendimiento-comentario" name="comentario" data-cy="comentario" type="text" />
              <ValidatedField label="Comen Can" id="emprendimiento-comenCan" name="comenCan" data-cy="comenCan" type="text" />
              <ValidatedField label="Estado Firma" id="emprendimiento-estadoFirma" name="estadoFirma" data-cy="estadoFirma" type="select">
                {estadoFirmaValues.map(estadoFirma => (
                  <option value={estadoFirma} key={estadoFirma}>
                    {estadoFirma}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Estado" id="emprendimiento-estado" name="estado" data-cy="estado" type="select">
                {estadoValues.map(estado => (
                  <option value={estado} key={estado}>
                    {estado}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Estado BC" id="emprendimiento-estadoBC" name="estadoBC" data-cy="estadoBC" type="select">
                {estadoBCValues.map(estadoBC => (
                  <option value={estadoBC} key={estadoBC}>
                    {estadoBC}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField id="emprendimiento-grupoEmp" name="grupoEmp" data-cy="grupoEmp" label="Grupo Emp" type="select">
                <option value="" key="0" />
                {grupoEmps
                  ? grupoEmps.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="emprendimiento-tipoObra" name="tipoObra" data-cy="tipoObra" label="Tipo Obra" type="select">
                <option value="" key="0" />
                {tipoObras
                  ? tipoObras.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="emprendimiento-tipoEmp" name="tipoEmp" data-cy="tipoEmp" label="Tipo Emp" type="select">
                <option value="" key="0" />
                {tipoEmps
                  ? tipoEmps.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="emprendimiento-despliegue" name="despliegue" data-cy="despliegue" label="Despliegue" type="select">
                <option value="" key="0" />
                {despliegues
                  ? despliegues.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="emprendimiento-nSE" name="nSE" data-cy="nSE" label="N SE" type="select">
                <option value="" key="0" />
                {nSES
                  ? nSES.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="emprendimiento-segmento" name="segmento" data-cy="segmento" label="Segmento" type="select">
                <option value="" key="0" />
                {segmentos
                  ? segmentos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="emprendimiento-tecnologia" name="tecnologia" data-cy="tecnologia" label="Tecnologia" type="select">
                <option value="" key="0" />
                {tecnologias
                  ? tecnologias.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="emprendimiento-ejecCuentas" name="ejecCuentas" data-cy="ejecCuentas" label="Ejec Cuentas" type="select">
                <option value="" key="0" />
                {ejecCuentas
                  ? ejecCuentas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.nombre}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="emprendimiento-direccion" name="direccion" data-cy="direccion" label="Direccion" type="select">
                <option value="" key="0" />
                {direccions
                  ? direccions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.calle}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="emprendimiento-compentencia"
                name="compentencia"
                data-cy="compentencia"
                label="Compentencia"
                type="select"
              >
                <option value="" key="0" />
                {competencias
                  ? competencias.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/emprendimiento" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default EmprendimientoUpdate;
