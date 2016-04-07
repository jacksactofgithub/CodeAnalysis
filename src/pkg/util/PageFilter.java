package pkg.util;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;


public class PageFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
//			HttpServletResponse response =(HttpServletResponse) arg1;
			HttpServletRequest request =(HttpServletRequest) arg0;
			String startRow =request.getParameter("pager.offset");
			int startR =0;
			if(startRow!=null){
				 try{
					 startR= Integer.parseInt(startRow);
				 }catch (Exception e) {
					startR=0;
				}
			}
			String ps =request.getParameter("pageSize");
			int pageSize =20;
			if(ps!=null&&!"".equals(ps.trim())){
				try{
					pageSize= Integer.parseInt(ps);
					
				}catch (Exception e) {
					pageSize=20;
				}
			}else{
				Object o = request.getSession().getAttribute("pageSize");
				if(o!=null)
					pageSize =(Integer) o;
			}
			request.getSession().setAttribute("pageSize", pageSize);
			SystemContext.setStartRow(startR);
			SystemContext.setPageSize(pageSize);
			
			arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
