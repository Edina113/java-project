//imenovanje paketa
package CarRentalApp;
//pozivanje biblioteka
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
//kreiranje klase CarRentalApp implementirane u glavni prozor definisan svojim dijelovima (s, e, w), naslovom, dugmiæima i sl.
public class CarRentalApp extends JFrame implements ActionListener {
	//Dijelovi glavnom prozora
  JPanel s = new JPanel();
  JPanel e = new JPanel();
  JPanel w = new JPanel();
  //definisanje naslova gl. prozora
  JLabel title = new JLabel("Rent a car", JLabel.CENTER);
  //definisanje dugmad
  JButton cMang = new JButton("");
  JButton rMang = new JButton("");
  JButton cOMang = new JButton("");
  JButton Accounting = new JButton("");
  //najvažnija java metoda !!!
  public static void main(String[] args) {
    CarRentalApp cra = new CarRentalApp();
  }
  //definisanje karakteristika glavnog prozora za tekst, dugmiæe, slike (velièina, pozadina, naslov, font, boja, pozicioniranje, sl )
  CarRentalApp() {
    getContentPane().setBackground(new Color(255, 204, 153));
    getContentPane().setLayout(new FlowLayout());
    setSize(800, 600);

    setTitle("Iznajmljivanje vozila");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    s.setBackground(new Color(255, 204, 153));
    //add("North",n);
    getContentPane().add("South", s);
    title.setFont(new Font("Moire Light", Font.BOLD, 48));
    getContentPane().add(title);
    w.setBackground(new Color(255, 204, 153));
    getContentPane().add("West", w);
    e.setBackground(new Color(255, 204, 153));
    getContentPane().add("East", e);

    e.setLayout(new GridLayout(1, 1, 100, 92));
    w.setLayout(new GridLayout(1, 1, 100, 92));
    cOMang.setBackground(new Color(255, 204, 153));
    cOMang.setIcon(new ImageIcon("lib/System-users-3.svg.png"));
    e.add(cOMang);
    Accounting.setBackground(new Color(255, 204, 153));
    Accounting.setIcon(new ImageIcon("lib/accounting-icon-5.jpg"));
    e.add(Accounting);
    cMang.setBackground(new Color(255, 204, 153));
    cMang.setIcon(new ImageIcon("lib/397639-200.png"));
    w.add(cMang);
    rMang.setBackground(new Color(255, 204, 153));
    rMang.setIcon(new ImageIcon("lib/GSA-OLU-Program-Managers-Icon-1.png"));
    w.add(rMang);
    cMang.setFont(new Font("Arial", Font.BOLD, 15));
    rMang.setFont(new Font("Arial", Font.BOLD, 15));
    Accounting.setFont(new Font("Arial", Font.BOLD, 15));
    cOMang.setFont(new Font("Arial", Font.BOLD, 15));
    cMang.setPreferredSize(new Dimension(220, 220));
    rMang.setPreferredSize(new Dimension(180, 180));
    cOMang.setPreferredSize(new Dimension(220, 220));
    Accounting.setPreferredSize(new Dimension(180, 180));

    cMang.addActionListener(this);
    rMang.addActionListener(this);
    Accounting.addActionListener(this);
    cOMang.addActionListener(this);

    setVisible(true);
    setResizable(false);

  }
//definisanje radnji za dugmiæe CarMang, RentMang, Accounting, CustomerMang
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cMang) {
      CarMang cm = new CarMang();
      dispose();
    }
    if (e.getSource() == rMang) {
      RentMang rm = new RentMang();
      dispose();
    }
    if (e.getSource() == Accounting) {
      Accounting accounting = new Accounting();
      dispose();
    }
    if (e.getSource() == cOMang) {
      CustomerMang cm = new CustomerMang();
      dispose();
    }

  }

}