package view;

import controller.HocPhanDAO;
import controller.SinhVienHocPhanDAO;
import model.Hocki;
import model.Hocphan;
import model.Sinhvien;
import model.SinhvienHocphan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DangKyHPFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tblHocPhanMo;
    private DefaultTableModel hocPhanMoModel;
    private List<Hocphan> ls_hocphan;
    private Hocki currentSemester;
    private Sinhvien currentSinhVien;
    //private List<SinhvienHocphan> sv_hp;

    private HomeFrm home;

    public DangKyHPFrm(Frame parent, boolean modal, Hocki h, Sinhvien s, List<SinhvienHocphan> sv_hp) {
        super(parent, modal);
        setContentPane(contentPane);
        setTitle("Đăng ký học phần");
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(800, 600);
        setLocationRelativeTo(parent);
        home = (HomeFrm) parent;
        currentSinhVien = s;


        tblHocPhanMo.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblHocPhanMo.setColumnSelectionAllowed(false);
        tblHocPhanMo.setRowSelectionAllowed(true);

        currentSemester = h;
        ls_hocphan = HocPhanDAO.layDaySachHocPhanCuaHocKi(currentSemester);
        Iterator<Hocphan> i = ls_hocphan.iterator();
        while (i.hasNext()) {
            Hocphan hp = i.next();
            List<SinhvienHocphan> dsdk = SinhVienHocPhanDAO.layDanhSachSinhVienDangKyHP(hp.getId());
            if(dsdk.size() == hp.getSlot()) {
                i.remove();
            } else {
                for (SinhvienHocphan svh :sv_hp) {
                    if(hp.getId().equals(svh.getIdHocPhan())) {
                        i.remove();
                    }
                }
            }
        }

        hocPhanMoModel = new DefaultTableModel(new Object[] {
                "STT", "Mã học phần", "Tên môn học", "Giáo viên", "Ngày học", "Slot", "Chọn"
        }, 0) {
            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 5:
                        return Integer.class;
                    case 6:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        tblHocPhanMo.setModel(hocPhanMoModel);
        updateHocPhanMoTable();


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = false;
                // Lấy số lượng học phần cho phép đăng ký;
                int numberOfHocPhanValid = 8 - sv_hp.size();
                ArrayList<Integer> selectedRowIndex = new ArrayList<>();
                for (int i=0; i < tblHocPhanMo.getRowCount(); i++) {
                    if((Boolean) tblHocPhanMo.getValueAt(i, 6)) {
                        if(numberOfHocPhanValid == 0) {
                            JOptionPane.showMessageDialog(contentPane, "Bạn không thể đăng ký hơn 8 học phần!");
                            return;
                        }
                        numberOfHocPhanValid -= 1;
                        selectedRowIndex.add(i);
                        isSelected = true;
                    }
                }
                if(isSelected) {
                    int output = JOptionPane.showConfirmDialog(contentPane, "Bạn muốn đăng ký các học phần đã chọn?",
                            "Cảnh báo", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if(output == JOptionPane.YES_OPTION) {
                        List<SinhvienHocphan> ls = new ArrayList<>();
                        for (Integer i : selectedRowIndex) {
                            Hocphan h = ls_hocphan.get(i);
                            SinhvienHocphan svh = new SinhvienHocphan(currentSinhVien.getId(), currentSinhVien, h.getId(), h, new Date());
                            ls.add(svh);
                            System.out.println(h.getMonHoc());
                            for (SinhvienHocphan svhp : sv_hp) {
                                if(svhp.getHocPhan().getCa().equals(h.getCa()) &&
                                        svhp.getHocPhan().getNgayTrongTuan().equals(h.getNgayTrongTuan())) {
                                    JOptionPane.showMessageDialog(contentPane, "Có học phần trùng ca học " +
                                            "với học phần đã đăng ký!");
                                    return;
                                }
                            }
                        }
                        if(home.dangKyHocPhan(ls)) {
                            JOptionPane.showMessageDialog(contentPane, "Đăng ký thành công!");
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Có một số học phần đăng ký không thành công!");
                            home.updateHocPhanDKTable();
                            dispose();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Hãy chọn ít nhất 1 học phần!");
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void updateHocPhanMoTable() {
        hocPhanMoModel.setRowCount(0);
        if(ls_hocphan != null) {
            for (int i=0; i<ls_hocphan.size(); i++) {
                Hocphan h = ls_hocphan.get(i);
                List<SinhvienHocphan> sv_hp = SinhVienHocPhanDAO.layDanhSachSinhVienDangKyHP(h.getId());
                hocPhanMoModel.addRow(new Object[] {
                        i+1, h.getId(), h.getMonHoc().getTenMh(), h.getGiaoVien().getHoVaTen(),
                        h.getNgayTrongTuan() + ", " + h.getCa(), sv_hp.size() +"/"+ h.getSlot(), false
                });
            }
        }
    }

}
