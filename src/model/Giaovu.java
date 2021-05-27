package model;

import java.util.Date;
import java.util.Objects;

public class Giaovu {
    private String maGiaoVu;
    private String hoVaTen;
    private String matKhau;
    private Date ngaySinh;
    private String dienThoai;

    public Giaovu() {
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public Giaovu(String maGiaoVu, String hoVaTen, String matKhau, Date ngaySinh, String dienThoai) {
        this.maGiaoVu = maGiaoVu;
        this.hoVaTen = hoVaTen;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.dienThoai = dienThoai;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getMaGiaoVu() {
        return maGiaoVu;
    }

    public void setMaGiaoVu(String maGiaoVu) {
        this.maGiaoVu = maGiaoVu;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giaovu giaovu = (Giaovu) o;
        return Objects.equals(maGiaoVu, giaovu.maGiaoVu) && Objects.equals(hoVaTen, giaovu.hoVaTen) && Objects.equals(matKhau, giaovu.matKhau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maGiaoVu, hoVaTen, matKhau);
    }
}
