package repository.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.Restrictions;

/**
 * 实体仓储基类
 * 
 * @author houyong
 *
 */

@MappedSuperclass
public class EntityRepository implements java.io.Serializable {

	private static final long serialVersionUID = -4045790452749373259L;

	/**
	 * 主键id
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * spring context自动注入sessionFactory
	 */
	private static SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		EntityRepository.sessionFactory = sessionFactory;
	}

	/**
	 * 根据sessionFactory获取当前session
	 * 
	 * @return
	 */
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 ********** dao方法**********
	 */

	/**
	 * 根据id查询实体
	 * 
	 * @param clazz
	 *            表映射实体类
	 * @param id
	 *            主键id
	 * @return 实体对象
	 */
	@SuppressWarnings("unchecked")
	public <T extends EntityRepository> T findById(final Class<T> clazz,
			final Serializable id) {
		return (T) session().get(clazz, id);
	}

	/**
	 * 根据多个条件与查询
	 * 
	 * @param clazz
	 *            表映射实体类
	 * @param properties
	 *            多个条件map
	 * @return 实体对象数组
	 */
	@SuppressWarnings("unchecked")
	public <T extends EntityRepository> List<T> findByProperties(
			Class<T> clazz, Map<String, Object> properties) {
		Criteria criteria = session().createCriteria(clazz);
		for (String key : properties.keySet()) {
			criteria.add(Restrictions.eq(key, properties.get(key)));
		}
		return criteria.list();
	}

	/**
	 * 按单个条件查询
	 * 
	 * @param clazz
	 *            表映射实体类
	 * @param propertyName
	 *            查询的字段名称
	 * @param propertyValue
	 *            查询的值
	 * @return 实体对象数组
	 */
	@SuppressWarnings("unchecked")
	public <T extends EntityRepository> List<T> findByProperty(Class<T> clazz,
			String propertyName, Object propertyValue) {
		return session().createCriteria(clazz)
				.add(Restrictions.eq(propertyName, propertyValue)).list();
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象
	 */
	public <T extends EntityRepository> T save(T entity) {
		session().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void remove(EntityRepository entity) {
		session().delete(entity);
	}

}
