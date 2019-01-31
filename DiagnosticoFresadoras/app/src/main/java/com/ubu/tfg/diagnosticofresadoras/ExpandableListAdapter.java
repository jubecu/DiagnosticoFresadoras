package com.ubu.tfg.diagnosticofresadoras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase que implementa un adaptador para un ExpandableListView. Crea las listas que lo forman,
 * establece varios métodos para interactuar con los elementos de estas listas e instancia las
 * vistas (View) de los Groups y los Children.
 *
 * @author Juan Francisco Benito Cuesta
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    /**
     * Contexto, que en este caso es la Activity, donde se va a crear el ExpandableListAdapter para
     * enlazarlo a un ExpandableListView.
     */
    private Context context;
    /**
     * Lista con los elementos padre/grupos del menú, que son las alarmas más las opciones de inicio
     * y salir
     */
    private List<String> listGroup;
    /**
     * Map con los elementos hijo del menú, las preguntas de las alarmas, junto con el nombre de su
     * elemento padre o grupo
     */
    private HashMap<String, List<String>> listChildren;

    /**
     * Constructor que inicializa el contexto y las listas anteriores, y ejecuta el método que las
     * rellena.
     *
     * @param context Contexto que representa a la Activity donde se inicializa este adaptador
     */
    ExpandableListAdapter(Context context) {
        this.context = context;
        listGroup = new ArrayList<>();
        listChildren = new HashMap<>();
        createListData();
    }

    /**
     * Rellena las dos listas que forman el menú con las alarmas y sus respectivas preguntas.
     */
    private void createListData() {
        listGroup.add("Inicio");
        listGroup.add("Alarma 631");
        listGroup.add("Alarma 712");
        listGroup.add("Alarma 713");
        listGroup.add("Alarma 821");
        listGroup.add("Alarma 822");
        listGroup.add("Alarma 846");
        listGroup.add("Alarma 852");
        listGroup.add("Alarma 928");
        listGroup.add("Alarma 968");
        listGroup.add("Alarma 969");
        listGroup.add("Alarma 970");
        listGroup.add("Alarma 971");
        listGroup.add("Alarma 972");
        listGroup.add("Alarma 973");
        listGroup.add("Salir");

        List<String> alarm631 = new ArrayList<>();
        alarm631.add("Información");
        alarm631.add("Pregunta 1");
        alarm631.add("Pregunta 1.1");
        alarm631.add("Pregunta 2");
        alarm631.add("Pregunta 2.1");
        alarm631.add("Pregunta 3");
        alarm631.add("Pregunta 3.1");
        alarm631.add("Pregunta 4");
        alarm631.add("Pregunta 4.1");
        alarm631.add("Pregunta 5");
        alarm631.add("Pregunta 5.1");
        alarm631.add("Pregunta 6");
        alarm631.add("Pregunta 6.1");
        alarm631.add("Pregunta 6.2");

        List<String> alarm712 = new ArrayList<>();
        alarm712.add("Información");
        alarm712.add("Pregunta 0");
        alarm712.add("Pregunta 0.1");
        alarm712.add("Pregunta 1");
        alarm712.add("Pregunta 1.1");
        alarm712.add("Pregunta 2");
        alarm712.add("Pregunta 2.1");
        alarm712.add("Pregunta 4");
        alarm712.add("Pregunta 5");
        alarm712.add("Pregunta 5.1");
        alarm712.add("Pregunta 6");
        alarm712.add("Pregunta 6.1");
        alarm712.add("Pregunta 7");
        alarm712.add("Pregunta 7.1");
        alarm712.add("Pregunta 8");
        alarm712.add("Pregunta 8.1");
        alarm712.add("Pregunta 8.2");
        alarm712.add("Pregunta 8.3");
        alarm712.add("Pregunta 9");

        List<String> alarm713 = new ArrayList<>();
        alarm713.add("Información");
        alarm713.add("Pregunta 1");
        alarm713.add("Pregunta 1.1");

        List<String> alarm821 = new ArrayList<>();
        alarm821.add("Información");
        alarm821.add("Pregunta 1");
        alarm821.add("Pregunta 1.1");
        alarm821.add("Pregunta 2");
        alarm821.add("Pregunta 2.1");
        alarm821.add("Pregunta 3");
        alarm821.add("Pregunta 3.1");
        alarm821.add("Pregunta 4");
        alarm821.add("Pregunta 4.1");
        alarm821.add("Pregunta 5");
        alarm821.add("Pregunta 5.1");
        alarm821.add("Pregunta 6");
        alarm821.add("Pregunta 6.1");

        List<String> alarm822 = new ArrayList<>();
        alarm822.addAll(alarm821);
        alarm822.add("Pregunta 6.2");
        alarm822.add("Pregunta 6.3");

        List<String> alarm846 = new ArrayList<>();
        alarm846.addAll(alarm821);

        List<String> alarm852 = new ArrayList<>();
        alarm852.add("Información");
        alarm852.add("Pregunta 1");
        alarm852.add("Pregunta 1.1");
        alarm852.add("Pregunta 2");
        alarm852.add("Pregunta 2.1");
        alarm852.add("Pregunta 3");
        alarm852.add("Pregunta 3.1");
        alarm852.add("Pregunta 4");
        alarm852.add("Pregunta 4.1");
        alarm852.add("Pregunta 5a");
        alarm852.add("Pregunta 5a.1");
        alarm852.add("Pregunta 5b");
        alarm852.add("Pregunta 5b.1");
        alarm852.add("Pregunta 7");
        alarm852.add("Pregunta 7.1");
        alarm852.add("Pregunta 8");
        alarm852.add("Pregunta 8.1");

        List<String> alarm928 = new ArrayList<>();
        alarm928.add("Información");
        alarm928.add("Pregunta 1");
        alarm928.add("Pregunta 1.1");
        alarm928.add("Pregunta 2");
        alarm928.add("Pregunta 2.1");
        alarm928.add("Pregunta 3");
        alarm928.add("Pregunta 3.1");
        alarm928.add("Pregunta 4");
        alarm928.add("Pregunta 4.1");
        alarm928.add("Pregunta 4.2");
        alarm928.add("Pregunta 5");
        alarm928.add("Pregunta 5.1");
        alarm928.add("Pregunta 6");
        alarm928.add("Pregunta 6.1");
        alarm928.add("Pregunta 6.2");

        List<String> alarm968 = new ArrayList<>();
        alarm968.add("Información");
        alarm968.add("Pregunta 1");
        alarm968.add("Pregunta 1.1");
        alarm968.add("Pregunta 1.2");
        alarm968.add("Pregunta 2");

        List<String> alarm969 = new ArrayList<>();
        alarm969.addAll(alarm968);
        alarm969.add("Pregunta 2.1");

        List<String> alarm970 = new ArrayList<>();
        alarm970.add("Información");
        alarm970.add("Pregunta 1");
        alarm970.add("Pregunta 2");
        alarm970.add("Pregunta 3");
        alarm970.add("Pregunta 3.1");
        alarm970.add("Pregunta 4");
        alarm970.add("Pregunta 4.1");
        alarm970.add("Pregunta 4.2");

        List<String> alarm971 = new ArrayList<>();
        alarm971.add("Información");
        alarm971.add("Pregunta 1");
        alarm971.add("Pregunta 2");

        List<String> alarm972 = new ArrayList<>();
        alarm972.addAll(alarm970);

        List<String> alarm973 = new ArrayList<>();
        alarm973.addAll(alarm971);

        listChildren.put(listGroup.get(1), alarm631);
        listChildren.put(listGroup.get(2), alarm712);
        listChildren.put(listGroup.get(3), alarm713);
        listChildren.put(listGroup.get(4), alarm821);
        listChildren.put(listGroup.get(5), alarm822);
        listChildren.put(listGroup.get(6), alarm846);
        listChildren.put(listGroup.get(7), alarm852);
        listChildren.put(listGroup.get(8), alarm928);
        listChildren.put(listGroup.get(9), alarm968);
        listChildren.put(listGroup.get(10), alarm969);
        listChildren.put(listGroup.get(11), alarm970);
        listChildren.put(listGroup.get(12), alarm971);
        listChildren.put(listGroup.get(13), alarm972);
        listChildren.put(listGroup.get(14), alarm973);
    }

    /**
     * Devuelve el número de elementos padre/grupos que forman la lista del menú.
     *
     * @return Número de elementos padre
     */
    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    /**
     * Devuelve el número de elementos hijo de un elemento padre o grupo concreto.
     *
     * @param groupPosition Posición del elemento padre concreto en la lista
     * @return Número de elementos hijo de ese elemento padre
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0 || groupPosition == 15)
            return 0;
        else
            return listChildren.get(listGroup.get(groupPosition)).size();
    }

    /**
     * Devuelve un elemento padre/grupo concreto.
     *
     * @param groupPosition Posición en la lista del elemento padre a devolver
     * @return Un elemento padre
     */
    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    /**
     * Devuelve un elemento hijo concreto.
     *
     * @param groupPosition  Posición del elemento padre al que pertenece el elemento hijo
     * @param childPosititon Posición del elemento hijo dentro del padre
     * @return Un elemento hijo
     */
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return listChildren.get(listGroup.get(groupPosition))
                .get(childPosititon);
    }

    /**
     * Devuelve el Id de un grupo a partir de su posición.
     *
     * @param groupPosition Posición del grupo del que queremos conocer su Id
     * @return Id del grupo que en este caso coincide con su posición
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * Devuelve el Id de un elemento hijo dentro de su grupo.
     *
     * @param groupPosition Posición del grupo al que pertenece el hijo.
     * @param childPosition Posición del hijo dentro del grupo
     * @return Id del elemento hijo dentro del grupo que coincide con su posición
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Indica si las Id de los grupos e hijos son estables a partir de los cambios en los datos.
     *
     * @return Falso
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Establece y devuelve la vista de un elemento padre o grupo.
     *
     * @param groupPosition Posición del elemento padre al que se configura su vista
     * @param isExpanded    Si el grupo está expandido o no
     * @param convertView   Vista del elemento padre o grupo
     * @param parent        Vista grupal a la que se adjuntará la vista del elemento padre
     * @return Vista de un elemento padre o grupo
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String nameGroup = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, parent, false);
        }

        TextView tvListGroup = convertView.findViewById(R.id.tvListGroup);
        tvListGroup.setText(nameGroup);

        return convertView;
    }

    /**
     * Establece y devuelve la vista de un elemento hijo.
     *
     * @param groupPosition Posición del elemento padre al que pertenece el hijo al cual se le
     *                      configura su vista
     * @param childPosition Posición del elemento hijo al que se configura su vista
     * @param isLastChild   Si el hijo es el último dentro del grupo
     * @param convertView   Vista del elemento hijo
     * @param parent        Vista grupal a la que se adjuntará la vista del elemento hijo
     * @return Vista de un elemento hijo
     */
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        String nameChild = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child, null);
        }
        TextView tvListChild = convertView.findViewById(R.id.tvListChild);
        tvListChild.setText(nameChild);
        return convertView;
    }

    /**
     * Indica si un elemento hijo concreto se puede pulsar.
     *
     * @param groupPosition Posición del grupo al que pertenece el elemento hijo
     * @param childPosition Posición del elemento hijo dentro del grupo.
     * @return Verdadero porque todos los elementos hijos se pueden pulsar
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
