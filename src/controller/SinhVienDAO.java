package controller;

import model.Sinhvien;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class SinhVienDAO {
    public static Sinhvien LayThongTinSinhVienBangMa(String maSinhVien) {
        Sinhvien sv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            final String hql = "SELECT sv from Sinhvien sv WHERE sv.maSv = :maSinhVien";
            Query query = session.createQuery(hql);
            query.setParameter("maSinhVien", maSinhVien);
            List ls= query.list();
            if(!ls.isEmpty()) {
                sv = (Sinhvien) ls.get(0);
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sv;
    }
}
