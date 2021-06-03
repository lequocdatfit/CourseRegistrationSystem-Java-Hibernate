package model;

import java.util.Objects;

public class Monhoc {
    private String maMh;
    private String tenMh;
    private int soTinChi;

    public Monhoc() {
    }

    public Monhoc(String maMh, String tenMh, int soTinChi) {
        this.maMh = maMh;
        this.tenMh = tenMh;
        this.soTinChi = soTinChi;
    }

    public String getMaMh() {
        return maMh;
    }

    public void setMaMh(String maMh) {
        this.maMh = maMh;
    }

    public String getTenMh() {
        return tenMh;
    }

    public void setTenMh(String tenMh) {
        this.tenMh = tenMh;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monhoc monhoc = (Monhoc) o;
        return Objects.equals(maMh, monhoc.maMh) && Objects.equals(tenMh, monhoc.tenMh) && Objects.equals(soTinChi, monhoc.soTinChi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMh, tenMh, soTinChi);
    }

    @Override
    public String toString() {
        return this.tenMh;
    }
}
