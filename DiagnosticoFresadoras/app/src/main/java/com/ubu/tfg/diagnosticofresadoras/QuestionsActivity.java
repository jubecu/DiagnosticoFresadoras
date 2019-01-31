package com.ubu.tfg.diagnosticofresadoras;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Alarm;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.AlarmTable;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Answer;
import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.Question;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;

/**
 * Activity de la pantalla donde se muestran las preguntas de la alarma.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class QuestionsActivity extends AppCompatActivity {
    /**
     * Conjunto de todas las alarmas
     */
    private AlarmTable alarms;
    /**
     * Alarma de la cual se muestran sus preguntas en la pantalla
     */
    private Alarm alarm;
    /**
     * Pregunta que se muestra en la pantalla
     */
    private Question question;
    /**
     * Código de la alarma
     */
    private String cod;

    /**
     * Rellena la pantalla con el contenido de la pregunta.
     *
     * @param savedInstanceState Paquete que contiene el estado de la instancia del Activity
     *                           previamente guardado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        String idQuestion = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cod = extras.getString("codAlarm");
            idQuestion = extras.getString("idQuestion");
        }

        alarms = AlarmTable.getInstance();
        alarm = alarms.getAlarm(cod);
        question = alarm.getQuestionById(idQuestion);
        NavigationDrawer navDr=new NavigationDrawer(this);
        final DrawerLayout menu = findViewById(R.id.dlMenu);

        ExpandableListView listMenu = findViewById(R.id.lvMenu);
        ExpandableListAdapter adapterMenu =
                new ExpandableListAdapter(this);
        listMenu.setAdapter(adapterMenu);
        navDr.onItemClick(listMenu);
        navDr.initialize(menu, myToolbar);

        fillData();

        fillImages();

        fillButtons();
    }

    /**
     * Establece las imágenes de la pregunta para mostrarlas en la pantalla.
     */
    private void fillImages() {

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int heightPixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;

        LinearLayout llImageQuestion = findViewById(R.id.llImageQuestion);
        LinearLayout.LayoutParams llImageParams = new LinearLayout.LayoutParams(
                widthPixels / 2, heightPixels / 3);
        if (question.getImages() != null) {
            for (String nameImage : question.getImages()) {
                ImageView image = new ImageView(this);
                image.setLayoutParams(llImageParams);
                final int resImage = alarms.getDiccImages().get(nameImage);
                image.setImageResource(resImage);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(QuestionsActivity.this,
                                ImageActivity.class);
                        i.putExtra("resImage", resImage);
                        startActivity(i);
                    }
                });
                llImageQuestion.addView(image);
            }
        }
    }

    /**
     * Establece las respuestas de la pregunta en forma de botón para mostrarlos en la pantalla.
     */
    private void fillButtons() {
        LinearLayout llKeypad = findViewById(R.id.llKeypad);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (Answer answer : question.getAnswers()) {
            Button ans = new Button(this);
            ans.setLayoutParams(llParams);
            ans.setText(answer.getText());
            ans.setTextSize(20);
            ans.setAllCaps(false);
            llKeypad.addView(ans);
            ans.setOnClickListener(new ButtonsOnClickListener(answer.getNext(), answer.getMessage(),
                    answer.getText()));
        }
    }

    /**
     * Establece el número y título de la alarma y el enunciado de la pregunta para mostrarlos en la
     * pantalla.
     */
    private void fillData() {
        long num = alarm.getNum();
        String title = alarm.getTitle();
        TextView tvAlarmTitle = findViewById(R.id.tvAlarmaTitulo);
        tvAlarmTitle.setText("Alarma " + String.valueOf(num) + ": " + title);

        TextView tvQues = findViewById(R.id.tvQues);
        tvQues.setText(question.getText());
    }

    /**
     * Clase que implementa el código que se ejecuta al pulsar en una respuesta.
     */
    class ButtonsOnClickListener implements View.OnClickListener {
        /**
         * Id de la siguiente pregunta a mostrar
         */
        String next;
        /**
         * Mensaje final y texto de la respuesta elegida
         */
        String finalMessage, text;

        /**
         * Constructor que asigna el id de la siguiente pregunta, el mensaje final y el texto de la
         * respuesta.
         *
         * @param next         Id de la siguiente pregunta a mostrar
         * @param finalMessage Mensaje final
         * @param text         Texto de la respuesta elegida
         */
        ButtonsOnClickListener(String next, String finalMessage, String text) {
            this.next = next;
            this.finalMessage = finalMessage;
            this.text = text;
        }

        /**
         * Pasa a una pantalla u otra en función de lo que valga el id de la pregunta
         *
         * @param v Elemento View
         */
        @Override
        public void onClick(View v) {
            if (next.isEmpty()) {
                Intent i = new Intent(QuestionsActivity.this,
                        MessageFinalActivity.class);
                i.putExtra("codAlarm", cod);
                i.putExtra("finalMessage", finalMessage);
                startActivity(i);
            } else {
                Intent i = new Intent(QuestionsActivity.this,
                        QuestionsActivity.class);
                i.putExtra("codAlarm", cod);
                i.putExtra("idQuestion", next);
                startActivity(i);
            }
            writeRegistry();
        }

        /**
         * Escribe en el fichero del registro de la actividad la información correspondiente.
         */
        private void writeRegistry() {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(
                        "registry.csv", Context.MODE_APPEND));
                DateFormat dateFormat = DateFormat.getDateTimeInstance();
                String ts = dateFormat.format(new Date());
                osw.write(cod.substring(0, 3) + "," + "\""
                        + question.getText() + "\"" + "," + "\"" + text +
                        "\"" + "," + "\"" + ts + "\"" + "\n");
                osw.close();
            } catch (IOException ex) {
                Log.v("InfoAlarmaActivity", "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
