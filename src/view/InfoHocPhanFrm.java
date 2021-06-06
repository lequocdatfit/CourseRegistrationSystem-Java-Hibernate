package view;

import controller.GiaoVuDAO;
import controller.HocPhanDAO;
import controller.SinhVienHocPhanDAO;
import model.Giaovu;
import model.Hocphan;
import model.Sinhvien;
import model.SinhvienHocphan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;

public class InfoHocPhanFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tblSinhVienHocPhan;
    private JTextField txtMaSV;
    private JButton btnSearchSV;
    private JTextField txtTenSV;
    private DefaultTableModel sinhVienHocPhanModel;
    private HomeGVFrm home;
    private List<SinhvienHocphan> sv_hp;

    public InfoHocPhanFrm(Frame parent, boolean modal, Hocphan selectedHocPhan) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(parent);
        home = (HomeGVFrm) parent;

        sv_hp = SinhVienHocPhanDAO.layDanhSachSinhVienDangKyHP(selectedHocPhan.getId());
        sinhVienHocPhanModel = (DefaultTableModel) tblSinhVienHocPhan.getModel();
        sinhVienHocPhanModel.setColumnIdentifiers(new Object[] {
                "STT", "MSSV", "Họ tên", "Mã môn học", "Tên môn học", "Giáo viên", "Thời gian học", "Thời gian ĐK"
        });
        updateSinhVienHocPhanTable();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
        btnSearchSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sv_hp = SinhVienHocPhanDAO.layDanhSachSinhVienDangKyHP(selectedHocPhan.getId());
                String searchMaStr = txtMaSV.getText();
                String searchTenStr = txtTenSV.getText();
                Iterator<SinhvienHocphan> i = sv_hp.iterator();
                while (i.hasNext()) {
                    SinhvienHocphan svh = i.next();
                    if(!svh.getSinhVien().getHoVaTen().toLowerCase().contains(searchTenStr.toLowerCase()) ||
                            !svh.getSinhVien().getMaSv().toLowerCase().contains(searchMaStr.toLowerCase())) {
                        i.remove();
                    }
                }
                updateSinhVienHocPhanTable();
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

    public void updateSinhVienHocPhanTable() {
        sinhVienHocPhanModel.setRowCount(0);
        if(sv_hp != null) {
            for (int i=0; i<sv_hp.size(); i++) {
                SinhvienHocphan svh = sv_hp.get(i);
                Sinhvien s = svh.getSinhVien();
                Hocphan h = HocPhanDAO.layThongTinHocPhan(svh.getHocPhan().getId());
                sinhVienHocPhanModel.addRow(new Object[] {
                        i+1, s.getMaSv(), s.getHoVaTen(), h.getMonHoc().getMaMh(),
                        h.getMonHoc().getTenMh(), h.getGiaoVien().getHoVaTen(),
                        h.getNgayTrongTuan(), svh.getNgayDangKy()
                });
            }
        }
    }
}
