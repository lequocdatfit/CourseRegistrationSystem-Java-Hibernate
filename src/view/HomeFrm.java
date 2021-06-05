package view;

import controller.HocKiDAO;
import controller.HocPhanDAO;
import controller.KiDangKyDAO;
import controller.SinhVienHocPhanDAO;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class HomeFrm extends JFrame{
    private JPanel mainPanel;
    private JTable tblHocPhanDaDK;
    private JButton btnDKHP;
    private JButton btnDeleteDK;
    private JButton btnSignOut;
    private DefaultTableModel hocPhanDaDKModel;
    private List<SinhvienHocphan> ls_svhp;
    private Sinhvien currentSV;
    private Hocki currentSemester;
    private LoginFrm loginFrm;

    public HomeFrm(String title, Sinhvien sv, Frame login) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        currentSV = sv;
        loginFrm = (LoginFrm) login;
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
                    DangKyHPFrm frm = new DangKyHPFrm(HomeFrm.this, rootPaneCheckingEnabled, currentSemester, currentSV, ls_svhp);
                    frm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Không có kì đăng ký học phần nào đang diễn ra!");
                }
            }
        });
        btnDeleteDK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa học phần
                int index = tblHocPhanDaDK.getSelectedRow();
                if(ls_svhp.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Không có học phần nào để xóa!");
                } else if (index == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Hãy chọn một học phần trên bảng!");
                } else {
                    int output = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn hủy đăng ký học phần đã chọn ?",
                            "Cảnh báo", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if(output == JOptionPane.YES_OPTION) {
                        if(huyDangKyHocPhan(ls_svhp.get(index))) {
                            JOptionPane.showMessageDialog(rootPane, "Hủy học phần thành công!");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Hủy học phần thất bại!");
                        }
                    } else {
                        return;
                    }
                }
            }
        });
        btnSignOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                loginFrm.setVisible(true);
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

    public boolean dangKyHocPhan(List<SinhvienHocphan> svh) {
        if(SinhVienHocPhanDAO.dangKyNhieuHocPhan(svh)) {
            ls_svhp = SinhVienHocPhanDAO.layDanhSachHocPhanSVDangKy(currentSV.getId());
            hocPhanDaDKModel.setRowCount(0);
            updateHocPhanDKTable();
            return true;
        }
        return false;
    }

    public boolean huyDangKyHocPhan(SinhvienHocphan h) {
        if(SinhVienHocPhanDAO.huyDangKyHocPhan(h)) {
            ls_svhp = SinhVienHocPhanDAO.layDanhSachHocPhanSVDangKy(currentSV.getId());
            hocPhanDaDKModel.setRowCount(0);
            updateHocPhanDKTable();
            return true;
        }
        return false;
    }
}
