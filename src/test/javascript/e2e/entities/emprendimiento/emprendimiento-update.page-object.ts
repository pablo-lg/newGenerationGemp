import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class EmprendimientoUpdatePage {
  pageTitle: ElementFinder = element(by.id('newGenerationGempApp.emprendimiento.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nombreInput: ElementFinder = element(by.css('input#emprendimiento-nombre'));
  contactoInput: ElementFinder = element(by.css('input#emprendimiento-contacto'));
  fechaFinObraInput: ElementFinder = element(by.css('input#emprendimiento-fechaFinObra'));
  codigoObraInput: ElementFinder = element(by.css('input#emprendimiento-codigoObra'));
  elementosDeRedInput: ElementFinder = element(by.css('input#emprendimiento-elementosDeRed'));
  clientesCatvInput: ElementFinder = element(by.css('input#emprendimiento-clientesCatv'));
  clientesFibertelInput: ElementFinder = element(by.css('input#emprendimiento-clientesFibertel'));
  clientesFibertelLiteInput: ElementFinder = element(by.css('input#emprendimiento-clientesFibertelLite'));
  clientesFlowInput: ElementFinder = element(by.css('input#emprendimiento-clientesFlow'));
  clientesComboInput: ElementFinder = element(by.css('input#emprendimiento-clientesCombo'));
  lineasVozInput: ElementFinder = element(by.css('input#emprendimiento-lineasVoz'));
  mesesDeFinalizadoInput: ElementFinder = element(by.css('input#emprendimiento-mesesDeFinalizado'));
  altasBCInput: ElementFinder = element(by.css('input#emprendimiento-altasBC'));
  penetracionVivLotInput: ElementFinder = element(by.css('input#emprendimiento-penetracionVivLot'));
  penetracionBCInput: ElementFinder = element(by.css('input#emprendimiento-penetracionBC'));
  demanda1Input: ElementFinder = element(by.css('input#emprendimiento-demanda1'));
  demanda2Input: ElementFinder = element(by.css('input#emprendimiento-demanda2'));
  demanda3Input: ElementFinder = element(by.css('input#emprendimiento-demanda3'));
  demanda4Input: ElementFinder = element(by.css('input#emprendimiento-demanda4'));
  demanda5Input: ElementFinder = element(by.css('input#emprendimiento-demanda5'));
  lotesInput: ElementFinder = element(by.css('input#emprendimiento-lotes'));
  viviendasInput: ElementFinder = element(by.css('input#emprendimiento-viviendas'));
  comProfInput: ElementFinder = element(by.css('input#emprendimiento-comProf'));
  habitacionesInput: ElementFinder = element(by.css('input#emprendimiento-habitaciones'));
  manzanasInput: ElementFinder = element(by.css('input#emprendimiento-manzanas'));
  demandaInput: ElementFinder = element(by.css('input#emprendimiento-demanda'));
  fechaDeRelevamientoInput: ElementFinder = element(by.css('input#emprendimiento-fechaDeRelevamiento'));
  telefonoInput: ElementFinder = element(by.css('input#emprendimiento-telefono'));
  anoPriorizacionInput: ElementFinder = element(by.css('input#emprendimiento-anoPriorizacion'));
  contratoOpenInput: ElementFinder = element(by.css('input#emprendimiento-contratoOpen'));
  negociacionInput: ElementFinder = element(by.css('input#emprendimiento-negociacion'));
  fechaInput: ElementFinder = element(by.css('input#emprendimiento-fecha'));
  codigoDeFirmaInput: ElementFinder = element(by.css('input#emprendimiento-codigoDeFirma'));
  fechaFirmaInput: ElementFinder = element(by.css('input#emprendimiento-fechaFirma'));
  observacionesInput: ElementFinder = element(by.css('input#emprendimiento-observaciones'));
  comentarioInput: ElementFinder = element(by.css('input#emprendimiento-comentario'));
  comenCanInput: ElementFinder = element(by.css('input#emprendimiento-comenCan'));
  estadoFirmaSelect: ElementFinder = element(by.css('select#emprendimiento-estadoFirma'));
  estadoSelect: ElementFinder = element(by.css('select#emprendimiento-estado'));
  estadoBCSelect: ElementFinder = element(by.css('select#emprendimiento-estadoBC'));
  grupoEmpSelect: ElementFinder = element(by.css('select#emprendimiento-grupoEmp'));
  tipoObraSelect: ElementFinder = element(by.css('select#emprendimiento-tipoObra'));
  tipoEmpSelect: ElementFinder = element(by.css('select#emprendimiento-tipoEmp'));
  despliegueSelect: ElementFinder = element(by.css('select#emprendimiento-despliegue'));
  nSESelect: ElementFinder = element(by.css('select#emprendimiento-nSE'));
  segmentoSelect: ElementFinder = element(by.css('select#emprendimiento-segmento'));
  tecnologiaSelect: ElementFinder = element(by.css('select#emprendimiento-tecnologia'));
  ejecCuentasSelect: ElementFinder = element(by.css('select#emprendimiento-ejecCuentas'));
  direccionSelect: ElementFinder = element(by.css('select#emprendimiento-direccion'));
  compentenciaSelect: ElementFinder = element(by.css('select#emprendimiento-compentencia'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNombreInput(nombre) {
    await this.nombreInput.sendKeys(nombre);
  }

  async getNombreInput() {
    return this.nombreInput.getAttribute('value');
  }

  async setContactoInput(contacto) {
    await this.contactoInput.sendKeys(contacto);
  }

  async getContactoInput() {
    return this.contactoInput.getAttribute('value');
  }

  async setFechaFinObraInput(fechaFinObra) {
    await this.fechaFinObraInput.sendKeys(fechaFinObra);
  }

  async getFechaFinObraInput() {
    return this.fechaFinObraInput.getAttribute('value');
  }

  async setCodigoObraInput(codigoObra) {
    await this.codigoObraInput.sendKeys(codigoObra);
  }

  async getCodigoObraInput() {
    return this.codigoObraInput.getAttribute('value');
  }

  async setElementosDeRedInput(elementosDeRed) {
    await this.elementosDeRedInput.sendKeys(elementosDeRed);
  }

  async getElementosDeRedInput() {
    return this.elementosDeRedInput.getAttribute('value');
  }

  async setClientesCatvInput(clientesCatv) {
    await this.clientesCatvInput.sendKeys(clientesCatv);
  }

  async getClientesCatvInput() {
    return this.clientesCatvInput.getAttribute('value');
  }

  async setClientesFibertelInput(clientesFibertel) {
    await this.clientesFibertelInput.sendKeys(clientesFibertel);
  }

  async getClientesFibertelInput() {
    return this.clientesFibertelInput.getAttribute('value');
  }

  async setClientesFibertelLiteInput(clientesFibertelLite) {
    await this.clientesFibertelLiteInput.sendKeys(clientesFibertelLite);
  }

  async getClientesFibertelLiteInput() {
    return this.clientesFibertelLiteInput.getAttribute('value');
  }

  async setClientesFlowInput(clientesFlow) {
    await this.clientesFlowInput.sendKeys(clientesFlow);
  }

  async getClientesFlowInput() {
    return this.clientesFlowInput.getAttribute('value');
  }

  async setClientesComboInput(clientesCombo) {
    await this.clientesComboInput.sendKeys(clientesCombo);
  }

  async getClientesComboInput() {
    return this.clientesComboInput.getAttribute('value');
  }

  async setLineasVozInput(lineasVoz) {
    await this.lineasVozInput.sendKeys(lineasVoz);
  }

  async getLineasVozInput() {
    return this.lineasVozInput.getAttribute('value');
  }

  async setMesesDeFinalizadoInput(mesesDeFinalizado) {
    await this.mesesDeFinalizadoInput.sendKeys(mesesDeFinalizado);
  }

  async getMesesDeFinalizadoInput() {
    return this.mesesDeFinalizadoInput.getAttribute('value');
  }

  async setAltasBCInput(altasBC) {
    await this.altasBCInput.sendKeys(altasBC);
  }

  async getAltasBCInput() {
    return this.altasBCInput.getAttribute('value');
  }

  async setPenetracionVivLotInput(penetracionVivLot) {
    await this.penetracionVivLotInput.sendKeys(penetracionVivLot);
  }

  async getPenetracionVivLotInput() {
    return this.penetracionVivLotInput.getAttribute('value');
  }

  async setPenetracionBCInput(penetracionBC) {
    await this.penetracionBCInput.sendKeys(penetracionBC);
  }

  async getPenetracionBCInput() {
    return this.penetracionBCInput.getAttribute('value');
  }

  async setDemanda1Input(demanda1) {
    await this.demanda1Input.sendKeys(demanda1);
  }

  async getDemanda1Input() {
    return this.demanda1Input.getAttribute('value');
  }

  async setDemanda2Input(demanda2) {
    await this.demanda2Input.sendKeys(demanda2);
  }

  async getDemanda2Input() {
    return this.demanda2Input.getAttribute('value');
  }

  async setDemanda3Input(demanda3) {
    await this.demanda3Input.sendKeys(demanda3);
  }

  async getDemanda3Input() {
    return this.demanda3Input.getAttribute('value');
  }

  async setDemanda4Input(demanda4) {
    await this.demanda4Input.sendKeys(demanda4);
  }

  async getDemanda4Input() {
    return this.demanda4Input.getAttribute('value');
  }

  async setDemanda5Input(demanda5) {
    await this.demanda5Input.sendKeys(demanda5);
  }

  async getDemanda5Input() {
    return this.demanda5Input.getAttribute('value');
  }

  async setLotesInput(lotes) {
    await this.lotesInput.sendKeys(lotes);
  }

  async getLotesInput() {
    return this.lotesInput.getAttribute('value');
  }

  async setViviendasInput(viviendas) {
    await this.viviendasInput.sendKeys(viviendas);
  }

  async getViviendasInput() {
    return this.viviendasInput.getAttribute('value');
  }

  async setComProfInput(comProf) {
    await this.comProfInput.sendKeys(comProf);
  }

  async getComProfInput() {
    return this.comProfInput.getAttribute('value');
  }

  async setHabitacionesInput(habitaciones) {
    await this.habitacionesInput.sendKeys(habitaciones);
  }

  async getHabitacionesInput() {
    return this.habitacionesInput.getAttribute('value');
  }

  async setManzanasInput(manzanas) {
    await this.manzanasInput.sendKeys(manzanas);
  }

  async getManzanasInput() {
    return this.manzanasInput.getAttribute('value');
  }

  async setDemandaInput(demanda) {
    await this.demandaInput.sendKeys(demanda);
  }

  async getDemandaInput() {
    return this.demandaInput.getAttribute('value');
  }

  async setFechaDeRelevamientoInput(fechaDeRelevamiento) {
    await this.fechaDeRelevamientoInput.sendKeys(fechaDeRelevamiento);
  }

  async getFechaDeRelevamientoInput() {
    return this.fechaDeRelevamientoInput.getAttribute('value');
  }

  async setTelefonoInput(telefono) {
    await this.telefonoInput.sendKeys(telefono);
  }

  async getTelefonoInput() {
    return this.telefonoInput.getAttribute('value');
  }

  async setAnoPriorizacionInput(anoPriorizacion) {
    await this.anoPriorizacionInput.sendKeys(anoPriorizacion);
  }

  async getAnoPriorizacionInput() {
    return this.anoPriorizacionInput.getAttribute('value');
  }

  async setContratoOpenInput(contratoOpen) {
    await this.contratoOpenInput.sendKeys(contratoOpen);
  }

  async getContratoOpenInput() {
    return this.contratoOpenInput.getAttribute('value');
  }

  getNegociacionInput() {
    return this.negociacionInput;
  }
  async setFechaInput(fecha) {
    await this.fechaInput.sendKeys(fecha);
  }

  async getFechaInput() {
    return this.fechaInput.getAttribute('value');
  }

  async setCodigoDeFirmaInput(codigoDeFirma) {
    await this.codigoDeFirmaInput.sendKeys(codigoDeFirma);
  }

  async getCodigoDeFirmaInput() {
    return this.codigoDeFirmaInput.getAttribute('value');
  }

  async setFechaFirmaInput(fechaFirma) {
    await this.fechaFirmaInput.sendKeys(fechaFirma);
  }

  async getFechaFirmaInput() {
    return this.fechaFirmaInput.getAttribute('value');
  }

  async setObservacionesInput(observaciones) {
    await this.observacionesInput.sendKeys(observaciones);
  }

  async getObservacionesInput() {
    return this.observacionesInput.getAttribute('value');
  }

  async setComentarioInput(comentario) {
    await this.comentarioInput.sendKeys(comentario);
  }

  async getComentarioInput() {
    return this.comentarioInput.getAttribute('value');
  }

  async setComenCanInput(comenCan) {
    await this.comenCanInput.sendKeys(comenCan);
  }

  async getComenCanInput() {
    return this.comenCanInput.getAttribute('value');
  }

  async setEstadoFirmaSelect(estadoFirma) {
    await this.estadoFirmaSelect.sendKeys(estadoFirma);
  }

  async getEstadoFirmaSelect() {
    return this.estadoFirmaSelect.element(by.css('option:checked')).getText();
  }

  async estadoFirmaSelectLastOption() {
    await this.estadoFirmaSelect.all(by.tagName('option')).last().click();
  }
  async setEstadoSelect(estado) {
    await this.estadoSelect.sendKeys(estado);
  }

  async getEstadoSelect() {
    return this.estadoSelect.element(by.css('option:checked')).getText();
  }

  async estadoSelectLastOption() {
    await this.estadoSelect.all(by.tagName('option')).last().click();
  }
  async setEstadoBCSelect(estadoBC) {
    await this.estadoBCSelect.sendKeys(estadoBC);
  }

  async getEstadoBCSelect() {
    return this.estadoBCSelect.element(by.css('option:checked')).getText();
  }

  async estadoBCSelectLastOption() {
    await this.estadoBCSelect.all(by.tagName('option')).last().click();
  }
  async grupoEmpSelectLastOption() {
    await this.grupoEmpSelect.all(by.tagName('option')).last().click();
  }

  async grupoEmpSelectOption(option) {
    await this.grupoEmpSelect.sendKeys(option);
  }

  getGrupoEmpSelect() {
    return this.grupoEmpSelect;
  }

  async getGrupoEmpSelectedOption() {
    return this.grupoEmpSelect.element(by.css('option:checked')).getText();
  }

  async tipoObraSelectLastOption() {
    await this.tipoObraSelect.all(by.tagName('option')).last().click();
  }

  async tipoObraSelectOption(option) {
    await this.tipoObraSelect.sendKeys(option);
  }

  getTipoObraSelect() {
    return this.tipoObraSelect;
  }

  async getTipoObraSelectedOption() {
    return this.tipoObraSelect.element(by.css('option:checked')).getText();
  }

  async tipoEmpSelectLastOption() {
    await this.tipoEmpSelect.all(by.tagName('option')).last().click();
  }

  async tipoEmpSelectOption(option) {
    await this.tipoEmpSelect.sendKeys(option);
  }

  getTipoEmpSelect() {
    return this.tipoEmpSelect;
  }

  async getTipoEmpSelectedOption() {
    return this.tipoEmpSelect.element(by.css('option:checked')).getText();
  }

  async despliegueSelectLastOption() {
    await this.despliegueSelect.all(by.tagName('option')).last().click();
  }

  async despliegueSelectOption(option) {
    await this.despliegueSelect.sendKeys(option);
  }

  getDespliegueSelect() {
    return this.despliegueSelect;
  }

  async getDespliegueSelectedOption() {
    return this.despliegueSelect.element(by.css('option:checked')).getText();
  }

  async nSESelectLastOption() {
    await this.nSESelect.all(by.tagName('option')).last().click();
  }

  async nSESelectOption(option) {
    await this.nSESelect.sendKeys(option);
  }

  getNSESelect() {
    return this.nSESelect;
  }

  async getNSESelectedOption() {
    return this.nSESelect.element(by.css('option:checked')).getText();
  }

  async segmentoSelectLastOption() {
    await this.segmentoSelect.all(by.tagName('option')).last().click();
  }

  async segmentoSelectOption(option) {
    await this.segmentoSelect.sendKeys(option);
  }

  getSegmentoSelect() {
    return this.segmentoSelect;
  }

  async getSegmentoSelectedOption() {
    return this.segmentoSelect.element(by.css('option:checked')).getText();
  }

  async tecnologiaSelectLastOption() {
    await this.tecnologiaSelect.all(by.tagName('option')).last().click();
  }

  async tecnologiaSelectOption(option) {
    await this.tecnologiaSelect.sendKeys(option);
  }

  getTecnologiaSelect() {
    return this.tecnologiaSelect;
  }

  async getTecnologiaSelectedOption() {
    return this.tecnologiaSelect.element(by.css('option:checked')).getText();
  }

  async ejecCuentasSelectLastOption() {
    await this.ejecCuentasSelect.all(by.tagName('option')).last().click();
  }

  async ejecCuentasSelectOption(option) {
    await this.ejecCuentasSelect.sendKeys(option);
  }

  getEjecCuentasSelect() {
    return this.ejecCuentasSelect;
  }

  async getEjecCuentasSelectedOption() {
    return this.ejecCuentasSelect.element(by.css('option:checked')).getText();
  }

  async direccionSelectLastOption() {
    await this.direccionSelect.all(by.tagName('option')).last().click();
  }

  async direccionSelectOption(option) {
    await this.direccionSelect.sendKeys(option);
  }

  getDireccionSelect() {
    return this.direccionSelect;
  }

  async getDireccionSelectedOption() {
    return this.direccionSelect.element(by.css('option:checked')).getText();
  }

  async compentenciaSelectLastOption() {
    await this.compentenciaSelect.all(by.tagName('option')).last().click();
  }

  async compentenciaSelectOption(option) {
    await this.compentenciaSelect.sendKeys(option);
  }

  getCompentenciaSelect() {
    return this.compentenciaSelect;
  }

  async getCompentenciaSelectedOption() {
    return this.compentenciaSelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setNombreInput('nombre');
    await waitUntilDisplayed(this.saveButton);
    await this.setContactoInput('contacto');
    await waitUntilDisplayed(this.saveButton);
    await this.setFechaFinObraInput('01-01-2001');
    await waitUntilDisplayed(this.saveButton);
    await this.setCodigoObraInput('codigoObra');
    await waitUntilDisplayed(this.saveButton);
    await this.setElementosDeRedInput('elementosDeRed');
    await waitUntilDisplayed(this.saveButton);
    await this.setClientesCatvInput('clientesCatv');
    await waitUntilDisplayed(this.saveButton);
    await this.setClientesFibertelInput('clientesFibertel');
    await waitUntilDisplayed(this.saveButton);
    await this.setClientesFibertelLiteInput('clientesFibertelLite');
    await waitUntilDisplayed(this.saveButton);
    await this.setClientesFlowInput('clientesFlow');
    await waitUntilDisplayed(this.saveButton);
    await this.setClientesComboInput('clientesCombo');
    await waitUntilDisplayed(this.saveButton);
    await this.setLineasVozInput('lineasVoz');
    await waitUntilDisplayed(this.saveButton);
    await this.setMesesDeFinalizadoInput('mesesDeFinalizado');
    await waitUntilDisplayed(this.saveButton);
    await this.setAltasBCInput('altasBC');
    await waitUntilDisplayed(this.saveButton);
    await this.setPenetracionVivLotInput('penetracionVivLot');
    await waitUntilDisplayed(this.saveButton);
    await this.setPenetracionBCInput('penetracionBC');
    await waitUntilDisplayed(this.saveButton);
    await this.setDemanda1Input('demanda1');
    await waitUntilDisplayed(this.saveButton);
    await this.setDemanda2Input('demanda2');
    await waitUntilDisplayed(this.saveButton);
    await this.setDemanda3Input('demanda3');
    await waitUntilDisplayed(this.saveButton);
    await this.setDemanda4Input('demanda4');
    await waitUntilDisplayed(this.saveButton);
    await this.setDemanda5Input('demanda5');
    await waitUntilDisplayed(this.saveButton);
    await this.setLotesInput('lotes');
    await waitUntilDisplayed(this.saveButton);
    await this.setViviendasInput('viviendas');
    await waitUntilDisplayed(this.saveButton);
    await this.setComProfInput('comProf');
    await waitUntilDisplayed(this.saveButton);
    await this.setHabitacionesInput('habitaciones');
    await waitUntilDisplayed(this.saveButton);
    await this.setManzanasInput('manzanas');
    await waitUntilDisplayed(this.saveButton);
    await this.setDemandaInput('demanda');
    await waitUntilDisplayed(this.saveButton);
    await this.setFechaDeRelevamientoInput('01-01-2001');
    await waitUntilDisplayed(this.saveButton);
    await this.setTelefonoInput('telefono');
    await waitUntilDisplayed(this.saveButton);
    await this.setAnoPriorizacionInput('01-01-2001');
    await waitUntilDisplayed(this.saveButton);
    await this.setContratoOpenInput('contratoOpen');
    await waitUntilDisplayed(this.saveButton);
    const selectedNegociacion = await this.getNegociacionInput().isSelected();
    if (selectedNegociacion) {
      await this.getNegociacionInput().click();
    } else {
      await this.getNegociacionInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    await this.setFechaInput('01-01-2001');
    await waitUntilDisplayed(this.saveButton);
    await this.setCodigoDeFirmaInput('codigoDeFirma');
    await waitUntilDisplayed(this.saveButton);
    await this.setFechaFirmaInput('01-01-2001');
    await waitUntilDisplayed(this.saveButton);
    await this.setObservacionesInput('observaciones');
    await waitUntilDisplayed(this.saveButton);
    await this.setComentarioInput('comentario');
    await waitUntilDisplayed(this.saveButton);
    await this.setComenCanInput('comenCan');
    await waitUntilDisplayed(this.saveButton);
    await this.estadoFirmaSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.estadoSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.estadoBCSelectLastOption();
    await this.grupoEmpSelectLastOption();
    await this.tipoObraSelectLastOption();
    await this.tipoEmpSelectLastOption();
    await this.despliegueSelectLastOption();
    await this.nSESelectLastOption();
    await this.segmentoSelectLastOption();
    await this.tecnologiaSelectLastOption();
    await this.ejecCuentasSelectLastOption();
    await this.direccionSelectLastOption();
    await this.compentenciaSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
