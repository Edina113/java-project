//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteka
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//kreiranje klase RentMang implementirane u glavni prozor definisan svojim dijelovima (l,r, n), tabelom (jt), dugmiæima i sl.
public class RentMang extends JFrame implements ActionListener {
//podjela prozora na dijelove
  JPanel l = new JPanel();
  JPanel r = new JPanel();
  JPanel n = new JPanel();
  //dodavanje tabele
  JTable jt = new JTable();
  //dodavanje dugmad za dodavanje, osvježavanje, brisanje, ažuriranje
  JButton add = new JButton("");
  //JButton update=new JButton("Update");
  JButton delete = new JButton("");
  JButton refresh = new JButton("");
  ImageIcon h = new ImageIcon("lib/home-back.png");
  JButton home = new JButton(h);
  //definisanje naslova
  JLabel title = new JLabel("Iznajmljivanje vozila", JLabel.CENTER);
  //poziv baze
  DB db = new DB();
//definisanje karakteristika glavnog prozora za tekst, dugmiæe, slike (velièina, pozadina, naslov, font, boja, pozicioniranje, sl )
  RentMang() {
    getContentPane().setLayout(new BorderLayout());
    setSize(800, 600);
    setTitle("Iznajmljivanje vozila");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    n.setBackground(new Color(255, 255, 204));
    getContentPane().add("North", n);
    n.add(new Label("       "));
    n.add(title);
    title.setFont(new Font("Moire Light", Font.BOLD, 48));
    l.setBackground(new Color(255, 255, 204));
    getContentPane().add(l);
    r.setBackground(new Color(255, 255, 204));
    getContentPane().add(r);
    l.setSize(150, 500);
    l.add(new Label("                                                                                                       "));
    l.add(new Label("          "));
    l.add(new Label("          "));
    l.add(new Label("          "));
    l.add(new Label("          "));
    l.add(new Label("          "));
    home.setBackground(Color.WHITE);
    l.add(home);
    l.add(new Label("          "));
    add.setIcon(new ImageIcon("lib/car_add.png"));
    add.setBackground(Color.WHITE);
    l.add(add);
    l.add(new Label("          "));
    l.add(new Label("          "));
    refresh.setIcon(new ImageIcon("lib/refresh.png"));
    refresh.setBackground(Color.WHITE);
    l.add(refresh);
    l.add(new Label("          "));
    l.add(new Label("          "));
    delete.setIcon(new ImageIcon("lib/kos.png"));
    delete.setBackground(Color.WHITE);
    l.add(delete);
    delete.setFont(new Font("Arial", Font.BOLD, 15));
    delete.setPreferredSize(new Dimension(100, 50));
    delete.addActionListener(this);
    add.setFont(new Font("Arial", Font.BOLD, 15));
    refresh.setFont(new Font("Arial", Font.BOLD, 15));
    add.setPreferredSize(new Dimension(100, 50));
    refresh.setPreferredSize(new Dimension(100, 50));
    home.setPreferredSize(new Dimension(100, 50));
    r.add(new Label("          "));
    r.add(new Label("          "));
    r.add(new Label("          "));
    r.add(new Label("          "));
    db.select("rent_mang");
    jt.setBackground(new Color(255, 255, 204));
    jt.setModel(db.rent_mang);
    r.add(jt);
    jt.setSize(550, 300);
    jt.setPreferredScrollableViewportSize(new Dimension(550, 450));
    jt.setFillsViewportHeight(true);
    JScrollPane jsp = new JScrollPane(jt);
    r.add(jsp);

    refresh.addActionListener(this);
    add.addActionListener(this);
    //update.addActionListener(this);
    home.addActionListener(this);
    //delete.setEnabled(false);
    setVisible(true);
    setResizable(false);
  }
//definisanje radnji za dugmad
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == refresh) {
      db.select("rent_mang");

    }
    if (e.getSource() == delete) {

      try {

        int row = jt.getSelectedRow();

        int col = 0; // ID is the first Column
        Object id = jt.getValueAt(row, col);
        if (db.areUsuretoDelete()) {
          db.delet(id, "rent_mang");
          db.select("rent_mang");
        }
      } catch (Exception ee) {
        JOptionPane.showMessageDialog(null, "Odaberite red za brisanje ", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      }

    }
    if (e.getSource() == add) {
      try {
        AddToRentMang atrm = new AddToRentMang();
        dispose();
      } catch (Exception e2) {
        JOptionPane.showMessageDialog(null, "Došlo je do greške " + e2, "Nema odabira", JOptionPane.ERROR_MESSAGE);
      }
    }
    if (e.getSource() == home) {
      CarRentalApp cra = new CarRentalApp();
      dispose();
    }

  }

}