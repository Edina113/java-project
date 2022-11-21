//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteka
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//kreiranje klase AddCustomerMang implementirane u glavnom prozoru sa svim komponentama (naslov, dugmiæi, tekstualna polja, tabela, slika i sl.) 
public class AddToCustomerMang extends JFrame implements ActionListener {
  int i = 0;
  CustomerMang cm = new CustomerMang();
  JLabel title = new JLabel("Dodavanje kupca", JLabel.CENTER);
  JLabel id = new JLabel("ID kupca *:       ");
  JLabel customerName = new JLabel("Ime i prezime *: ");
  JLabel customerPhone = new JLabel("Telefon *: ");
  JLabel customerAddress = new JLabel("Adresa *: ");
  JLabel comment = new JLabel("Komentar : ");
  JTextField tid = new JTextField(20);
  JTextField tname = new JTextField(20);
  JTextField tphone = new JTextField(20);
  JTextField taddress = new JTextField(20);
  JTextField tcomment = new JTextField(20);
  JPanel l = new JPanel();
  JPanel r = new JPanel();
  JPanel n = new JPanel();
  JPanel s = new JPanel();
  JButton submit = new JButton("");
  JButton clear = new JButton("");
  JButton cancel = new JButton("");
  //poziv baze
  DB d = new DB();
  //definisanje glavnog prozora AddToCustomerMang (pozadina, velièina, naziv i sl)
  AddToCustomerMang() {
    getContentPane().setBackground(new Color(240, 255, 240));
    getContentPane().setLayout(new BorderLayout());
    setSize(450, 350);
    setTitle("Dodavanje kupca");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    n.setBackground(new Color(240, 255, 255));

    getContentPane().add("North", n);
    n.add(title);
    title.setFont(new Font("Moire Light", Font.BOLD, 48));
    l.setBackground(new Color(240, 255, 240));
    getContentPane().add("West", l);
    r.setBackground(new Color(240, 255, 240));
    getContentPane().add("East", r);
    l.setPreferredSize(new Dimension(100, 280));
    l.add(id);
    l.add(customerName);
    l.add(new Label(" "));
    l.add(customerPhone);
    l.add(new Label("  "));
    l.add(customerAddress);
    l.add(comment);
    r.setPreferredSize(new Dimension(300, 280));
    r.add(tid);
    r.add(tname);
    r.add(tphone);
    r.add(taddress);
    r.add(tcomment);
    s.setBackground(new Color(240, 255, 240));
    getContentPane().add("South", s);
    submit.setIcon(new ImageIcon("lib/add_customers.png"));

    s.add(submit);
    clear.setIcon(new ImageIcon("lib/kos.png"));
    s.add(clear);
    cancel.setIcon(new ImageIcon("lib/delete (1).png"));
    s.add(cancel);

    submit.setPreferredSize(new Dimension(100, 50));
    cancel.setPreferredSize(new Dimension(100, 50));
    clear.setPreferredSize(new Dimension(100, 50));
    submit.addActionListener(this);
    cancel.addActionListener(this);
    clear.addActionListener(this);

    setVisible(true);
    setResizable(false);
  }
//definisaje radnji za opciju ažuriranja
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == submit) {

      if (tid.getText().equals("") || tname.getText().equals("") || tphone.getText().equals("") || taddress.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Ispunite potrebno polje !", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      } else {
        d.insert("INSERT INTO customer_mang (id,customer_name,customer_phone,customer_address,comment ) VALUES ('" + tid.getText() + "','" + tname.getText() + "','" + tphone.getText() + "','" + taddress.getText() + "','" + tcomment.getText() + "')");
        i++;
        JOptionPane.showMessageDialog(null, "" + i + " Kupac je dodan !", "Kupac je uspješno dodan", JOptionPane.INFORMATION_MESSAGE);
      }

      tid.setText("");
      tname.setText("");
      tphone.setText("");
      taddress.setText("");
      tcomment.setText("");

    }
    if (e.getSource() == clear) {
      tid.setText("");
      tname.setText("");
      tphone.setText("");
      taddress.setText("");
      tcomment.setText("");
    }
    if (e.getSource() == cancel) {
      CustomerMang cm = new CustomerMang();
      dispose();
    }

  }
}