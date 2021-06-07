package view;

import controller.LopHocDAO;
import model.Lophoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputLopHocFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtMaLopHoc;

    private HomeGVFrm home;

    public InputLopHocFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        setTitle("Thêm lớp học");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(parent);
        home = (HomeGVFrm) parent;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maLopHoc = txtMaLopHoc.getText();
                Integer tongNam = 0;
                Integer tongNu = 0;

                Lophoc l = new Lophoc(maLopHoc, tongNam, tongNu);
                if(((HomeGVFrm) parent).themLopHoc(l)) {
                    JOptionPane.showMessageDialog(contentPane, "Thêm lớp học thành công!");
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Thêm lớp học thất bại! Mã lớp học không được trùng");
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


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
