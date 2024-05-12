package bibliotecasqlserver;


public class main {
    public static void main(String[] args) {
        BibliotecaSQLSERVER ad = new BibliotecaSQLSERVER();
        ad.abrirConexion();
        //ad.InsertarUsuario("Josue", "Castillo", "EF5", "Domicilio-2", "Cancún", "QuintanaRoo", "2003-05-19");
        //ad.ActualizarUsuario(2, "Josue", "Isidro", "DF6", "Domicilio-2", "Cancún", "QuintanaRoo", "2009-05-19");
        //ad.InsertarLibro(1644, "caballo de troya", "pearson", "YOO", "Masculino", "Roma", 500, "1994-11-29", 994.99);
        //ad.InsertarPrestamo(1644, 2, "2023-01-05", "2023-01-08", "2023-01-08");
        //ad.InsertarPrestamo(1644, 3, "2023-01-06", "2023-01-08", "2023-01-08");
        //ad.MostrarLibrosPrestadosPorDia("2023-01-06");
        //ad.LibrosPrestadosCantidadMes();
        //ad.PedidosAutoresNoEspanoles();
        //ad.CantidadDePrestamosXLibro();
        //ad.Resumen();
        ad.MostrarLibrosporVencer();
        //ad.MostrarLibrosDevueltospordia();
        ad.cerrarConexion();
    }
}
