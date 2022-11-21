//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteka
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//kreiranje klase UpdateToCarMang implementirane u glavni prozor definisan svojim dijelovima (l,r, n), tabelom (jt), dugmiæima i sl.
public class UpdateToCarMang extends JFrame implements ActionListener {

  JLabel title = new JLabel("Osvjezi", JLabel.CENTER);
  JLabel id = new JLabel("ID vozila *:       ");
  JLabel carModel = new JLabel("Model vozila *: ");
  JLabel color = new JLabel("Boja vozila *: ");
  JLabel price = new JLabel("Cijena iznajmljivanja *: ");
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
  JButton cancel = new JButton("");
  //poziv baze
  DB d = new DB();
//definisanje karakteristika glavnog prozora za tekst, dugmiæe, slike (velièina, pozadina, naslov, font, boja, pozicioniranje, sl )
  UpdateToCarMang() {
    getContentPane().setBackground(new Color(204, 255, 204));
    getContentPane().setLayout(new BorderLayout());
    setSize(450, 350);
    setTitle("Ažuriraj inofmacije vozila");
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
    l.add(carModel);
    l.add(new Label(" "));
    l.add(color);
    l.add(new Label("  "));
    l.add(price);

    l.add(comment);
    r.setPreferredSize(new Dimension(300, 280));
    r.add(tid);
    r.add(tcarModel);
    r.add(tcolor);
    r.add(tprice);
    r.add(tcomment);
    s.setBackground(new Color(204, 255, 204));
    getContentPane().add("South", s);
    submit.setIcon(new ImageIcon("lib/refresh.png"));

    s.add(submit);
    cancel.setIcon(new ImageIcon("lib/delete (1).png"));
    s.add(cancel);
    tid.setEnabled(false);
    submit.setPreferredSize(new Dimension(100, 50));
    cancel.setPreferredSize(new Dimension(100, 50));
    submit.addActionListener(this);
    cancel.addActionListener(this);

    setVisible(true);
    setResizable(false);
  }
//definisanje radnji za dugmad
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cancel) {
      CarMang cm = new CarMang();
      dispose();
    }
    if (e.getSource() == submit) {

      d.update(" UPDATE `car_mang` SET `id`=" + tid.getText() + "," + "`car_model`='" + tcarModel.getText() + "'," + "`color`='" + tcolor.getText() + "'," + "`price_rent`='" + tprice.getText() + "'," + "`comment`='" + tcomment.getText() + "' where id=" + tid.getText());
      JOptionPane.showMessageDialog(null, "updated successfully");
    }
  }
  public void setId(Object a) {
    tid.setText(a.toString());
  }
  public void setCarModel(Object b) {
    tcarModel.setText(b.toString());
  }
  public void setColor(Object c) {
    tcolor.setText(c.toString());
  }
  public void setPrice(Object d) {
    tprice.setText(d.toString());
  }
  public void setComment(Object e) {
    tcomment.setText(e.toString());
  }

}