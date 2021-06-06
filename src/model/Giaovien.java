package model;

import controller.GiaoVienDAO;

import java.util.Objects;

public class Giaovien {
    private String maGv;
    private String hoVaTen;


    public String getMaGv() {
        return maGv;
    }

    public void setMaGv(String maGv) {
        this.maGv = maGv;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giaovien giaovien = (Giaovien) o;
        return Objects.equals(maGv, giaovien.maGv) && Objects.equals(hoVaTen, giaovien.hoVaTen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maGv, hoVaTen);
    }

    @Override
    public String toString() {
        return this.getHoVaTen();
    }
}
