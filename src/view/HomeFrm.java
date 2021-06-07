package view;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeFrm extends JFrame{
    private JPanel mainPanel;
    private JTable tblHocPhanDaDK;
    private JButton btnDKHP;
    private JButton btnDeleteDK;
    private JButton btnSignOut;
    private JTabbedPane tabbedPane1;
    private JTextField txtId;
    private JTextField txtGioiTinh;
    private JTextField txtMaSV;
    private JTextField txtNamNhapHoc;
    private JTextField txtLopHoc;
    private JButton btnEditAccount;
    private JButton btnChangePass;
    private JTextField txtHoVaTen;
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

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Thread updateData = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("State change!");
                        int index = tabbedPane1.getSelectedIndex();
                        if(index == 0) {
                            // Dang ky hoc phan
                            ls_svhp = SinhVienHocPhanDAO.layDanhSachHocPhanSVDangKy(currentSV.getId());
                            updateHocPhanDKTable();
                        } else if(index == 1) {
                            // Tai khoan
                            updateInfoSinhVienUI();
                        }
                    }
                });
                updateData.start();
            }
        });


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
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Bạn không thể hủy học phần khi vì kì đăng ký học phần đã kết thúc!");
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
        btnEditAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditSinhVienInfoFrm frm = new EditSinhVienInfoFrm(HomeFrm.this, rootPaneCheckingEnabled);
                frm.setEditData(currentSV);
                frm.setVisible(true);
            }
        });
        btnChangePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeSVPasswordFrm frm = new ChangeSVPasswordFrm(HomeFrm.this, rootPaneCheckingEnabled, currentSV);
                frm.setVisible(true);
            }
        });
    }

    public void updateHocPhanDKTable() {
        hocPhanDaDKModel.setRowCount(0);
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

    public void updateInfoSinhVienUI() {
        if(currentSV != null) {
            txtId.setText(currentSV.getId());
            txtMaSV.setText(currentSV.getMaSv());
            txtHoVaTen.setText(currentSV.getHoVaTen());
            txtGioiTinh.setText(currentSV.getGioiTinh() ? "Nam": "Nữ");
            txtNamNhapHoc.setText(new SimpleDateFormat("dd/MM/yyyy").format(currentSV.getNamNhapHoc()));
            txtLopHoc.setText(currentSV.getMaLop());
        }
    }

    public boolean capNhatThongTinSV(Sinhvien sv) {
        if(SinhVienDAO.capNhatSinhVien(sv)) {
            currentSV = SinhVienDAO.layThongTinSinhVienBangID(sv.getId());
            updateInfoSinhVienUI();
            return true;
        }
        return false;
    }

}
