package app.westtabs.chanl.androidboilerplate.ui.main;

import java.util.List;

import app.westtabs.chanl.androidboilerplate.ui.base.MvpView;
import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;

public interface MainMvpView extends MvpView {

    void showRibots(List<Repo> ribots);

    void showRibotsEmpty();
    void showError();

    void showUser(User user);

    void showToast(String text);

}
