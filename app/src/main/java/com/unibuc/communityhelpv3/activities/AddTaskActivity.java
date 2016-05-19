package com.unibuc.communityhelpv3.activities;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.interfaces.CreateTaskListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Serban Theodor on 01-Apr-16.
 */
public class AddTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, CreateTaskListener {

    private TextView task_start_time_editText;
    private TextView task_end_time_editText;

    private TextView task_start_date_textView;
    private TextView task_end_date_textView ;


    private TextView task_start_date_textView;
    private TextView task_end_date_textView ;

    private Calendar toTime;


    private TextView task_title_editText;
    private TextView task_description_editText;
    private TextView task_reward_cost_editText;
    private TextView task_location_editText;

    private Button task_post_button;

    private Spinner task_category_spinner;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private TimePickerDialog fromTimePickerDialog;
    private TimePickerDialog toTimePickerDialog;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;
    private Calendar fromTime;
    private Calendar toTime;

    ArrayList<String> categoryList;
    ArrayAdapter<String> spinnerAdapter;

    private NetworkManager networkManager;
    AccessToken accessToken;

    private static final String TAG = "AddTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        networkManager = NetworkManager.getInstance();

        categoryList = new ArrayList<>();
        categoryList.add("Select a category");
        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, categoryList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        accessToken = AccessToken.getCurrentAccessToken();

        initLayout();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        if (position > 0) {
            String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {

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
        task_start_date_textView = (TextView) findViewById(R.id.task_start_date_textView);
        task_end_date_textView = (TextView) findViewById(R.id.task_end_date_textView);



        task_title_editText = (TextView) findViewById(R.id.task_title_editText);
        task_description_editText = (TextView) findViewById(R.id.task_description_editText);
        task_location_editText = (TextView) findViewById(R.id.task_location_editText);
        task_reward_cost_editText = (TextView) findViewById(R.id.task_reward_cost_editText);

        task_post_button = (Button) findViewById(R.id.task_post_button);

        task_category_spinner = (Spinner) findViewById(R.id.task_category_spinner);

        task_category_spinner.setAdapter(spinnerAdapter);
        task_category_spinner.setSelection(0);

        dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);
        timeFormatter = new SimpleDateFormat("H:mm", Locale.US);

        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.i(TAG, year + " " + monthOfYear + " " + dayOfMonth);
                fromTime.set(Calendar.YEAR, year);
                fromTime.set(Calendar.MONTH, monthOfYear);
                fromTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                task_start_date_textView.setText(dateFormatter.format(fromTime.getTime()));

                toTime.set(Calendar.YEAR, year);
                toTime.set(Calendar.MONTH, monthOfYear);
                toTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                task_end_date_textView.setText(dateFormatter.format(toTime.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.i(TAG, year + " " + monthOfYear + " " + dayOfMonth);

                toTime.set(Calendar.YEAR, year);
                toTime.set(Calendar.MONTH, monthOfYear);
                toTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                task_end_date_textView.setText(dateFormatter.format(toTime.getTime()));

            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        fromTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.i(TAG, hourOfDay + ":" + minute);

                fromTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                fromTime.set(Calendar.MINUTE, minute);

                task_start_time_editText.setText(timeFormatter.format(fromTime.getTime()));

                toTime.set(Calendar.HOUR_OF_DAY, hourOfDay + 1);
                toTime.set(Calendar.MINUTE, minute);

                task_end_time_editText.setText(timeFormatter.format(toTime.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);

        toTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.i(TAG, hourOfDay + ":" + minute);

                toTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                toTime.set(Calendar.MINUTE, minute);

                task_end_time_editText.setText(timeFormatter.format(toTime.getTime()));

            }
        }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);

        initFields();
        initListeners();
    }

    private void initFields() {
        fromTime = Calendar.getInstance();
        toTime = Calendar.getInstance();
        toTime.add(Calendar.HOUR_OF_DAY, 1);

        task_start_date_textView.setText(dateFormatter.format(fromTime.getTime()));
        task_end_date_textView.setText(dateFormatter.format(toTime.getTime()));
        task_start_time_editText.setText(timeFormatter.format(fromTime.getTime()));
        task_end_time_editText.setText(timeFormatter.format(toTime.getTime()));
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
                toTimePickerDialog.show();
            }
        });

        task_start_date_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });

        task_end_date_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog.show();
            }
        });

        task_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task_title = task_title_editText.getText().toString();
                String task_description = task_description_editText.getText().toString();
                //String task_category = task_category_spinner.getSelectedItem().toString();
                int task_category = 1;
                String task_location = task_location_editText.getText().toString();
                String task_cost_string = task_reward_cost_editText.getText().toString();
                int task_cost = -1;
                if(!task_cost_string.isEmpty())
                    task_cost = Integer.parseInt(task_cost_string);

                long duration = toTime.getTimeInMillis() - fromTime.getTimeInMillis();
                int task_duration = (int)duration;
                Log.i(TAG, duration+"");

                if(accessToken != null && !task_title.equals("") && !task_description.equals("") &&
                        !task_location.equals("") && task_category > 0 && task_cost > 0 && duration > 0) {
                    Log.i(TAG, accessToken.getToken());
                    networkManager.createTask(accessToken.getToken(), task_title, task_description, task_category
                            , task_cost, task_duration, AddTaskActivity.this);

                    Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(AddTaskActivity.this, "All fields must be completed!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "No access token");
                }

            }
        });
    }

    @Override
    public void onCreateTaskSuccess()
    {
        Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCreateTaskFailed()
    {
        Toast.makeText(this, "Failed to fetch tasks!", Toast.LENGTH_SHORT).show();
    }


}
