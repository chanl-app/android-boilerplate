package app.westtabs.chanl.androidboilerplate.data;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;

import javax.inject.Inject;

import app.westtabs.chanl.androidboilerplate.BoilerplateApplication;
import app.westtabs.chanl.androidboilerplate.util.AndroidComponentUtil;
import app.westtabs.chanl.androidboilerplate.util.NetworkUtil;
import rx.Subscription;

public class SyncService extends Service {

    protected String TAG = this.getClass().getSimpleName();

    @Inject
    DataManager mDataManager;
    private Subscription mSubscription;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static boolean isRunning(Context context) {
        return AndroidComponentUtil.isServiceRunning(context, SyncService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BoilerplateApplication.get(this).getComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.d(TAG, "onStartCommand: " +
                "Starting sync...");

        if (!NetworkUtil.isNetworkConnected(this)) {
            Log.d(TAG, "onStartCommand: Sync canceled, connection not available");
            AndroidComponentUtil.toggleComponent(this, SyncOnConnectionAvailable.class, true);
            stopSelf(startId);
            return START_NOT_STICKY;
        }

        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
//        mSubscription = mDataManager.syncUser()
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<User>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "onCompleted: Synced successfully!");
//                        stopSelf(startId);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: Error syncing.");
//                        stopSelf(startId);
//
//                    }
//
//                    @Override
//                    public void onNext(User ribot) {
//                    }
//                });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null) mSubscription.unsubscribe();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class SyncOnConnectionAvailable extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)
                    && NetworkUtil.isNetworkConnected(context)) {
                Log.d("Service", "onReceive: Connection is now available, triggering sync...");
                AndroidComponentUtil.toggleComponent(context, this.getClass(), false);
                context.startService(getStartIntent(context));
            }
        }
    }

}