package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormatSymbols;


public class AddProject extends AppCompatActivity {

    private EditText etStartDate, etEndDate, groupTask, nameProject, description;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private Button btn, calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        calendar = Calendar.getInstance();

        groupTask = findViewById(R.id.group_task);
        nameProject = findViewById(R.id.name_project);
        description = findViewById(R.id.description);

        groupTask.setTextColor(Color.BLACK);
        nameProject.setTextColor(Color.BLACK);
        description.setTextColor(Color.BLACK);

        btn = findViewById(R.id.btnAddProject);
        calendarView = findViewById(R.id.btnView);

        NotificationHelper.createNotificationChannel(this);

        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(etStartDate);
            }
        });

        etEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(etEndDate);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddProject.this, "Create successfully!", Toast.LENGTH_LONG).show();
                scheduleTask();
            }
        });

        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProject.this, ListItem.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog(final EditText editText){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddProject.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + " " + getMonthName(month) + ", " + year;
                        editText.setText(date);
                        editText.setTextColor(Color.BLACK);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private String getMonthName(int month){
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        return months[month];
    }

    private void setTaskReminder(String taskName, Calendar taskTime){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TaskReminderReceiver.class);
        intent.putExtra("TASK_NAME", taskName);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(alarmManager != null){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, taskTime.getTimeInMillis(), pendingIntent);
        }
    }
    //Dat lich
    private void scheduleTask(){
        Calendar taskTime = Calendar.getInstance();
        taskTime.set(Calendar.YEAR, 2024);
        taskTime.set(Calendar.MONTH, Calendar.JULY);
        taskTime.set(Calendar.DAY_OF_MONTH, 25);
        taskTime.set(Calendar.HOUR_OF_DAY, 1);
        taskTime.set(Calendar.MINUTE, 16);

        String taskName = "Hello Samsung";
        setTaskReminder(taskName, taskTime);
    }
}