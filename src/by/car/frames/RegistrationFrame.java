package by.car.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import by.car.dao.DaoUsers;
import by.car.db.DB;
import by.car.entity.Users;

public class RegistrationFrame extends JFrame {

    private JPanel panel;
    private JTextField login;
    private JPasswordField password, password2;
    private JLabel labelLogin, labelPassword, labelPassword2;
    private JButton ok, back;
    private DB db;

    public RegistrationFrame(DB db) {
        this.db = db;
        setTitle("Registration");
        setSize(250, 220);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        action();
        setVisible(true);
    }

    public void initComponents() {
        panel = new JPanel();

        login = new JTextField("", 20);
        password = new JPasswordField("", 20);
        password2 = new JPasswordField("", 20);
        labelLogin = new JLabel("Ведите логин");
        labelPassword = new JLabel("Введите пароль");
        labelPassword2 = new JLabel("Повторите пароль");
        ok = new JButton("Зарегистрироваться");
        back = new JButton("Назад");

        panel.add(labelLogin);
        panel.add(login);
        panel.add(labelPassword);
        panel.add(password);
        panel.add(labelPassword2);
        panel.add(password2);
        panel.add(ok);
        panel.add(back);
        add(panel);
    }

    public void action() {
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = db.query("SELECT * FROM users WHERE login = '" + login.getText() + "'");
                try {
                    if (rs.next() == false) {
                        if (login.getText().length()>=1 && login.getText().length() <= 20) {
                            if (String.valueOf(password.getPassword()).length()>=1 && String.valueOf(password.getPassword()).length() <= 20) {
                                if (String.valueOf(password.getPassword())
                                        .equals(String.valueOf(password2.getPassword()))) {
                                    DaoUsers daoUser = new DaoUsers(db);
                                    daoUser.insert(new Users(login.getText(), String.valueOf(password.getPassword())));
                                    JOptionPane.showMessageDialog(panel, "Регистрация выполнена!", "Message",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    new LoginFrame(db, login.getText(), String.valueOf(password.getPassword()));
                                    dispose();
                                } else {
                                    JOptionPane.showMessageDialog(panel, "Упс, повторите пароль правильно", "Ошибка",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(panel, "Упс, пароль должен содержать от 1 до 20 символов", "Ошибка",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Ошибка ввода логина", "Ошибка",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Пользователь с таким именем уже существует", "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
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
    }

}
