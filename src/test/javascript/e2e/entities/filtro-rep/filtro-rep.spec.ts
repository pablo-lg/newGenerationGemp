import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FiltroRepComponentsPage from './filtro-rep.page-object';
import FiltroRepUpdatePage from './filtro-rep-update.page-object';
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

describe('FiltroRep e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let filtroRepComponentsPage: FiltroRepComponentsPage;
  let filtroRepUpdatePage: FiltroRepUpdatePage;
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
    filtroRepComponentsPage = new FiltroRepComponentsPage();
    filtroRepComponentsPage = await filtroRepComponentsPage.goToPage(navBarPage);
  });

  it('should load FiltroReps', async () => {
    expect(await filtroRepComponentsPage.title.getText()).to.match(/Filtro Reps/);
    expect(await filtroRepComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete FiltroReps', async () => {
    const beforeRecordsCount = (await isVisible(filtroRepComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(filtroRepComponentsPage.table);
    filtroRepUpdatePage = await filtroRepComponentsPage.goToCreateFiltroRep();
    await filtroRepUpdatePage.enterData();
    expect(await isVisible(filtroRepUpdatePage.saveButton)).to.be.false;

    expect(await filtroRepComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(filtroRepComponentsPage.table);
    await waitUntilCount(filtroRepComponentsPage.records, beforeRecordsCount + 1);
    expect(await filtroRepComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await filtroRepComponentsPage.deleteFiltroRep();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(filtroRepComponentsPage.records, beforeRecordsCount);
      expect(await filtroRepComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(filtroRepComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
