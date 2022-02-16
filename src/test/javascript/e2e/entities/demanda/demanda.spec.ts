import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import DemandaComponentsPage from './demanda.page-object';
import DemandaUpdatePage from './demanda-update.page-object';
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

describe('Demanda e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let demandaComponentsPage: DemandaComponentsPage;
  let demandaUpdatePage: DemandaUpdatePage;
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
    demandaComponentsPage = new DemandaComponentsPage();
    demandaComponentsPage = await demandaComponentsPage.goToPage(navBarPage);
  });

  it('should load Demandas', async () => {
    expect(await demandaComponentsPage.title.getText()).to.match(/Demandas/);
    expect(await demandaComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Demandas', async () => {
    const beforeRecordsCount = (await isVisible(demandaComponentsPage.noRecords)) ? 0 : await getRecordsCount(demandaComponentsPage.table);
    demandaUpdatePage = await demandaComponentsPage.goToCreateDemanda();
    await demandaUpdatePage.enterData();
    expect(await isVisible(demandaUpdatePage.saveButton)).to.be.false;

    expect(await demandaComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(demandaComponentsPage.table);
    await waitUntilCount(demandaComponentsPage.records, beforeRecordsCount + 1);
    expect(await demandaComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await demandaComponentsPage.deleteDemanda();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(demandaComponentsPage.records, beforeRecordsCount);
      expect(await demandaComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(demandaComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
