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

import pers.kp.model.ManagerBean;


@WebFilter("/back/*")
public class BackSecurityFilter implements Filter {

    public BackSecurityFilter() {
       
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		
		HttpSession session=req.getSession();
		ManagerBean mb=(ManagerBean) session.getAttribute("BACK_USER");
		if(mb!=null){
			chain.doFilter(request, response);
		}else{
			session.setAttribute("MSG", "Î´µÇÂ¼»òµÇÂ¼³¬Ê±!");
			res.sendRedirect(req.getContextPath()+"/backLogin.jsp");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
