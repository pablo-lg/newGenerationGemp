import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class DemandaUpdatePage {
  pageTitle: ElementFinder = element(by.id('newGenerationGempApp.demanda.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  a1Input: ElementFinder = element(by.css('input#demanda-a1'));
  a2Input: ElementFinder = element(by.css('input#demanda-a2'));
  a3Input: ElementFinder = element(by.css('input#demanda-a3'));
  a4Input: ElementFinder = element(by.css('input#demanda-a4'));
  a5Input: ElementFinder = element(by.css('input#demanda-a5'));
  tipoEmpSelect: ElementFinder = element(by.css('select#demanda-tipoEmp'));
  despliegueSelect: ElementFinder = element(by.css('select#demanda-despliegue'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setA1Input(a1) {
    await this.a1Input.sendKeys(a1);
  }

  async getA1Input() {
    return this.a1Input.getAttribute('value');
  }

  async setA2Input(a2) {
    await this.a2Input.sendKeys(a2);
  }

  async getA2Input() {
    return this.a2Input.getAttribute('value');
  }

  async setA3Input(a3) {
    await this.a3Input.sendKeys(a3);
  }

  async getA3Input() {
    return this.a3Input.getAttribute('value');
  }

  async setA4Input(a4) {
    await this.a4Input.sendKeys(a4);
  }

  async getA4Input() {
    return this.a4Input.getAttribute('value');
  }

  async setA5Input(a5) {
    await this.a5Input.sendKeys(a5);
  }

  async getA5Input() {
    return this.a5Input.getAttribute('value');
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
    await this.setA1Input('a1');
    await waitUntilDisplayed(this.saveButton);
    await this.setA2Input('a2');
    await waitUntilDisplayed(this.saveButton);
    await this.setA3Input('a3');
    await waitUntilDisplayed(this.saveButton);
    await this.setA4Input('a4');
    await waitUntilDisplayed(this.saveButton);
    await this.setA5Input('a5');
    await this.tipoEmpSelectLastOption();
    await this.despliegueSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
