package by.car.entity;

public class Cars {

    private int id_car;
    private String model;
    private int year;
    private String kpp;
    private int price;
    private int rent_status;

    public Cars(int id_car, String model, int year, String kpp, int price, int rent_status) {
        this.id_car=id_car;
        this.model = model;
        this.year = year;
        this.kpp = kpp;
        this.price = price;
        this.rent_status = rent_status;
    }

    public Cars(String model, int year, String kpp, int price) {
        this.model = model;
        this.year = year;
        this.kpp = kpp;
        this.price = price;
        rent_status = 1;
    }

    public Cars(int rent_status) {
        this.rent_status = rent_status;
    }

    public Cars() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_car;
        result = prime * result + ((kpp == null) ? 0 : kpp.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + price;
        result = prime * result + rent_status;
        result = prime * result + year;
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
        if (!(obj instanceof Cars)) {
            return false;
        }
        Cars other = (Cars) obj;
        if (id_car != other.id_car) {
            return false;
        }
        if (kpp == null) {
            if (other.kpp != null) {
                return false;
            }
        } else if (!kpp.equals(other.kpp)) {
            return false;
        }
        if (model == null) {
            if (other.model != null) {
                return false;
            }
        } else if (!model.equals(other.model)) {
            return false;
        }
        if (price != other.price) {
            return false;
        }
        if (rent_status != other.rent_status) {
            return false;
        }
        if (year != other.year) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cars [id_car=" + id_car + ", model=" + model + ", year=" + year + ", kpp=" + kpp + ", price=" + price
                + ", rent_status=" + rent_status + "]";
    }

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRent_status() {
        return rent_status;
    }

    public void setRent_status(int rent_status) {
        this.rent_status = rent_status;
    }


}
