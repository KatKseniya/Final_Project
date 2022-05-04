package by.car.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import by.car.db.DB;
import by.car.db.WorkDB;

public class StartFrame extends JFrame {

    private JPanel panel;
    private JTextField url, name, login;
    private JPasswordField password;
    private JLabel labelUrl, labelName, labelLogin, labelPassword;
    private JButton create, delete, connect;

    public StartFrame() {
        setTitle("StartFrame");
        setSize(285, 275);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    public void initComponents() {

        panel = new JPanel();

        url = new JTextField("jdbc:mysql://127.0.0.1/", 22);
        name = new JTextField("youcar", 22);
        login = new JTextField("root", 22);

        password = new JPasswordField("1234", 22);

        labelUrl = new JLabel("url адрес");
        labelName = new JLabel("имя БД");
        labelLogin = new JLabel("Логин");
        labelPassword = new JLabel("Пароль");

        create = new JButton("Создать");
        delete = new JButton("Удалить");
        connect = new JButton("Войти");

        panel.add(labelUrl);
        panel.add(url);
        panel.add(labelName);
        panel.add(name);
        panel.add(labelLogin);
        panel.add(login);
        panel.add(labelPassword);
        panel.add(password);
        panel.add(create);
        panel.add(delete);
        panel.add(connect);

        add(panel);
    }

    public void action() {
        create.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (url.getText().equals("jdbc:mysql://127.0.0.1/")) {
                    if (name.getText().equals("youcar")) {
                        if (login.getText().equals("root")) {
                            if (String.valueOf(password.getPassword()).equals("1234")) {
                                try {
                                    WorkDB.createDB(url.getText(), name.getText(), login.getText(),
                                            String.valueOf(password.getPassword()));
                                } catch (SQLException e1) {
                                    System.out.println("уже есть");
                                }
                                JOptionPane.showMessageDialog(panel, "База данных создана", "Сообщение",
                                        JOptionPane.INFORMATION_MESSAGE);

                            } else {
                                JOptionPane.showMessageDialog(panel, "Пароль введен неверно", "Ошибка",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Логин введен неверно", "Ошибка",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Имя БД введено неверно", "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "URL адресс введен неверно", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (url.getText().equals("jdbc:mysql://127.0.0.1/")) {
                    if (name.getText().equals("youcar")) {
                        if (login.getText().equals("root")) {
                            if (String.valueOf(password.getPassword()).equals("1234")) {
                                try {
                                    WorkDB.deleteDB(url.getText(), name.getText(), login.getText(),
                                            String.valueOf(password.getPassword()));
                                } catch (SQLException e1) {
                                    System.out.println("уже есть");
                                }
                                JOptionPane.showMessageDialog(panel, "База данных удалена", "Сообщение",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(panel, "Пароль введен неверно", "Ошибка",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Логин введен неверно", "Ошибка",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Имя БД введено неверно", "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "URL адресс введен неверно", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        connect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (url.getText().equals("jdbc:mysql://127.0.0.1/")) {
                    if (name.getText().equals("youcar")) {
                        if (login.getText().equals("root")) {
                            if (String.valueOf(password.getPassword()).equals("1234")) {
                                DB db = new DB(url.getText(), name.getText(), login.getText(),
                                        String.valueOf(password.getPassword()));
                                new LoginFrame(db);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(panel, "Пароль введен неверно", "Ошибка",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Логин введен неверно", "Ошибка",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Имя БД введено неверно", "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "URL адресс введен неверно", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
