package com.example.a31c_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a31c_java.ResultsActivity;

import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private String userName;
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button submitButton;
    private ProgressBar progressBar;

    private List<String> questions;
    private List<List<String>> options;
    private List<String> answers;

    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        userName = getIntent().getStringExtra("userName");
        questions = Arrays.asList(
                "What is the 5 x 5?",
                "Which of the following is an OOP language?",
                "What is the capital of Hungary?",
                "What is the fastest runtime for an algorithm?",
                "How many sides are in a Decagon"
        );
        options = Arrays.asList(
                Arrays.asList("20", "25", "50", "30"),
                Arrays.asList("SQL", "Assembly", "PHP", "C#"),
                Arrays.asList("Bucharest", "Budapest", "Prague", "Warsaw"),
                Arrays.asList("O(n^3)", "O(log n)", "O(n)", "O(1)"),
                Arrays.asList("12", "8", "10", "6")
        );
        answers = Arrays.asList("25", "C#", "Budapest", "O(1)", "10");

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);

        displayQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            String question = questions.get(currentQuestionIndex);
            List<String> questionOptions = options.get(currentQuestionIndex);

            questionTextView.setText(question);
            for (int i = 0; i < questionOptions.size(); i++) {
                RadioButton radioButton = (RadioButton) optionsRadioGroup.getChildAt(i);
                radioButton.setText(questionOptions.get(i));
            }

            updateProgressBar();
        } else {
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("userName", userName);
            intent.putExtra("totalQuestions", questions.size());
            intent.putExtra("correctAnswers", correctAnswers);
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedAnswer = selectedRadioButton.getText().toString();

            if (selectedAnswer.equals(answers.get(currentQuestionIndex))) {
                correctAnswers++;
            }

            currentQuestionIndex++;
            displayQuestion();
        }
    }

    private void updateProgressBar() {
        int progress = ((currentQuestionIndex + 1) * 100) / questions.size();
        progressBar.setProgress(progress);
    }
}
