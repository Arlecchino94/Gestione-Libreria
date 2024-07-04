package alby.x;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://db4free.net:3306/libreriakhalix";
    private static final String USER = "khalix";
    private static final String PASSWORD = "Alby_1994";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean c = true;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            while (c) {
                System.out.println("Ciao, sono il gestionale della biblioteca \n" +
                        "Cosa vuoi fare? \n" +
                        "Seleziona il numero corrispondente alla funzione: \n" +
                        "1 - Inserisci nuovo libro \n" +
                        "2 - Modifica un libro \n" +
                        "3 - Elimina un libro");

                switch (scan.nextLine()) {
                    case "1":
                        Inserimento.ins(scan,conn);
                        System.out.println("Libro aggiunto!");
                        break;
                    case "2":
                        EsecutoreSuDB.modificaLibro(conn,scan);
                        System.out.println("Dati modificati con successo");
                        break;
                    case "3":
                        System.out.println("Inserisci il nome del libro da eliminare:");
                        EsecutoreSuDB.eliminalibro(conn, scan);
                        break;
                    default:
                        c = false;
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
