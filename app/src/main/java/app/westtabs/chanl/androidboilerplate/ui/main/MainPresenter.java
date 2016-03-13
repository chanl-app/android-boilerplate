package app.westtabs.chanl.androidboilerplate.ui.main;

import javax.inject.Inject;

import app.westtabs.chanl.androidboilerplate.data.DataManager;
import app.westtabs.chanl.androidboilerplate.ui.base.BasePresenter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    protected String TAG = this.getClass().getSimpleName();

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void getUser(String username) {
        checkViewAttached();
        mSubscription = mDataManager.syncUser(username)
                .doOnNext(user ->
                        mDataManager.syncUserRepos(user.getLogin())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(repos -> getMvpView().showRepos(repos)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    getMvpView().showToast("User not found");
                    return Observable.empty();
                })
                .subscribe(user -> getMvpView().showUser(user));
    }

//    public void getRepos(String login) {
//        checkViewAttached();
//        return mDataManager.syncUserRepos(login);
//    }
}
