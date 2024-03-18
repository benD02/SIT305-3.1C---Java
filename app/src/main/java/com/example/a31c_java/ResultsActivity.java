package com.example.a31c_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button retakeQuizButton = findViewById(R.id.retakeQuizButton);
        Button finishQuizButton = findViewById(R.id.finishQuizButton);

        retakeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        finishQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });


    }
}
