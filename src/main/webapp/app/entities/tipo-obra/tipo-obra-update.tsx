import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ISegmento } from 'app/shared/model/segmento.model';
import { getEntities as getSegmentos } from 'app/entities/segmento/segmento.reducer';
import { getEntity, updateEntity, createEntity, reset } from './tipo-obra.reducer';
import { ITipoObra } from 'app/shared/model/tipo-obra.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TipoObraUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const segmentos = useAppSelector(state => state.segmento.entities);
  const tipoObraEntity = useAppSelector(state => state.tipoObra.entity);
  const loading = useAppSelector(state => state.tipoObra.loading);
  const updating = useAppSelector(state => state.tipoObra.updating);
  const updateSuccess = useAppSelector(state => state.tipoObra.updateSuccess);
  const handleClose = () => {
    props.history.push('/tipo-obra');
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
      ...tipoObraEntity,
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
          ...tipoObraEntity,
          segmento: tipoObraEntity?.segmento?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newGenerationGempApp.tipoObra.home.createOrEditLabel" data-cy="TipoObraCreateUpdateHeading">
            Create or edit a TipoObra
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="tipo-obra-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Descripcion" id="tipo-obra-descripcion" name="descripcion" data-cy="descripcion" type="text" />
              <ValidatedField id="tipo-obra-segmento" name="segmento" data-cy="segmento" label="Segmento" type="select">
                <option value="" key="0" />
                {segmentos
                  ? segmentos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descripcion}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/tipo-obra" replace color="info">
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

export default TipoObraUpdate;
