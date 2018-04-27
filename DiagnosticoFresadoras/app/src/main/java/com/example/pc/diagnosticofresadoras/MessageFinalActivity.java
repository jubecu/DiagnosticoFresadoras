package com.example.pc.diagnosticofresadoras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageFinalActivity extends AppCompatActivity {

    TextView tvAlarmTitle;
    TextView tvMess;
    Button home;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_final);

        home = (Button) findViewById(R.id.bHome);
        back = (Button) findViewById(R.id.bVolver);

        agregarTitulo();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MessageFinalActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void agregarTitulo(){
        Bundle extras=getIntent().getExtras();
        String d1=extras.getString("dato1");
        String d2=extras.getString("dato2");

        tvAlarmTitle=(TextView) findViewById(R.id.tvAlarmaTitulo);
        tvAlarmTitle.setText(d1);

        tvMess=(TextView) findViewById(R.id.tvMessage);
        tvMess.setText(d2);
    }
}
