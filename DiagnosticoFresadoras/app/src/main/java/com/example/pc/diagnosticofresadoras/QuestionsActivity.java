package com.example.pc.diagnosticofresadoras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionsActivity extends AppCompatActivity {

    TextView tvAlarmTitle;
    Button ansA;
    Button ansB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ansA = (Button) findViewById(R.id.bAnsA);
        ansB = (Button) findViewById(R.id.bAnsB);

        agregarTitulo();

        ansA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(QuestionsActivity.this, Questions2Activity.class);
                i.putExtra("dato1",tvAlarmTitle.getText());
                startActivity(i);
            }
        });

        ansB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(QuestionsActivity.this, MessageFinalActivity.class);
                i.putExtra("dato1",tvAlarmTitle.getText());
                i.putExtra("dato2","Comprobar cableado circuito auxiliar del térmico y la entrada del autómata.");
                startActivity(i);
            }
        });
    }

    private void agregarTitulo(){
        Bundle extras=getIntent().getExtras();
        String d1=extras.getString("dato1");

        tvAlarmTitle=(TextView) findViewById(R.id.tvAlarmaTitulo);
        tvAlarmTitle.setText(d1);
    }
}
