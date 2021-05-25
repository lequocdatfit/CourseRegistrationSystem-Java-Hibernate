package model;

import java.util.Objects;

public class Hocphan {
    private String id;
    private String maMh;
    private String maGv;
    private String ngayTrongTuan;
    private String ca;
    private Integer slot;
    private String phongHoc;
    private String tenHk;
    private String namHoc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaMh() {
        return maMh;
    }

    public void setMaMh(String maMh) {
        this.maMh = maMh;
    }

    public String getMaGv() {
        return maGv;
    }

    public void setMaGv(String maGv) {
        this.maGv = maGv;
    }

    public String getNgayTrongTuan() {
        return ngayTrongTuan;
    }

    public void setNgayTrongTuan(String ngayTrongTuan) {
        this.ngayTrongTuan = ngayTrongTuan;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
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
        Hocphan hocphan = (Hocphan) o;
        return Objects.equals(id, hocphan.id) && Objects.equals(maMh, hocphan.maMh) && Objects.equals(maGv, hocphan.maGv) && Objects.equals(ngayTrongTuan, hocphan.ngayTrongTuan) && Objects.equals(ca, hocphan.ca) && Objects.equals(slot, hocphan.slot) && Objects.equals(phongHoc, hocphan.phongHoc) && Objects.equals(tenHk, hocphan.tenHk) && Objects.equals(namHoc, hocphan.namHoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maMh, maGv, ngayTrongTuan, ca, slot, phongHoc, tenHk, namHoc);
    }
}
