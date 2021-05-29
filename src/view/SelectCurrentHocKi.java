package view;

import model.Hocki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HockiComboboxModel extends DefaultComboBoxModel<Hocki> {
    public HockiComboboxModel(Hocki[] items) {
        super(items);
    }
}

public class SelectCurrentHocKi extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private Hocki currentSemester;

    private HomeGVFrm home;

    public SelectCurrentHocKi(Frame parent, boolean modal, Hocki[] data) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(300, 400);
        setLocationRelativeTo(null);
        home = (HomeGVFrm) parent;

        for (Hocki hk: data) {
            System.out.println(hk.getTenHk());
        }
        HockiComboboxModel hockiComboboxModel = new HockiComboboxModel(data);

        comboBox1.setModel(hockiComboboxModel);
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Hocki hocki = (Hocki) e.getItem();
                System.out.println(hocki.getTenHk());
                currentSemester = hocki;
            }
        });


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(home.setCurrentSemester(currentSemester)) {
                    JOptionPane.showMessageDialog(contentPane,"Cập nhật thành công!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Cập nhật thất bại!");
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

    public void setComboBoxData(Hocki [] h) {

    }

}
