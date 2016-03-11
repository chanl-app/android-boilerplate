package app.westtabs.chanl.androidboilerplate.ui.events;

import java.util.List;

import dao.greenrobot.dao.Repo;

/**
 * Created by bubu on 3/11/16.
 */
public class OnReposListReadyEvent {
    private List<Repo> list;

    public OnReposListReadyEvent(List<Repo> list) {
        this.list = list;
    }

    public List<Repo> getList() {
        return list;
    }
}
