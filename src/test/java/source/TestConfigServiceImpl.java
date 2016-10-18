/**
 * 
 */
package source;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import service.ConfigService;
import dao.Config;
import dao.repository.EntityRepository;

/**
 * @author houyong
 *
 */
public class TestConfigServiceImpl extends TestBase {

	@Autowired
	private ConfigService configService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link serviceImpl.ConfigServiceImpl#getConfig()}.
	 */
	@Test
	public void testGetConfig() {

		Config config = new Config();
		config = config.findById(Config.class, "a");
//		System.out.println(config.getSystemName());
//
		ObjectMapper om = new ObjectMapper();
//		om.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
//				true);
//		om.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES,
//				false);
//		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		om.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS,
//				false);
//		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//
//		
//		om.convertValue(config, new TypeReference<Map<String, Object>>(){});
//		
//		JSONObject json=JSONObject.fromObject(config);
//		System.out.println(json);

//		ObjectMapper om = new ObjectMapper();
//		B b=new B();
//		b.setSessionFactory(config.s);
		
		Map<String, Object> map =om.convertValue(config, new TypeReference<Map<String, Object>>(){});

		System.out.println(map);
		
		//		System.out.println(map);

	}
	
	

}



@MappedSuperclass
class A{
	
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
	@Transient
	private static SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		A.sessionFactory = sessionFactory;
	}

	/**
	 * 根据sessionFactory获取当前session
	 * 
	 * @return
	 */
	public Session getSession() {
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
		return (T) getSession().get(clazz, id);
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
		Criteria criteria = getSession().createCriteria(clazz);
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
		return getSession().createCriteria(clazz)
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
		getSession().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void remove(EntityRepository entity) {
		getSession().delete(entity);
	}
	
}

class B extends A{
	private String name="houyong";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
