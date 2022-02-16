import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './direccion.reducer';
import { IDireccion } from 'app/shared/model/direccion.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DireccionUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const direccionEntity = useAppSelector(state => state.direccion.entity);
  const loading = useAppSelector(state => state.direccion.loading);
  const updating = useAppSelector(state => state.direccion.updating);
  const updateSuccess = useAppSelector(state => state.direccion.updateSuccess);
  const handleClose = () => {
    props.history.push('/direccion' + props.location.search);
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
      ...direccionEntity,
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
          ...direccionEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newGenerationGempApp.direccion.home.createOrEditLabel" data-cy="DireccionCreateUpdateHeading">
            Create or edit a Direccion
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="direccion-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Identification"
                id="direccion-identification"
                name="identification"
                data-cy="identification"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label="Pais"
                id="direccion-pais"
                name="pais"
                data-cy="pais"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Provincia"
                id="direccion-provincia"
                name="provincia"
                data-cy="provincia"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Partido"
                id="direccion-partido"
                name="partido"
                data-cy="partido"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Localidad"
                id="direccion-localidad"
                name="localidad"
                data-cy="localidad"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Calle"
                id="direccion-calle"
                name="calle"
                data-cy="calle"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Altura"
                id="direccion-altura"
                name="altura"
                data-cy="altura"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Region" id="direccion-region" name="region" data-cy="region" type="text" />
              <ValidatedField label="Subregion" id="direccion-subregion" name="subregion" data-cy="subregion" type="text" />
              <ValidatedField label="Hub" id="direccion-hub" name="hub" data-cy="hub" type="text" />
              <ValidatedField
                label="Barrios Especiales"
                id="direccion-barriosEspeciales"
                name="barriosEspeciales"
                data-cy="barriosEspeciales"
                type="text"
              />
              <ValidatedField label="Codigo Postal" id="direccion-codigoPostal" name="codigoPostal" data-cy="codigoPostal" type="text" />
              <ValidatedField label="Tipo Calle" id="direccion-tipoCalle" name="tipoCalle" data-cy="tipoCalle" type="text" />
              <ValidatedField
                label="Zona Competencia"
                id="direccion-zonaCompetencia"
                name="zonaCompetencia"
                data-cy="zonaCompetencia"
                type="text"
              />
              <ValidatedField
                label="Intersection Left"
                id="direccion-intersectionLeft"
                name="intersectionLeft"
                data-cy="intersectionLeft"
                type="text"
              />
              <ValidatedField
                label="Intersection Right"
                id="direccion-intersectionRight"
                name="intersectionRight"
                data-cy="intersectionRight"
                type="text"
              />
              <ValidatedField label="Street Type" id="direccion-streetType" name="streetType" data-cy="streetType" type="text" />
              <ValidatedField label="Latitud" id="direccion-latitud" name="latitud" data-cy="latitud" type="text" />
              <ValidatedField label="Longitud" id="direccion-longitud" name="longitud" data-cy="longitud" type="text" />
              <ValidatedField
                label="Elementos De Red"
                id="direccion-elementosDeRed"
                name="elementosDeRed"
                data-cy="elementosDeRed"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/direccion" replace color="info">
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

export default DireccionUpdate;
