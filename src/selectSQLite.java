
import java.sql.*;
import java.util.Scanner;


/**
 * Created by dremon on 09/11/15.
 */
public class selectSQLite {

//Este es el gestor en si de la base de datos
    public static void main( String args[] )
    {
        Scanner scr = new Scanner(System.in);
        int opcion = 0;
        while(opcion != 6){
            //Try catch para evitar que el programa termine si hay un error
            try{

                System.out.println("--------------------------------------------");
                System.out.println("Elige el numero del opcion que quieres : ");
                System.out.println("1) mostrar las peliculas . ");
                System.out.println("2) mostrar los actores . ");
                System.out.println("3) mostrar los actores de una pelicula . ");
                System.out.println("4) mostrar las peliculas de un actor . ");
                System.out.println("5) Borrar datos de la base de datos. ");
                System.out.println("6) Exit");
                System.out.println("---------------------------------------");
                opcion = scr.nextInt();
                switch (opcion) {
                    case 1:
                        sacar_peliculas();
                        break;
                    case 2:
                        sacar_actores();
                        break;
                    case 3:
                        relacion_pelicula();
                        break;
                    case 4:
                        relacion_actor();
                        break;
                    default:
                        break;
                }
                System.out.println("Operacion realizada con exito");
            }catch(Exception e){
                System.out.println("Uoop! Error!");
            }
        }

    }

    //Este metodo saca todas las peliculas
    private static void sacar_peliculas() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://172.31.73.191:5432/themovie","postgres","root");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM FILMS;" );

            while ( rs.next() ) {
                //Recogemos los datos de la base de datos con el tag
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                Date fecha_estreno  = rs.getDate("FECHA_ESTRENO");
                //Imprimimos
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "FECHA_ESTRENO = " + fecha_estreno);
                System.out.println();
            }
            System.out.println();
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    //Consulta que muestra todos los actores
    private static void sacar_actores() {

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://172.31.73.191:5432/themovie","postgres","root");
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            //consulta que muestra todos los actores
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ACTORES;" );
            while ( rs.next() ) {
                //Recogemos los datos de la base de datos con el tag
                int ida = rs.getInt("ID_ACTOR");
                String nombre_act =  rs.getString("Nombre_Actor");

                //Imprimimos
                System.out.println( "ID_ACTOR  = " + ida );
                System.out.println( "Nombre_Actor = " + nombre_act);
                System.out.println("");
            }
            System.out.println("");
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    //Este metodo hace una consulta que pide todos los actores de una pelicula
    private static void relacion_pelicula() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://172.31.73.191:5432/themovie","postgres","root");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT ID, NAME FROM FILMS;" );
            //Imprimimos
            while ( rs.next() ) {
                int id = rs.getInt("ID");
                String nom = rs.getString("NAME");

                System.out.println( "La pelicla : " + nom + " tiene la ID : " + id );
                System.out.println();
            }
            System.out.println("");
            System.out.println("-------------------------------");
            System.out.println("Escribe la id DE PELICULA : ");
            System.out.println("-------------------------------");
            System.out.println("");
            Scanner scr = new Scanner(System.in);
            int ID = scr.nextInt();

            //Variable de la consulta en BBDD
            rs = stmt.executeQuery( "SELECT  f.ID , f.NAME, a.ID_ACTOR, a.Nombre_Actor,per.NOMBRE_PERSONAJE, per.iD_MOVIE \n" +
                    "  FROM ACTORES  a \n" +
                    "  INNER JOIN PERS per on per.ID_ACTOR = a.ID_ACTOR \n" +
                    "  INNER JOIN FILMS f on per.ID_MOVIE = f.ID \n" +
                    "  where f.ID= "+ ID + ";" );


            System.out.println("ID_PELICULA   |    NOM_PELICULA    |    ID_ACTOR   |   NOM_ACTOR   |   PERSONAJE");
            System.out.println();

            //Imprimimos la relacion por pantalla
            while ( rs.next() ) {
                //Aqui se sacan los datos de la consulta realizada
                int id_peli = rs.getInt("ID");
                String nom_peli = rs.getString("NAME");
                int id_actor = rs.getInt("ID_ACTOR");
                String nom_actor = rs.getString("Nombre_Actor");
                String nom_personaje = rs.getString("NOMBRE_PERSONAJE");


                System.out.println( id_peli + "   |   " + nom_peli + "   |   " + id_actor + "   |   " + nom_actor + "  |  " + nom_personaje);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    //Este metodo hace una consulta que relaciona el actor con todas sus peliculas
    private static void relacion_actor() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://172.31.73.191:5432/themovie","postgres","root");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            //Consulta que muestra el actor y su id
            ResultSet rs = stmt.executeQuery( "SELECT ID_ACTOR, Nombre_Actor FROM ACTORES;" );
            while ( rs.next() ) {
                //Aqui se sacan los datos de la consult5a realizada
                int id = rs.getInt("ID_ACTOR");
                String nom = rs.getString("Nombre_Actor");

                System.out.println( "El actor/a : " + nom + " tiene la ID : " + id );
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------");
            System.out.println("Escribe la id del actor que quieres mostrar sus peliculas :");
            System.out.println("-----------------------------------------------------------");
            System.out.println("");
            Scanner scr = new Scanner(System.in);
            int idacr = scr.nextInt();

            rs = stmt.executeQuery( "select a.ID_ACTOR, a.Nombre_Actor, pel.NAME \n" +
                    "  from FILMS pel\n" +
                    "  INNER JOIN PERS per on pel.ID = per.iD_MOVIE \n" +
                    "  INNER JOIN ACTORES a on per.ID_Actor = a.ID_ACTOR\n" +
                    "  where a.ID_ACTOR = "+idacr+";" );

            System.out.println("ID_ACTOR   |   Nombre_Actor  |    NOM_PELICULA |");
            System.out.println();
            while (rs.next()){
                //Aqui se sacan los datos de la consult5a realizada
                int id_actor = rs.getInt("ID_ACTOR");
                String nom_actor = rs.getString("Nombre_Actor");
                String nom_pelicula = rs.getString("NAME");

                System.out.println( id_actor + "   |   " + nom_actor + "   |   " + nom_pelicula  +  "   |   ");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}



