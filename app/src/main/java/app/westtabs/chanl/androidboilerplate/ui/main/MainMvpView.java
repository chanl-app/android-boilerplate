package app.westtabs.chanl.androidboilerplate.ui.main;

import java.util.List;

import app.westtabs.chanl.androidboilerplate.ui.base.MvpView;
import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;

public interface MainMvpView extends MvpView {

    void showRepos(List<Repo> ribots);

    void showRibotsEmpty();
    void showError();

    Void showUser(User user);

    void showToast(String text);

}
