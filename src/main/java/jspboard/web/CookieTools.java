package jspboard.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieTools {
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		
		return null;
	}
}
