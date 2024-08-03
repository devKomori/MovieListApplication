package com.example.movielistapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * AdminControlsActivity is the activity for admin users to access admin controls.
 */
public class AdminControlsActivity extends AppCompatActivity {

    private Button manageUsersButton;
    private Button manageMoviesButton;
    private Button viewReportsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_controls);

        manageUsersButton = findViewById(R.id.button_manage_users);
        manageMoviesButton = findViewById(R.id.button_manage_movies);
        viewReportsButton = findViewById(R.id.button_view_reports);

        manageUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminControlsActivity.this, "Manage Users clicked", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
