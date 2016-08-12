package daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import dao.UserDAO;

@Repository
public class UserDAOImpl extends UserDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
}