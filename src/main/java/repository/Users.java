package repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import repository.entity.EntityRepository;

@Entity
@Repository
@Table(name = "users")
public class Users extends EntityRepository implements java.io.Serializable {

	private static final long serialVersionUID = 999607175131148821L;

	@Column(name = "userName")
	private String userName;

	@Column(name = "password")
	private String password;


	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}