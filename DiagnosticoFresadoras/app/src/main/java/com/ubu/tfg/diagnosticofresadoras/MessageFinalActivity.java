package com.ubu.tfg.diagnosticofresadoras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.AlarmTable;

public class MessageFinalActivity extends AppCompatActivity {

    TextView tvAlarmTitle;
    TextView tvMess;
    Button home;
    Button choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_final);

        home = findViewById(R.id.bHome);
        choose = findViewById(R.id.bChoose);

        fillData();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MessageFinalActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MessageFinalActivity.this,
                        ChooseAlarmActivity.class);
                startActivity(i);
            }
        });
    }

    private void fillData() {
        Bundle extras = getIntent().getExtras();
        String cod = extras.getString("codAlarm");
        String mess = extras.getString("finalMessage");

        long num = AlarmTable.getInstance().getAlarm(cod).getNum();
        String title = AlarmTable.getInstance().getAlarm(cod).getTitle();
        tvAlarmTitle = findViewById(R.id.tvAlarmaTitulo);
        tvAlarmTitle.setText("Alarma " + String.valueOf(num) + ": " + title);

        tvMess = findViewById(R.id.tvMessage);
        tvMess.setText(mess);
    }
}
