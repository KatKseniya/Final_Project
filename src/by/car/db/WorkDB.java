package by.car.db;

import java.sql.SQLException;

import by.car.dao.DaoCars;
import by.car.dao.DaoUsers;
import by.car.entity.Cars;
import by.car.entity.Users;

public class WorkDB {

    public static void createDB(String url, String name, String login, String password) throws SQLException {

        DB db = new DB(url, "", login, password);
        db.update("CREATE DATABASE " + name);
        db.update("USE " + name);

        db.update("CREATE TABLE users (id_user INT AUTO_INCREMENT," + "login VARCHAR (30) UNIQUE,"
                + "password VARCHAR (30) ," + "role INT(1) DEFAULT 1,"
                + "del_status INT(1) DEFAULT 1, PRIMARY KEY (id_user))");
        DaoUsers du = new DaoUsers(db);
        du.insert(new Users(1, "admin", "admin", 1, 1));
        du.insert(new Users("Kseniya", "1234"));

        db.update("CREATE TABLE cars ( id_car INT AUTO_INCREMENT," + "model VARCHAR(30), year INT(4),"
                + "kpp VARCHAR (15), price INT(4)," + "rent_status INT (1) DEFAULT 1, PRIMARY KEY (id_car))");
        DaoCars dc = new DaoCars(db);
        dc.insert(new Cars(1, "Mercedes-Benz E63", 2020, "automatic", 500, 1));
        dc.insert(new Cars(2, "Brabus Rocket 900", 2019, "automatic", 550, 1));
        dc.insert(new Cars(3, "BMW M5 G-Power", 2019, "automatic", 600, 1));
        dc.insert(new Cars(4, "Cadillac CTS-V", 2021, "automatic", 700, 1));
        dc.insert(new Cars(5, "Mercedes-Benz CLS", 2016, "automatic", 500, 1));
        dc.insert(new Cars(6, "Porsche Panamera", 2022, "automatic", 580, 1));
        dc.insert(new Cars(7, "Tesla Model S", 2018, "automatic", 620, 1));
        dc.insert(new Cars(8, "Mercedes-AMG", 2019, "automatic", 510, 1));
        dc.insert(new Cars(9, "BMW M6 G-Power", 2021, "automatic", 660, 1));
        dc.insert(new Cars(10, "Audi RS6 Avant", 2021, "automatic", 670, 1));

        db.update("CREATE TABLE orders (id_order INT AUTO_INCREMENT," + "id_user INT, id_car INT,"
                + "termin INT(2), od_status VARCHAR (15), cost INT(10),"
                + "foreign key (id_user) references users (id_user)," + "foreign key (id_car) references cars (id_car),"
                + "PRIMARY KEY (id_order))");

    }

    public static void deleteDB(String url, String name, String login, String password) throws SQLException {
        DB db = new DB(url, "", login, password);
        db.update("DROP DATABASE " + name);
    }

}