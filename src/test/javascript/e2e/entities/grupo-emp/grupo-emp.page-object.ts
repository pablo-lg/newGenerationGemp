import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import GrupoEmpUpdatePage from './grupo-emp-update.page-object';

const expect = chai.expect;
export class GrupoEmpDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('newGenerationGempApp.grupoEmp.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-grupoEmp'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class GrupoEmpComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('grupo-emp-heading'));
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
    await navBarPage.getEntityPage('grupo-emp');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateGrupoEmp() {
    await this.createButton.click();
    return new GrupoEmpUpdatePage();
  }

  async deleteGrupoEmp() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const grupoEmpDeleteDialog = new GrupoEmpDeleteDialog();
    await waitUntilDisplayed(grupoEmpDeleteDialog.deleteModal);
    expect(await grupoEmpDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/newGenerationGempApp.grupoEmp.delete.question/);
    await grupoEmpDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(grupoEmpDeleteDialog.deleteModal);

    expect(await isVisible(grupoEmpDeleteDialog.deleteModal)).to.be.false;
  }
}
