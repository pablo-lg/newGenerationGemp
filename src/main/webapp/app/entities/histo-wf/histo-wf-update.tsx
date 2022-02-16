import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IEmprendimiento } from 'app/shared/model/emprendimiento.model';
import { getEntities as getEmprendimientos } from 'app/entities/emprendimiento/emprendimiento.reducer';
import { getEntity, updateEntity, createEntity, reset } from './histo-wf.reducer';
import { IHistoWF } from 'app/shared/model/histo-wf.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { Estado } from 'app/shared/model/enumerations/estado.model';

export const HistoWFUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const emprendimientos = useAppSelector(state => state.emprendimiento.entities);
  const histoWFEntity = useAppSelector(state => state.histoWF.entity);
  const loading = useAppSelector(state => state.histoWF.loading);
  const updating = useAppSelector(state => state.histoWF.updating);
  const updateSuccess = useAppSelector(state => state.histoWF.updateSuccess);
  const estadoValues = Object.keys(Estado);
  const handleClose = () => {
    props.history.push('/histo-wf' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getEmprendimientos({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...histoWFEntity,
      ...values,
      emprendimiento: emprendimientos.find(it => it.id.toString() === values.emprendimiento.toString()),
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
          estadoAnterior: 'SIN_ESTADO',
          estadoActual: 'SIN_ESTADO',
          ...histoWFEntity,
          emprendimiento: histoWFEntity?.emprendimiento?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newGenerationGempApp.histoWF.home.createOrEditLabel" data-cy="HistoWFCreateUpdateHeading">
            Create or edit a HistoWF
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="histo-wf-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Estado Anterior"
                id="histo-wf-estadoAnterior"
                name="estadoAnterior"
                data-cy="estadoAnterior"
                type="select"
              >
                {estadoValues.map(estado => (
                  <option value={estado} key={estado}>
                    {estado}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Estado Actual" id="histo-wf-estadoActual" name="estadoActual" data-cy="estadoActual" type="select">
                {estadoValues.map(estado => (
                  <option value={estado} key={estado}>
                    {estado}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                id="histo-wf-emprendimiento"
                name="emprendimiento"
                data-cy="emprendimiento"
                label="Emprendimiento"
                type="select"
              >
                <option value="" key="0" />
                {emprendimientos
                  ? emprendimientos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.nombre}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/histo-wf" replace color="info">
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

export default HistoWFUpdate;
