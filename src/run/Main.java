package run;

import utils.HibernateUtil;
import view.LoginFrm;

public class Main {
    public static void main(String[] args) {
        new HibernateUtil();
        LoginFrm loginFrm = new LoginFrm("Đăng nhập sinh viên");
        loginFrm.setVisible(true);
    }
}
