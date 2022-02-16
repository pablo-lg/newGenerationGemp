import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class UsuGempUpdatePage {
  pageTitle: ElementFinder = element(by.id('newGenerationGempApp.usuGemp.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  usuInput: ElementFinder = element(by.css('input#usu-gemp-usu'));
  nombreInput: ElementFinder = element(by.css('input#usu-gemp-nombre'));
  apellidoInput: ElementFinder = element(by.css('input#usu-gemp-apellido'));
  emailInput: ElementFinder = element(by.css('input#usu-gemp-email'));
  perfilesInput: ElementFinder = element(by.css('input#usu-gemp-perfiles'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUsuInput(usu) {
    await this.usuInput.sendKeys(usu);
  }

  async getUsuInput() {
    return this.usuInput.getAttribute('value');
  }

  async setNombreInput(nombre) {
    await this.nombreInput.sendKeys(nombre);
  }

  async getNombreInput() {
    return this.nombreInput.getAttribute('value');
  }

  async setApellidoInput(apellido) {
    await this.apellidoInput.sendKeys(apellido);
  }

  async getApellidoInput() {
    return this.apellidoInput.getAttribute('value');
  }

  async setEmailInput(email) {
    await this.emailInput.sendKeys(email);
  }

  async getEmailInput() {
    return this.emailInput.getAttribute('value');
  }

  async setPerfilesInput(perfiles) {
    await this.perfilesInput.sendKeys(perfiles);
  }

  async getPerfilesInput() {
    return this.perfilesInput.getAttribute('value');
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
    await this.setUsuInput('usu');
    await waitUntilDisplayed(this.saveButton);
    await this.setNombreInput('nombre');
    await waitUntilDisplayed(this.saveButton);
    await this.setApellidoInput('apellido');
    await waitUntilDisplayed(this.saveButton);
    await this.setEmailInput('email');
    await waitUntilDisplayed(this.saveButton);
    await this.setPerfilesInput('perfiles');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
