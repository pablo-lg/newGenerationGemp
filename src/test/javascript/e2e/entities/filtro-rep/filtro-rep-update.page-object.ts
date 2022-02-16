import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FiltroRepUpdatePage {
  pageTitle: ElementFinder = element(by.id('newGenerationGempApp.filtroRep.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nombreInput: ElementFinder = element(by.css('input#filtro-rep-nombre'));
  filtroInput: ElementFinder = element(by.css('textarea#filtro-rep-filtro'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNombreInput(nombre) {
    await this.nombreInput.sendKeys(nombre);
  }

  async getNombreInput() {
    return this.nombreInput.getAttribute('value');
  }

  async setFiltroInput(filtro) {
    await this.filtroInput.sendKeys(filtro);
  }

  async getFiltroInput() {
    return this.filtroInput.getAttribute('value');
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
    await this.setFiltroInput('filtro');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
