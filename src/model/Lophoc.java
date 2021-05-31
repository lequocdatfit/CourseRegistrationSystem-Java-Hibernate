package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Lophoc {
    private String maLop;
    private Integer tongNam;
    private Integer tongNu;
    private Set<Sinhvien> ls_sinhvien = new HashSet<Sinhvien>(0);

    public Lophoc() {
    }

    public Lophoc(String maLop, Integer tongNam, Integer tongNu) {
        this.maLop = maLop;
        this.tongNam = tongNam;
        this.tongNu = tongNu;
    }

    public Lophoc(String maLop, Integer tongNam, Integer tongNu, Set<Sinhvien> ls_sinhvien) {
        this.maLop = maLop;
        this.tongNam = tongNam;
        this.tongNu = tongNu;
        this.ls_sinhvien = ls_sinhvien;
    }

    public Set<Sinhvien> getLs_sinhvien() {
        return ls_sinhvien;
    }

    public void setLs_sinhvien(Set<Sinhvien> ls_sinhvien) {
        this.ls_sinhvien = ls_sinhvien;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public Integer getTongNam() {
        return tongNam;
    }

    public void setTongNam(Integer tongNam) {
        this.tongNam = tongNam;
    }

    public Integer getTongNu() {
        return tongNu;
    }

    public void setTongNu(Integer tongNu) {
        this.tongNu = tongNu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lophoc lophoc = (Lophoc) o;
        return Objects.equals(maLop, lophoc.maLop) && Objects.equals(tongNam, lophoc.tongNam) && Objects.equals(tongNu, lophoc.tongNu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLop, tongNam, tongNu);
    }
}
