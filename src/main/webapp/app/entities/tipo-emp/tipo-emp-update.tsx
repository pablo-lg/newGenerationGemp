import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './tipo-emp.reducer';
import { ITipoEmp } from 'app/shared/model/tipo-emp.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TipoEmpUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const tipoEmpEntity = useAppSelector(state => state.tipoEmp.entity);
  const loading = useAppSelector(state => state.tipoEmp.loading);
  const updating = useAppSelector(state => state.tipoEmp.updating);
  const updateSuccess = useAppSelector(state => state.tipoEmp.updateSuccess);
  const handleClose = () => {
    props.history.push('/tipo-emp');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...tipoEmpEntity,
      ...values,
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
          ...tipoEmpEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newGenerationGempApp.tipoEmp.home.createOrEditLabel" data-cy="TipoEmpCreateUpdateHeading">
            Create or edit a TipoEmp
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="tipo-emp-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Demanda" id="tipo-emp-demanda" name="demanda" data-cy="demanda" check type="checkbox" />
              <ValidatedField label="Descripcion" id="tipo-emp-descripcion" name="descripcion" data-cy="descripcion" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/tipo-emp" replace color="info">
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

export default TipoEmpUpdate;
