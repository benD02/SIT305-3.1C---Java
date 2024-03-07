package com.example.a31c_java;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String userName = getIntent().getStringExtra("userName");
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);
        int correctAnswers = getIntent().getIntExtra("correctAnswers", 0);

        TextView resultsTextView = findViewById(R.id.resultsTextView);
        resultsTextView.setText("Congratulations, " + userName + "!\nYou answered " + correctAnswers +
                " out of " + totalQuestions + " questions correctly.");
    }
}
