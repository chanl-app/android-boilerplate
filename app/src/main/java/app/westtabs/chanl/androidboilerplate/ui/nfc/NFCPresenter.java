package app.westtabs.chanl.androidboilerplate.ui.nfc;

import javax.inject.Inject;

import app.westtabs.chanl.androidboilerplate.data.DataManager;
import app.westtabs.chanl.androidboilerplate.ui.base.BasePresenter;

/**
 * Created by bubu on 3/16/16.
 */
public class NFCPresenter extends BasePresenter<NFCMvpView> {
    DataManager dataManager;

    @Inject
    public NFCPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
