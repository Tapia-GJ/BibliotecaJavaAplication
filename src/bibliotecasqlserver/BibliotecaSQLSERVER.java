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
            System.out.println("Error en conexión: " + e.getMessage());
        }
     
    }

    public void cerrarConexion() {
        try {
            con.close();
            System.out.println("Conexión cerrada");
        } catch (Exception e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }

    public void InsertarUsuario(String nom, String apell, String dni, String dom, String ciu, String est, String fn) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = dateFormat.parse(fn);
            Statement s2 = con.createStatement();
            s2.executeUpdate("insert into Usuarios values ('" + nom + "', '" + apell + "', '" + dni + "', '" + dom + "', '" + ciu + "', '" + est + "', '" + new java.sql.Date(fecha.getTime()) + "');");
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
            } else {
                System.out.println("Elemento Insertado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error al Insertar: " + e.getMessage());
        }
    }

    public void ActualizarUsuario(int cu, String nom, String apell, String dni, String dom, String ciu, String est, String fn) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = dateFormat.parse(fn);
            Statement s2 = con.createStatement();
            s2.executeUpdate("update Usuarios set NombreUsuario='" + nom + "', Apellidos='" + apell + "',DNI='" + dni + "',"
                    + " Domicilio='" + dom + "', Poblacion='" + ciu + "', Provincia='" + est + "', FechaNacimiento='" + new java.sql.Date(fecha.getTime()) + "' where CodigoUsuario =" + cu + "");
            System.out.println("Elemento modificado correctamente");
        } catch (Exception e) {
            System.out.println("Error al modificar la matrícula" + e);
        }
    }

    public void InsertarLibro(int cod, String noml, String edit, String aut, String gen, String paut, int numpag, String aedit, double prec) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = dateFormat.parse(aedit);
            Statement s2 = con.createStatement();
            s2.executeUpdate("insert into Libros values ('" + cod + "','" + noml + "','" + edit + "', '" + aut + "','"
                    + gen + "', '" + paut + "','" + numpag + "','" + new java.sql.Date(fecha.getTime()) + "','" + prec + "')");
            System.out.println("Elemento Insertado correctamente");
        } catch (Exception e) {
            System.out.println("Error al Insertar el elemento" + e.getMessage());
        }
    }

    public void ActualizarLibro(int cod, String noml, String edit, String aut, String gen, String paut, int numpag, String aedit, double prec) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = dateFormat.parse(aedit);
            Statement s2 = con.createStatement();
            s2.executeUpdate("update Libros set NombreLibro='" + noml + "', Editorial='" + edit + "',Autor='" + aut + "', Genero='" + gen + "', "
                    + "PaisAutor='" + paut + "', NumeroDePaginas=" + numpag + ", AnioDeEdicion='" + new java.sql.Date(fecha.getTime()) + "', "
                    + "Precio = " + prec + " where CodigoLibro = " + cod);
            System.out.println("Elemento modificado correctamente");
        } catch (Exception e) {
            System.out.println("Error al modificar la matrícula" + e);
        }
    }

    public void InsertarPrestamo(int codlib, int coduse, String FechaSal, String Fechamax, String Fechadev) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = dateFormat.parse(FechaSal);
            Date fecha2 = dateFormat.parse(Fechamax);
            Date fecha3 = dateFormat.parse(Fechadev);
            Statement s2 = con.createStatement();
            s2.executeUpdate("insert into Prestamos values (" + codlib + "," + coduse + ",'" + new java.sql.Date(fecha.getTime()) + "', '" + new java.sql.Date(fecha2.getTime()) + "','"
                    + new java.sql.Date(fecha3.getTime()) + "')");
            System.out.println("Elemento Insertado correctamente");
        } catch (Exception e) {
            System.out.println("Error al Insertar el elemento" + e.getMessage());
        }
    }

    public void MostrarLibrosPrestados() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //Date fecha = dateFormat.parse(FechaSal);
            st = con.createStatement();
            rs = st.executeQuery("select t1.CodigoLibro, t1.NombreLibro, t1.NumeroDePaginas, t1.Autor, t1.Precio\n"
                    + "from Libros as t1\n"
                    + "inner join Prestamos as t2 on t1.CodigoLibro = t2.FKCodigoLibro");
            while (rs.next()) {
                String strCod = rs.getString("CodigoLibro");
                String strNom = rs.getString("NombreLibro");
                int strNumpag = rs.getInt("NumeroDePaginas");
                String strAut = rs.getString("Autor");
                double strPrec = rs.getDouble("Precio");
                System.out.println(strCod + ", " + strNom + ", " + strNumpag + ", " + strAut + ", " + strPrec);
            }
        } catch (Exception e) {
            System.out.println("Error al Consultar la tabla: " + e.getMessage());
        }
    }

    public void MostrarLibrosPrestadosPorDia(String FechaSal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = dateFormat.parse(FechaSal);
            st = con.createStatement();
            rs = st.executeQuery("select t1.CodigoLibro, t1.NombreLibro, t1.NumeroDePaginas, t1.Autor, t1.Precio\n"
                    + "from Libros as t1\n"
                    + "inner join Prestamos as t2 on t1.CodigoLibro = t2.FKCodigoLibro where t2.FechaSalida='" + new java.sql.Date(fecha.getTime()) + "'");
            while (rs.next()) {
                String strCod = rs.getString("CodigoLibro");
                String strNom = rs.getString("NombreLibro");
                int strNumpag = rs.getInt("NumeroDePaginas");
                String strAut = rs.getString("Autor");
                double strPrec = rs.getDouble("Precio");
                System.out.println(strCod + ", " + strNom + ", " + strNumpag + ", " + strAut + ", " + strPrec);
            }
        } catch (Exception e) {
            System.out.println("Error al Consultar la tabla: " + e.getMessage());
        }
    }

    public void LibrosPrestadosCantidadMes() {
        try {
            CallableStatement callableStatement = con.prepareCall("{call spLibrosPrestadosCantidadMes()}");
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("NombreLibro");
                System.out.println(nom);
            }
        } catch (Exception e) {
            System.out.println("Error al Insertar el elemento" + e.getMessage());
        }
    }

    public void librosprestadosmes(int fec) {
        try {
            CallableStatement callableStatement = con.prepareCall("{call splibrosprestadosmes(?)}");
            callableStatement.setInt(1, fec);
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("NombreUsuario");
                String apell = rs.getString("Apellidos");
                int cl = rs.getInt("CodigoLibro");
                String nomlib = rs.getString("NombreLibro");
                Date fdev = rs.getDate("FechaDevolucion");
                Date fmdev = rs.getDate("FechaMaximaDevolver");
                Date fsa = rs.getDate("FechaSalida");
                System.out.println(nom + "----" + apell + "----" + cl + "----" + nomlib + "----" + fdev + "----" + fmdev + "----" + fsa);
            }
        } catch (Exception e) {
            System.out.println("Error al Insertar el elemento" + e.getMessage());
        }
    }

    public void NumerodeLibrosUsuario() {
        try {
            CallableStatement callableStatement = con.prepareCall("{call SpNumerodeLibrosUsuario()}");
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("Usuario");
                String lib = rs.getString("Libro");
                int np = rs.getInt("NumeroPedidos");
                System.out.println(nom + "----" + lib + "----" + np);

            }
        } catch (Exception e) {
            System.out.println("Error al Insertar el elemento" + e.getMessage());
        }
    }

    public void CantidadDePrestamosXLibro() {
        try {
            CallableStatement callableStatement = con.prepareCall("{call spCantidadDePrestamosXLibro()}");
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                int cl = rs.getInt("CodigoLibro");
                String nl = rs.getString("NombreLibro");
                int vp = rs.getInt("VecesPrestado");
                System.out.println(cl + "----" + nl + "----" + vp);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void PedidosAutoresNoEspanoles() {
        try {
            CallableStatement callableStatement = con.prepareCall("{call SpPedidosAutoresNoEspanoles()}");
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                String lib = rs.getString("Libro");
                String aut = rs.getString("Autor");
                int prestado = rs.getInt("NumeroPedidos");
                System.out.println(lib + "----" + aut + "----" + prestado);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void Resumen() {
        try {
            CallableStatement callableStatement = con.prepareCall("{call spResumen()}");
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                String nm = rs.getString("Nombre");
                String prestado = rs.getString("Titulo ; Autor ; Editoral");
                System.out.println(nm + "----- " + prestado);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void MostrarLibrosporVencer() {
        try {
            CallableStatement callableStatement = con.prepareCall("{call MostrarLibrosporVencer()}");
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                String cl = rs.getString("FKCodigoLibro");
                String nl = rs.getString("NombreLibro");
                Date f = rs.getDate("FechaSalida");
                Date fdev = rs.getDate("FechaDevolucion");
                System.out.println(cl + "----- " + nl+"----"+f+"----"+fdev);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void MostrarLibrosDevueltospordia() {
        try {
            CallableStatement callableStatement = con.prepareCall("{call MostrarLibrosDevueltospordia()}");
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                String cl = rs.getString("FKCodigoLibro");
                String nl = rs.getString("NombreLibro");
                System.out.println(cl + "----- " + nl);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
