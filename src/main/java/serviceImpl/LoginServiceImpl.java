package serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import service.LoginService;
import common.ResponseResult;
import dao.User;
import daoImpl.UserDAOImpl;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserDAOImpl userDAOImpl;
	
	public ResponseResult login(Map<String,String> paramMap) {
		// TODO Auto-generated method stub
		ResponseResult result=null;
		String userName=paramMap.get("userName");
		String password=paramMap.get("password");
		List<User> userList=userDAOImpl.findByUserName(userName);
		if(userList!=null&&!userList.isEmpty()){
			User user=userList.get(0);
			password=Integer.toString(user.getId())+password;
			password=DigestUtils.md5DigestAsHex(password.getBytes());
			if(password.equals(user.getPassword())){
				result=ResponseResult.success("登录成功");
			}else{
				result=ResponseResult.failure("密码错误");
			}
		}else{
			result=ResponseResult.failure("用户名不存在");
		}
		return result;
	}
	
}
