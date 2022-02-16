import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class TipoEmpUpdatePage {
  pageTitle: ElementFinder = element(by.id('newGenerationGempApp.tipoEmp.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  demandaInput: ElementFinder = element(by.css('input#tipo-emp-demanda'));
  descripcionInput: ElementFinder = element(by.css('input#tipo-emp-descripcion'));

  getPageTitle() {
    return this.pageTitle;
  }

  getDemandaInput() {
    return this.demandaInput;
  }
  async setDescripcionInput(descripcion) {
    await this.descripcionInput.sendKeys(descripcion);
  }

  async getDescripcionInput() {
    return this.descripcionInput.getAttribute('value');
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
    const selectedDemanda = await this.getDemandaInput().isSelected();
    if (selectedDemanda) {
      await this.getDemandaInput().click();
    } else {
      await this.getDemandaInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    await this.setDescripcionInput('descripcion');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
