package ClienteServidorAlum;

import java.sql.*;

public class SistemaAdeudos {
    private Connection conn;

    public SistemaAdeudos(String url, String username, String password) throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }

    public void addStudent(String curp, String matricula, String nombre) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO estudiantes (curp, matricula, nombre) VALUES (?, ?, ?)");
        stmt.setString(1, curp);
        stmt.setString(2, matricula);
        stmt.setString(3, nombre);
        stmt.executeUpdate();
    }

    public void addAdeudo(int estudianteId, String semestre, String tipo, String estado) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO adeudos (estudiante_id, semestre, tipo, estado) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, estudianteId);
        stmt.setString(2, semestre);
        stmt.setString(3, tipo);
        stmt.setString(4, estado);
        stmt.executeUpdate();
    }

    public ResultSet getAdeudosByStudentId(int estudianteId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM adeudos WHERE estudiante_id = ?");
        stmt.setInt(1, estudianteId);
        return stmt.executeQuery();
    }

    public ResultSet getAllAdeudos() throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM adeudos");
    }

    public static void main(String[] args) {
        try {
            SistemaAdeudos sistema = new SistemaAdeudos("jdbc:mysql://localhost:3306/universidad", "usr", "Unsij2022");
            sistema.addStudent("CURP123456789", "123456", "Juan Perez");
            sistema.addAdeudo(1, "2022-1", "Pago de colegiatura", "Pendiente");
            ResultSet adeudos = sistema.getAdeudosByStudentId(1);
            while (adeudos.next()) {
                System.out.println("Adeudo: " + adeudos.getString("tipo") + " - Estado: " + adeudos.getString("estado"));
            }
            ResultSet allAdeudos = sistema.getAllAdeudos();
            while (allAdeudos.next()) {
                System.out.println("Adeudo: " + allAdeudos.getString("tipo") + " - Estado: " + allAdeudos.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}