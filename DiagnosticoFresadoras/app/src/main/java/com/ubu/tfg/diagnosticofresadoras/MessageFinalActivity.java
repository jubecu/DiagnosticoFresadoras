package com.ubu.tfg.diagnosticofresadoras;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ubu.tfg.diagnosticofresadoras.modeloAlarmas.AlarmTable;

/**
 * Activity de la pantalla que muestra el mensaje final.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class MessageFinalActivity extends AppCompatActivity {

    /**
     * Inicializa los dos botones y ejecuta el resto de métodos de la clase.
     *
     * @param savedInstanceState Paquete que contiene el estado de la instancia del Activity
     *                           previamente guardado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_final);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Button home = findViewById(R.id.bHome);
        Button choose = findViewById(R.id.bChoose);
        NavigationDrawer navDr=new NavigationDrawer(this);
        final DrawerLayout menu = findViewById(R.id.dlMenu);

        ExpandableListView listMenu = findViewById(R.id.lvMenu);
        ExpandableListAdapter adapterMenu =
                new ExpandableListAdapter(this);
        listMenu.setAdapter(adapterMenu);
        navDr.onItemClick(listMenu);
        navDr.initialize(menu, myToolbar);

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

    /**
     * Establece el mensaje final para mostrarlo en la pantalla.
     */
    private void fillData() {
        String mess = null, cod = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cod = extras.getString("codAlarm");
            mess = extras.getString("finalMessage");
        }

        long num = AlarmTable.getInstance().getAlarm(cod).getNum();
        String title = AlarmTable.getInstance().getAlarm(cod).getTitle();
        TextView tvAlarmTitle = findViewById(R.id.tvAlarmaTitulo);
        tvAlarmTitle.setText("Alarma " + String.valueOf(num) + ": " + title);

        TextView tvMess = findViewById(R.id.tvMessage);
        tvMess.setText(mess);
    }
}
