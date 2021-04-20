
package jp.dcnet.untils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author ykofi
 *
 */
public class LoginHandLerInterCeptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//登録成功したら、ユーザーのセッションがあり
		Object userlogin = request.getSession().getAttribute("UserLogin");

		if (userlogin == null) {

						request.setAttribute("msg", "沒有訪問權限請先登錄");
						request.getRequestDispatcher(IndexUrl.LOGIN_VIEW).forward(request, response);

			return false;

		} else {

			return true;
		}

	}

}
