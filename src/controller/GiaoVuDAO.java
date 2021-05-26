package controller;

import model.Giaovu;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utils.HibernateUtil;

public class GiaoVuDAO {
    public static Giaovu LayThongTinGiaoVu(String maGiaoVu) {
        Giaovu gv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            gv = (Giaovu) session.get(Giaovu.class, maGiaoVu);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return gv;
    }
}
