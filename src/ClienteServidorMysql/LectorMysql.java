package ClienteServidorMysql;

import java.sql.*;

public class LectorMysql {
    public static String ejecutarOperacion(String tipoOperacion, String parametros) throws SQLException {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prueba?useSSL=false",
                    "usr", "Unsij2022" );
            Statement consulta = conexion.createStatement();

            if (tipoOperacion.equals("leer")) {
                ResultSet resultado = consulta.executeQuery("SELECT id, nombre FROM alumnos");
                StringBuilder contenido = new StringBuilder();
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    contenido.append(id).append("\t").append(nombre).append("\n");
                }
                conexion.close();
                return contenido.toString();
            } else if (tipoOperacion.equals("crear")) {
                String[] valores = parametros.split(",");
                String nombre = valores[0];
                String sql = "INSERT INTO alumnos(nombre) VALUES(0+'" + nombre + "')";
                consulta.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet generatedKeys = consulta.getGeneratedKeys();
                int id = -1;
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
                conexion.close();
                return "Registro creado exitosamente. ID: " + id;
            } else if (tipoOperacion.equals("actualizar")) {
                String[] valores = parametros.split(",");
                int id = Integer.parseInt(valores[0]);
                String nuevoNombre = valores[1];
                String sql = "UPDATE alumnos SET nombre='" + nuevoNombre + "' WHERE id=" + id;
                consulta.executeUpdate(sql);
                conexion.close();
                return "Registro actualizado exitosamente.";
            } else if (tipoOperacion.equals("eliminar")) {
                int id = Integer.parseInt(parametros);
                String sql = "DELETE FROM alumnos WHERE id=" + id;
                consulta.executeUpdate(sql);
                conexion.close();
                return "Registro eliminado exitosamente.";
            } else {
                return "Operación no válida.";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
}
