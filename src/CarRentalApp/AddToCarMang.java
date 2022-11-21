//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteka
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.security.Principal;
//kreiranje klase AddToRentMang implementirane u glavnom prozoru sa svim komponentama (naslov, dugmiæi, tekstualna polja, tabela, slika i sl.) 
public class AddToCarMang extends JFrame implements ActionListener {
  int i = 0;
  CarMang cm = new CarMang();
  JLabel title = new JLabel("Dodavanje vozila", JLabel.CENTER);
  JLabel id = new JLabel("ID vozila *:       ");
  JLabel carModel = new JLabel("Model vozila *: ");
  JLabel color = new JLabel("Boja vozila *: ");
  JLabel price = new JLabel("Cijena *: ");
  JLabel comment = new JLabel("Komentar : ");
  JTextField tid = new JTextField(20);
  JTextField tcarModel = new JTextField(20);
  JTextField tcolor = new JTextField(20);
  JTextField tprice = new JTextField(20);
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
  //definisanje glavnog prozora AddToRentMang (pozadina, velièina, naziv i sl)
  AddToCarMang() {
    getContentPane().setBackground(new Color(255, 204, 255));
    getContentPane().setLayout(new BorderLayout());
    setSize(450, 350);
    setTitle("Dodaj vozilo");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    n.setBackground(new Color(255, 204, 255));

    getContentPane().add("North", n);
    n.add(title);
    title.setFont(new Font("Moire Light", Font.BOLD, 48));
    l.setBackground(new Color(255, 204, 255));
    getContentPane().add("West", l);
    r.setBackground(new Color(255, 204, 255));
    getContentPane().add("East", r);
    l.setPreferredSize(new Dimension(100, 280));
    id.setVerticalAlignment(SwingConstants.TOP);
    l.add(id);
    carModel.setVerticalAlignment(SwingConstants.TOP);
    l.add(carModel);
    l.add(new Label(" "));
    color.setVerticalAlignment(SwingConstants.TOP);
    l.add(color);
    l.add(new Label("  "));
    price.setVerticalAlignment(SwingConstants.TOP);
    l.add(price);
    comment.setVerticalAlignment(SwingConstants.TOP);

    l.add(comment);
    r.setPreferredSize(new Dimension(300, 280));
    r.add(tid);
    r.add(tcarModel);
    r.add(tcolor);
    r.add(tprice);
    r.add(tcomment);
    s.setBackground(new Color(255, 204, 255));
    getContentPane().add("South", s);
    submit.setIcon(new ImageIcon("lib/car_add.png"));

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

      if (this.tid.getText().toString().equals("") || this.tcolor.getText().toString().equals("") || this.tcarModel.getText().equals("") || this.tprice.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Ispunite potrebno polje !", "Nema odabira", JOptionPane.ERROR_MESSAGE);
      }
      if (!d.isInteger(this.tprice.getText())) {
        JOptionPane.showMessageDialog(null, "Provjerite jeste li unijeli odgovarajuæu cijenu vozila", "Data Type Error", JOptionPane.ERROR_MESSAGE);

      } else {
        d.insert("INSERT INTO car_mang (id,car_model,color,price_rent,comment ) VALUES ('" + tid.getText() + "','" + tcarModel.getText() + "','" + tcolor.getText() + "','" + tprice.getText() + "','" + tcomment.getText() + "')");
        i++;

        JOptionPane.showMessageDialog(null, "" + i + " Vozilo je dodano!", "Vozilo je uspješno dodano", JOptionPane.INFORMATION_MESSAGE);
      }

      tid.setText("");
      tcarModel.setText("");
      tcolor.setText("");
      tprice.setText("");
      tcomment.setText("");

    }
    if (e.getSource() == clear) {
      tid.setText("");
      tcarModel.setText("");
      tcolor.setText("");
      tprice.setText("");
      tcomment.setText("");
    }
    if (e.getSource() == cancel) {
      CarMang cm = new CarMang();
      dispose();
    }

  }
}