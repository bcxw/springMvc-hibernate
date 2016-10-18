package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import dao.repository.EntityRepository;

@Entity
@Repository
@Table(name = "user")
public class User extends EntityRepository implements java.io.Serializable {

	private static final long serialVersionUID = 999607175131148821L;

	@Column(name = "userName")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "lastTime")
	private String lastTime;

	@Column(name = "errorTimes")
	private Integer errorTimes;

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

	public String getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getErrorTimes() {
		return this.errorTimes;
	}

	public void setErrorTimes(Integer errorTimes) {
		this.errorTimes = errorTimes;
	}

}