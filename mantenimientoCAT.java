package categorias;
import java.util.Scanner;
import java.sql.*;
public class mantenimientoCAT {

public static void main(String[] args) {
String cadConexion = "jdbc:mysql://localhost:3306/"; 
String bd = "compras";
String usuario = "root";
String pass = "kGd25870";

try {
Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
Connection con = DriverManager.getConnection(cadConexion + bd, usuario, pass);
Statement stmt;
PreparedStatement pstmt;
ResultSet rs;
Scanner entrada = new Scanner(System.in);
int res, id;
String nombre; do {
System.out.println("Escoje opción:");
System.out.println("1.- Ver todas las categorías");
System.out.println("2.- Ver una categoría concreta");
System.out.println("3.- Crear una nueva categoría");
System.out.println("4.- Modificar una categoría");
System.out.println("5.- Eliminar una categoría");
System.out.println("0.- Salir");
res = Integer.parseInt(entrada.nextLine());
switch (res) { 
case 1:
    stmt = con.createStatement();
    rs = stmt.executeQuery("select * from categorias");
    while(rs.next())
    System.out.println(rs.getInt("idcategoria") + " " +rs.getString("nombre"));
    break;
case 2:
	System.out.println("Introduzca el id de la categoría que quiere ver: ");
    id = Integer.parseInt(entrada.nextLine());
    pstmt = con.prepareStatement("select * from categorias where idcategoria=?");
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();
    if (rs.next()) {
    	System.out.println("ID:" + rs.getString("idcategoria") + " | Nombre:" + rs.getString("nombre"));
    } else {
        System.out.println("No encontrado");
    }
    break;
case 3:
	System.out.println("Introduzca el nombre de la categoría : ");
	nombre = entrada.nextLine();
	pstmt = con.prepareStatement("insert into categorias (nombre) values(?)");
	pstmt.setString(1, nombre);
	pstmt.execute();
	System.out.println("Categoría insertada"); 
	break;    
case 4:
    System.out.println("Introduzca el id de la categoría que quiere modificar: ");
    id = Integer.parseInt(entrada.nextLine());
    System.out.println("Introduzca el nuevo nombre de la categoría: ");
    nombre = entrada.nextLine();
    pstmt = con.prepareStatement("update categorias set nombre=? where idcategoria=?");
    pstmt.setString(1, nombre);
    pstmt.setInt(2, id);
    pstmt.execute(); System.out.println("Categoría modificada"); 
    break;
case 5:
    System.out.println("Introduzca el id de la categoría que quiere eliminar: ");
    id = Integer.parseInt(entrada.nextLine());
    pstmt = con.prepareStatement("delete from categorias where idcategoria=?");
    pstmt.setInt(1, id);
    pstmt.execute();
    System.out.println("Categoría eliminada"); 
    break;
    }
} while (res != 0);
con.close();
entrada.close();
}
catch (Exception e) {
    System.out.println(e);   
}   
/* FINAL */
} 
}