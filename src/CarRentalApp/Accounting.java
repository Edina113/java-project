//imenovanje paketa
package CarRentalApp;
//ubacivanje biblioteka
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
//kreiranje klase Accounting implementirane u glavnom prozoru sa svim komponentama (dugmiæi, tekstualna polja, tabela, slika i sl.) 
public class Accounting extends JFrame implements ActionListener {
//podjela glavnog prozora na komponente
  JPanel n = new JPanel();
  JPanel s = new JPanel();
  JTable jt = new JTable();
  //ubacivanje slike u dugme
  ImageIcon h = new ImageIcon("lib/home-back.png");
  JButton home = new JButton(h);
  JButton update = new JButton("");
  //pozivanje baze podataka
  DB db = new DB();
  Accounting() {
	//definisanje pozadine i velièine glavnog prozora, naslova 
    getContentPane().setBackground(new Color(245, 245, 220));
    getContentPane().setLayout(new FlowLayout());
    setSize(800, 600);
    setTitle("Predra\u010Dun");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    getContentPane().add("North", n);
    getContentPane().add("South", s);
    //definisanje svake komponete glavnog prozora
    s.add(home);
    db.select("accounting");
    jt.setModel(db.accounting);
    n.add(jt);
    jt.setSize(550, 300);
    jt.setPreferredScrollableViewportSize(new Dimension(784, 450));
    jt.setFillsViewportHeight(true);
    JScrollPane jsp = new JScrollPane(jt);
    n.add(jsp);
    //ubacivanje slike u dugme, te definisanje velièine dugmiæa
    update.setIcon(new ImageIcon("lib/cost.png"));
    update.setPreferredSize(new Dimension(150, 50));
    home.setPreferredSize(new Dimension(150, 50));
    s.add(update);
    home.addActionListener(this);
    update.addActionListener(this);
    //postavljanje vidljivosti i dozvola promjene velièine
    setVisible(true);
    setResizable(false);
  }
//Ukljuèuje kod koji implementira metode u interfejs (metode za dugmad)
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == home) {
      CarRentalApp cra = new CarRentalApp();
      dispose();
    }
    if (e.getSource() == update) {

      int col = 6; // ID je prva kolona
      int row = jt.getSelectedRow();
      if (row == -1) {
        JOptionPane.showMessageDialog(null, "Odaberite red za dodavanje plaæanja ", "Nije odabrano", JOptionPane.ERROR_MESSAGE);
      } else {
    	//kreiranje predraèuna na osonovu podataka unešenih u bazu 
        String id = jt.getValueAt(row, 0).toString();
        String paid_amount = jt.getValueAt(row, 5).toString();
        String cost = jt.getValueAt(row, 4).toString();
        double dcost = Double.parseDouble(cost);
        double dpaid_amount = Double.parseDouble(paid_amount);
        double drequired = dcost - dpaid_amount;
        double tpay = Double.parseDouble(JOptionPane.showInputDialog("Molim te dodaj plaæanje"));
        String total = String.valueOf(dpaid_amount + tpay);
        String last = String.valueOf(dcost - (dpaid_amount + tpay));
        db.update(" UPDATE `rent_mang` SET `paid_cost`=" + total + "," + "`total`='" + last + "' where id=" + id);

        db.select("accounting");

      }

    }

  }

}