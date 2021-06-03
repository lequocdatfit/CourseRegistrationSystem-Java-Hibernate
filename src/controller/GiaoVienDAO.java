package controller;

import model.Giaovien;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class GiaoVienDAO {
    public static List<Giaovien> layDanhSachGiaoVien() {
        List<Giaovien> ls = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            final String hql = "from Giaovien g";
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
