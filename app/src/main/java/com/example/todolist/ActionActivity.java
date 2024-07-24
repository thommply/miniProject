package com.example.todolist;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        // Hiển thị thông báo toast khi activity này được khởi động
        Toast.makeText(this, "Task completed!!", Toast.LENGTH_SHORT).show();
    }
}