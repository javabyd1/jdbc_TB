//STEP 1. Import required packages

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/j1b";
    // Database credentials
    static final String USER = "root";
    static final String PASS = "12345";

    public static void main(String[] args) {

        int selectedOperation = -1;

        while (selectedOperation != 0) {
            System.out.println("Wybierz operacje:");
            System.out.println("1: Lista adresow");
            System.out.println("2: Dodaj adres");
            System.out.println("0: Wyjscie");

            Scanner scanner = new Scanner(System.in);
            selectedOperation = scanner.nextInt();

            switch (selectedOperation) {
                case 1:
                    listAddresses();
                    break;
                case 2:
                    insertAddress();
                    break;
            }
        }
    }

    private static void insertAddress() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ID adresu");
        int idAdresu = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Podaj nazwe ulicy");
        String ulica = scanner.nextLine();

        System.out.println("Podaj nazwe miasta");
        String miasto = scanner.nextLine();

        System.out.println("Podaj numer mieszkania");
        int numerMieszkania = scanner.nextInt();
        scanner.nextLine();


        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO adresy (id_adresu, ulica, miasto, numer_mieszkania) " +
                    "VALUES (" + idAdresu + ", '" + ulica + "', '" + miasto + "', " + numerMieszkania + ")";

            int result = stmt.executeUpdate(sql);
            System.out.println("Result: " + result);

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    private static void listAddresses() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM adresy";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idAdresu = rs.getInt("id_adresu");
                String ulica = rs.getString("ulica");
                String miasto = rs.getString("miasto");
                int numerMieszkania = rs.getInt("numer_mieszkania");

                //Display values
                System.out.print("ID: " + idAdresu);
                System.out.print(", ulica: " + ulica);
                System.out.print(", miasto: " + miasto);
                System.out.println(", numer_mieszkania: " + numerMieszkania);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}
