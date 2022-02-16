import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './filtro-rep.reducer';
import { IFiltroRep } from 'app/shared/model/filtro-rep.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FiltroRepUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const filtroRepEntity = useAppSelector(state => state.filtroRep.entity);
  const loading = useAppSelector(state => state.filtroRep.loading);
  const updating = useAppSelector(state => state.filtroRep.updating);
  const updateSuccess = useAppSelector(state => state.filtroRep.updateSuccess);
  const handleClose = () => {
    props.history.push('/filtro-rep');
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
      ...filtroRepEntity,
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
          ...filtroRepEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newGenerationGempApp.filtroRep.home.createOrEditLabel" data-cy="FiltroRepCreateUpdateHeading">
            Create or edit a FiltroRep
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="filtro-rep-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Nombre" id="filtro-rep-nombre" name="nombre" data-cy="nombre" type="text" />
              <ValidatedField label="Filtro" id="filtro-rep-filtro" name="filtro" data-cy="filtro" type="textarea" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/filtro-rep" replace color="info">
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

export default FiltroRepUpdate;
