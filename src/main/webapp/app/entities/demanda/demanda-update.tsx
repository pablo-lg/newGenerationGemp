import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ITipoEmp } from 'app/shared/model/tipo-emp.model';
import { getEntities as getTipoEmps } from 'app/entities/tipo-emp/tipo-emp.reducer';
import { IDespliegue } from 'app/shared/model/despliegue.model';
import { getEntities as getDespliegues } from 'app/entities/despliegue/despliegue.reducer';
import { getEntity, updateEntity, createEntity, reset } from './demanda.reducer';
import { IDemanda } from 'app/shared/model/demanda.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DemandaUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const tipoEmps = useAppSelector(state => state.tipoEmp.entities);
  const despliegues = useAppSelector(state => state.despliegue.entities);
  const demandaEntity = useAppSelector(state => state.demanda.entity);
  const loading = useAppSelector(state => state.demanda.loading);
  const updating = useAppSelector(state => state.demanda.updating);
  const updateSuccess = useAppSelector(state => state.demanda.updateSuccess);
  const handleClose = () => {
    props.history.push('/demanda');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getTipoEmps({}));
    dispatch(getDespliegues({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...demandaEntity,
      ...values,
      tipoEmp: tipoEmps.find(it => it.id.toString() === values.tipoEmp.toString()),
      despliegue: despliegues.find(it => it.id.toString() === values.despliegue.toString()),
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
          ...demandaEntity,
          tipoEmp: demandaEntity?.tipoEmp?.id,
          despliegue: demandaEntity?.despliegue?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newGenerationGempApp.demanda.home.createOrEditLabel" data-cy="DemandaCreateUpdateHeading">
            Create or edit a Demanda
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="demanda-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="A 1" id="demanda-a1" name="a1" data-cy="a1" type="text" />
              <ValidatedField label="A 2" id="demanda-a2" name="a2" data-cy="a2" type="text" />
              <ValidatedField label="A 3" id="demanda-a3" name="a3" data-cy="a3" type="text" />
              <ValidatedField label="A 4" id="demanda-a4" name="a4" data-cy="a4" type="text" />
              <ValidatedField label="A 5" id="demanda-a5" name="a5" data-cy="a5" type="text" />
              <ValidatedField id="demanda-tipoEmp" name="tipoEmp" data-cy="tipoEmp" label="Tipo Emp" type="select">
                <option value="" key="0" />
                {tipoEmps
                  ? tipoEmps.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="demanda-despliegue" name="despliegue" data-cy="despliegue" label="Despliegue" type="select">
                <option value="" key="0" />
                {despliegues
                  ? despliegues.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/demanda" replace color="info">
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

export default DemandaUpdate;
