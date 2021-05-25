package model;

import java.sql.Date;
import java.util.Objects;

public class Hocki {
    private String tenHk;
    private String namHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Boolean hkHienTai;

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

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Boolean getHkHienTai() {
        return hkHienTai;
    }

    public void setHkHienTai(Boolean hkHienTai) {
        this.hkHienTai = hkHienTai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hocki hocki = (Hocki) o;
        return Objects.equals(tenHk, hocki.tenHk) && Objects.equals(namHoc, hocki.namHoc) && Objects.equals(ngayBatDau, hocki.ngayBatDau) && Objects.equals(ngayKetThuc, hocki.ngayKetThuc) && Objects.equals(hkHienTai, hocki.hkHienTai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenHk, namHoc, ngayBatDau, ngayKetThuc, hkHienTai);
    }
}
