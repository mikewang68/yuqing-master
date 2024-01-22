package com.stonedt.intelligence.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stonedt.intelligence.aop.SystemControllerLog;
import com.stonedt.intelligence.dao.ProjectTaskDao;
import com.stonedt.intelligence.entity.Analysis;
import com.stonedt.intelligence.service.AnalysisService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 即时头条
 */
@Controller
@RequestMapping(value = "/headlines")
public class HeadlinesController {

	@Autowired
	private AnalysisService analysisService;
	
	@Autowired
	private ProjectTaskDao projectTaskDao;

	/**
	 * 跳转到头条页面
	 */
	@SystemControllerLog(module = "即时头条", submodule = "即时头条页面", type = "查询", operation = "")
	@GetMapping(value = "")
	public ModelAndView analysis(HttpServletRequest request, ModelAndView mv) {
		String url = "https://apis.tianapi.com/world/index?key=e04bf6a5c243d104aca403109f2ba179";
		String result = null;
		//声明httpClient,httpResponse于try...catch语句外.方便最后关闭资源
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try{
			//创建一个连接实例
			httpClient = HttpClients.createDefault();
			//将接口地址封装到HttpGet对象中
			HttpGet httpGet = new HttpGet(url);
			//调用指定接口,并获取response响应对象
			httpResponse = httpClient.execute(httpGet);
			//HTTP-200状态码表示请求已成功
			if(httpResponse.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			//逐一关闭使用过的资源,释放内存
			try{
				if(httpResponse!=null){
					httpResponse.close();
				}
				if(httpClient!=null){
					httpClient.close();
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		JSONObject jsonObject = JSON.parseObject(result);
		String listStr = jsonObject.getString("result");
		jsonObject = JSON.parseObject(listStr);
		listStr = jsonObject.getString("newslist");
		JSONArray array = JSONArray.parseArray(listStr);
		mv.addObject("array", array);
		mv.setViewName("headlines/headlines");
		return mv;
	}

	
	

}
