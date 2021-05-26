package model;

import java.util.Objects;

public class Giaovu {
    private String maGiaoVu;
    private String hoVaTen;
    private String matKhau;

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
