package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 获取session的基类，dao中只有这个类是自己写的(dao中方法都自动生成的)。sessionFactory使用spring bean方式注入。
 * @author houyong
 *
 */
public class HibernateSessionFactory {

	
	private static SessionFactory sessionFactory;

	private HibernateSessionFactory(){}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateSessionFactory.sessionFactory = sessionFactory;
	}
	
    public static Session getSession() throws HibernateException {
        return sessionFactory.getCurrentSession();
    }

}