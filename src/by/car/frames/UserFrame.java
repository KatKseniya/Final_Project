package by.car.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import by.car.dao.DaoCars;
import by.car.dao.DaoOrders;
import by.car.db.DB;
import by.car.entity.Cars;
import by.car.entity.Orders;
import by.car.entity.Users;

public class UserFrame extends JFrame {

    private DB db;
    private JPanel panel;
    private JButton add, change, pay, ok, back;
    private JScrollPane scroll, scroll2;
    private Table table, table2;
    private Users user;
    private int price;
    private JLabel labelCars, labelBorder, labelOrders;

    public UserFrame(DB db, Users user) {
        this.db = db;
        this.user = user;
        setTitle("UserFrame");
        setSize(550, 500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        action();
        setVisible(true);
    }

    public void initComponents() {
        panel = new JPanel();

        add = new JButton("Добавить в Заказ");
        add.setPreferredSize(new Dimension(150, 20));

        change = new JButton("Расчет стоимости");
        change.setPreferredSize(new Dimension(150, 20));

        pay = new JButton("Оплатить");
        pay.setPreferredSize(new Dimension(150, 20));

        ok = new JButton("Подтвердить заказ");
        ok.setPreferredSize(new Dimension(150, 20));

        back = new JButton("< Назад");
        back.setPreferredSize(new Dimension(150, 20));

        table = new Table(db.query("SELECT * FROM cars WHERE rent_status ='1'"));
        table.getColumnModel().getColumn(1).setMinWidth(120);
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(500, 150));

        table2 = new Table(db.query("SELECT * FROM orders WHERE id_user = '" + user.getId_user() + "'"));
        scroll2 = new JScrollPane(table2);
        scroll2.setPreferredSize(new Dimension(500, 150));

        labelCars = new JLabel("Автомобили, доступные Вам");
        labelOrders = new JLabel("Ваши заказы");
        labelBorder = new JLabel("");
        labelBorder.setPreferredSize(new Dimension(550, 20));

        panel.add(labelCars);
        panel.add(scroll);
        panel.add(add);
        panel.add(labelBorder);
        panel.add(labelOrders);
        panel.add(scroll2);
        panel.add(change);
        panel.add(ok);
        panel.add(pay);
        panel.add(back);

        add(panel);
    }

    public void action() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DaoOrders dor = new DaoOrders(db);
                    dor.insert(new Orders(user.getId_user(),
                            Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))), 1,
                            Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)))));
                    price = Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
                    updateTable();
                    JOptionPane.showMessageDialog(panel, "Укажите срок аренды в днях в графе 'termin'", "Сообщение",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите автомобиль", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        change.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (String.valueOf(table2.getValueAt(table2.getSelectedRow(), 4)).equals("complete")) {
                        JOptionPane.showMessageDialog(panel, "Этот заказ оплачен. Вы не можете его редактировать",
                                "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (String.valueOf(table2.getValueAt(table2.getSelectedRow(), 4)).equals("processing")) {
                            JOptionPane.showMessageDialog(panel,
                                    "Этот заказ подтвержден. Вы не можете его редактировать", "Сообщение",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            DaoOrders dor = new DaoOrders(db);
                            DaoCars dc = new DaoCars(db);
                            Cars car = dc.get(
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 2))));

                            dor.update(new Orders(
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 0))),
                                    user.getId_user(), car.getId_car(),
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 3))),
                                    "wait",
                                    (Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 3))))
                                            * car.getPrice()));
                            updateTable();
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите заказ для рассчета стоимости", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (String.valueOf(table2.getValueAt(table2.getSelectedRow(), 4)).equals("complete")) {
                        JOptionPane.showMessageDialog(panel, "Этот заказ оплачен. Вам не нужно оплачивать его дважды",
                                "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (String.valueOf(table2.getValueAt(table2.getSelectedRow(), 4)).equals("ok")) {
                            DaoOrders dor = new DaoOrders(db);
                            dor.update(new Orders(
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 0))),
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 1))),
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 2))),
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 3))),
                                    "complete",
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 5)))));
                            updateTable();
                            JOptionPane.showMessageDialog(panel, "Автомобиль оплачен!", "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(panel, "Ваша заявка на рассмотрении. Подождите, пожалуйста",
                                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите заказ для оплаты", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        ok.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    if (String.valueOf(table2.getValueAt(table2.getSelectedRow(), 4)).equals("complete")) {
                        JOptionPane.showMessageDialog(panel, "Этот заказ оплачен. Вам не нужно подтверждать его",
                                "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (String.valueOf(table2.getValueAt(table2.getSelectedRow(), 4)).equals("processing")) {
                            JOptionPane.showMessageDialog(panel,
                                    "Этот заказ подтвержден. Вам не нужно подтверждать его снова", "Сообщение",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            DaoOrders dor = new DaoOrders(db);
                            dor.update(new Orders(
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 0))),
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 1))),
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 2))),
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 3))),
                                    "processing",
                                    Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 5)))));
                            updateTable();
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите заказ для подтверждения", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(db);
                dispose();
            }
        });

        table2.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    DaoOrders dor = new DaoOrders(db);
                    dor.update(new Orders(
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 0))),
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 1))),
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 2))),
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 3))), "wait",
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 5)))));

                }
            }
        });

    }

    public void updateTable() {
        panel.remove(scroll);
        panel.remove(scroll2);

        table = new Table(db.query("SELECT * FROM cars WHERE rent_status ='1'"));
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(500, 150));

        table2 = new Table(db.query("SELECT * FROM orders WHERE id_user = '" + user.getId_user() + "'"));
        scroll2 = new JScrollPane(table2);
        scroll2.setPreferredSize(new Dimension(500, 150));

        panel.add(labelCars);
        panel.add(scroll);
        panel.add(add);
        panel.add(labelBorder);
        panel.add(labelOrders);
        panel.add(scroll2);
        panel.add(change);
        panel.add(ok);
        panel.add(pay);
        panel.add(back);

        panel.updateUI();
    }
}
