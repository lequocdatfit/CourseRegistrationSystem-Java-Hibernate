package model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.Objects;

public class SinhvienHocphan {
    @Id
    private String idSinhVien;
    @OneToOne
    @JoinColumn(name = "idSinhVien")
    @MapsId
    private Sinhvien sinhVien;
    @Id
    private String idHocPhan;
    @OneToOne
    @JoinColumn(name = "idHocPhan")
    @MapsId
    private Hocphan hocPhan;
    private Date ngayDangKy;

    public SinhvienHocphan() {
    }

    public SinhvienHocphan(String idSinhVien, Sinhvien sinhVien, String idHocPhan, Hocphan hocPhan) {
        this.idSinhVien = idSinhVien;
        this.sinhVien = sinhVien;
        this.idHocPhan = idHocPhan;
        this.hocPhan = hocPhan;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public Sinhvien getSinhVien() {
        return sinhVien;
    }

    public Hocphan getHocPhan() {
        return hocPhan;
    }

    public void setSinhVien(Sinhvien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public void setHocPhan(Hocphan hocPhan) {
        this.hocPhan = hocPhan;
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
        SinhvienHocphan that = (SinhvienHocphan) o;
        return Objects.equals(idSinhVien, that.idSinhVien) && Objects.equals(idHocPhan, that.idHocPhan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSinhVien, idHocPhan);
    }
}
