import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class GrupoEmpUpdatePage {
  pageTitle: ElementFinder = element(by.id('newGenerationGempApp.grupoEmp.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descripcionInput: ElementFinder = element(by.css('input#grupo-emp-descripcion'));
  esProtegidoInput: ElementFinder = element(by.css('input#grupo-emp-esProtegido'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDescripcionInput(descripcion) {
    await this.descripcionInput.sendKeys(descripcion);
  }

  async getDescripcionInput() {
    return this.descripcionInput.getAttribute('value');
  }

  getEsProtegidoInput() {
    return this.esProtegidoInput;
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
    await this.setDescripcionInput('descripcion');
    await waitUntilDisplayed(this.saveButton);
    const selectedEsProtegido = await this.getEsProtegidoInput().isSelected();
    if (selectedEsProtegido) {
      await this.getEsProtegidoInput().click();
    } else {
      await this.getEsProtegidoInput().click();
    }
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
