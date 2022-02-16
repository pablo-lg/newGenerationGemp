import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ParametrosUpdatePage {
  pageTitle: ElementFinder = element(by.id('newGenerationGempApp.parametros.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  claveInput: ElementFinder = element(by.css('input#parametros-clave'));
  valorInput: ElementFinder = element(by.css('input#parametros-valor'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setClaveInput(clave) {
    await this.claveInput.sendKeys(clave);
  }

  async getClaveInput() {
    return this.claveInput.getAttribute('value');
  }

  async setValorInput(valor) {
    await this.valorInput.sendKeys(valor);
  }

  async getValorInput() {
    return this.valorInput.getAttribute('value');
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
    await this.setClaveInput('clave');
    await waitUntilDisplayed(this.saveButton);
    await this.setValorInput('valor');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
