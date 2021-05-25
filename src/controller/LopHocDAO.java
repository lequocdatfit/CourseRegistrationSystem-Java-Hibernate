package controller;

import model.Lophoc;
import model.Sinhvien;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class LopHocDAO {
    public static List<Lophoc> layDanhSachLopHoc() {
        List<Lophoc> ls = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            final String hql = "from Lophoc l";
            Query query = session.createQuery(hql);
            ls = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }
}
