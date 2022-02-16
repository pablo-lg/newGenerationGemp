import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ParametrosComponentsPage from './parametros.page-object';
import ParametrosUpdatePage from './parametros-update.page-object';
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

describe('Parametros e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let parametrosComponentsPage: ParametrosComponentsPage;
  let parametrosUpdatePage: ParametrosUpdatePage;
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
    parametrosComponentsPage = new ParametrosComponentsPage();
    parametrosComponentsPage = await parametrosComponentsPage.goToPage(navBarPage);
  });

  it('should load Parametros', async () => {
    expect(await parametrosComponentsPage.title.getText()).to.match(/Parametros/);
    expect(await parametrosComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Parametros', async () => {
    const beforeRecordsCount = (await isVisible(parametrosComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(parametrosComponentsPage.table);
    parametrosUpdatePage = await parametrosComponentsPage.goToCreateParametros();
    await parametrosUpdatePage.enterData();
    expect(await isVisible(parametrosUpdatePage.saveButton)).to.be.false;

    expect(await parametrosComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(parametrosComponentsPage.table);
    await waitUntilCount(parametrosComponentsPage.records, beforeRecordsCount + 1);
    expect(await parametrosComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await parametrosComponentsPage.deleteParametros();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(parametrosComponentsPage.records, beforeRecordsCount);
      expect(await parametrosComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(parametrosComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
