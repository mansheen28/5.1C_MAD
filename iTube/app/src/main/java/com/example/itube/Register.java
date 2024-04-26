package com.example.itube;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText fullNameEditText, usernameEditText, passwordEditText, confirmPasswordEditText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullNameEditText = findViewById(R.id.editTextFullName);
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        confirmPasswordEditText = findViewById(R.id.editTextCPassword);
        registerButton = findViewById(R.id.buttonRegister);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String fullName = fullNameEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if(!password.equals(confirmPassword)) {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                    Toast.makeText(Register.this, "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    insertUserIntoDatabase(username, password, fullName);
                }
            }
        });
    }

    private void insertUserIntoDatabase(String username, String password, String fullName) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_FULL_NAME, fullName);
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();

        Toast.makeText(Register.this, "Registration done!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
