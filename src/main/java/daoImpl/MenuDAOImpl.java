package daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import dao.Menu;
import dao.MenuDAO;

@Repository
public class MenuDAOImpl extends MenuDAO{
	private static final Logger log = LoggerFactory.getLogger(MenuDAOImpl.class);
	
	public List<Menu> findByParentId(Object parentId) {
		log.debug("finding Menu instance with property:parentId, value: " + parentId);
		try {
			String queryString = "from Menu as model where model.parentId= ? order by model.sort";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, parentId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}