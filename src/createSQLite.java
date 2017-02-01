/**
 * Created by dremon on 09/11/15.
 */
import java.sql.*;

public class createSQLite {

    public static void main(String[] args) {
        {
            Connection c = null;
            Statement stmt = null;
            /*Esta es la primera clase que ejecutaremos,se encarga de hacer los creates en la base de datos
            * Una vez ejecutada esta clase ejecutaremos themovieDBproject */

            try {
                Class.forName("org.postgresql.Driver");
                //aqui me conecto con la maquina y pongo la ip y el nombre que le daremos a la base de datos the movie
                c = DriverManager.getConnection("jdbc:postgresql://172.31.73.191:5432/themovie","postgres","root");
                System.out.println("Opened database successfully");
                stmt = c.createStatement();

                //Creo la tabla peliculas
                String sql = "CREATE TABLE FILMS " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " FECHA_ESTRENO  TEXT     NOT NULL) ";

                //Creo la tabla actores
                String sqlActor = "CREATE TABLE ACTORES" +
                        "(ID_ACTOR INT PRIMARY KEY    NOT NULL," +
                        "Nombre_Actor TEXT )";

                //Creo la tabla personajes  y su relación
                String sqlPersonajes = "CREATE TABLE PERS " +
                        "(ID_ACTOR INT  NOT NULL, " +
                        "iD_MOVIE INT  NOT NULL, " +
                        "iD_CAST INT  NOT NULL, " +
                        "NOMBRE_PERSONAJE TEXT NOT NULL, " +
                        "PRIMARY KEY (ID_ACTOR,iD_MOVIE,iD_CAST))";

                stmt.executeUpdate(sql);
                stmt.executeUpdate(sqlActor);
                stmt.executeUpdate(sqlPersonajes);


                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Table created successfully");
        }

    }


}
