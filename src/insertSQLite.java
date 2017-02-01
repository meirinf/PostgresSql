/**
 * Created by dremon on 09/11/15.
 */

    import java.sql.*;

public class insertSQLite {
    //Este metodo se encarga de insertar peliculas se comunica con themovieDBproject
    // la ultima clase que ejecutaremos es  selectSQLite

    public static void insertFilms(int ID ,String NAME ,Date FECHA_ESTRENO) {

        Connection c = null;
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://172.31.73.191:5432/themovie","postgres","root");

            //Este es el clasico insert asignamos en que valores queremos meter los datos
            String sql = "INSERT INTO FILMS (ID,NAME, FECHA_ESTRENO) " +
                    "VALUES (?,?,?);";

            //Metemos los datos recogidos en themovieDBproject pasando las variables
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1 , ID);
            preparedStatement.setString(2 , NAME);
            preparedStatement.setDate(3 , FECHA_ESTRENO);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            c.close();

        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    //Este metodo se encarga de insertar actores

    public static void insertActores(int ID_ACTOR ,String Actonnom) {
        Connection c = null;
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://172.31.73.191:5432/themovie","postgres","root");

            String sql = "INSERT INTO ACTORES(ID_ACTOR,Nombre_Actor) " +
                    "VALUES (?,?);";

            //Metemos los datos recogidos en themovieDBproject pasando las variables
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1 , ID_ACTOR);
            preparedStatement.setString(2 , Actonnom);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            c.close();

        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    //Este metodo se ecarga de meter personajes
    public static void insertPersonajes (int id_actor,int id_film,int cast_id, String nombre_personaje){
        Connection c = null;
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://172.31.73.191:5432/themovie","postgres","root");

            String sql = "INSERT INTO PERS VALUES (?,?,?,?);";

            //Metemos los datos recogidos en themovieDBproject pasando las variables
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1 , id_actor);
            preparedStatement.setInt(2 , id_film);
            preparedStatement.setInt(3 , cast_id);
            preparedStatement.setString(4 ,nombre_personaje);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            c.close();

        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }



}
