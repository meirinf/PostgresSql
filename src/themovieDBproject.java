/**
 * Created by 53638138e on 21/12/16.
 */
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.xml.transform.Transformer;
import java.io.*;
import java.net.*;
import java.sql.Date;

  /*Esta es la segunda clase que ejecutaremos  que es la que se encarga de insertar los datos en las tablas
        los datos que metera los descarga de una api de internet utilizando la clase insertSql para meterlos en la base de datos.
        */


public class themovieDBproject {

    //Es un metodo que se conecta con internet
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    //Este metodo realiza la conexion a internet cada url es utilizada para conectar y bajar los datos
    public static void main(String[] args){
        String s = "";
        String j = "";
        String api_key = "5e9707dca1d918600724188d7bcdb593";//Key de la Api

        for (int i = 0; i < 10 ; i++) {
            int peli = 700 +i;
            String film = String.valueOf(peli);
            String peticio = "https://api.themoviedb.org/3/movie/"+film+"?api_key="+api_key;//dirección de peticion de peliculas
            String personaje_peticion ="https://api.themoviedb.org/3/movie/"+film+"/credits?api_key="+api_key;//actores y personajes
            try {
                s = getHTML(peticio);
                SJS(s);// Inserta Peliculas
                j = getHTML(personaje_peticion);
                SJPER(j);//Inserta Personajes y actores
            } catch (Exception e) {
                System.out.println("La peli "+film+" no existeix");
            }
        }


    }


    //peliculas

    public static void SJS (String cadena){

        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        // Films
        int ID = Integer.parseInt(String.valueOf(arra02.get("id")));
        String NAME =  String.valueOf(arra02.get("original_title"));
        Date FECHA_ESTRENO= Date.valueOf(arra02.get("release_date").toString());

        //Imprimo lo que estoy sacando
        System.out.println("ID :"+ID);
        System.out.println("Titul original :"+NAME);
        System.out.println("Data d'estrena :" + FECHA_ESTRENO);
        System.out.println();

        insertSQLite.insertFilms(ID,NAME,FECHA_ESTRENO);

    }

    //Personajes
    private static void SJPER(String cadena) {

        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        //De aqui saco el id pelicula
        int id_film = Integer.parseInt(String.valueOf(arra02.get("id")));
        JSONArray arra03 = (JSONArray)arra02.get("cast");


        for (int i = 0; i < arra03.size(); i++) {

            JSONObject jb= (JSONObject)arra03.get(i);
            //Aqui busca en la api el tag "String.valueOf(jb.get("id"))" y lo meto en la variable
            int id_actor = Integer.parseInt(String.valueOf(jb.get("id")));
            String nombre_actor = String.valueOf(jb.get("name"));
            int cast_id = Integer.parseInt(String.valueOf(jb.get("cast_id")));
            String nombre_personaje =  String.valueOf(jb.get("character"));

            //Imprimo lo que estoy sacando
            System.out.println("ID :"+id_actor);
            System.out.println("Nom :"+nombre_actor);
            System.out.println();

            //LLamo a la clase insert y meto los datos recogidos de la web
            insertSQLite.insertActores(id_actor,nombre_actor);
            insertSQLite.insertPersonajes(id_actor,id_film,cast_id,nombre_personaje);

        }



    }

}