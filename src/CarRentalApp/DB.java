//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteka
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
//kreiranje klase DB
public class DB {
  DecimalFormat myformat = new DecimalFormat("$###,##0.00");
  //definisanje tabele car_mang
  DefaultTableModel car_mang = new DefaultTableModel(new String[] {
    "CAR ID",
    "CAR MODEL",
    "COLOR",
    "RENTING PRICE",
    "COMMENT"
  }, 0);
  //definisanje tabele customer_mang
  DefaultTableModel customer_mang = new DefaultTableModel(new String[] {
    "Customer ID",
    "FULL NAME",
    "PHONE",
    "ADDRESS",
    "COMMENT"
  }, 0);
  //definisanje tabele rent_mang
  DefaultTableModel rent_mang = new DefaultTableModel(new String[] {
    "#Rent",
    "CAR ID",
    "Customer Name",
    "#Renting Days",
    "Paid Amount"
  }, 0);
//definisanje tabele accounting
  DefaultTableModel accounting = new DefaultTableModel(new String[] {
    "#Transaction",
    "CAR ID",
    "Customer Name",
    "#Renting Days",
    "Total Amount",
    "Paid Amount",
    "Reqiured"
  }, 0);

  String carCost;
  String jtable = "";

  ArrayList < String > carIds = new ArrayList < String > ();
  ArrayList < String > cnames = new ArrayList < String > ();
//uspostava konekcije sa bazom
  public void select(String table) {
    try {
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://localhost/car_rental";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
      // uèitavanje podataka iz tabele pomoæu upita 
      String query = "SELECT * FROM " + table;
      if (table == "carIDcombo") {
        query = "SELECT id FROM car_mang";
      }
      if (table == jtable) {
        query = "SELECT price_rent FROM car_mang";
      }
      if (table == "customerName") {
        query = "select customer_name from customer_mang";
      }
      if (table == "accounting") {
        query = "select customer_name from customer_mang";
      }
      // kreiranje java izraza
      Statement st = conn.createStatement();

      // izvršavanje upita i dobijanje Java skupa podataka
      ResultSet rs = st.executeQuery(query);

      // Iteracije kroz Java skup rezultata
      if (table == "car_mang") {
        car_mang.setRowCount(0);
        while (rs.next()) {

          String id = rs.getString("id");
          String carModel = rs.getString("car_model");
          String color = rs.getString("color");
          String priceRent = rs.getString("price_rent");
          String comment = rs.getString("comment");
          car_mang.addRow(new Object[] {
            id,
            carModel,
            color,
            priceRent,
            comment
          });

        }

      }
      if (table == "customer_mang") {
        customer_mang.setRowCount(0);
        while (rs.next()) {
          String customerId = rs.getString("id");
          String customerName = rs.getString("customer_name");
          String customerPhone = rs.getString("customer_phone");
          String customerAddress = rs.getString("customer_address");
          String comment = rs.getString("comment");
          customer_mang.addRow(new Object[] {
            customerId,
            customerName,
            customerPhone,
            customerAddress,
            comment
          });
          // ispis rezultata
        }
      }
      if (table == "carIDcombo") {

        try {
          rs = st.executeQuery("select id from car_mang");
          while (rs.next()) {
            String carID = rs.getString("id");
            carIds.add(carID);
          }

        } catch (Exception e) {
          System.out.println("Error" + e);
        }
      }

      if (table == "customerName") {

        try {
          rs = st.executeQuery("select customer_name from customer_mang");
          while (rs.next()) {
            String cname = rs.getString("customer_name");
            cnames.add(cname);
          }

        } catch (Exception e) {
          System.out.println("Error" + e);
        }
      } 

      if (table == "accounting") {
        rs = st.executeQuery("select * from rent_mang");
        accounting.setRowCount(0);
        while (rs.next()) {
          String id = rs.getString("id");
          String carId = rs.getString("car_id");
          String customerName = rs.getString("customer_name");
          String date = rs.getString("date");
          String tamount = rs.getString("cost");
          String pamount = rs.getString("paid_cost");
          String total = rs.getString("total");
          String teto = myformat.format(Double.parseDouble(total));
          accounting.addRow(new Object[] {
            id,
            carId,
            customerName,
            date,
            tamount,
            pamount,
            teto
          });
          // ispis rezultata
        }
      }
      if (table == jtable) {
        try {
          rs = st.executeQuery("select price_rent from car_mang where id=" + "'" + jtable + "'");
          while (rs.next()) {
            String carCostt = rs.getString("price_rent");
            this.carCost = (carCostt);
          }

        } catch (Exception e) {
          System.out.println("Error" + e);
        }
      }

      if (table == "rent_mang") {
        rent_mang.setRowCount(0);
        while (rs.next()) {
          String id = rs.getString("id");
          String carId = rs.getString("car_id");
          String customerName = rs.getString("customer_name");
          String date = rs.getString("date");
          String pamount = rs.getString("paid_cost");
          rent_mang.addRow(new Object[] {
            id,
            carId,
            customerName,
            date,
            pamount
          });
        }
      }
      
      if (table == jtable) {
        try {
          rs = st.executeQuery("select price_rent from car_mang where id=" + "'" + jtable + "'");
          while (rs.next()) {
            String carCostt = rs.getString("price_rent");
            this.carCost = (carCostt);
          }

        } catch (Exception e) {
          System.out.println("Greška" + e);
        }
      }

      st.close();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Greška ! Odstupanje \n" + e.getMessage());
    }

  }

