package com.xiaoshabao.gate.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class TestFilter extends ZuulFilter {

	/*
	 * 操作：只是打印请求地址
	 */
	@Override
	public Object run() {
		HttpServletRequest request=RequestContext.getCurrentContext().getRequest();
		String host=request.getRemoteHost();
		System.out.println("请求地址："+host);
		return null;
	}

	/*
	 * 判断是否走filter
	 * @return true走，false关闭不走
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/*
	 * 过滤的顺序
	 * 说明：数字越大 越靠后执行
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/*
	 * 过滤器类型有四种：
	 * 	pre：路由之前
	 *	routing：路由之时
	 *	post： 路由之后
	 *	error：发送错误调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
