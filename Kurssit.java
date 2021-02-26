import java.sql.*;
import java.util.*;

public class Kurssit {
    private static Connection db;

    public Kurssit(Connection db) {
        this.db = db;
    }

    public static void main(String[] args) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:kurssit.db");
        Kurssit k = new Kurssit(db);
        Scanner s = new Scanner("2\nAnna Leppänen");
        //Scanner s = new Scanner(System.in);
        ui(s, db);

        /*
         * PreparedStatement p =
         * db.prepareStatement("SELECT hinta FROM Tuotteet WHERE nimi=?");
         * p.setString(1, nimi);
         * 
         * ResultSet r = p.executeQuery(); if (r.next()) { System.out.println("Hinta: "
         * + r.getInt("hinta")); } else { System.out.println("Tuotetta ei löytynyt"); }
         */
        s.close();
    }

    private static void ui(Scanner s, Connection db) throws SQLException {
        OUTER: while (true) {
            System.out.print("Valitse toiminto: ");
            int cmd = Integer.valueOf(s.nextLine());
            switch (cmd) {
                case 1:
                    System.out.print("Anna vuosi: ");
                    int vuosi = s.nextInt();
                    vuosiraportti(vuosi);
                    break;
                case 2:
                    System.out.print("Anna opiskelijan nimi: ");
                    String nimi = s.nextLine();
                    opiskelijaraportti(nimi);
                    break;
                case 3:
                    System.out.print("Anna kurssin nimi: ");
                    String kurssiNimi = s.nextLine();
                    kurssiraportti(kurssiNimi);
                    break;
                case 4:
                    System.out.print("Anna opettajien määrä: ");
                    int opettajia = s.nextInt();
                    opettajaRaportti(opettajia);
                    break;
                case 5:
                    System.out.println("Lopetetaan ohjelma.");
                    break OUTER;
                default:
                    System.out.println("Komennot:");
                    System.out.println("1 - Laske annettuna vuonna saatujen opintopisteiden yhteismäärä.");
                    System.out.println("2 - Tulosta annetun opiskelijan kaikki suoritukset aikajärjestyksessä.");
                    System.out.println("3 - Tulosta annetun kurssin suoritusten arvosanojen jakauma.");
                    System.out.println("4 - Tulosta top x eniten opintopisteitä antaneet opettajat.");
                    System.out.println("5 - Sulje ohjelma.");

            }

        }
    }

    private static void opettajaRaportti(int opettajia) throws SQLException {
        PreparedStatement p = db.prepareStatement("SELECT * FR");
    }

    private static void kurssiraportti(String kurssiNimi) throws SQLException {
        PreparedStatement p = db.prepareStatement("SELECT ");
    }

    private static void opiskelijaraportti(String nimi) throws SQLException {
        PreparedStatement p0 = db.prepareStatement("select kurssit.nimi, kurssit.laajuus,"
                + "suoritukset.paivays, suoritukset.arvosana from suoritukset, opiskelijat,"
                + " kurssit where suoritukset.opiskelija_id = opiskelijat.id AND kurssit.id = "
                + "suoritukset.kurssi_id AND opiskelijat.nimi = ? order by paivays;");
        p0.setString(1, nimi);
        ResultSet r = p0.executeQuery();

        // if (r.next()) {
        //     System.out.println("kurssi" + "\top" + "\tpäiväys" + "\tarvosana");
            while (r.next()) {
                System.out.print(
                    r.getString("Kurssit.nimi") +
                    "\t" + r.getString("kurssit.laajuus")
                    + "\t" + r.getDate("suoritukset.paivays")
                    + "\t" + r.getInt("suoritukset.arvosana"));
            }
        // } else {
        //     System.out.println("Opiskelijaa ei löytynyt");
        // }

    }

    private static void vuosiraportti(int vuosi) throws SQLException {
        PreparedStatement p = db.prepareStatement("SELECT * FROM ");
    }
}