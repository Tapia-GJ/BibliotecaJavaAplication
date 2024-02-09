package bibliotecasqlserver;


public class main {
    public static void main(String[] args) {
        BibliotecaSQLSERVER ad = new BibliotecaSQLSERVER();
        ad.abrirConexion();
        //ad.InsertarUsuario("Josue", "Castillo", "EF5", "Domicilio-2", "Cancún", "QuintanaRoo", "2003-05-19");
        //ad.ActualizarUsuario(2, "Josue", "Isidro", "DF6", "Domicilio-2", "Cancún", "QuintanaRoo", "2009-05-19");
        ad.InsertarLibro(1644, "caballo de troya", "pearson", "YOO", "Masculino", "Roma", 500, "1994-11-29", 994.99);
        ad.cerrarConexion();
    }
}
