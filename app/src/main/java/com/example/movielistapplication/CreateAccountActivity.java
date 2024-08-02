package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.movielistapplication.Database.entities.User;

/*
public class CreateAccountActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private EditText usernameEditText, passwordEditText, confirmPasswordEditText;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        usernameEditText = findViewById(R.id.edit_text_username);
        passwordEditText = findViewById(R.id.edit_text_password);
        confirmPasswordEditText = findViewById(R.id.edit_text_confirm_password);
        createAccountButton = findViewById(R.id.button_create_account);
        createAccountButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setIsAdmin(false);  // Default to non-admin user
                userViewModel.insertUser(newUser);
                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                intent.putExtra("userId", newUser.getId());
                intent.putExtra("isAdmin", newUser.isAdmin());
                startActivity(intent);
                finish();
            }
        });
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, CreateAccountActivity.class);
    }
}

*/
