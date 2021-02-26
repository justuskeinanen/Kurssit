// package Kurssit;

import java.sql.*;
import java.util.*;

public class Kurssit {
    private Connection connection;
    private Scanner scn;

    public Kurssit(Scanner scn) {
        connection = connect();
        this.scn = scn;
    }

    private Connection connect() {
        String url = "jdbc:sqlite:kurssit.db";
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Scanner scn = new Scanner(System.in);
        Kurssit app = new Kurssit(scn);
        app.runUI();
        scn.close();
    }

    private void runUI() throws SQLException {
        OUTER: while (true) {
            System.out.print("Valitse toiminto: ");
            int cmd = Integer.valueOf(scn.nextLine());
            switch (cmd) {
                case 1:
                    System.out.print("Anna vuosi: ");
                    int vuosi = scn.nextInt();
                    vuosiraportti(vuosi);
                    break;
                case 2:
                    System.out.print("Anna opiskelijan nimi: ");
                    String nimi = scn.nextLine();
                    opiskelijaraportti(nimi);
                    break;
                case 3:
                    System.out.print("Anna kurssin nimi: ");
                    String kurssiNimi = scn.nextLine();
                    kurssiraportti(kurssiNimi);
                    break;
                case 4:
                    System.out.print("Anna opettajien määrä: ");
                    int opettajia = scn.nextInt();
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

    private void opettajaRaportti(int opettajia) throws SQLException {
        PreparedStatement p = connection.prepareStatement("SELECT * FR");
    }

    private void kurssiraportti(String kurssiNimi) throws SQLException {
        PreparedStatement p = connection.prepareStatement("SELECT ");
    }

    private void opiskelijaraportti(String nimi) throws SQLException {

        PreparedStatement ps = connection.prepareStatement(
                "SELECT kurssit.nimi, kurssit.laajuus, suoritukset.paivays, suoritukset.arvosana FROM suoritukset, opiskelijat, kurssit WHERE suoritukset.opiskelija_id = opiskelijat.id AND kurssit.id = suoritukset.kurssi_id AND opiskelijat.nimi = ? ORDER BY paivays;");
        ps.setString(1, nimi);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("kurssi" + "\top" + "\tpäiväys" + "\tarvosana");
            while (rs.next()) {
                System.out.println(rs.getString("nimi") + "\t" + rs.getString("laajuus") + "\t" + rs.getString("paivays")
                        + "\t" + rs.getInt("arvosana"));
            }
        } else {
            System.out.println("Opiskelijaa ei löytynyt");
        }

    }

    private void vuosiraportti(int vuosi) throws SQLException {
        PreparedStatement p = connection.prepareStatement("SELECT * FROM ");
    }
}