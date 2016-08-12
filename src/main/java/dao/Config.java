package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Config entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "config", catalog = "source")
public class Config implements java.io.Serializable {

	// Fields

	private Integer id;
	private String systemName;

	// Constructors

	/** default constructor */
	public Config() {
	}

	/** full constructor */
	public Config(String systemName) {
		this.systemName = systemName;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "systemName", length = 100)
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

}