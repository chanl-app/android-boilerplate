package app.westtabs.chanl.androidboilerplate.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import app.westtabs.chanl.androidboilerplate.R;
import app.westtabs.chanl.androidboilerplate.data.SyncService;
import app.westtabs.chanl.androidboilerplate.ui.base.BaseActivity;
import app.westtabs.chanl.androidboilerplate.ui.nfc.NFCActivity;
import app.westtabs.chanl.androidboilerplate.util.DialogFactory;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String EXTRA_TRIGGER_SYNC_FLAG = "uk.co.ribot.androidboilerplate.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    RepoAdapter mReposAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.username)
    TextView username;

    @Bind(R.id.imageView)
    ImageView userAvatar;

    @Bind(R.id.username_text)
    TextView name;

    @Bind(R.id.login)
    TextView login;

    @OnClick(R.id.button)
    public void submit(View view) {
        mMainPresenter.getUser(username.getText().toString());
    }


    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        return intent;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(mReposAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);

        if (getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_nfc){
           startActivity(new Intent(this, NFCActivity.class));
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    /*****
     * MVP View methods implementation
     *****/

    @Override
    public void showRepos(List<Repo> repos) {
        mReposAdapter.setRibots(repos);
        mReposAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_ribots))
                .show();
    }

    @Override
    public Void showUser(User user) {
        runOnUiThread(() -> {
            login.setText(user.getLogin());
            name.setText(user.getName());
            Glide.with(MainActivity.this).load(user.getAvatar_url()).into(userAvatar);
        });
        return null;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRibotsEmpty() {
        mReposAdapter.setRibots(Collections.<Repo>emptyList());
        mReposAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_ribots, Toast.LENGTH_LONG).show();
    }
}
