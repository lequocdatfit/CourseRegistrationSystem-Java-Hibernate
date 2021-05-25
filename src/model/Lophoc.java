package model;

import java.util.Objects;

public class Lophoc {
    private String maLop;
    private Integer tongNam;
    private Integer tongNu;

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
