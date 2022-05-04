package by.car.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import by.car.dao.DaoCars;
import by.car.dao.DaoOrders;
import by.car.dao.DaoUsers;
import by.car.db.DB;
import by.car.entity.Cars;
import by.car.entity.Orders;
import by.car.entity.Users;

public class AdminFrame extends JFrame {

    private DB db;
    private JPanel panel;
    private JButton update1, update2, update3, add1, add2, add3, del1, del2, del3, back, ok;
    private JScrollPane scroll, scroll2, scroll3;
    private Table table, table2, table3;
    private JLabel labelUser, labelCar, labelOrder;

    public AdminFrame(DB db) {
        this.db = db;
        setTitle("AdminFrame");
        setSize(580, 680);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        action();
        setVisible(true);
    }

    public void initComponents() {
        panel = new JPanel();

        update1 = new JButton("Обновить");
        update1.setPreferredSize(new Dimension(160, 20));

        add1 = new JButton("Добавить");
        add1.setPreferredSize(new Dimension(160, 20));

        del1 = new JButton("Удалить");
        del1.setPreferredSize(new Dimension(160, 20));

        update2 = new JButton("Обновить");
        update2.setPreferredSize(new Dimension(160, 20));

        add2 = new JButton("Добавить");
        add2.setPreferredSize(new Dimension(160, 20));

        del2 = new JButton("Удалить");
        del2.setPreferredSize(new Dimension(160, 20));

        update3 = new JButton("Обновить");
        update3.setPreferredSize(new Dimension(120, 20));

        add3 = new JButton("Добавить");
        add3.setPreferredSize(new Dimension(120, 20));

        del3 = new JButton("Удалить");
        del3.setPreferredSize(new Dimension(120, 20));

        back = new JButton("< Назад");
        back.setPreferredSize(new Dimension(130, 20));

        ok = new JButton("Подтвердить");
        ok.setPreferredSize(new Dimension(120, 20));

        labelUser = new JLabel("----- Пользователи -----");
        labelCar = new JLabel("----- Автопарк -----");
        labelOrder = new JLabel("----- Заказы -----");

        table = new Table(db.query("SELECT * FROM users"));
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(500, 150));

        table2 = new Table(db.query("SELECT * FROM cars"));
        table2.getColumnModel().getColumn(1).setMinWidth(120);
        scroll2 = new JScrollPane(table2);
        scroll2.setPreferredSize(new Dimension(500, 150));

        table3 = new Table(db.query("SELECT * FROM orders"));
        scroll3 = new JScrollPane(table3);
        scroll3.setPreferredSize(new Dimension(500, 150));

        panel.add(labelUser);
        panel.add(scroll);
        panel.add(add1);
        panel.add(update1);
        panel.add(del1);
        panel.add(labelCar);
        panel.add(scroll2);
        panel.add(add2);
        panel.add(update2);
        panel.add(del2);
        panel.add(labelOrder);
        panel.add(scroll3);
        panel.add(add3);
        panel.add(update3);
        panel.add(ok);
        panel.add(del3);
        panel.add(back);

