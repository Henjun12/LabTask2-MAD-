package com.example.dfp50293_lt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextEnglish, editTextBahasa;
    Button buttonAdd, buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEnglish = findViewById(R.id.editTextEnglish);
        editTextBahasa = findViewById(R.id.editTextBahasa);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonView = findViewById(R.id.buttonView);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered English word and Bahasa Malaysia translation
                String englishWord = editTextEnglish.getText().toString();
                String bahasaTranslation = editTextBahasa.getText().toString();

                // You can implement SQLite database insertion here
                // For simplicity, let's assume you have a method to add data to the database

                // Assuming you have a method in DBHelper class to add word to database
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                dbHelper.addWord(englishWord, bahasaTranslation);

                // Clear input fields after adding the word
                editTextEnglish.setText("");
                editTextBahasa.setText("");
            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DictionaryActivity to view the dictionary entries
                Intent intent = new Intent(MainActivity.this, DictionaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
