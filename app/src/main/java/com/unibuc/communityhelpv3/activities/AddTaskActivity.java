package com.unibuc.communityhelpv3.activities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.unibuc.communityhelpv3.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Serban Theodor on 01-Apr-16.
 */
public class AddTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView task_start_time_editText;
    private TextView task_end_time_editText;
    private TextView task_title_editText;
    private TextView task_description_editText;
    private TextView task_reward_cost_editText;

    private Button task_post_button;

    private Spinner task_category_spinner;

    private TimePickerDialog fromTimePickerDialog;

    private SimpleDateFormat timeFormatter;
    private Calendar fromTime;

    ArrayList<String> categoryList;
    ArrayAdapter<String> spinnerAdapter;

    private static final String TAG = "AddTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        categoryList = new ArrayList<>();
        categoryList.add("Select a category");
        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, categoryList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        initLayout();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        if (position > 0) {
            String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        } else if (id == R.id.user_profile){
            startActivity(new Intent(AddTaskActivity.this, ProfileActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void initLayout() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.layout_action_bar);
        TextView title = (TextView) actionBar.getCustomView().findViewById(R.id.title_action_bar_layout_textView);
        title.setText(R.string.task_add_title);

        task_start_time_editText = (TextView) findViewById(R.id.task_start_time_textView);
        task_end_time_editText = (TextView) findViewById(R.id.task_end_time_textView);
        task_title_editText = (TextView) findViewById(R.id.task_title_editText);
        task_description_editText = (TextView) findViewById(R.id.task_description_editText);

        task_post_button = (Button) findViewById(R.id.task_post_button);

        task_category_spinner = (Spinner) findViewById(R.id.task_category_spinner);

        task_category_spinner.setAdapter(spinnerAdapter);
        task_category_spinner.setSelection(0);

        timeFormatter = new SimpleDateFormat("H:mm", Locale.US);

        fromTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.i(TAG, hourOfDay + ":" + minute);

                fromTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                fromTime.set(Calendar.MINUTE, minute);

                task_start_time_editText.setText(timeFormatter.format(fromTime.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);

        initListeners();
    }


    private void initListeners()
    {
        task_start_time_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromTimePickerDialog.show();
            }
        });

        task_end_time_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fromTimePickerDialog.show();
            }
        });

        task_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cacat la tava
            }
        });
    }


}