        add(panel);
    }

    public void action() {
        update1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (String.valueOf(table.getValueAt(table.getSelectedRow(), 3)).equals("1")
                            || String.valueOf(table.getValueAt(table.getSelectedRow(), 3)).equals("2")) {
                        if (String.valueOf(table.getValueAt(table.getSelectedRow(), 4)).equals("1")
                                || String.valueOf(table.getValueAt(table.getSelectedRow(), 4)).equals("2")) {
                            DaoUsers du = new DaoUsers(db);
                            du.update(new Users(
                                    Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                    String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
                                    String.valueOf(table.getValueAt(table.getSelectedRow(), 2)),
                                    Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                                    Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)))));

                            updateTable();
                        } else {
                            JOptionPane.showMessageDialog(panel, "Проверте правильность ввода", "Ошибка",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Проверте правильность ввода", "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите пользователя", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                DaoUsers du = new DaoUsers(db);
                du.insert(new Users(1, 1));
                updateTable();
            }
        });

        del1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DaoUsers du = new DaoUsers(db);
                    du.delete(Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
                    updateTable();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите пользователя", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        update2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DaoCars dc = new DaoCars(db);
                    dc.update(new Cars(Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 0))),
                            String.valueOf(table2.getValueAt(table2.getSelectedRow(), 1)),
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 2))),
                            String.valueOf(table2.getValueAt(table2.getSelectedRow(), 3)),
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 4))),
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 5)))));
                    updateTable();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите автомобиль", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DaoCars dc = new DaoCars(db);
                dc.insert(new Cars(1));
                updateTable();
            }
        });

        del2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DaoCars dc = new DaoCars(db);
                    dc.delete(Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 0))));
                    updateTable();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите автомобиль", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        update3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DaoOrders dor = new DaoOrders(db);
                    dor.update(
                            new Orders(Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 0))),
                                    Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 1))),
                                    Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 2))),
                                    Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 3))),
                                    String.valueOf(table3.getValueAt(table3.getSelectedRow(), 4)),
                                    Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 5)))));

                    updateTable();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите заказ", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DaoOrders dor = new DaoOrders(db);
                dor.insert(new Orders(1, 1, 1));
                updateTable();
            }
        });

        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (String.valueOf(table3.getValueAt(table3.getSelectedRow(), 4)).equals("processing")) {
                        DaoOrders dor = new DaoOrders(db);
                        DaoCars dc = new DaoCars(db);
                        Cars car = dc
                                .get(Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 2))));
                        dor.update(new Orders(
                                Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 0))),
                                Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 1))),
                                Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 2))),
                                Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 3))), "ok",
                                Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 5)))));
                        dc.update(new Cars(car.getId_car(), car.getModel(), car.getYear(), car.getKpp(), car.getPrice(),
                                2));
                        updateTable();
                    } else {
                        if (String.valueOf(table3.getValueAt(table3.getSelectedRow(), 4)).equals("ok")) {
                            JOptionPane.showMessageDialog(panel, "Заказ уже подтвержден", "Сообщение",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        if (String.valueOf(table3.getValueAt(table3.getSelectedRow(), 4)).equals("wait")) {
                            JOptionPane.showMessageDialog(panel, "Пользователь еще не оформил заказ", "Сообщение",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        if (String.valueOf(table3.getValueAt(table3.getSelectedRow(), 4)).equals("complete")) {
                            JOptionPane.showMessageDialog(panel, "Пользователь уже оплатил заказ", "Сообщение",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        if (String.valueOf(table3.getValueAt(table3.getSelectedRow(), 4)).equals("delete")) {
                            JOptionPane.showMessageDialog(panel, "Заказ был удален", "Сообщение",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите заказ", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        del3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DaoOrders dor = new DaoOrders(db);
                    dor.delete(Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 0))));
                    DaoCars dc = new DaoCars(db);
                    Cars car = dc.get(Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 2))));
                    dc.update(
                            new Cars(car.getId_car(), car.getModel(), car.getYear(), car.getKpp(), car.getPrice(), 1));
                    updateTable();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(panel, "Выберите заказ", "Ошибка", JOptionPane.ERROR_MESSAGE);
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

        table.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        DaoUsers du = new DaoUsers(db);
                        du.update(
                                new Users(Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                                        String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
                                        String.valueOf(table.getValueAt(table.getSelectedRow(), 2)),
                                        Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                                        Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)))));
                    }
                } catch (NumberFormatException ex) {

                }
            }
        });

        table2.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    DaoCars dc = new DaoCars(db);
                    dc.update(new Cars(Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 0))),
                            String.valueOf(table2.getValueAt(table2.getSelectedRow(), 1)),
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 2))),
                            String.valueOf(table2.getValueAt(table2.getSelectedRow(), 3)),
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 4))),
                            Integer.valueOf(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 5)))));
                }
            }
        });

        table3.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    DaoOrders dor = new DaoOrders(db);
                    dor.update(
                            new Orders(Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 0))),
                                    Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 1))),
                                    Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 2))),
                                    Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 3))),
                                    String.valueOf(table3.getValueAt(table3.getSelectedRow(), 4)),
                                    Integer.valueOf(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 5)))));
                }
            }
        });
    }

    public void updateTable() {
        panel.remove(scroll);
        panel.remove(scroll2);
        panel.remove(scroll3);

        table = new Table(db.query("SELECT * FROM users"));
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(500, 150));

        table2 = new Table(db.query("SELECT * FROM cars"));
        table2.getColumnModel().getColumn(1).setMinWidth(120);
        scroll2 = new JScrollPane(table2);
        scroll2.setPreferredSize(new Dimension(500, 150));

        table3 = new Table(db.query("SELECT * FROM orders"));
        scroll3 = new JScrollPane(table3);
        scroll3.setPreferredSize(new Dimension(500, 150));

        panel.add(labelUser);
        panel.add(scroll);
        panel.add(add1);
        panel.add(update1);
        panel.add(del1);
        panel.add(labelCar);
        panel.add(scroll2);
        panel.add(add2);
        panel.add(update2);
        panel.add(del2);
        panel.add(labelOrder);
        panel.add(scroll3);
        panel.add(add3);
        panel.add(update3);
        panel.add(ok);
        panel.add(del3);
        panel.add(back);

        panel.updateUI();
    }

}