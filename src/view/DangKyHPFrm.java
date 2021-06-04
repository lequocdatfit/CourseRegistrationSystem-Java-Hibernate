package view;

import controller.HocPhanDAO;
import model.Hocki;
import model.Hocphan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DangKyHPFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tblHocPhanMo;
    private DefaultTableModel hocPhanMoModel;
    private List<Hocphan> ls_hocphan;
    private Hocki currentSemester;

    public DangKyHPFrm(Frame parent, boolean modal, Hocki h) {
        super(parent, modal);
        setContentPane(contentPane);
        setTitle("Đăng ký học phần");
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(parent);

        currentSemester = h;
        ls_hocphan = HocPhanDAO.layDaySachHocPhanCuaHocKi(currentSemester);
        hocPhanMoModel = (DefaultTableModel) tblHocPhanMo.getModel();
        hocPhanMoModel.setColumnIdentifiers(new Object[] {
                "STT", "Mã học phần", "Tên môn học", "Giáo viên", "Ngày học", "Slot"
        });
        updateHocPhanMoTable();

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
        for (int i =0; i<ls_hocphan.size(); i++) {
            Hocphan h = ls_hocphan.get(i);
            hocPhanMoModel.addRow(new Object[] {
                    i+1, h.getId(), h.getMonHoc().getTenMh(), h.getGiaoVien().getHoVaTen(),
                    h.getNgayTrongTuan() + ", " + h.getCa() + h.getSlot()
            });
        }
    }

}
