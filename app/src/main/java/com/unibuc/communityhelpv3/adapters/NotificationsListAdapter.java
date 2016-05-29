package com.unibuc.communityhelpv3.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.pojos.NotificationsGetBody;

/**
 * Created by Serban Theodor on 13-May-16.
 */
public class NotificationsListAdapter extends BaseAdapter {
    private final String TAG = getClass().getSimpleName();
    NotificationsGetBody notifications;

    Context context;

    public NotificationsListAdapter(NotificationsGetBody notifications, Context context)
    {
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.getNotifications().size();
    }

    @Override
    public NotificationsGetBody.Notification getItem(int position) {
        return notifications.getNotifications().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.layout_notification_item, null);


        TextView notification_title = (TextView) convertView.findViewById(R.id.notification_title);
        TextView notification_text = (TextView) convertView.findViewById(R.id.notification_text);
        TextView notification_time = (TextView) convertView.findViewById(R.id.notification_timestamp);
        ImageView notification_icon = (ImageView) convertView.findViewById(R.id.notification_icon);

        NotificationsGetBody.Notification not = notifications.getNotifications().get(position);
        if(not.getSeen().equals("1"))
            convertView.setBackgroundColor(context.getResources().getColor(R.color.grey));

        notification_title.setText(not.getTitle());
        notification_text.setText(not.getMessage());
        notification_time.setText(not.getCreated_at());
        notification_icon.setImageResource(R.mipmap.ic_launcher);

        return convertView;
    }

}
