package com.example.a31c_java;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.a31c_java.ResultsActivity;

import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private String userName;
    private TextView questionTextView;
    private ProgressBar progressBar;

    private List<String> questions;
    private List<List<String>> options;
    private List<String> answers;

    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private Button option1Button, option2Button, option3Button, option4Button;


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
        progressBar = findViewById(R.id.progressBar);

        displayQuestion();



        setButtonListeners();


    }

    private void displayQuestion() {

        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        option4Button = findViewById(R.id.option4Button);

        if (currentQuestionIndex < questions.size()) {
            String question = questions.get(currentQuestionIndex);
            List<String> questionOptions = options.get(currentQuestionIndex);

            questionTextView.setText(question);
            option1Button.setText(questionOptions.get(0));
            option2Button.setText(questionOptions.get(1));
            option3Button.setText(questionOptions.get(2));
            option4Button.setText(questionOptions.get(3));

            option1Button.setBackgroundColor(Color.LTGRAY); // Resetting color
            option2Button.setBackgroundColor(Color.LTGRAY);
            option3Button.setBackgroundColor(Color.LTGRAY);
            option4Button.setBackgroundColor(Color.LTGRAY);

            option1Button.setEnabled(true); // Re-enabling the buttons
            option2Button.setEnabled(true);
            option3Button.setEnabled(true);
            option4Button.setEnabled(true);

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

    private void setButtonListeners() {
        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String selectedAnswer = clickedButton.getText().toString();
                if (selectedAnswer.equals(answers.get(currentQuestionIndex))) {
                    clickedButton.setBackgroundColor(ContextCompat.getColor(QuizActivity.this, R.color.correctAnswer));
                    correctAnswers++;
                } else {
                    clickedButton.setBackgroundColor(ContextCompat.getColor(QuizActivity.this, R.color.incorrectAnswer));
                }

                // Disable all buttons after one is clicked to prevent multiple answers
                option1Button.setEnabled(false);
                option2Button.setEnabled(false);
                option3Button.setEnabled(false);
                option4Button.setEnabled(false);

                // Handler to add some delay before moving to next question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentQuestionIndex++;
                        displayQuestion();
                    }
                }, 1000); // 1 second delay
            }
        };

        option1Button.setOnClickListener(answerButtonClickListener);
        option2Button.setOnClickListener(answerButtonClickListener);
        option3Button.setOnClickListener(answerButtonClickListener);
        option4Button.setOnClickListener(answerButtonClickListener);
    }



    private void updateProgressBar() {
        int progress = ((currentQuestionIndex + 1) * 100) / questions.size();
        progressBar.setProgress(progress);
    }
}
