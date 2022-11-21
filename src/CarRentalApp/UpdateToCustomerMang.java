//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteka
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//kreiranje klase UpdateToCarMang implementirane u glavni prozor definisan svojim dijelovima (l,r, n), tabelom (jt), dugmiæima i sl.
public class UpdateToCustomerMang extends JFrame implements ActionListener {
  int i = 0;
  CustomerMang cm = new CustomerMang();
  JLabel title = new JLabel("Osvjezi", JLabel.CENTER);
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
  JButton cancel = new JButton("");
  //poziv baze
  DB d = new DB();
//definisanje karakteristika glavnog prozora za tekst, dugmiæe, slike (velièina, pozadina, naslov, font, boja, pozicioniranje, sl )
  UpdateToCustomerMang() {
    getContentPane().setBackground(new Color(204, 255, 204));
    getContentPane().setLayout(new BorderLayout());
    setSize(450, 350);
    setTitle("Ažuriraj podatke kupca");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    n.setBackground(new Color(204, 255, 204));

    getContentPane().add("North", n);
    n.add(title);
    title.setFont(new Font("Moire Light", Font.BOLD, 48));
    l.setBackground(new Color(204, 255, 204));
    getContentPane().add("West", l);
    r.setBackground(new Color(204, 255, 204));
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
    s.setBackground(new Color(204, 255, 204));
    getContentPane().add("South", s);
    submit.setIcon(new ImageIcon("lib/refresh.png"));

    s.add(submit);
    cancel.setIcon(new ImageIcon("lib/delete (1).png"));
    s.add(cancel);

    submit.setPreferredSize(new Dimension(100, 50));
    cancel.setPreferredSize(new Dimension(100, 50));
    tid.setEditable(false);
    submit.addActionListener(this);
    cancel.addActionListener(this);
    setVisible(true);
    setResizable(false);
  }
//definisanje radnji za dugmad
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cancel) {
      CustomerMang cm = new CustomerMang();
      dispose();
    }
    if (e.getSource() == submit) {

      d.update(" UPDATE `customer_mang` SET `id`=" + tid.getText() + "," + "`customer_name`='" + tname.getText() + "'," + "`customer_phone`='" + tphone.getText() + "'," + "`customer_address`='" + taddress.getText() + "'," + "`comment`='" + tcomment.getText() + "' where id=" + tid.getText());
      JOptionPane.showMessageDialog(null, "updated successfully");
    }

  }
  public void setId(Object a) {
    tid.setText(a.toString());
  }
  public void setName(Object b) {
    tname.setText(b.toString());
  }
  public void setPhone(Object c) {
    tphone.setText(c.toString());
  }
  public void setAddress(Object d) {
    taddress.setText(d.toString());
  }
  public void setComment(Object e) {
    tcomment.setText(e.toString());
  }
}