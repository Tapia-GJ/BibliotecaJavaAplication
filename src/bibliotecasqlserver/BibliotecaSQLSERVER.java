
package bibliotecasqlserver;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class BibliotecaSQLSERVER {
    Connection con;
    Statement st;
    ResultSet rs;
    
    public void abrirConexion() {
        try {
            String userName = "tapiagj";
            String password = "123456";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=biblioteca";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexión a la BD");
        } catch (Exception e) {
            System.out.println("Error en conexión: "+ e.getMessage());
        }
    }
    public void cerrarConexion() {
        try {
            con.close();
            System.out.println("Conexión cerrada");
        } catch (Exception e) {
            System.out.println("Error al cerrar conexión: "+e.getMessage());
        }
    }
    public void InsertarUsuario(String nom, String apell, String dni, String dom, String ciu, String est, String fn) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date fecha = dateFormat.parse(fn);
            Statement s2 = con.createStatement();
            s2.executeUpdate ("insert into Usuarios values ('"+nom+"', '"+apell+"', '"+dni+"', '"+dom+"', '"+ciu+"', '"+est+"', '"+new java.sql.Date(fecha.getTime())+"');");
            System.out.println("Elemento Insertado correctamente");
        } catch (Exception e) {
            System.out.println("Error al Insertar el elemento" + e.getMessage());
        }
    }
    public void InsertarUsuario2(String nom, String apell, String dni, String dom, String ciu, String est, String fn) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = dateFormat.parse(fn);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Usuarios VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, nom);
            ps.setString(2, apell);
            ps.setString(3, dni);
            ps.setString(4, dom);
            ps.setString(5, ciu);
            ps.setString(6, est);
            ps.setDate(7, new java.sql.Date(fecha.getTime()));
            if (ps.executeUpdate() != 1) {
                throw new Exception("Error en la Inserción");
            }else{
                System.out.println("Elemento Insertado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error al Insertar: "+e.getMessage());
        }
    }
    
    public void ActualizarUsuario(int cu, String nom, String apell, String dni, String dom, String ciu, String est, String fn) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = dateFormat.parse(fn);
            Statement s2 = con.createStatement();
            s2.executeUpdate("update Usuarios set NombreUsuario='"+nom+"', Apellidos='"+apell+"',DNI='"+dni+"',"
                    + " Domicilio='"+dom+"', Poblacion='"+ciu+"', Provincia='"+est+"', FechaNacimiento='"+new java.sql.Date(fecha.getTime())+"' where CodigoUsuario ="+cu+"");
            System.out.println("Elemento modificado correctamente");
        } catch (Exception e) {
            System.out.println("Error al modificar la matrícula" + e);
        }
    }
    
    public void InsertarLibro(int cod,String noml, String edit, String aut, String gen, String paut, int numpag, String aedit, double prec) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date fecha = dateFormat.parse(aedit);
            Statement s2 = con.createStatement();
            s2.executeUpdate ("insert into Libros values ('"+cod+"','"+noml+"','"+edit+"', '"+aut+"','"+
                    gen+"', '"+paut+"','"+numpag+"','"+new java.sql.Date(fecha.getTime())+"','"+prec+"')");
            System.out.println("Elemento Insertado correctamente");
        } catch (Exception e) {
            System.out.println("Error al Insertar el elemento" + e.getMessage());
        }
    }
    
//    public void ActualizarLibro(int cod,String noml, String edit, String aut, String gen, String paut, int numpag, String aedit, double prec) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date fecha = dateFormat.parse(fn);
//            Statement s2 = con.createStatement();
//            s2.executeUpdate("update Usuarios set NombreUsuario='"+nom+"', Apellidos='"+apell+"',DNI='"+dni+"',"
//                    + " Domicilio='"+dom+"', Poblacion='"+ciu+"', Provincia='"+est+"', FechaNacimiento='"+new java.sql.Date(fecha.getTime())+"' where CodigoUsuario ="+cu+"");
//            System.out.println("Elemento modificado correctamente");
//        } catch (Exception e) {
//            System.out.println("Error al modificar la matrícula" + e);
//        }
//    }
}
