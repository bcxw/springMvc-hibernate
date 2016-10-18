package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import dao.repository.EntityRepository;

@Entity
@Repository
@Table(name = "config")
public class Config extends EntityRepository implements java.io.Serializable {

	private static final long serialVersionUID = 6434736325502162891L;

	@Column(name = "systemName", length = 100)
	private String systemName;

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

}