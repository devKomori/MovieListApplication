/*
package com.example.movielistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
TODO this was commented out because the program cannot run otherwise.
    highlight all code and press ctrl + shift + / to uncomment

*/
/**
 * AdminControlsActivity is the activity for admin users to access admin controls.
 *//*


public class AdminControlsActivity extends AppCompatActivity {

    */
/**
     * Called when the activity is first created.
     *//*

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_controls);

        // Initialize button
        Button manageUsersButton = findViewById(R.id.button_manage_users);

        // Set onClick listener button
        manageUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminControlsActivity.this, "Manage Users clicked", Toast.LENGTH_SHORT).show();
                // Start ManageUsersActivity
                Intent intent = new Intent(AdminControlsActivity.this, ManageUsersActivity.class);
                startActivity(intent);
            }
        });
    }
}
*/
