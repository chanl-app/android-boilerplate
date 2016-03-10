package app.westtabs.chanl.androidboilerplate.ui.main;

import java.util.List;

import app.westtabs.chanl.androidboilerplate.data.model.Ribot;
import app.westtabs.chanl.androidboilerplate.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
