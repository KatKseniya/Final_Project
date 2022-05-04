package by.car.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.car.db.DB;
import by.car.entity.Orders;

public class DaoOrders implements DaoInterface<Orders> {

    private DB db;

    public DaoOrders(DB db) {
        this.db = db;
    }

    @Override
    public void insert(Orders ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?,?)");
            ps.setInt(1, ob.getId_order());
            ps.setInt(2, ob.getId_user());
            ps.setInt(3, ob.getId_car());
            ps.setInt(4, ob.getTermin());
            ps.setString(5, ob.getOd_status());
            ps.setInt(6, ob.getCost());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = db.getCn()
                    .prepareStatement("UPDATE orders SET od_status = 'delete' WHERE id_order = " + id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Orders ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement(
                    "UPDATE orders SET id_user = ?, id_car = ?, termin = ?, od_status = ?, cost= ? WHERE id_order = ?");
            ps.setInt(1, ob.getId_user());
            ps.setInt(2, ob.getId_car());
            ps.setInt(3, ob.getTermin());
            ps.setString(4, ob.getOd_status());
            ps.setInt(5, ob.getCost());
            ps.setInt(6, ob.getId_order());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orders get(int id) {
        ResultSet rs = db.query("SELECT * FROM orders WHERE id_order = " + id);
        try {
            if (rs.next()) {
                return new Orders(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
