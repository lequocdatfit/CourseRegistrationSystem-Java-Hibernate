package view;

import controller.GiaoVienDAO;
import controller.GiaoVuDAO;
import controller.HocKiDAO;
import controller.MonhocDAO;
import model.Giaovien;
import model.Hocki;
import model.Hocphan;
import model.Monhoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.List;

class MonHocComboboxModel extends DefaultComboBoxModel<Monhoc> {
    public MonHocComboboxModel(Monhoc[] items) {
        super(items);
    }
}

class GiaoVienComboboxModel extends DefaultComboBoxModel<Giaovien> {
    public GiaoVienComboboxModel(Giaovien[] items) {
        super(items);
    }
}


public class InputHocPhanFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtId;
    private JTextField txtNgayTrongTuan;
    private JComboBox comboBoxMonHoc;
    private JComboBox comboBoxGiaoVien;
    private JComboBox comboBoxCa;
    private JTextField txtSlot;
    private JTextField txtPhongHoc;
    private List<Monhoc> ls_monHoc;
    private List<Giaovien> ls_giaovien;
    private HomeGVFrm home;
    private Hocki currentSemester;

    public InputHocPhanFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Thêm học phần");
        setSize(500, 400);
        setLocationRelativeTo(parent);
        home = (HomeGVFrm) parent;

        currentSemester = HocKiDAO.layHocKiHienTai();

        ls_monHoc = MonhocDAO.LayDanhSachMonHoc();
        MonHocComboboxModel monHocModel = new MonHocComboboxModel(ls_monHoc.toArray(new Monhoc[ls_monHoc.size()]));
        comboBoxMonHoc.setModel(monHocModel);

        ls_giaovien = GiaoVienDAO.layDanhSachGiaoVien();
        GiaoVienComboboxModel giaoVienModel = new GiaoVienComboboxModel(ls_giaovien.toArray(new Giaovien[ls_giaovien.size()]));
        comboBoxGiaoVien.setModel(giaoVienModel);

        String caHoc[] = {"7:30-9:30", "9:30-11:30", "13:30-15:30", "15:30-17:30"};
        comboBoxCa.setModel(new DefaultComboBoxModel(caHoc));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText();
                String ngayTrongTuan = txtNgayTrongTuan.getText();
                String ca = comboBoxCa.getSelectedItem().toString();
                Integer slot = null;
                String phongHoc = txtPhongHoc.getText();
                String tenHocKi = currentSemester.getTenHk();
                String namHoc = currentSemester.getNamHoc();
                Monhoc mon = (Monhoc) comboBoxMonHoc.getSelectedItem();
                Giaovien giaovien = (Giaovien) comboBoxGiaoVien.getSelectedItem();
                boolean isOk = true;
                try {
                    slot = Integer.parseInt(txtSlot.getText());
                } catch (Exception ex) {
                    isOk = false;
                    JOptionPane.showMessageDialog(contentPane, "slot phải là số tự nhiên!");
                    ex.printStackTrace();
                }
                if(isOk) {
                    Hocphan h = new Hocphan(id, ngayTrongTuan, ca, slot, phongHoc, tenHocKi, namHoc, mon, giaovien);
                    if(home.themHocPhan(h)) {
                        JOptionPane.showMessageDialog(contentPane, "Thêm học phần thành công!");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Thêm học phần thất bại! (Mã học phần không được trùng)");
                    }
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

}
