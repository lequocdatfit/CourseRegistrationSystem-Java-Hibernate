package controller;

import model.Lophoc;
import model.Sinhvien;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public static Lophoc layThongTinLopHoc(String maLopHoc) {
        Lophoc l = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            l = (Lophoc) session.get(Lophoc.class, maLopHoc);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
       return l;
    }

    public static boolean themLopHoc(Lophoc l) {
        if(LopHocDAO.layThongTinLopHoc(l.getMaLop()) != null) {
            return false;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(l);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            //session.close();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean xoaLopHoc(Lophoc l) {
        if(LopHocDAO.layThongTinLopHoc(l.getMaLop()) == null) {
            return false;
        }
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(l);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
