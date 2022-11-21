//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteke
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//kreiranje klase CarMang implementirane u glavni prozor definisan svojim dijelovima (l, r, n), tabelom (jt), dugmiæima i sl.
public class CarMang extends JFrame implements ActionListener {
	//definisanje dijelova glavnog prozora
  JPanel l = new JPanel();
  JPanel r = new JPanel();
  JPanel n = new JPanel();
  //kreiranje tabele 
  JTable jt = new JTable();
  //dugmiæi da dodavanje, osvježavanje, brisanje i ažuriranje
  JButton add = new JButton("");
  JButton update = new JButton("");
  JButton delete = new JButton("");
  JButton refresh = new JButton("");
  //definisanje slike u dugme
  ImageIcon h = new ImageIcon("lib/home-back.png");
  JButton home = new JButton(h);
  //definisanje naslova 
  JLabel title = new JLabel("Upravljanje vozilima", JLabel.CENTER);
  //poziv baze 
  DB db = new DB();
  //definisanje glavnih karakteristika prozora, dugmiæa, teksta za iznajmljivanje vozila (naslov, velièina, pozadina, font i sl.)
  CarMang() {
    getContentPane().setLayout(new BorderLayout());
    setSize(800, 600);
    setTitle("Iznajmljivanje vozila");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    car s = new car();
    n.setBackground(new Color(255, 204, 204));
    getContentPane().add("North", n);
    n.add(new Label("       "));
    title.setForeground(new Color(255, 255, 255));

    n.add(title);
    title.setFont(new Font("Moire Light", Font.BOLD, 48));
    l.setBackground(new Color(255, 204, 204));
    // dodavanje dugmiæa 
    getContentPane().add(l);
    r.setBackground(new Color(255, 204, 204));
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
    add.setBackground(Color.WHITE);
    add.setIcon(new ImageIcon("lib/add (1).png"));
    l.add(add);
    l.add(new Label("          "));
    update.setBackground(Color.WHITE);
    update.setIcon(new ImageIcon("lib/update.png"));
    l.add(update);
    l.add(new Label("          "));
    delete.setBackground(Color.WHITE);
    delete.setIcon(new ImageIcon("lib/kos.png"));
    l.add(delete);
    l.add(new Label("          "));
    l.add(update);
    l.add(new Label("          "));
    refresh.setBackground(Color.WHITE);
    refresh.setIcon(new ImageIcon("lib/refresh.png"));
    l.add(refresh);
    add.setFont(new Font("Arial", Font.BOLD, 15));
    update.setFont(new Font("Arial", Font.BOLD, 15));
    delete.setFont(new Font("Arial", Font.BOLD, 15));
    refresh.setFont(new Font("Arial", Font.BOLD, 15));
    add.setPreferredSize(new Dimension(100, 50));
    update.setPreferredSize(new Dimension(100, 50));
    delete.setPreferredSize(new Dimension(100, 50));
    refresh.setPreferredSize(new Dimension(100, 50));
    home.setPreferredSize(new Dimension(100, 50));
    r.add(new Label("          "));
    r.add(new Label("          "));
    r.add(new Label("          "));
    r.add(new Label("          "));

    db.select("car_mang");
    jt.setBackground(new Color(255, 255, 255));

    jt.setModel(db.car_mang);
    r.add(jt);
    jt.setSize(550, 300);
    jt.setPreferredScrollableViewportSize(new Dimension(550, 450));
    jt.setFillsViewportHeight(true);
    JScrollPane jsp = new JScrollPane(jt);
    r.add(jsp);

    refresh.addActionListener(this);
    delete.addActionListener(this);
    add.addActionListener(this);
    update.addActionListener(this);
    home.addActionListener(this);
    setVisible(true);
    setResizable(false);
  }
//definisaje radnji za opciju ažuriranja
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == update) {

      int col = 0; // ID je prva kolona
      int row = jt.getSelectedRow();
      if (row == -1) {
        JOptionPane.showMessageDialog(null, "Odaberite red za ažuriranje ", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      } else {
        UpdateToCarMang utcm = new UpdateToCarMang();
        utcm.setId(jt.getValueAt(row, col));
        col++;
        utcm.setCarModel(jt.getValueAt(row, col));
        col++;
        utcm.setColor(jt.getValueAt(row, col));
        col++;
        utcm.setPrice(jt.getValueAt(row, col));
        col++;
        utcm.setComment(jt.getValueAt(row, col));
        col = 0;
        dispose();
      }

    }
    //definisanje radnji za opciju osvježavanje
    if (e.getSource() == refresh) {
      db.select("car_mang");

    }
    //definisanje radnji za opciju brisanja
    if (e.getSource() == delete) {

      try {

        int row = jt.getSelectedRow();

        int col = 0; // ID je prva kolona
        Object id = jt.getValueAt(row, col);
        if (db.areUsuretoDelete()) {
          db.delet(id, "car_mang");
          db.select("car_mang");
        }
      } catch (Exception ee) {
        JOptionPane.showMessageDialog(null, "Odaberite red za brisanje ", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      }

    }
    //definisanje radnji za opciju dodavanja
    if (e.getSource() == add) {
      try {
        AddToCarMang atcm = new AddToCarMang();
        dispose();

      } catch (Exception e2) {
        JOptionPane.showMessageDialog(null, "Došlo je do greške", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      }

    }
    if (e.getSource() == home) {
      CarRentalApp cra = new CarRentalApp();
      dispose();
    }

  }

}