package test;

import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceClient;

import org.springframework.context.ApplicationContext;

import com.clint.Aware;
import com.clint.shishitongxun.xinxi.model.Xinxi;
import com.clint.shishitongxun.xinxi.service.XinxiService;

@WebService
public class ServiceHello {

	/**
	 * 供客户端调用的方法
	 * 
	 * **/
	public String getValue(String name){
		System.out.println("调用getValue");
		ApplicationContext ac = Aware.context;
		XinxiService xinxiService = (XinxiService) ac.getBean("xinxiService");
		List<Xinxi> l = xinxiService.getAllXinxi();
		return "我叫"+name+"2014更改,信息数量"+l.size();
	}
	
	
	//启动webservice
	public void returnWebService(){
		Endpoint.publish("http://localhost:9001/Service/ServiceHello", new ServiceHello());
		System.out.println("WebService success!");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//第一句话很重要，用于发布服务器localhost为地址指向本地，但必须未被占用第二个参数new的为本类的类名
		Endpoint.publish("http://localhost:9001/Service/ServiceHello", new ServiceHello());
		System.out.println("service success!");
	}
}