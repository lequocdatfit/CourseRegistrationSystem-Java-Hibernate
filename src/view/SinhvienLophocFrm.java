package view;

import controller.LopHocDAO;
import controller.MonhocDAO;
import controller.SinhVienDAO;
import model.Lophoc;
import model.Monhoc;
import model.Sinhvien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class
SinhvienLophocFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tblSinhVien;
    private JButton thêmSinhViênButton;
    private JButton btnEdit;
    private JButton btnReset;
    private JTextField txtSearchSv;
    private JButton btnSearchSV;
    private DefaultTableModel modelSinhvien;
    private List<Sinhvien> ls_sinhvien;
    private Lophoc l;

    private HomeGVFrm home;

    public SinhvienLophocFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        setTitle("Danh sách sinh viên");
        getRootPane().setDefaultButton(buttonOK);
        setSize(800, 600);
        setLocationRelativeTo(home);
        home = (HomeGVFrm) parent;

        modelSinhvien = (DefaultTableModel) tblSinhVien.getModel();
        modelSinhvien.setColumnIdentifiers(new Object[] {
                "STT", "ID", "Mã sinh viên", "Họ và tên", "Giới tính", "Năm nhập học", "Mã lớp"
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
        thêmSinhViênButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputSinhVien input = new InputSinhVien(SinhvienLophocFrm.this);
                input.setLopHoc(l);
                input.setVisible(true);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblSinhVien.getSelectedRow();
                if(ls_sinhvien.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Không có sinh viên nào để cập nhật!");
                } else if(index == -1) {
                    JOptionPane.showMessageDialog(contentPane, "Hãy chọn 1 sinh viên trên bảng!");
                } else {
                    EditSinhVien edit = new EditSinhVien(SinhvienLophocFrm.this, rootPaneCheckingEnabled);
                    edit.setEditData(ls_sinhvien.get(index), l);
                    edit.setVisible(true);
                }
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblSinhVien.getSelectedRow();
                if(ls_sinhvien.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Hiện tại không có sinh viên nào!");
                } else if(index == -1) {
                    JOptionPane.showMessageDialog(contentPane, "Hãy chọn 1 sinh viên trên bảng!");
                } else {
                    int output = JOptionPane.showConfirmDialog(contentPane, "Bạn có muốn reset mật khẩu của sinh viên này ?",
                            "Cảnh báo", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if(output == JOptionPane.YES_OPTION) {
                        Sinhvien s = ls_sinhvien.get(index);
                        s.setMatKhau("demo@123");
                        if(SinhvienLophocFrm.this.resetPassword(s)) {
                            JOptionPane.showMessageDialog(contentPane, "Reset thành công! mật khẩu mặc định là demo@123");
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Reset thất bại!");
                        }
                    } else {
                        return;
                    }
                }
            }
        });
        btnSearchSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatDanhSachSinhVien2();
                String searchStr = txtSearchSv.getText();
                Iterator<Sinhvien> i = ls_sinhvien.iterator();
                while (i.hasNext()) {
                    Sinhvien s = i.next();
                    if(!s.getHoVaTen().toLowerCase().contains(searchStr.toLowerCase())) {
                        i.remove();
                    }
                }
                updateSinhVienTable();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void updateSinhVienTable() {
        modelSinhvien.setRowCount(0);
        if(ls_sinhvien != null) {
            for (int i=0; i< ls_sinhvien.size(); i++) {
                Sinhvien s = ls_sinhvien.get(i);
                modelSinhvien.addRow(new Object[] {
                        i+1, s.getId(), s.getMaSv(), s.getHoVaTen(), s.getGioiTinh() ? "Nam": "Nữ",
                        new SimpleDateFormat("dd/MM/yyyy").format(s.getNamNhapHoc()), s.getMaLop()
                });
            }
        }
    }

    public void setData(Lophoc l) {
        this.l = l;
        l = LopHocDAO.layThongTinLopHoc(l.getMaLop());
        Iterator<Sinhvien> i = l.getLs_sinhvien().iterator();
        ls_sinhvien = new ArrayList<Sinhvien>();
        while (i.hasNext()) {
            Sinhvien s = i.next();
            ls_sinhvien.add(s);
        }

        modelSinhvien.setRowCount(0);
        updateSinhVienTable();
    }

    public void capNhatDanhSachSinhVien() {
        l = LopHocDAO.layThongTinLopHoc(l.getMaLop());
        Iterator<Sinhvien> i = l.getLs_sinhvien().iterator();
        ls_sinhvien = new ArrayList<Sinhvien>();
        while (i.hasNext()) {
            Sinhvien s = i.next();
            ls_sinhvien.add(s);
        }
        modelSinhvien.setRowCount(0);
        updateSinhVienTable();
    }

    public void capNhatDanhSachSinhVien2() {
        l = LopHocDAO.layThongTinLopHoc(l.getMaLop());
        Iterator<Sinhvien> i = l.getLs_sinhvien().iterator();
        ls_sinhvien = new ArrayList<Sinhvien>();
        while (i.hasNext()) {
            Sinhvien s = i.next();
            ls_sinhvien.add(s);
        }
    }

    public boolean themSinhVien(Sinhvien s) {
        if(SinhVienDAO.themSinhVien(s)) {
            capNhatDanhSachSinhVien();
            return true;
        }
        return false;
    }

    public boolean capNhatSinhVien(Sinhvien s) {
        if(SinhVienDAO.capNhatSinhVien(s)) {
            capNhatDanhSachSinhVien();
            return true;
        }
        return false;
    }

    public boolean resetPassword(Sinhvien s) {
        if(SinhVienDAO.resetMatKhauSV(s)) {
            //capNhatDanhSachSinhVien();
            return true;
        }
        return false;
    }
}
