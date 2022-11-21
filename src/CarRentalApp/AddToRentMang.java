//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteka
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
//kreiranje klase AddToRentMang implementirane u glavnom prozoru sa svim komponentama (naslov, dugmiæi, tekstualna polja, tabela, slika i sl.) 
public class AddToRentMang extends JFrame implements ActionListener {
  int i = 0;
  RentMang rm = new RentMang();
  //labela za naslov 
  JLabel title = new JLabel("Dodaj novo iznajmljivanje", JLabel.CENTER);
  //labela za rubriku ID
  JLabel id = new JLabel("      ID *:       ");
  //labela za rubriku carID
  JLabel carId = new JLabel("ID vozila *:       ");
  //labela za rubriku kupac
  JLabel customerName = new JLabel("Kupac *: ");
  //labela za broj dana 
  JLabel nOfDays = new JLabel("# dana *: ");
  //labela za unos kapare
  JLabel pcost = new JLabel("Kapara*: ");
  //kreiranje liste za id
  JComboBox cid = new JComboBox();
  //kreiranje liste za ime
  JComboBox cCName = new JComboBox();
  //kreiranje rubrika za tekst, id, unos cijene 
  JTextField tday = new JTextField(20);
  JTextField tid = new JTextField(20);
  JTextField tcost = new JTextField(20);
  //podjela glavnog prozora na ploèe (dijelove)
  JPanel l = new JPanel();
  JPanel r = new JPanel();
  JPanel n = new JPanel();
  JPanel s = new JPanel();
  //kreiranje dugmiæa za snimanje i odustajanje od snimanja
  JButton submit = new JButton("");
  JButton cancel = new JButton("");
  //pozivanje baze podataka
  DB d = new DB();
  //definisanje glavnog prozora AddToRentMang (pozadina, velièina, naziv i sl)
  AddToRentMang() {
    getContentPane().setBackground(new Color(245, 245, 220));
    getContentPane().setLayout(new BorderLayout());
    setSize(450, 350);
    setTitle("Dodaj auto");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    //novi prozor - karakteristike iz baze podataka
    d.select("carIDcombo");
    cCName.setModel(new DefaultComboBoxModel(d.carIds.toArray()));
    d.select("customerName");
    cid.setModel(new DefaultComboBoxModel(d.cnames.toArray()));
    n.setBackground(new Color(245, 245, 220));
    getContentPane().add("North", n);
    n.add(title);
    title.setFont(new Font("Dialog", Font.BOLD, 30));
    l.setBackground(new Color(245, 245, 220));
    getContentPane().add("West", l);
    r.setBackground(new Color(245, 245, 220));
    getContentPane().add("East", r);
    l.setPreferredSize(new Dimension(100, 280));
    l.add(id);
    l.add(carId);
    l.add(new Label(" "));
    l.add(customerName);
    l.add(new Label(" "));
    l.add(nOfDays);
    l.add(new Label("  "));
    l.add(pcost);
    r.setPreferredSize(new Dimension(300, 280));
    cid.setPreferredSize(new Dimension(225, 30));
    cCName.setPreferredSize(new Dimension(225, 30));
    r.add(tid);
    r.add(cCName);

    r.add(cid);
    r.add(tday);
    r.add(tcost);
    s.setBackground(new Color(245, 245, 220));
    getContentPane().add("South", s);
    submit.setIcon(new ImageIcon("lib/document_new.png"));

    s.add(submit);
    cancel.setIcon(new ImageIcon("lib/kos.png"));
    s.add(cancel);

    submit.setPreferredSize(new Dimension(100, 50));
    cancel.setPreferredSize(new Dimension(100, 50));
    submit.addActionListener(this);
    cancel.addActionListener(this);
    cid.setSelectedIndex(-1);
    cCName.setSelectedIndex(-1);
    setVisible(true);
    setResizable(false);
  }
//definisaje radnji za opciju ažuriranja
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == submit) {

      if (cid.getSelectedIndex() == -1 || cCName.getSelectedIndex() == -1 || tday.getText().equals("") || tcost.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Ispunite potrebno polje !", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      }
      if (!d.isInteger(this.tday.getText())) {
        JOptionPane.showMessageDialog(null, "Provjerite jeste li unijeli ispravan broj dana", "Pogreška tipa podataka", JOptionPane.ERROR_MESSAGE);

      }
      if (!d.isInteger(this.tcost.getText())) {
        JOptionPane.showMessageDialog(null, "Provjerite jeste li unijeli ispravnu cijenu plaæanja", "Greška odabira tipa podataka", JOptionPane.ERROR_MESSAGE);

      } else {
        d.setJtable(cCName.getSelectedItem().toString());
        d.select(cCName.getSelectedItem().toString());

        double totalc = Double.parseDouble(tday.getText()) * Double.parseDouble(d.carCost());
        String sTotalc = String.valueOf(totalc);
        double bTotal = totalc - Double.parseDouble(tcost.getText());
        String sbTotal = String.valueOf(bTotal);
        d.insert("INSERT INTO rent_mang (id,car_id,customer_name,date,paid_cost,cost,total) VALUES ('" + tid.getText() + "','" + cCName.getSelectedItem().toString() + "','" + cid.getSelectedItem().toString() + "','" + tday.getText() + "','" + tcost.getText() + "','" + sTotalc + "','" + sbTotal + "')");
        i++;
        JOptionPane.showMessageDialog(null, "" + i + "Uspješno dodano!", " uspješno dodano", JOptionPane.INFORMATION_MESSAGE);
      }

      cid.setSelectedIndex(-1);;
      cCName.setSelectedIndex(-1);
      tday.setText("");
      tcost.setText("");
      tid.setText("");

    }

    if (e.getSource() == cancel) {
      RentMang rm = new RentMang();
      dispose();
    }

  }
}