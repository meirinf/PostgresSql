/**
 * Created by dremon on 09/11/15.
 */

    import com.sun.xml.internal.bind.v2.model.core.ID;

    import java.sql.*;

public class insertSQLite {

    public static void insertFilms(int ID ,String NAME ,String FECHA_ESTRENO) {
        Connection c = null;
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://172.31.73.191:5432/themovie","postgres","root");

            String sql = "INSERT INTO FILMS (ID,NAME, FECHA_ESTRENO) " +
                    "VALUES (?,?,?);";

            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1 , ID);
            preparedStatement.setString(2 , NAME);
            preparedStatement.setString(3 , FECHA_ESTRENO);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            c.close();

        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void insertActores(int ID_ACTOR ,String Actonnom) {
        Connection c = null;
        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:movie.db");

            String sql = "INSERT INTO ACTORES(ID_ACTOR,Nombre_Actor) " +
                    "VALUES (?,?);";

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

    public static void insertPersonajes (int id_actor,int id_film,int cast_id, String nombre_personaje){
        Connection c = null;
        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:movie.db");

            String sql = "INSERT INTO PERS VALUES (?,?,?,?);";

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
