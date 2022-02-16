import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './usu-gemp.reducer';
import { IUsuGemp } from 'app/shared/model/usu-gemp.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const UsuGempUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const usuGempEntity = useAppSelector(state => state.usuGemp.entity);
  const loading = useAppSelector(state => state.usuGemp.loading);
  const updating = useAppSelector(state => state.usuGemp.updating);
  const updateSuccess = useAppSelector(state => state.usuGemp.updateSuccess);
  const handleClose = () => {
    props.history.push('/usu-gemp');
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
      ...usuGempEntity,
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
          ...usuGempEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newGenerationGempApp.usuGemp.home.createOrEditLabel" data-cy="UsuGempCreateUpdateHeading">
            Create or edit a UsuGemp
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="usu-gemp-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Usu" id="usu-gemp-usu" name="usu" data-cy="usu" type="text" />
              <ValidatedField label="Nombre" id="usu-gemp-nombre" name="nombre" data-cy="nombre" type="text" />
              <ValidatedField label="Apellido" id="usu-gemp-apellido" name="apellido" data-cy="apellido" type="text" />
              <ValidatedField label="Email" id="usu-gemp-email" name="email" data-cy="email" type="text" />
              <ValidatedField label="Perfiles" id="usu-gemp-perfiles" name="perfiles" data-cy="perfiles" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/usu-gemp" replace color="info">
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

export default UsuGempUpdate;
