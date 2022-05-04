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

import by.car.db.DB;
import by.car.entity.Users;

public class LoginFrame extends JFrame {

    private JPanel panel;
    private JTextField login;
    private JPasswordField password;
    private JLabel labelLogin, labelPassword;
    private JButton ok, reg;
    private DB db;
    private String log, pass;

    public LoginFrame(DB db, String log, String pass) {
        this.db = db;
        this.log =log;
        this.pass = pass;
        setSize(250, 170);
        setResizable(false);
        setTitle("LoginFrame");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    public LoginFrame(DB db) {
        this.db = db;
        setSize(250, 170);
        setResizable(false);
        setTitle("LoginFrame");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setVisible(true);
    }

    public void initComponents() {

        panel = new JPanel();

        login = new JTextField(log, 20);
        password = new JPasswordField(pass, 20);

        labelLogin = new JLabel("Введите логин");
        labelPassword = new JLabel("Введите пароль");

        ok = new JButton("Войти");
        reg = new JButton("Регистрация");

        panel.add(labelLogin);
        panel.add(login);
        panel.add(labelPassword);
        panel.add(password);
        panel.add(ok);
        panel.add(reg);

        add(panel);

    }

    public void action() {
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = db.query("SELECT * FROM users WHERE login = '" + login.getText() + "'");
                try {
                    if (rs.next()) {
                        if (rs.getString("password").equals(String.valueOf(password.getPassword()))) {
                            if (rs.getInt("del_status") == 1) {
                                if (rs.getInt("role") == 1) {
                                    new UserFrame(db, new Users(rs.getInt("id_user"), rs.getString("login"), rs.getString("password"), rs.getInt("role"), rs.getInt("del_status")));
                                    dispose();
                                    JOptionPane.showMessageDialog(panel, "Добро пожаловать в наш сервис!", "Welcome",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    new AdminFrame(db);
                                    dispose();
                                }
                            } else {
                                JOptionPane.showMessageDialog(panel, "Вы заблокированы", "Ошибка",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Пароль не верный", "Ошибка",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(panel, "Пользователя не существует", "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        reg.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationFrame(db);
                dispose();

            }
        });
    }

}
