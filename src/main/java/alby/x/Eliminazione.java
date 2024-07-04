package alby.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Eliminazione {
    public static void canc(Connection conn, Scanner scan){
        String nomeLibro = scan.nextLine();
        String sql = "DELETE FROM Libreria WHERE `Nome libro` = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomeLibro);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Libro e riga corrispondente cancellati con successo.");
            } else {
                System.out.println("Libro non trovato, nessuna riga cancellata.");
                return;
            }
            EsecutoreSuDB.eliminalibro(conn,scan);
        } catch (SQLException e) {
            System.out.println("Errore durante la cancellazione del libro: " + e.getMessage());
        }

    }

}
