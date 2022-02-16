import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import HistoWFComponentsPage from './histo-wf.page-object';
import HistoWFUpdatePage from './histo-wf-update.page-object';
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

describe('HistoWF e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let histoWFComponentsPage: HistoWFComponentsPage;
  let histoWFUpdatePage: HistoWFUpdatePage;
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
    histoWFComponentsPage = new HistoWFComponentsPage();
    histoWFComponentsPage = await histoWFComponentsPage.goToPage(navBarPage);
  });

  it('should load HistoWFS', async () => {
    expect(await histoWFComponentsPage.title.getText()).to.match(/Histo WFS/);
    expect(await histoWFComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete HistoWFS', async () => {
    const beforeRecordsCount = (await isVisible(histoWFComponentsPage.noRecords)) ? 0 : await getRecordsCount(histoWFComponentsPage.table);
    histoWFUpdatePage = await histoWFComponentsPage.goToCreateHistoWF();
    await histoWFUpdatePage.enterData();
    expect(await isVisible(histoWFUpdatePage.saveButton)).to.be.false;

    expect(await histoWFComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(histoWFComponentsPage.table);
    await waitUntilCount(histoWFComponentsPage.records, beforeRecordsCount + 1);
    expect(await histoWFComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await histoWFComponentsPage.deleteHistoWF();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(histoWFComponentsPage.records, beforeRecordsCount);
      expect(await histoWFComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(histoWFComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
