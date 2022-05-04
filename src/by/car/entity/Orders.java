package by.car.entity;

public class Orders {

    private int id_order;
    private int id_user;
    private int id_car;
    private int termin;
    private String od_status;
    private int cost;

    public Orders(int id_order, int id_user, int id_car, int termin, String od_status, int cost) {
        this.id_order = id_order;
        this.id_user = id_user;
        this.id_car = id_car;
        this.termin = termin;
        this.od_status = od_status;
        this.cost = cost;
    }

    public Orders(int id_order, String od_status) {
        this.id_order = id_order;
        this.od_status = od_status;
    }

    public Orders(int id_user, int id_car, int termin, int cost) {
        this.id_user = id_user;
        this.id_car = id_car;
        this.termin = termin;
        this.od_status = "wait";
        this.cost = cost;
    }

    public Orders(int id_user, int id_car, int termin) {
        this.id_user = id_user;
        this.id_car = id_car;
        this.termin = termin;
        this.od_status = "wait";
    }

    public Orders() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cost;
        result = prime * result + id_car;
        result = prime * result + id_order;
        result = prime * result + id_user;
        result = prime * result + ((od_status == null) ? 0 : od_status.hashCode());
        result = prime * result + termin;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) obj;
        if (cost != other.cost) {
            return false;
        }
        if (id_car != other.id_car) {
            return false;
        }
        if (id_order != other.id_order) {
            return false;
        }
        if (id_user != other.id_user) {
            return false;
        }
        if (od_status == null) {
            if (other.od_status != null) {
                return false;
            }
        } else if (!od_status.equals(other.od_status)) {
            return false;
        }
        if (termin != other.termin) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orders [id_order=" + id_order + ", id_user=" + id_user + ", id_car=" + id_car + ", termin=" + termin
                + ", od_status=" + od_status + ", cost=" + cost + "]";
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public int getTermin() {
        return termin;
    }

    public void setTermin(int termin) {
        this.termin = termin;
    }

    public String getOd_status() {
        return od_status;
    }

    public void setOd_status(String od_status) {
        this.od_status = od_status;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
