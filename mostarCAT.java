package categorias;
import java.sql.*; 
public class mostarCAT {
public static void main(String[] args) {
String cadConexion = "jdbc:mysql://localhost:3306/";
String bd = "compras";
String usuario = "root";
String pass = "kGd25870";
try {
Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
Connection con = DriverManager.getConnection(cadConexion + bd, usuario, pass);
Statement stmt = con.createStatement(); 
ResultSet rs = stmt.executeQuery("select * from categorias");
while (rs.next())
System.out.println(rs.getInt(1) + " " +rs.getString(2));
con.close();
    }
catch (Exception e) {
	System.out.println(e);
    }

/* FINAL */
}
}