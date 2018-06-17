package com.example.pc.diagnosticofresadoras;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pc.diagnosticofresadoras.modeloAlarmas.AlarmTable;
import com.example.pc.diagnosticofresadoras.modeloAlarmas.Answer;
import com.example.pc.diagnosticofresadoras.modeloAlarmas.Question;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;

import uk.co.senab.photoview.PhotoViewAttacher;

public class QuestionsActivity extends AppCompatActivity {

    TextView tvAlarmTitle, tvQues;
    AlarmTable alarms;
    Question question;
    String cod;
    PhotoViewAttacher pAttacher;

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

        LinearLayout llImageQuestion = findViewById(R.id.llImageQuestion);
        LinearLayout.LayoutParams llImageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (question.getImages() != null) {
            for (String nameImage : question.getImages()) {
                ImageView image = new ImageView(this);
                image.setLayoutParams(llImageParams);
                int resImage = alarms.getDiccImages().get(nameImage);
                image.setImageResource(resImage);
                pAttacher = new PhotoViewAttacher(image);
                llImageQuestion.addView(image);
            }
        }


        fillButtons();
    }

    private void fillButtons() {
        LinearLayout llKeypad = findViewById(R.id.llKeypad);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (Answer answer : question.getAnswers()) {
            Button ans = new Button(this);
            ans.setLayoutParams(llParams);
            ans.setText(answer.getText());
            llKeypad.addView(ans);
            ans.setOnClickListener(new ButtonsOnClickListener(answer.getNext(), answer.getMessage(),
                    answer.getText()));
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
        String finalMessage, text;

        public ButtonsOnClickListener(double next, String finalMessage, String text) {
            this.next = next;
            this.finalMessage = finalMessage;
            this.text = text;
        }

        @Override
        public void onClick(View v) {
            if (next == -1.0) {
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
            writeRegistry();
        }

        private void writeRegistry() {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(
                        "registry.csv", Context.MODE_APPEND));
                int time = (int) (System.currentTimeMillis());
                Timestamp tsTemp = new Timestamp(time);
                String ts = tsTemp.toString();
                osw.write(cod + "," + "\"" + question.getText() + "\"" + "," + "\"" + text +
                        "\"" + "," + ts + "\n");
                osw.close();
            } catch (IOException ex) {
                Log.v("InfoAlarmaActivity", "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
