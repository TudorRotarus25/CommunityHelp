package com.unibuc.communityhelpv3.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.pojos.NotificationsGetBody;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;

/**
 * Created by Serban Theodor on 13-May-16.
 */
public class NotificationsListAdapter extends BaseAdapter {
    private final String TAG = getClass().getSimpleName();
    NotificationsGetBody notifications;

    Context context;

    public NotificationsListAdapter(NotificationsGetBody notifications)
    {
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listView = convertView;
        ViewHolder holder;

        if (listView == null) {

            listView = inflater.inflate(R.layout.layout_notification_item, null);
            holder = new ViewHolder();

            holder.notification_title = (TextView) listView.findViewById(R.id.notification_title);
            holder.notification_text = (TextView) listView.findViewById(R.id.notification_text);
            holder.notification_time = (TextView) listView.findViewById(R.id.notification_timestamp);
            holder.notification_icon = (ImageView) listView.findViewById(R.id.notification_icon);

        } else {
            holder = (ViewHolder) listView.getTag();
        }

        NotificationsGetBody.Notification not = notifications.getNotifications().get(position);
        holder.notification_title.setText(not.getTitle());
        holder.notification_text.setText("" + not.getText());
        holder.notification_time.setText(not.getDate_received());
        holder.notification_icon.setImageResource(R.mipmap.ic_launcher);

        return null;
    }

    public class ViewHolder{

        //other tasks
        TextView notification_title;
        TextView notification_text;
        TextView notification_time;
        ImageView notification_icon;

    }
}
