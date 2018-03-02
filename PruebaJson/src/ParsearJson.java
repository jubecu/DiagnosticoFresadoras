import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ParsearJson {

	public static void main(String[] args) {
		JSONParser parser=new JSONParser();
        try{
            Object objeto=parser.parse(new FileReader("Ejemplo.json"));
            JSONObject jsonObject=(JSONObject) objeto;

            String nombre= (String) jsonObject.get("firstName");
            System.out.println("Nombre: "+nombre);

            String apellido= (String) jsonObject.get("lastName");
            System.out.println("Apellido: "+apellido);
            
            long edad= (long) jsonObject.get("age");
            System.out.println("Edad: "+edad);
            
            JSONObject direccion= (JSONObject) jsonObject.get("address");
            String ciudad= (String) direccion.get("city");
            System.out.println("Ciudad: "+ciudad);
            
            JSONArray numTlf= (JSONArray) jsonObject.get("phoneNumbers");
            Iterator<String> iterador=numTlf.iterator();
            while(iterador.hasNext()) {
            	System.out.println(iterador.next());
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }

	}

}
