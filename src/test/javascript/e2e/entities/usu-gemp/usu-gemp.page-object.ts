import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import UsuGempUpdatePage from './usu-gemp-update.page-object';

const expect = chai.expect;
export class UsuGempDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('newGenerationGempApp.usuGemp.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-usuGemp'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class UsuGempComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('usu-gemp-heading'));
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
    await navBarPage.getEntityPage('usu-gemp');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateUsuGemp() {
    await this.createButton.click();
    return new UsuGempUpdatePage();
  }

  async deleteUsuGemp() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const usuGempDeleteDialog = new UsuGempDeleteDialog();
    await waitUntilDisplayed(usuGempDeleteDialog.deleteModal);
    expect(await usuGempDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/newGenerationGempApp.usuGemp.delete.question/);
    await usuGempDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(usuGempDeleteDialog.deleteModal);

    expect(await isVisible(usuGempDeleteDialog.deleteModal)).to.be.false;
  }
}
