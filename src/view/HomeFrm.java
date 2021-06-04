package view;

import controller.HocKiDAO;
import controller.HocPhanDAO;
import controller.KiDangKyDAO;
import controller.SinhVienHocPhanDAO;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class HomeFrm extends JFrame{
    private JPanel mainPanel;
    private JTable tblHocPhanDaDK;
    private JButton btnDKHP;
    private JButton btnDeleteDK;
    private DefaultTableModel hocPhanDaDKModel;
    private List<SinhvienHocphan> ls_svhp;
    private Sinhvien currentSV;
    private Hocki currentSemester;

    public HomeFrm(String title, Sinhvien sv) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(600, 650);
        this.setLocationRelativeTo(null);
        currentSV = sv;

        hocPhanDaDKModel = (DefaultTableModel) tblHocPhanDaDK.getModel();
        hocPhanDaDKModel.setColumnIdentifiers(new Object[] {
                "STT", "Mã học phần", "Tên môn học", "Giáo viên", "Ngày học"
        });
        ls_svhp = SinhVienHocPhanDAO.layDanhSachHocPhanSVDangKy(currentSV.getId());
        updateHocPhanDKTable();


        btnDKHP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra có kì đăng ký nào hay không?
                boolean isAvailable = false;
                currentSemester = HocKiDAO.layHocKiHienTai();
                List<Kidangkihocphan> ls = KiDangKyDAO.layDanhSachKiDangKyCuaHocKi(currentSemester);
                Date today = new Date();
                for (Kidangkihocphan k : ls) {
                    if(today.after(k.getNgayBatDau()) && today.before(k.getNgayKetThuc())) {
                        isAvailable = true;
                        break;
                    }
                }
                if(isAvailable) {
                    DangKyHPFrm frm = new DangKyHPFrm(HomeFrm.this, rootPaneCheckingEnabled, currentSemester);
                    frm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Không có kì đăng ký học phần nào đang diễn ra!");
                }
            }
        });
    }

    public void updateHocPhanDKTable() {
        if(ls_svhp != null) {
            for (int i=0; i<ls_svhp.size(); i++) {
                SinhvienHocphan svh = ls_svhp.get(i);
                Sinhvien s = svh.getSinhVien();
                Hocphan h = HocPhanDAO.layThongTinHocPhan(svh.getHocPhan().getId());
                hocPhanDaDKModel.addRow(new Object[] {
                        i+1, h.getId(), h.getMonHoc().getTenMh(), h.getGiaoVien().getHoVaTen(),
                        h.getNgayTrongTuan() + ", " + h.getCa()
                });
            }
        }
    }

}
