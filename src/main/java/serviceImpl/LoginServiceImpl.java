package serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import service.LoginService;

import common.ResponseResult;

import dao.User;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private User user;

	public ResponseResult login(Map<String, String> paramMap) {
		ResponseResult responseResult = null;
		String userName = paramMap.get("userName");
		String password = paramMap.get("password");
		List<User> userList = user.findByProperty(User.class, "userName",
				userName);
		if (userList != null && !userList.isEmpty()) {
			User user = userList.get(0);
			password = user.getId() + password;
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			if (password.equals(user.getPassword())) {
				responseResult = ResponseResult.success("登录成功");
			} else {
				responseResult = ResponseResult.failure("密码错误");
			}
		} else {
			responseResult = ResponseResult.failure("用户名不存在");
		}
		return responseResult;
	}

}
