package com.wines.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wines.entity.Customer;
import com.wines.entity.LinkMan;
import com.wines.service.CustomerService;
import com.wines.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	//文件上传
	private File upload;
	private String uploadFileName;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	//到添加页面的方法
	public String toAddPage() {
		//做下拉列表选择客户的操作
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
	
	//添加联系人的方法
	public String addLinkMan() throws Exception {
		//判断是否需要上传文件
		if(upload != null) {
			//在服务器文件夹中创建文件
			File serverFile = new File("F:\\sshTest" + "/" + uploadFileName);
			//把上传的文件复制到服务器文件中
			FileUtils.copyFile(upload, serverFile);
		}
		
		linkManService.add(linkMan);
		return "addLinkMan";
	}
	
	//联系人列表的方法
	public String list() {
		List<LinkMan> list = linkManService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	//修改和删除的方法
	//修改的方法-1、根据id查询
	public String showLinkMan() {
		int linkid = linkMan.getLinkid();
		LinkMan linkman = linkManService.findOne(linkid);
		//需要所有客户的list集合
		List<Customer> listCustomer = customerService.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("linkman", linkman);
		request.setAttribute("listCustomer", listCustomer);
		return "showLinkMan";
	}
	//修改的方法-2、调用方法修改
	public String update() {
		linkManService.update(linkMan);
		return "update";
	}
	
	//到多条件查询页面的方法
	public String toSelectPage() {
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toSelectPage";
	}
	
	//多条件查询的方法
	public String moreCondition() {
		List<LinkMan> list = linkManService.findMoreCondition(linkMan);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
	//返回所有联系人json数据的方法
	public String linkmanJson() throws Exception {
		List<LinkMan> list = linkManService.findAll();
		//list转换为json数据格式
		//因为json数据格式为名称和值，使用map集合进行数据封装，再转换为json
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", list);
		//map转换为json数据格式
		//禁止循环调用，因为当有两个联系人共同所属同一个用户的时候，只会显示一个，另一个不会显示
		String json = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
		//使用response对象进行返回
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return NONE;
	}
}
