package model;

import java.io.Serializable;
import java.util.Objects;

public class Hocphan implements Serializable {
    private String id;
    private String ngayTrongTuan;
    private String ca;
    private Integer slot;
    private String phongHoc;
    private String tenHk;
    private String namHoc;
    private Monhoc monHoc;

    public Monhoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(Monhoc monHoc) {
        this.monHoc = monHoc;
    }

    private Giaovien giaoVien;

    public Giaovien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(Giaovien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public Hocphan() {
    }

    public Hocphan(String id, String ngayTrongTuan, String ca, Integer slot, String phongHoc, String tenHk, String namHoc, Monhoc monHoc, Giaovien giaoVien) {
        this.id = id;
        this.ngayTrongTuan = ngayTrongTuan;
        this.ca = ca;
        this.slot = slot;
        this.phongHoc = phongHoc;
        this.tenHk = tenHk;
        this.namHoc = namHoc;
        this.monHoc = monHoc;
        this.giaoVien = giaoVien;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public int hashCode() {
        return Objects.hash(id, monHoc, giaoVien, ngayTrongTuan, ca, slot, phongHoc, tenHk, namHoc);
    }
}
