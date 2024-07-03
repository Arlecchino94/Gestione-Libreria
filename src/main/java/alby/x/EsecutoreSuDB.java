package alby.x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EsecutoreSuDB {
    public static void inserisciLibro(Connection conn, String nomeLibro, String q, String inout) throws SQLException {
        String sql = "INSERT INTO Libreria (`nome libro`, quantità, `in o out`) VALUES (?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomeLibro);
            pstmt.setString(2, q);
            pstmt.setString(3, inout);
            pstmt.executeUpdate();
        }
    }

    public static int verifica(Connection conn,String nomeLibro){
        int ver = 0;
        String verifica = "SELECT COUNT(*) FROM Libreria WHERE `nome libro` = ?";
        try (PreparedStatement pstmtVerifica = conn.prepareStatement(verifica)) {
            pstmtVerifica.setString(1, nomeLibro);
            ResultSet rs = pstmtVerifica.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Un libro con lo stesso nome esiste già nel database.");
                ver++;
                return ver;
            }else {
                ver = 0;
                return ver;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminalibro(Connection conn, Scanner scan) throws SQLException {
        Eliminazione.canc(conn, scan);
        return;
    }

    public static void modificaLibro(Connection conn, Scanner scan) throws SQLException {
        System.out.println("Inserisci il nome del libro da modificare:");
        String nomeLibro = scan.nextLine();
        int verifica = verifica(conn, nomeLibro);
        if (verifica > 0) {
            System.out.println("Quale campo vuoi modificare? (Nome libro/Quantità/IN o OUT)\n " +
                    "digitare esattamente le parole scritte tra parentesi \n" +
                    "ESEMPIO: nel campo scrivere 'IN o OUT'");
            String campoDaModificare = scan.nextLine();
            if (!campoDaModificare.equals("IN o OUT") && !campoDaModificare.equals("Quantità") && !campoDaModificare.equals("Nome libro")){
                System.out.println("Non esiste la colonna\n" +
                        "Digita una delle colonne tra: \n" +
                        "Nome libro\n" +
                        "Quantità\n" +
                        "IN o OUT");
                return;
            }
            System.out.println("Inserisci il nuovo valore:");
            String nuovoValore = scan.nextLine();

            String sql = "UPDATE Libreria SET " + campoDaModificare + " = ? WHERE `nome libro` = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nuovoValore);
                pstmt.setString(2, nomeLibro);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Libro modificato con successo!");
                } else {
                    System.out.println("Nessuna modifica effettuata.");
                }
            }
        } else {
            System.out.println("Libro non trovato nel database.");
        }
    }

}
