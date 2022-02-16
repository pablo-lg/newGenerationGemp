import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class HistoWFUpdatePage {
  pageTitle: ElementFinder = element(by.id('newGenerationGempApp.histoWF.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  estadoAnteriorSelect: ElementFinder = element(by.css('select#histo-wf-estadoAnterior'));
  estadoActualSelect: ElementFinder = element(by.css('select#histo-wf-estadoActual'));
  emprendimientoSelect: ElementFinder = element(by.css('select#histo-wf-emprendimiento'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setEstadoAnteriorSelect(estadoAnterior) {
    await this.estadoAnteriorSelect.sendKeys(estadoAnterior);
  }

  async getEstadoAnteriorSelect() {
    return this.estadoAnteriorSelect.element(by.css('option:checked')).getText();
  }

  async estadoAnteriorSelectLastOption() {
    await this.estadoAnteriorSelect.all(by.tagName('option')).last().click();
  }
  async setEstadoActualSelect(estadoActual) {
    await this.estadoActualSelect.sendKeys(estadoActual);
  }

  async getEstadoActualSelect() {
    return this.estadoActualSelect.element(by.css('option:checked')).getText();
  }

  async estadoActualSelectLastOption() {
    await this.estadoActualSelect.all(by.tagName('option')).last().click();
  }
  async emprendimientoSelectLastOption() {
    await this.emprendimientoSelect.all(by.tagName('option')).last().click();
  }

  async emprendimientoSelectOption(option) {
    await this.emprendimientoSelect.sendKeys(option);
  }

  getEmprendimientoSelect() {
    return this.emprendimientoSelect;
  }

  async getEmprendimientoSelectedOption() {
    return this.emprendimientoSelect.element(by.css('option:checked')).getText();
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
    await this.estadoAnteriorSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.estadoActualSelectLastOption();
    await this.emprendimientoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
