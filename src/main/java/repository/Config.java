package repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Entity
@Repository
@Table(name = "config")
public class Config extends RepositoryEntity implements java.io.Serializable {

	private static final long serialVersionUID = 6434736325502162891L;

	@Column(name = "param")
	private String param;

	@Column(name = "value")
	private String value;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}