  public void delet(Object id, String table) {
    // naziv JDBC drajvera i url baze podataka 
    Connection conn = null;
    Statement stmt = null;
    try {
      //Registracija JDBC drajvera
      Class.forName("com.mysql.jdbc.Driver");

      //Otvaranje konekcije
      conn = DriverManager.getConnection("jdbc:mysql://localhost/car_rental", "root", "");
      System.out.println("Uspješno povezana baza podataka...");

      //Izvršavanje upita
      stmt = conn.createStatement();
      String sql = "DELETE FROM " + table + " WHERE id = " + "'" + id + "'";

      stmt.executeUpdate(sql);

    } catch (SQLException se) {
      //Greška prilikom upotrebe JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Greška prilikom upotrebe Class.forName
      e.printStackTrace();
    } finally {
      //blok za zatvaranje resursa
      try {
        if (stmt != null)
          conn.close();
      } catch (SQLException se) {} // ne radi ništa
      try {
        if (conn != null)
          conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      } 
    } 
  }
  public void update(String sql) {
    try {
      // kreiranje konekcije sa mysql bazom
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://localhost/car_rental";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");

      Statement st = conn.createStatement();

      st.executeUpdate(sql);

      conn.close();
    } catch (Exception e) {
      System.err.println("Odstupanje!");
      System.err.println(e.getMessage());
    }
  }
  public void insert(String query) {
    try {
      // kreiranje konekcije sa mysql bazom
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://localhost/car_rental";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");

      Statement st = conn.createStatement();

      // dodavanje
      st.executeUpdate(query);

      conn.close();
    } catch (Exception e) {
      System.err.println("Izuzetak!");
      System.err.println(e.getMessage());
    }
  }
  public boolean areUsuretoDelete() {
    int con = JOptionPane.showConfirmDialog(null, "Da li želiš izbrisati odabrani zapis ?", "Potvrdi", JOptionPane.YES_NO_OPTION);
    if (con == 1)
      return false;
    else
      return true;
  }
  public String carCost() {
    return this.carCost;
  }
  public void setJtable(String a) {
    this.jtable = a;
  }
  public static boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    } catch (NullPointerException e) {
      return false;
    }
    // završili smo ako nismo vratili netaèno 
    return true;
  }
}