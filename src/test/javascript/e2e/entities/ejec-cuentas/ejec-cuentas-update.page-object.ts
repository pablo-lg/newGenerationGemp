import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class EjecCuentasUpdatePage {
  pageTitle: ElementFinder = element(by.id('newGenerationGempApp.ejecCuentas.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  telefonoInput: ElementFinder = element(by.css('input#ejec-cuentas-telefono'));
  apellidoInput: ElementFinder = element(by.css('input#ejec-cuentas-apellido'));
  celularInput: ElementFinder = element(by.css('input#ejec-cuentas-celular'));
  mailInput: ElementFinder = element(by.css('input#ejec-cuentas-mail'));
  nombreInput: ElementFinder = element(by.css('input#ejec-cuentas-nombre'));
  codVendedorInput: ElementFinder = element(by.css('input#ejec-cuentas-codVendedor'));
  legajoInput: ElementFinder = element(by.css('input#ejec-cuentas-legajo'));
  segmentoSelect: ElementFinder = element(by.css('select#ejec-cuentas-segmento'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setTelefonoInput(telefono) {
    await this.telefonoInput.sendKeys(telefono);
  }

  async getTelefonoInput() {
    return this.telefonoInput.getAttribute('value');
  }

  async setApellidoInput(apellido) {
    await this.apellidoInput.sendKeys(apellido);
  }

  async getApellidoInput() {
    return this.apellidoInput.getAttribute('value');
  }

  async setCelularInput(celular) {
    await this.celularInput.sendKeys(celular);
  }

  async getCelularInput() {
    return this.celularInput.getAttribute('value');
  }

  async setMailInput(mail) {
    await this.mailInput.sendKeys(mail);
  }

  async getMailInput() {
    return this.mailInput.getAttribute('value');
  }

  async setNombreInput(nombre) {
    await this.nombreInput.sendKeys(nombre);
  }

  async getNombreInput() {
    return this.nombreInput.getAttribute('value');
  }

  async setCodVendedorInput(codVendedor) {
    await this.codVendedorInput.sendKeys(codVendedor);
  }

  async getCodVendedorInput() {
    return this.codVendedorInput.getAttribute('value');
  }

  async setLegajoInput(legajo) {
    await this.legajoInput.sendKeys(legajo);
  }

  async getLegajoInput() {
    return this.legajoInput.getAttribute('value');
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
    await this.setTelefonoInput('telefono');
    await waitUntilDisplayed(this.saveButton);
    await this.setApellidoInput('apellido');
    await waitUntilDisplayed(this.saveButton);
    await this.setCelularInput('celular');
    await waitUntilDisplayed(this.saveButton);
    await this.setMailInput('mail');
    await waitUntilDisplayed(this.saveButton);
    await this.setNombreInput('nombre');
    await waitUntilDisplayed(this.saveButton);
    await this.setCodVendedorInput('codVendedor');
    await waitUntilDisplayed(this.saveButton);
    await this.setLegajoInput('legajo');
    await this.segmentoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
