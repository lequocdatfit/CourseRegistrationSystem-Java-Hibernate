package view;

import controller.GiaoVuDAO;
import controller.HocKiDAO;
import controller.MonhocDAO;
import model.Giaovu;
import model.Hocki;
import model.Monhoc;
import model.Sinhvien;

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
    private JButton btnEditHocKi;
    private JButton btnXoaHocKi;
    private DefaultTableModel giaovuModel;
    private DefaultTableModel monhocModel;
    private DefaultTableModel hockiModel;


    private List<Sinhvien> ls_sinhvien;
    private List<Giaovu> ls_giaovu;
    private List<Monhoc> ls_monhoc;
    private List<Hocki> ls_hocki;
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
                            ls_hocki = HocKiDAO.layDanhSachHocKi();
                            hockiModel.setRowCount(0);
                            updateHockiTable();
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

    public void updateGiaovuTable() {
        if(!ls_giaovu.isEmpty()) {
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
        if(!ls_monhoc.isEmpty()) {
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
                        i+1, h.getTenHk(), h.getNamHoc(), h.getNgayBatDau(), h.getNgayKetThuc()
                });
            }
        }
    }
}
