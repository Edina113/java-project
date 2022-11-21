//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteka
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//kreiranje klase CustomerMang implementirane u glavni prozor definisan svojim dijelovima (l,r, n), tabelom (jt), dugmiæima i sl.
public class CustomerMang extends JFrame implements ActionListener {
	//definisanje dijelova glavnog programa (podjela programa na dijelove)
  JPanel l = new JPanel();
  JPanel r = new JPanel();
  JPanel n = new JPanel();
  //kreiranje tabele
  JTable jt = new JTable();
  //kreiranje dugmadi za dodavanje, ažuriranje, brisanje, osvježavanje
  JButton add = new JButton("");
  JButton update = new JButton("");
  JButton delete = new JButton("");
  JButton refresh = new JButton("");
  //definisanje slike u dugmetu
  ImageIcon h = new ImageIcon("lib/home-back.png");
  JButton home = new JButton(h);
  //definisanje naslova
  JLabel title = new JLabel("Upravljanje kupcima", JLabel.CENTER);
  //pozivanje baze
  DB db = new DB();
  //definisanje karakteristika glavnog prozora za tekst, dugmiæe, slike (velièina, pozadina, naslov, font, boja, pozicioniranje, sl )
  CustomerMang() {
    getContentPane().setLayout(new BorderLayout());
    setSize(800, 600);
    setTitle("Upravljanje kupcima");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    n.setBackground(new Color(204, 0, 51));
    n.setForeground(new Color(51, 204, 255));
    getContentPane().add("North", n);
    n.add(new Label("       "));
    title.setForeground(new Color(255, 255, 255));

    n.add(title);
    title.setFont(new Font("Moire Light", Font.BOLD, 48));
    l.setBackground(new Color(204, 0, 51)); 
    getContentPane().add(l);
    r.setBackground(new Color(204, 0, 51));
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
    add.setIcon(new ImageIcon("lib/add_customers.png"));
    l.add(add);
    l.add(new Label("          "));
    update.setIcon(new ImageIcon("lib/update.png"));
    update.setBackground(Color.WHITE);
    l.add(update);
    l.add(new Label("          "));
    delete.setIcon(new ImageIcon("lib/kos.png"));
    delete.setBackground(Color.WHITE);
    l.add(delete);
    l.add(new Label("          "));
    l.add(update);
    l.add(new Label("          "));
    refresh.setIcon(new ImageIcon("lib/refresh.png"));
    refresh.setBackground(Color.WHITE);
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
    //pozivanje tabele customer_mang 
    db.select("customer_mang");
    jt.setForeground(new Color(204, 0, 51));

    jt.setModel(db.customer_mang);
    r.add(jt);
    jt.setSize(550, 300);
    jt.setPreferredScrollableViewportSize(new Dimension(550, 450));
    jt.setFillsViewportHeight(true);
    JScrollPane jsp = new JScrollPane(jt);
    r.add(jsp);
    //dodavanje opcija  za osvježavanje, brisanje, dodavanje, osvježavanje, kuæa (povratak na poèetno)
    refresh.addActionListener(this);
    delete.addActionListener(this);
    add.addActionListener(this);
    update.addActionListener(this);
    home.addActionListener(this);
    setVisible(true);
    setResizable(false);
  }
  //definisanje radnji za dugmadi za osvježavanje, brisanje, dodavanje, osvježavanje, kuæa (povratak na poèetno)
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == update) {

      int col = 0;
      int row = jt.getSelectedRow();
      if (row == -1) {
        JOptionPane.showMessageDialog(null, "Odaberi red za ažuriranje ", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      } else {
        UpdateToCustomerMang utcm = new UpdateToCustomerMang();
        utcm.setId(jt.getValueAt(row, col));
        col++;
        utcm.setName(jt.getValueAt(row, col));
        col++;
        utcm.setPhone(jt.getValueAt(row, col));
        col++;
        utcm.setAddress(jt.getValueAt(row, col));
        col++;
        utcm.setComment(jt.getValueAt(row, col));
        col = 0;
        dispose();
      }

    }
    if (e.getSource() == refresh) {
      db.select("customer_mang");

    }
    if (e.getSource() == delete) {

      try {

        int row = jt.getSelectedRow();

        int col = 0;
        Object id = jt.getValueAt(row, col);
        if (db.areUsuretoDelete()) {
          db.delet(id, "customer_mang");
          db.select("customer_mang");
        }
      } catch (Exception ee) {
        JOptionPane.showMessageDialog(null, "Odaberi red za brisanje ", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      }

    }
    if (e.getSource() == add) {
      try {
        AddToCustomerMang atcm = new AddToCustomerMang();
        dispose();
      } catch (Exception e2) {
        JOptionPane.showMessageDialog(null, "Došlo je do greške", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      }
      db.select("customer_mang");
    }
    if (e.getSource() == home) {
      CarRentalApp cra = new CarRentalApp();
      dispose();
    }

  }

}