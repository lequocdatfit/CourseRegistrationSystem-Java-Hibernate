package model;

import java.io.Serializable;
import java.util.Objects;

public class SinhvienHocphanPK implements Serializable {
    private String idSinhVien;
    private String idHocPhan;

    public SinhvienHocphanPK() {
    }

    public SinhvienHocphanPK(String idSinhVien, String idHocPhan) {
        this.idSinhVien = idSinhVien;
        this.idHocPhan = idHocPhan;
    }

    public String getIdSinhVien() {
        return idSinhVien;
    }

    public void setIdSinhVien(String idSinhVien) {
        this.idSinhVien = idSinhVien;
    }

    public String getIdHocPhan() {
        return idHocPhan;
    }

    public void setIdHocPhan(String idHocPhan) {
        this.idHocPhan = idHocPhan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinhvienHocphanPK that = (SinhvienHocphanPK) o;
        return Objects.equals(idSinhVien, that.idSinhVien) && Objects.equals(idHocPhan, that.idHocPhan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSinhVien, idHocPhan);
    }
}
