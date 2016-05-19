package com.unibuc.communityhelpv3.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.adapters.NotificationsListAdapter;
import com.unibuc.communityhelpv3.pojos.NotificationsGetBody;
import com.unibuc.communityhelpv3.utils.NotificationUtils;

import java.util.ArrayList;

/**
 * Created by Serban Theodor on 13-May-16.
 */
public class NotificationsActivity extends AppCompatActivity {

    private static final String TAG = "NotificationActivity";
    private NotificationsListAdapter notificationsListAdapter;
    private ArrayList<NotificationsGetBody.Notification> notifications;
    private ListView listView;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_layout);
        notifications = new ArrayList<>();
        initLayout();
        setList(null);
    }

    @Override
    protected void onPause() {
        // Unregister since the activity is paused.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                MessageReceivedBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                MessageReceivedBroadcastReceiver, new IntentFilter("notification_received_from_server"));

        //setList();
        // clearing the notification tray
        NotificationUtils.clearNotifications();
    }


    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(MessageReceivedBroadcastReceiver);
        super.onDestroy();
    }

    private BroadcastReceiver MessageReceivedBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            intent= getIntent();
            setList(intent);
            Log.d(TAG, "broadcast received");
        }
    };

    private void initLayout() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.layout_action_bar);

        listView = (ListView) findViewById(R.id.listview_notifications);
        textview = (TextView) findViewById(R.id.no_notifications_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, position+"");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /*
        MenuItem item = menu.findItem(R.id.notification_button_action_bar);
        MenuItemCompat.setActionView(item, R.layout.layout_action_bar);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);

        TextView tv = (TextView) notifCount.findViewById(R.id.actionbar_notification_badge);
        tv.setText("12");
        */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.user_profile){
            startActivity(new Intent(NotificationsActivity.this, ProfileActivity.class));
        }
        else if (id == R.id.action_logout) {
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(NotificationsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if(id == R.id.notification_button_action_bar)
        {
            startActivity(new Intent(NotificationsActivity.this, MainActivity.class));
            //Log.d(TAG, "notification button clicked");

        }

        return super.onOptionsItemSelected(item);
    }

    public void setList(Intent intent)
    {
        if(notifications.size() == 0)
        {
            textview.setText("No notifications");
            textview.setVisibility(View.VISIBLE);
        }
        else
        {
            textview.setVisibility(View.INVISIBLE);
        }

        if(intent != null) {
            String message, title, user_id, task_id, current_time, type;
            title = intent.getStringExtra("title");
            message = intent.getStringExtra("message");
            user_id = intent.getStringExtra("user_id");
            task_id = intent.getStringExtra("task_id");
            current_time = intent.getStringExtra("date_received");
            type = intent.getStringExtra("type");

            NotificationsGetBody.Notification not = new NotificationsGetBody().
                    new Notification(title, message, type, task_id, user_id, current_time);

            notifications.add(not);
        }

        NotificationsGetBody notificationsGetBody = new NotificationsGetBody(notifications);
        notificationsListAdapter = new NotificationsListAdapter(notificationsGetBody);
        listView.setAdapter(notificationsListAdapter);
        notificationsListAdapter.notifyDataSetChanged();
        Log.d(TAG, "setList reached");
    }
}
