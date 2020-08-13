package kr.or.devbada.freeBoards.commons;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CommonUtilService {

	Logger logger = (Logger) LogManager.getLogger(CommonUtilService.class);

	/** CLIENT IP 정보를 가져온다.
	 * @param request
	 * @return
	 */
	public String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		logger.info("> X-FORWARDED-FOR : " + ip);

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.info("> Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.info(">  WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			logger.info("> HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			logger.info("> HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
			logger.info("> getRemoteAddr : " + ip);
		}
		logger.info("> Result : IP Address : " + ip);

		return ip;
	}
}
