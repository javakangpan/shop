package pers.kp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.kp.model.UserBean;


@WebFilter("/front/filter/*")
public class FrontSecurityFilter implements Filter {

    public FrontSecurityFilter() {
        
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		HttpSession session=req.getSession();
		UserBean user=(UserBean) session.getAttribute("FRONT_USER");
		if(user!=null){
			chain.doFilter(request, response);
			
		}else{
			session.setAttribute("FRONT_MSG", "ÇëµÇÂ½!!!");
			request.getRequestDispatcher("/frontLogin.jsp").forward(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
