package service.impl;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import repository.Users;

@Service(value="loginService")
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	private Users user;

	public Map<String, Object> login(Map<String, String> paramMap) {
		Map<String, Object> responseResult = new WeakHashMap<String,Object>(){{put("success",true);}};
		String userName = paramMap.get("userName");
		String password = paramMap.get("password");
		List<Users> userList = user.findByProperty(Users.class, "userName",
				userName);
		if (userList != null && !userList.isEmpty()) {
			Users user = userList.get(0);
			password = user.getId() + password;
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			if (password.equals(user.getPassword())) {
				responseResult = new WeakHashMap<String,Object>(){{put("success",true);put("message","登录成功");}};
			} else {
				responseResult = new WeakHashMap<String,Object>(){{put("success",false);put("message","密码错误");}};
			}
		} else {
			responseResult = new WeakHashMap<String,Object>(){{put("success",false);put("message","用户名不存在");}};
		}
		return responseResult;
	}

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
			
		return null;
	}

}
