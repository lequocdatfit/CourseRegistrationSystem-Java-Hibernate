package model;

import java.util.Date;
import java.util.Objects;

public class Kidangkihocphan {
    private String id;
    private String tenHocKi;
    private String namHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public Kidangkihocphan() {
    }

    public Kidangkihocphan(String id, String tenHocKi, String namHoc, Date ngayBatDau, Date ngayKetThuc) {
        this.id = id;
        this.tenHocKi = tenHocKi;
        this.namHoc = namHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenHocKi() {
        return tenHocKi;
    }

    public void setTenHocKi(String tenHocKi) {
        this.tenHocKi = tenHocKi;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kidangkihocphan that = (Kidangkihocphan) o;
        return Objects.equals(id, that.id) && Objects.equals(tenHocKi, that.tenHocKi) && Objects.equals(namHoc, that.namHoc) && Objects.equals(ngayBatDau, that.ngayBatDau) && Objects.equals(ngayKetThuc, that.ngayKetThuc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenHocKi, namHoc, ngayBatDau, ngayKetThuc);
    }
}
