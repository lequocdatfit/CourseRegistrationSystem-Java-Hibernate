package view;

import controller.GiaoVuDAO;
import controller.HocKiDAO;
import controller.LopHocDAO;
import controller.MonhocDAO;
import model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class HomeGVFrm extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTable giaovutbl;
    private JButton thêmGiáoVụButton;
    private JButton sửaGiáoVụButton;
    private JButton xóaGiáoVụButton;
    private JTable tblmonhoc;
    private JButton btnAddMonHoc;
    private JButton sửaMônHọcButton;
    private JButton xóaMônHọcButton;
    private JTable tblHocKi;
    private JButton addHocKi;
    private JButton btnXoaHocKi;
    private JButton btnSelectHocKi;
    private JLabel txtHocKiHienTai;
    private JTable tblLopHoc;
    private JButton btnAddClass;
    private JButton btnDeleteClass;
    private DefaultTableModel giaovuModel;
    private DefaultTableModel monhocModel;
    private DefaultTableModel hockiModel;
    private DefaultTableModel lophocModel;


    private List<Sinhvien> ls_sinhvien;
    private List<Lophoc> ls_lophoc;
    private List<Giaovu> ls_giaovu;
    private List<Monhoc> ls_monhoc;
    private List<Hocki> ls_hocki;
    private Hocki currentSemester;
    private int selectedIndex;

    public HomeGVFrm(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 650);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);

        ls_giaovu = GiaoVuDAO.LayDanhSachGiaoVu();
        ls_monhoc = null;

        giaovuModel = (DefaultTableModel) giaovutbl.getModel();
        giaovuModel.setColumnIdentifiers(new Object[] {
                "STT", "Mã giáo vụ", "Họ và tên", "Ngày sinh", "Điện thoại"
        });

        this.updateGiaovuTable();
        monhocModel = (DefaultTableModel) tblmonhoc.getModel();
        monhocModel.setColumnIdentifiers(new Object[] {
                "STT", "Mã môn học", "Tên Môn Học", "Số tín chỉ"
        });

        hockiModel = (DefaultTableModel) tblHocKi.getModel();
        hockiModel.setColumnIdentifiers(new Object[] {
                "STT", "Tên học kì", "Năm học", "Ngày bắt đầu", "Ngày kết thúc"
        });

        lophocModel = (DefaultTableModel) tblLopHoc.getModel();
        lophocModel.setColumnIdentifiers(new Object[] {
                "STT", "Mã lớp học", "Tổng sinh viên", "Tổng nam", "Tổng nữ"
        });

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Thread updateData = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("State change!");
                        int index = tabbedPane1.getSelectedIndex();
                        if(index == 0) {
                            ls_giaovu = GiaoVuDAO.LayDanhSachGiaoVu();
                            giaovuModel.setRowCount(0);
                            updateGiaovuTable();
                        } else if(index == 1) {

                            ls_monhoc = MonhocDAO.LayDanhSachMonHoc();

                            monhocModel.setRowCount(0);
                            updateMonhocTable();
                        } else if(index == 2) {
                            currentSemester = HocKiDAO.layHocKiHienTai();
                            txtHocKiHienTai.setText(currentSemester.getTenHk() + ' ' + currentSemester.getNamHoc());
                            ls_hocki = HocKiDAO.layDanhSachHocKi();
                            hockiModel.setRowCount(0);
                            updateHockiTable();
                        } else if(index == 3) {
                            ls_lophoc = LopHocDAO.layDanhSachLopHoc();
                            lophocModel.setRowCount(0);
                            updateLopHocTable();
                        }
                    }
                });
                updateData.start();
            }
        });

        thêmGiáoVụButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputGiaovuFrm input = new InputGiaovuFrm(HomeGVFrm.this, rootPaneCheckingEnabled);
                input.setVisible(true);
            }
        });


        sửaGiáoVụButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = giaovutbl.getSelectedRow();
                if(ls_giaovu.size() == 0) {
                    JOptionPane.showMessageDialog(mainPanel, "Không có giáo vụ nào để sửa");
                } else if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(mainPanel, "Hãy chọn một giáo vụ trên bảng rồi chọn Sửa");
                } else {
                    EditGiaovuFrm edit = new EditGiaovuFrm(HomeGVFrm.this, rootPaneCheckingEnabled);
                    edit.setEditData(ls_giaovu.get(selectedIndex));
                    edit.setVisible(true);
                }
            }
        });
        xóaGiáoVụButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = giaovutbl.getSelectedRow();
                if(ls_giaovu.size() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Không có giáo vụ nào để xóa !");
                } else if(selectedIndex == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Hãy chọn giáo vụ trên bảng rồi chọn xóa !");
                } else {
                    int output = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa giáo vụ này không ?",
                            "Cảnh báo", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if(output == JOptionPane.YES_OPTION) {
                        if(HomeGVFrm.this.xoaGiaoVu(ls_giaovu.get(selectedIndex))) {
                            JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Xóa thất bại!");
                        }
                    } else if(output == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }
        });
        btnAddMonHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputMonHocFrm input = new InputMonHocFrm(HomeGVFrm.this, rootPaneCheckingEnabled);
                input.setVisible(true);
            }
        });

        sửaMônHọcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblmonhoc.getSelectedRow();
                if(ls_monhoc.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Không có môn học nào để sửa !");
                } else if(index == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn môn học sau đó chọn sửa !");
                } else {
                    EditMonhocFrm edit = new EditMonhocFrm(HomeGVFrm.this, rootPaneCheckingEnabled);
                    edit.setEditData(ls_monhoc.get(index));
                    edit.setVisible(true);
                }
            }
        });
        xóaMônHọcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblmonhoc.getSelectedRow();
                if(ls_monhoc.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Không có môn học nào để xóa!");
                } else if(index == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn môn học sau đó chọn xóa!");
                } else {
                    int output = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xóa môn học này ?", "Cảnh báo",
                            JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if(output == JOptionPane.YES_OPTION) {
                        if(HomeGVFrm.this.xoaMonHoc(ls_monhoc.get(index))) {
                            JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Xóa thất bại!");
                        }
                    } else if(output == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }
        });
        addHocKi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputHocKiFrm input = new InputHocKiFrm(HomeGVFrm.this, rootPaneCheckingEnabled);
                input.setVisible(true);
            }
        });

        btnXoaHocKi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblHocKi.getSelectedRow();
                if(ls_hocki.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Không có học kì nào để xóa!");
                } else if(index == -1) {
                    JOptionPane.showMessageDialog(mainPanel, "Hãy chọn một học kì trên bảng rồi chọn xóa!");
                } else {
                    int output = JOptionPane.showConfirmDialog(mainPanel, "Bạn có chắc muốn xóa học kì này?",
                            "Cảnh báo", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if(output == JOptionPane.YES_OPTION) {
                        if(HomeGVFrm.this.xoaHocKi(ls_hocki.get(index))) {
                            JOptionPane.showMessageDialog(mainPanel, "Xóa thành công!");
                        } else {
                            JOptionPane.showMessageDialog(mainPanel, "Xóa thất bại!");
                        }
                    } else if(output == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }
        });
        btnSelectHocKi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectCurrentHocKi selectFrm = new SelectCurrentHocKi(HomeGVFrm.this, rootPaneCheckingEnabled, ls_hocki.toArray(new Hocki[ls_hocki.size()]));
                selectFrm.setVisible(true);
            }
        });
        btnAddClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputLopHocFrm input = new InputLopHocFrm(HomeGVFrm.this, rootPaneCheckingEnabled);
                input.setVisible(true);
            }
        });
        btnDeleteClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblLopHoc.getSelectedRow();
                if(ls_lophoc.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Không có lớp học nào để xóa!");
                } else if(index == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Chọn một lớp học trên bảng sau đó chọn xóa!");
                } else {
                    int output = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xóa lớp học",
                            "Cảnh báo", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if(output == JOptionPane.YES_OPTION) {
                        if(HomeGVFrm.this.xoaLopHoc(ls_lophoc.get(index))) {
                            JOptionPane.showMessageDialog(rootPane, "Xóa lớp học thành công!");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Xóa lớp học thất bại!");
                        }
                    } else if(output == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }
        });
    }

    public boolean themGiaoVu(Giaovu gv) {
        if(GiaoVuDAO.themGiaoVu(gv)) {
            ls_giaovu.add(gv);
            giaovuModel.setRowCount(0);
            updateGiaovuTable();
            return true;
        } else {
            return false;
        }
    }

    public boolean SuaGiaovu(Giaovu gv) {
        if(GiaoVuDAO.suaThongTinGiaoVu(gv)) {
            ls_giaovu.clear();
            ls_giaovu = GiaoVuDAO.LayDanhSachGiaoVu();
            giaovuModel.setRowCount(0);
            updateGiaovuTable();
            return true;
        }
        return false;
    }

    public boolean xoaGiaoVu(Giaovu gv) {
        if(GiaoVuDAO.xoaGiaoVu(gv)) {
            ls_giaovu.clear();
            ls_giaovu = GiaoVuDAO.LayDanhSachGiaoVu();
            giaovuModel.setRowCount(0);
            updateGiaovuTable();
            return true;
        }
        return false;
    }

    public boolean themMonHoc(Monhoc m) {
        if(MonhocDAO.themMonHoc(m)) {
            ls_monhoc.add(m);
            monhocModel.setRowCount(0);
            updateMonhocTable();
            return true;
        }
        return false;
    }

    public boolean suaMonHoc(Monhoc m) {
        if(MonhocDAO.capNhatMonHoc(m)) {
            ls_monhoc = MonhocDAO.LayDanhSachMonHoc();
            monhocModel.setRowCount(0);
            updateMonhocTable();
            return true;
        }
        return false;
    }

    public boolean xoaMonHoc(Monhoc m) {
        if(MonhocDAO.xoaThongTinMonHoc(m)) {
            ls_monhoc = MonhocDAO.LayDanhSachMonHoc();
            monhocModel.setRowCount(0);
            updateMonhocTable();
            return true;
        }
        return false;
    }

    public boolean themHocKi(Hocki h) {
        if(HocKiDAO.themHocKi(h)) {
            ls_hocki = HocKiDAO.layDanhSachHocKi();
            hockiModel.setRowCount(0);
            updateHockiTable();
            return true;
        }
        return false;
    }

    public boolean xoaHocKi(Hocki h) {
        if(HocKiDAO.xoaHocKi(h)) {
            ls_hocki = HocKiDAO.layDanhSachHocKi();
            hockiModel.setRowCount(0);
            updateHockiTable();
            return true;
        }
        return false;
    }

    public boolean setCurrentSemester(Hocki h) {
        if(HocKiDAO.setCurrentSemester(h)) {
            // cap nhat lai ui
            currentSemester = HocKiDAO.layHocKiHienTai();
            txtHocKiHienTai.setText(currentSemester.getTenHk() + ' ' + currentSemester.getNamHoc());
            return true;
        } else {
            return false;
        }
    }

    public boolean themLopHoc(Lophoc l) {
        if(LopHocDAO.themLopHoc(l)) {
            ls_lophoc = LopHocDAO.layDanhSachLopHoc();
            lophocModel.setRowCount(0);
            updateLopHocTable();
            return true;
        }
        return false;
    }

    public boolean xoaLopHoc(Lophoc l) {
        if(LopHocDAO.xoaLopHoc(l)) {
            ls_lophoc = LopHocDAO.layDanhSachLopHoc();
            lophocModel.setRowCount(0);
            updateLopHocTable();
            return true;
        }
        return false;
    }

    public void updateGiaovuTable() {
        if(ls_giaovu != null) {
            for (int i=0; i<ls_giaovu.size(); i++) {
                Giaovu g = ls_giaovu.get(i);
                giaovuModel.addRow(new Object[] {
                        i+1, g.getMaGiaoVu(), g.getHoVaTen(),
                        new SimpleDateFormat("dd/MM/yyyy").format(g.getNgaySinh()), g.getDienThoai()
                });
            }
        }
    }

    public void updateMonhocTable() {
        if(ls_monhoc != null) {
            for (int i=0; i < ls_monhoc.size(); i++) {
                Monhoc m = ls_monhoc.get(i);
                monhocModel.addRow(new Object[] {
                        i + 1, m.getMaMh(), m.getTenMh(), m.getSoTinChi()
                });
            }
        }
    }

    public void updateHockiTable() {
        if(ls_hocki != null) {
            for (int i=0; i< ls_hocki.size(); i++) {
                Hocki h = ls_hocki.get(i);
                hockiModel.addRow(new Object[] {
                        i+1, h.getTenHk(), h.getNamHoc(), new SimpleDateFormat("dd/MM/yyyy").format(h.getNgayBatDau())
                        , new SimpleDateFormat("dd/MM/yyyy").format(h.getNgayKetThuc())
                });
            }
        }
    }

    public void updateLopHocTable() {
        if(ls_lophoc != null) {
            for (int i=0; i< ls_lophoc.size(); i++) {
                Lophoc l = ls_lophoc.get(i);
                lophocModel.addRow(new Object[] {
                        i+1, l.getMaLop(), l.getTongNam() + l.getTongNu(), l.getTongNam(), l.getTongNu()
                });
            }
        }
    }
}
