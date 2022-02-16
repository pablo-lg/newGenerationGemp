import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import FiltroRepUpdatePage from './filtro-rep-update.page-object';

const expect = chai.expect;
export class FiltroRepDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('newGenerationGempApp.filtroRep.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-filtroRep'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class FiltroRepComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('filtro-rep-heading'));
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
    await navBarPage.getEntityPage('filtro-rep');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateFiltroRep() {
    await this.createButton.click();
    return new FiltroRepUpdatePage();
  }

  async deleteFiltroRep() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const filtroRepDeleteDialog = new FiltroRepDeleteDialog();
    await waitUntilDisplayed(filtroRepDeleteDialog.deleteModal);
    expect(await filtroRepDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/newGenerationGempApp.filtroRep.delete.question/);
    await filtroRepDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(filtroRepDeleteDialog.deleteModal);

    expect(await isVisible(filtroRepDeleteDialog.deleteModal)).to.be.false;
  }
}
