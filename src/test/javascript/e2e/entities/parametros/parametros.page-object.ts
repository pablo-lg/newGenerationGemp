import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import ParametrosUpdatePage from './parametros-update.page-object';

const expect = chai.expect;
export class ParametrosDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('newGenerationGempApp.parametros.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-parametros'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class ParametrosComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('parametros-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage) {
    await navBarPage.getEntityPage('parametros');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateParametros() {
    await this.createButton.click();
    return new ParametrosUpdatePage();
  }

  async deleteParametros() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const parametrosDeleteDialog = new ParametrosDeleteDialog();
    await waitUntilDisplayed(parametrosDeleteDialog.deleteModal);
    expect(await parametrosDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/newGenerationGempApp.parametros.delete.question/);
    await parametrosDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(parametrosDeleteDialog.deleteModal);

    expect(await isVisible(parametrosDeleteDialog.deleteModal)).to.be.false;
  }
}
