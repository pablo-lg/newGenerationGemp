import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import GrupoEmpComponentsPage from './grupo-emp.page-object';
import GrupoEmpUpdatePage from './grupo-emp-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';

const expect = chai.expect;

describe('GrupoEmp e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let grupoEmpComponentsPage: GrupoEmpComponentsPage;
  let grupoEmpUpdatePage: GrupoEmpUpdatePage;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();
    await signInPage.username.sendKeys(username);
    await signInPage.password.sendKeys(password);
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
    await waitUntilDisplayed(navBarPage.adminMenu);
    await waitUntilDisplayed(navBarPage.accountMenu);
  });

  beforeEach(async () => {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    grupoEmpComponentsPage = new GrupoEmpComponentsPage();
    grupoEmpComponentsPage = await grupoEmpComponentsPage.goToPage(navBarPage);
  });

  it('should load GrupoEmps', async () => {
    expect(await grupoEmpComponentsPage.title.getText()).to.match(/Grupo Emps/);
    expect(await grupoEmpComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete GrupoEmps', async () => {
    const beforeRecordsCount = (await isVisible(grupoEmpComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(grupoEmpComponentsPage.table);
    grupoEmpUpdatePage = await grupoEmpComponentsPage.goToCreateGrupoEmp();
    await grupoEmpUpdatePage.enterData();
    expect(await isVisible(grupoEmpUpdatePage.saveButton)).to.be.false;

    expect(await grupoEmpComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(grupoEmpComponentsPage.table);
    await waitUntilCount(grupoEmpComponentsPage.records, beforeRecordsCount + 1);
    expect(await grupoEmpComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await grupoEmpComponentsPage.deleteGrupoEmp();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(grupoEmpComponentsPage.records, beforeRecordsCount);
      expect(await grupoEmpComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(grupoEmpComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
