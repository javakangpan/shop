package pers.kp.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;

import com.sun.xml.internal.ws.wsdl.writer.document.Service;

import pers.kp.model.McBean;
import pers.kp.model.McTypeBean;
import pers.kp.model.PageBean;
import pers.kp.service.IMcService;
import pers.kp.service.impl.McServiceImpl;
import pers.kp.utils.WebUtils;

@WebServlet("/back/McServlet")
public class McServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private McBean mcModel;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String task = request.getParameter("task");
		IMcService service = new McServiceImpl();
		
		
		if ("query".equals(task)) {
			
			paging(request, response, service);
			
		} else if ("delete".equals(task)) {
			
			delete(request, response, service);
			
		} else if ("queryForUpdate".equals(task)) {
			
			update(request, response, service);

		} else if ("add".equals(task)) {

			add(request, response, service);

		} else if ("queryFather".equals(task)) {
			
			queryFather(response, service);

		} else if ("querySun".equals(task)) {

			querySun(request, response, service);
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response, IMcService service)
			throws ServletException, IOException {

		mcModel = service.queryForSingle(WebUtils.parseInt(request.getParameter("mcid"), -1));
		if (mcModel != null) {
			request.setAttribute("mc", mcModel);
			request.getRequestDispatcher("/back/mc/mcupdate.jsp").forward(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response, IMcService service)
			throws ServletException, IOException {
		int i = service.delete(WebUtils.parseInt(request.getParameter("mcid"), -1));
		if (i != -1) {
			paging(request, response, service);
		} else {
			WebUtils.showMsg("删除失败", response);

		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response, IMcService service) {
		
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		McBean mc = new McBean();
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					if ("mcname".equals(name)) {
						mc.setMcname(value);
					}

					if ("price".equals(name)) {
						mc.setPrice(WebUtils.parseDouble(value, 10000));

					}
					if ("smallid".equals(name)) {
						mc.setSmalltypeid(WebUtils.parseInt(value, 1));
					}
					if ("flag".equals(name)) {
						mc.setFlag(value);
					}
					if ("quantity".equals(name)) {
						mc.setQuantity(WebUtils.parseInt(value, 50));
					}
					if ("mcdecx".equals(name)) {
						mc.setMcdecx(value);
					}
				} else {
					String fileName = WebUtils.getFileName(fileItem.getName());
					String path = request.getServletContext().getRealPath("/upload");
					File file = new File(path + "/" + fileName);
					fileItem.write(file);
					mc.setPic(fileName);
				}
			}
			//出现过BUG
			if(mcModel!=null){
				service.delete(mcModel.getMcid());
			}
			
			int i = service.add(mc);
			if (i != -1) {
				paging(request, response, service);
			} else {
				WebUtils.showMsg("添加失败", response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void querySun(HttpServletRequest request, HttpServletResponse response, IMcService service)
			throws IOException {
		String typeid = request.getParameter("typeid");
		List<McTypeBean> list = service.queryForFather(WebUtils.parseInt(typeid, -1));
		JSONArray json = new JSONArray(list);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
	}

	private void queryFather(HttpServletResponse response, IMcService service) throws IOException {

		List<McTypeBean> list = service.queryForFather(0);

		JSONArray json = new JSONArray(list);
		PrintWriter out = response.getWriter();

		out.write(json.toString());
		out.flush();
		out.close();
	}

	private void paging(HttpServletRequest request, HttpServletResponse response, IMcService service)
			throws ServletException, IOException {

		// int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), 2);
		// int currentPage=WebUtils.parseInt(request.getParameter("currentPage"), 1);

		String mcname = request.getParameter("mcname");

		McBean mc = new McBean();
		if (!WebUtils.isEmpty(mcname)) {

			mc.setMcname(mcname);
		}

		int pageSize = WebUtils.getPageSize(request);
		int currentPage = WebUtils.getCurrentPage(request);
		PageBean<McBean> pageModel = service.queryPageModel(mc, currentPage, pageSize);

		request.setAttribute("model", pageModel);
		request.setAttribute("mcname", mcname);

		request.getRequestDispatcher("/back/mc/mclist2.jsp").forward(request, response);

		// -------------------以下mclist------------------------------------------

		/*
		 * List<McBean> listall = service.query(null); //总记录数 int
		 * totalCount=listall.size(); //总页数(totalCount-1)/pageSize + 1 int
		 * pageCount=(totalCount-1)/pageSize+1;
		 * 
		 * //开始下标 int startIndex=(currentPage-1)*pageSize; //结束下标 int
		 * endIndex=currentPage*pageSize;
		 * 
		 * 
		 * endIndex=endIndex>totalCount?totalCount:endIndex;
		 * 
		 * 
		 * //截取分页后的数据 List<McBean> list = listall.subList(startIndex, endIndex);
		 * request.setAttribute("list", list);
		 * request.setAttribute("currentPage", currentPage);
		 * request.setAttribute("pageCount", pageCount);
		 * request.setAttribute("totalCount", totalCount);
		 * request.setAttribute("pageSize", pageSize);
		 * 
		 * request.getRequestDispatcher("/back/mc/mclist.jsp").forward(request,
		 * response);
		 */

		// -------------------以上------------------------------------------

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	@SuppressWarnings("unused")
	private void query(HttpServletRequest request, HttpServletResponse response, IMcService service)
			throws ServletException, IOException {
		List<McBean> list = service.query(null);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/back/mc/mclist2.jsp").forward(request, response);
	}

}
