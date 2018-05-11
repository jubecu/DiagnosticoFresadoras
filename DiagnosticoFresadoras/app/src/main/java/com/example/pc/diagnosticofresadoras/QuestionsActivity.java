package com.example.pc.diagnosticofresadoras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pc.diagnosticofresadoras.modeloAlarmas.AlarmTable;
import com.example.pc.diagnosticofresadoras.modeloAlarmas.Answer;
import com.example.pc.diagnosticofresadoras.modeloAlarmas.Question;

public class QuestionsActivity extends AppCompatActivity {

    TextView tvAlarmTitle, tvQues;
    AlarmTable alarms;
    Question question;
    String cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Bundle extras = getIntent().getExtras();
        cod = extras.getString("numAlarm");
        double idQuestion = extras.getDouble("idQuestion");

        int num = Integer.parseInt(cod);
        alarms = AlarmTable.getInstance();
        question = alarms.getAlarm(num).getQuestion(idQuestion);

        fillData(cod);

        LinearLayout llKeypad = findViewById(R.id.llKeypad);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (Answer answer : question.getAnswers()) {
            Button ans = new Button(this);
            ans.setLayoutParams(llParams);
            ans.setText(answer.getText());
            llKeypad.addView(ans);
            ans.setOnClickListener(new ButtonsOnClickListener(answer.getNext(), answer.getMessage()));
        }
    }

    private void fillData(String cod) {
        String title;
        int num = Integer.parseInt(cod);

        title = alarms.getAlarm(num).getTitle();
        tvAlarmTitle = findViewById(R.id.tvAlarmaTitulo);
        tvAlarmTitle.setText("Alarma " + cod + ": " + title);

        tvQues = findViewById(R.id.tvQues);
        tvQues.setText(question.getText());
    }

    class ButtonsOnClickListener implements View.OnClickListener {

        double next;
        String finalMessage;

        public ButtonsOnClickListener(double next, String finalMessage) {
            this.next = next;
            this.finalMessage = finalMessage;
        }

        @Override
        public void onClick(View v) {
            if (next == 0.0) {
                Intent i = new Intent(QuestionsActivity.this, MessageFinalActivity.class);
                i.putExtra("numAlarm", cod);
                i.putExtra("finalMessage", finalMessage);
                startActivity(i);
            } else {
                Intent i = new Intent(QuestionsActivity.this, QuestionsActivity.class);
                i.putExtra("numAlarm", cod);
                i.putExtra("idQuestion", next);
                startActivity(i);
            }
        }
    }
}
