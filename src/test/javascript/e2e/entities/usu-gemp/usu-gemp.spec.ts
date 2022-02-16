import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import UsuGempComponentsPage from './usu-gemp.page-object';
import UsuGempUpdatePage from './usu-gemp-update.page-object';
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

describe('UsuGemp e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let usuGempComponentsPage: UsuGempComponentsPage;
  let usuGempUpdatePage: UsuGempUpdatePage;
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
    usuGempComponentsPage = new UsuGempComponentsPage();
    usuGempComponentsPage = await usuGempComponentsPage.goToPage(navBarPage);
  });

  it('should load UsuGemps', async () => {
    expect(await usuGempComponentsPage.title.getText()).to.match(/Usu Gemps/);
    expect(await usuGempComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete UsuGemps', async () => {
    const beforeRecordsCount = (await isVisible(usuGempComponentsPage.noRecords)) ? 0 : await getRecordsCount(usuGempComponentsPage.table);
    usuGempUpdatePage = await usuGempComponentsPage.goToCreateUsuGemp();
    await usuGempUpdatePage.enterData();
    expect(await isVisible(usuGempUpdatePage.saveButton)).to.be.false;

    expect(await usuGempComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(usuGempComponentsPage.table);
    await waitUntilCount(usuGempComponentsPage.records, beforeRecordsCount + 1);
    expect(await usuGempComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await usuGempComponentsPage.deleteUsuGemp();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(usuGempComponentsPage.records, beforeRecordsCount);
      expect(await usuGempComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(usuGempComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
