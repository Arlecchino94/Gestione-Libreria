package alby.x;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Inserimento {
    public static void ins(Scanner scan, Connection conn) throws SQLException {
        boolean m = true;
        boolean s = true;
        while (true) {
            System.out.print("Inserisci nome del libro: ");
            String ins = scan.nextLine();
            int ver = EsecutoreSuDB.verifica(conn, ins);
            if (ver == 1) {
                m = false;
            }else{
                m = true;
            }

            while (m) {
                while (s) {
                    System.out.print("Inserisci la quantità del libro: ");
                    String q = scan.nextLine();
                    for (int j = 0; j <= 1; j++) {
                        if (q.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("Hai inserito un carattere invece che un numero");
                            s = false;
                            break;
                        }
                    }

                    int cont = 0;
                    for (int j = 0; cont < 1; j++) {
                        System.out.print("Inserisci IN se è in magazzino, OUT se è fuori dal magazzino: ");
                        String inout = scan.nextLine();
                        if (inout.equals("IN") || inout.equals("OUT")) {
                            cont++;
                            EsecutoreSuDB.inserisciLibro(conn, ins, q, inout);
                            return;
                        }
                    }
                }
            }
        }
    }
}
