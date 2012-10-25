/**
 * 
 */
package com.soyomaker.handlers.login;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.handlers.login.LoginHandler;
import com.soyomaker.model.User;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.UserSession;
import com.soyomaker.net.UserSessionManager;
import com.soyomaker.service.UserService;

/**
 * @author chenwentao
 * 
 */
public class LoginHandlerTest {

	@Mocked
	private UserService userService;

	@Mocked
	private UserSessionManager userSessionManger;

	@Mocked
	private NetTransceiver netTransceiver;

	@Tested
	final LoginHandler handler = new LoginHandler();

	final String userName = "admin";

	final String password = "123456password";

	final User user = new User();

	final UserSession userSession = new UserSession(null);

	GameObject gObject = new GameObject();

	@Before
	public void initData() {
		user.setUsername(userName);
		user.setPassword(password);
		user.setId(10L);

		gObject.putString("username", userName);
		gObject.putString("password", password);
		gObject.putInt(AbHandler.SN_KEY, 1);

		new NonStrictExpectations() {
			{
				// 通过反射设置私有变量
				this.setField(handler, "userService", userService);
				this.setField(handler, "netTransceiver", netTransceiver);
				this.setField(handler, "userSessionManager", userSessionManger);
			}
		};
	}

	@Test
	public void testLoginWithoutUserName() {
		new NonStrictExpectations() {
			{
				userService.findByUsername(userName);
				returns(null);
				times = 1;

				// netTransceiver.sendMessage(with(UserSession.class), gObject);
				// times = 1;
			}
		};

		handler.handleMessage(userSession, gObject);
	}

	// 测试完整流程
	@Test
	public void testLogin() {
		new NonStrictExpectations() {
			{
				userService.findByUsername(userName);
				result = user;
				times = 1;
				userSessionManger.putUserSession(user.getId(), userSession);
				netTransceiver.sendMessage(userSession, gObject);
			}
		};

		handler.handleMessage(userSession, gObject);

		new Verifications() {
			{
				userService.findByUsername(withAny(""));
				times = 1;
			}
		};
	}

}
