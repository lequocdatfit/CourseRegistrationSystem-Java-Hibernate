package model;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Sinhvien implements Serializable {
    @Id
    private String id;
    private String maSv;
    private String hoVaTen;
    private Boolean gioiTinh;
    private Date namNhapHoc;
    private String matKhau;
    private String maLop;

    public Sinhvien() {
    }

    public Sinhvien(String id, String maSv, String hoVaTen, Boolean gioiTinh, Date namNhapHoc, String matKhau, String maLop) {
        this.id = id;
        this.maSv = maSv;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.namNhapHoc = namNhapHoc;
        this.matKhau = matKhau;
        this.maLop = maLop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNamNhapHoc() {
        return namNhapHoc;
    }

    public void setNamNhapHoc(Date namNhapHoc) {
        this.namNhapHoc = namNhapHoc;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sinhvien sinhvien = (Sinhvien) o;
        return Objects.equals(id, sinhvien.id) && Objects.equals(maSv, sinhvien.maSv) && Objects.equals(hoVaTen, sinhvien.hoVaTen) && Objects.equals(gioiTinh, sinhvien.gioiTinh) && Objects.equals(namNhapHoc, sinhvien.namNhapHoc) && Objects.equals(matKhau, sinhvien.matKhau) && Objects.equals(maLop, sinhvien.maLop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maSv, hoVaTen, gioiTinh, namNhapHoc, matKhau, maLop);
    }
}
