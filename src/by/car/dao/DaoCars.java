package by.car.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.car.db.DB;
import by.car.entity.Cars;

public class DaoCars implements DaoInterface<Cars> {

    private DB db;

    public DaoCars(DB db) {
        this.db = db;
    }

    @Override
    public void insert(Cars ob) {

        try {

            PreparedStatement ps;
            ps = db.getCn().prepareStatement("INSERT INTO cars VALUES (?,?,?,?,?,?)");
            ps.setInt(1, ob.getId_car());
            ps.setString(2, ob.getModel());
            ps.setInt(3, ob.getYear());
            ps.setString(4, ob.getKpp());
            ps.setInt(5, ob.getPrice());
            ps.setInt(6, ob.getRent_status());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        try {
            PreparedStatement ps = db.getCn().prepareStatement("UPDATE cars SET rent_status = 2 WHERE id_car = " + id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cars ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement(
                    "UPDATE cars SET model = ?, year = ?, kpp = ?, price =?, rent_status = ? WHERE id_car = ?");
            ps.setString(1, ob.getModel());
            ps.setInt(2, ob.getYear());
            ps.setString(3, ob.getKpp());
            ps.setInt(4, ob.getPrice());
            ps.setInt(5, ob.getRent_status());
            ps.setInt(6, ob.getId_car());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cars get(int id) {
        ResultSet rs = db.query("SELECT * FROM cars WHERE id_car = " + id);

        try {
            if (rs.next()) {
                return new Cars(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),
                        rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
