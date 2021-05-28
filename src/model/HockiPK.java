package model;

import java.io.Serializable;
import java.util.Objects;

public class HockiPK implements Serializable {
    private String tenHk;
    private String namHoc;

    public HockiPK() {
    }

    public HockiPK(String tenHk, String namHoc) {
        this.tenHk = tenHk;
        this.namHoc = namHoc;
    }

    public String getTenHk() {
        return tenHk;
    }

    public void setTenHk(String tenHk) {
        this.tenHk = tenHk;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HockiPK hockiPK = (HockiPK) o;
        return Objects.equals(tenHk, hockiPK.tenHk) && Objects.equals(namHoc, hockiPK.namHoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenHk, namHoc);
    }
}
