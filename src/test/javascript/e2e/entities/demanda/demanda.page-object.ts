import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import DemandaUpdatePage from './demanda-update.page-object';

const expect = chai.expect;
export class DemandaDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('newGenerationGempApp.demanda.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-demanda'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class DemandaComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('demanda-heading'));
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
    await navBarPage.getEntityPage('demanda');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateDemanda() {
    await this.createButton.click();
    return new DemandaUpdatePage();
  }

  async deleteDemanda() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const demandaDeleteDialog = new DemandaDeleteDialog();
    await waitUntilDisplayed(demandaDeleteDialog.deleteModal);
    expect(await demandaDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/newGenerationGempApp.demanda.delete.question/);
    await demandaDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(demandaDeleteDialog.deleteModal);

    expect(await isVisible(demandaDeleteDialog.deleteModal)).to.be.false;
  }
}
