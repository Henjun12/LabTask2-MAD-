package com.example.dfp50293_lt2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DictionaryActivity extends AppCompatActivity {

    private EditText editTextEnglishWord;
    private Button buttonTranslate;
    private TextView textViewEnglishResult, textViewMalayResult;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        editTextEnglishWord = findViewById(R.id.editTextEnglishWord);
        buttonTranslate = findViewById(R.id.buttonTranslate);
        textViewEnglishResult = findViewById(R.id.textViewEnglishResult);
        textViewMalayResult = findViewById(R.id.textViewMalayResult);
        dbHelper = new DBHelper(this);

        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateWord();
            }
        });
    }

    private void translateWord() {
        String englishWord = editTextEnglishWord.getText().toString().trim();
        if (!englishWord.isEmpty()) {
            String bahasaTranslation = dbHelper.getTranslation(englishWord);
            if (bahasaTranslation != null) {
                textViewEnglishResult.setText(englishWord);
                textViewMalayResult.setText(bahasaTranslation);
            } else {
                textViewEnglishResult.setText("");
                textViewMalayResult.setText("Translation not found.");
            }
        } else {
            textViewEnglishResult.setText("");
            textViewMalayResult.setText("Please enter an English word.");
        }
    }
}
