import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ISegmento } from 'app/shared/model/segmento.model';
import { getEntities as getSegmentos } from 'app/entities/segmento/segmento.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ejec-cuentas.reducer';
import { IEjecCuentas } from 'app/shared/model/ejec-cuentas.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const EjecCuentasUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const segmentos = useAppSelector(state => state.segmento.entities);
  const ejecCuentasEntity = useAppSelector(state => state.ejecCuentas.entity);
  const loading = useAppSelector(state => state.ejecCuentas.loading);
  const updating = useAppSelector(state => state.ejecCuentas.updating);
  const updateSuccess = useAppSelector(state => state.ejecCuentas.updateSuccess);
  const handleClose = () => {
    props.history.push('/ejec-cuentas');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getSegmentos({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...ejecCuentasEntity,
      ...values,
      segmento: segmentos.find(it => it.id.toString() === values.segmento.toString()),
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
          ...ejecCuentasEntity,
          segmento: ejecCuentasEntity?.segmento?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newGenerationGempApp.ejecCuentas.home.createOrEditLabel" data-cy="EjecCuentasCreateUpdateHeading">
            Create or edit a EjecCuentas
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="ejec-cuentas-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Telefono" id="ejec-cuentas-telefono" name="telefono" data-cy="telefono" type="text" />
              <ValidatedField label="Apellido" id="ejec-cuentas-apellido" name="apellido" data-cy="apellido" type="text" />
              <ValidatedField label="Celular" id="ejec-cuentas-celular" name="celular" data-cy="celular" type="text" />
              <ValidatedField label="Mail" id="ejec-cuentas-mail" name="mail" data-cy="mail" type="text" />
              <ValidatedField label="Nombre" id="ejec-cuentas-nombre" name="nombre" data-cy="nombre" type="text" />
              <ValidatedField label="Cod Vendedor" id="ejec-cuentas-codVendedor" name="codVendedor" data-cy="codVendedor" type="text" />
              <ValidatedField label="Legajo" id="ejec-cuentas-legajo" name="legajo" data-cy="legajo" type="text" />
              <ValidatedField id="ejec-cuentas-segmento" name="segmento" data-cy="segmento" label="Segmento" type="select">
                <option value="" key="0" />
                {segmentos
                  ? segmentos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/ejec-cuentas" replace color="info">
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

export default EjecCuentasUpdate;
