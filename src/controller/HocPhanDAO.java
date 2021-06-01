package controller;

import model.Hocphan;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class HocPhanDAO {
    public static List<Hocphan> layDaySachHocPhan() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Hocphan> ls = null;
        try {
            final String hql = "from Hocphan h";
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
