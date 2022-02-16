import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" data-cy="entity" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/despliegue">
      Despliegue
    </MenuItem>
    <MenuItem icon="asterisk" to="/segmento">
      Segmento
    </MenuItem>
    <MenuItem icon="asterisk" to="/competencia">
      Competencia
    </MenuItem>
    <MenuItem icon="asterisk" to="/tecnologia">
      Tecnologia
    </MenuItem>
    <MenuItem icon="asterisk" to="/nse">
      NSE
    </MenuItem>
    <MenuItem icon="asterisk" to="/tipo-obra">
      Tipo Obra
    </MenuItem>
    <MenuItem icon="asterisk" to="/tipo-emp">
      Tipo Emp
    </MenuItem>
    <MenuItem icon="asterisk" to="/ejec-cuentas">
      Ejec Cuentas
    </MenuItem>
    <MenuItem icon="asterisk" to="/direccion">
      Direccion
    </MenuItem>
    <MenuItem icon="asterisk" to="/emprendimiento">
      Emprendimiento
    </MenuItem>
    <MenuItem icon="asterisk" to="/demanda">
      Demanda
    </MenuItem>
    <MenuItem icon="asterisk" to="/histo-wf">
      Histo WF
    </MenuItem>
    <MenuItem icon="asterisk" to="/parametros">
      Parametros
    </MenuItem>
    <MenuItem icon="asterisk" to="/grupo-emp">
      Grupo Emp
    </MenuItem>
    <MenuItem icon="asterisk" to="/filtro-rep">
      Filtro Rep
    </MenuItem>
    <MenuItem icon="asterisk" to="/usu-gemp">
      Usu Gemp
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
