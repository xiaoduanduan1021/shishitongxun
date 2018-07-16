package com.clint.xiamipinglun;


import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import util.string.UnicodeToUtf8;



//这里是发送工具和普遍小单遍历发送
public class HttpGetPost{

	
	/**
	 * get请求
	 * @param url
	 * @param cookie
	 * @return
	 * @throws IOException
	 */
	
	public static Document httpGet(String url,String cookie) throws IOException{
        //获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
        //请求头设置，特别是cookie设置
        con.header("Accept", ""); 
        con.header("Content-Type", "application/javascript");
        con.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36"); 
        con.header("Cookie", cookie);
        //解析请求结果
        Document doc=con.get(); 
        //获取标题
        System.out.println(doc.title());
        //返回内容
        return doc;
    }
	
	/**
	 * 普通请求
	 */
	public void putong() {
		Document doc = null;
		String jieguo = "";
		try {
			doc = Jsoup.connect("https://www.xiami.com/collect/355288362").timeout(1000 * 10).get();
			System.out.println(doc.toString());
		} catch (IOException e) {
			System.out.println("超时");
		}
	}
	
	
	/**
	 * post请求
	 * @param url
	 * @param map
	 * @param cookie
	 * @return
	 * @throws IOException
	 */
	public static String httpPost(String url,Map<String,String> datamap,String cookie,Map<String,String> headermap) throws IOException{
		
        //获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000*20);
        //遍历生成参数
        if(datamap!=null){
            for (Entry<String, String> entry : datamap.entrySet()) {     
               //添加参数
                con.data(entry.getKey(), entry.getValue());
               } 
        }
        //插入cookie（头文件形式）
        con.header("Cookie", cookie);
        con.header("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        con.header("content-type", "text/html; charset=utf-8");
        con.header("x-requested-with", "XMLHttpRequest");
        
        if(headermap!=null){
        	for (Entry<String, String> entry : headermap.entrySet()) {     
        		//添加参数
        		con.header(entry.getKey(), entry.getValue());
        	} 
        }
        
        Document doc = con.post();  
        return doc.body().text();
    }
	
	private String list [][][] = {
			{
				{//一     291409482----8kj07nx2  冻结
					"gid=152997932279965; _xiamitoken=fd2eaf10c4be745ffb4c19408498f8d6; _unsign_token=6d3914d8fc8f98bbd0e5159fb0c5f343; user_from=1; cna=vI+4E+zPIjoCAXoOJ+CTzTYk; UM_distinctid=16439e01220271-014290d7042441-454c092b-140000-16439e01221387; xmgid=ba6a624f-3294-49e4-b53c-b93da0f2ce10; _m_h5_tk=688809ff12e05ff7762b1eb6ba3ae29b_1529987933875; _m_h5_tk_enc=4ada2c0d6ca50a32bbf70b041d03fa86; uidXM=376828102; CNZZDATA921634=cnzz_eid%3D360374184-1529974107-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1530057202; CNZZDATA2629111=cnzz_eid%3D1553856013-1529975217-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1530061154; xm_token=ebb2c9a075490f1e3dc34fd658e9c13b; member_auth=2TDLG4ZPum1u0aiWT4w2e3VN5e3USTXVlttWjb8otVZ2IIlcNYCtx6uSRQ1A2iiSqFomVuzVgmMK; user=376828102%22rayoht%22images%2Favatar_new%2F750052319_1529982542.jpg%220%22151%22%3Ca+href%3D%27http%3A%2F%2Fwww.xiami.com%2Fwebsitehelp%23help9_3%27+%3ELv3%3C%2Fa%3E%220%220%220%225d47a2c085%221530061716; t_sign_auth=0; isg=",
					"fd2eaf10c4be745ffb4c19408498f8d6"
				},
				{
					"360511724",
					"359451540",
					"280438129",
					"281202291",
					"282403074"
				}
			},
			{
				{//二     		//2686349341----zo0568an可以登录
					"gid=150668533445329; _unsign_token=6b907dbf6968901297d4682a094c0ffe; bdshare_firstime=1506685329664; cna=d7TOEdKY5ykCAXaQhSRqEaDU; UM_distinctid=16358249d85db-085b52468-671d1178-100200-16358249d8666; xmgid=42672b7f-6913-44c0-a35a-652908727ee1; _xiamitoken=e9b30ab928957eefc483f57b50c7f57d; user_from=1; xm_token=d44ceb970e173ec02ee980ab68c745d6; uidXM=376938120; member_auth=gzmRH9sauGtk1KCQTt9ldCBJtLHQE2fQw9lQjOMptFR2cY9YNtL8x6uSRQ1B2yiSqlgmV%2FnAgWgX; user=376938120%22stllci%22images%2Favatar_new%2F1061116067_1530081429.jpg%220%225538%22%3Ca+href%3D%27http%3A%2F%2Fwww.xiami.com%2Fwebsitehelp%23help9_3%27+%3ELv7%3C%2Fa%3E%220%220%220%22f8e784f426%221531567644; t_sign_auth=0; CNZZDATA921634=cnzz_eid%3D329892700-1526190701-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1531566338; CNZZDATA2629111=cnzz_eid%3D295240315-1526192282-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1531564319; isg=",
					"e9b30ab928957eefc483f57b50c7f57d"
				},
				{
					"364060436",
					"276268402",
					"359304445",
					"363053866",
					"357214539"
				}
			},
			{
				{//三     3321885686----rzs86j23冻结
					"gid=152997932279965; join_from=1zufSNtP6D010%2FjCCA; _xiamitoken=fd2eaf10c4be745ffb4c19408498f8d6; _unsign_token=6d3914d8fc8f98bbd0e5159fb0c5f343; user_from=1; cna=vI+4E+zPIjoCAXoOJ+CTzTYk; UM_distinctid=16439e01220271-014290d7042441-454c092b-140000-16439e01221387; xmgid=ba6a624f-3294-49e4-b53c-b93da0f2ce10; CNZZDATA921634=cnzz_eid%3D360374184-1529974107-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529979589; CNZZDATA2629111=cnzz_eid%3D1553856013-1529975217-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529980617; t_sign_auth=0; xm_token=e3b02a3b8632fc50ed7a0201feb9abe6; uidXM=376831079; member_auth=gjybE4sa6W40gaHCRI8zcyRNsuDRGWWGyYQGj7V44wsidYcAZouvwKuSRQ1A2yGTr1EmUObKhXkb; user=376831079%22tkfhre%22images%2Favatar_new%2F1035740965_1529984825.jpg%220%220%22%3Ca+href%3D%27http%3A%2F%2Fwww.xiami.com%2Fwebsitehelp%23help9_3%27+%3ELv1%3C%2Fa%3E%220%220%220%226267170342%221529984828; isg=",
					"fd2eaf10c4be745ffb4c19408498f8d6"
				},
				{
					"363500683",
					"359297293",
					"364089363",
					"360501612",
					"355288362"
				}
			},
			{
				{//四    3325874685----1d91l2yk冻结
					"gid=152997932279965; join_from=1zufSNtP6D010%2FjCCA; _xiamitoken=fd2eaf10c4be745ffb4c19408498f8d6; _unsign_token=6d3914d8fc8f98bbd0e5159fb0c5f343; user_from=1; cna=vI+4E+zPIjoCAXoOJ+CTzTYk; UM_distinctid=16439e01220271-014290d7042441-454c092b-140000-16439e01221387; xmgid=ba6a624f-3294-49e4-b53c-b93da0f2ce10; CNZZDATA921634=cnzz_eid%3D360374184-1529974107-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529979589; CNZZDATA2629111=cnzz_eid%3D1553856013-1529975217-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529980617; t_sign_auth=0; _m_h5_tk=688809ff12e05ff7762b1eb6ba3ae29b_1529987933875; _m_h5_tk_enc=4ada2c0d6ca50a32bbf70b041d03fa86; xm_token=bf3ed20af55df795b678716d20bd00a3; uidXM=376831236; member_auth=1m2YS4pK6j9lgffDG9gxdSIf4bbRGjnTyYxQ2bckvld3LIoBZdH6kKuSRQ1A2yGRq14mVfvDnmwZ; user=376831236%22qvosgg%22images%2Favatar_new%2F1640453301_1529984946.jpg%220%220%22%3Ca+href%3D%27http%3A%2F%2Fwww.xiami.com%2Fwebsitehelp%23help9_3%27+%3ELv1%3C%2Fa%3E%220%220%220%2208a1af7b61%221529984947; isg=",
					"fd2eaf10c4be745ffb4c19408498f8d6"
				},
				{
					"350342940",
					"387320778",
					"388467203",
					"366098559",
					"387325494"
				}
			},
			{
				{//五    //2692333704----wp6v63z2可以登录
					"gid=150668533445329; _unsign_token=6b907dbf6968901297d4682a094c0ffe; bdshare_firstime=1506685329664; cna=d7TOEdKY5ykCAXaQhSRqEaDU; UM_distinctid=16358249d85db-085b52468-671d1178-100200-16358249d8666; xmgid=42672b7f-6913-44c0-a35a-652908727ee1; user_from=1; _xiamitoken=19ad8ec2f28a3b3487a0bf3988eb7aae; xm_token=57c2fe77636a568c88a3f50110c72e3d; uidXM=377034552; member_auth=gTjPSIlM6DpgiqSUS4A4ICNN6ezcT2KElohV3LF5tQJ2dt9fNouqmquSRQxI2ySWrVomwQ0SCJTwyFfM%2F1EpERMK; user=377034552%22%E5%80%BE%E5%9F%8E%E7%89%A1%E4%B8%B9%E8%8A%B1%22images%2Favatar_new%2F1032126541_1530176277.jpg%220%226%22%3Ca+href%3D%27http%3A%2F%2Fwww.xiami.com%2Fwebsitehelp%23help9_3%27+%3ELv1%3C%2Fa%3E%220%220%220%220e6e7ef89c%221531626124; t_sign_auth=0; CNZZDATA921634=cnzz_eid%3D329892700-1526190701-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1531625738; CNZZDATA2629111=cnzz_eid%3D295240315-1526192282-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1531623719; isg=",
					"19ad8ec2f28a3b3487a0bf3988eb7aae"
				},
				{
					"364259700",
					"363040632",
					"281449075",
					"358948315",
					"273664561"
				}
			},
			{
				{//六   3335732806----89ub63qy冻结
					"gid=152997932279965; join_from=1zufSNtP6D010%2FjCCA; _xiamitoken=fd2eaf10c4be745ffb4c19408498f8d6; _unsign_token=6d3914d8fc8f98bbd0e5159fb0c5f343; user_from=1; cna=vI+4E+zPIjoCAXoOJ+CTzTYk; UM_distinctid=16439e01220271-014290d7042441-454c092b-140000-16439e01221387; xmgid=ba6a624f-3294-49e4-b53c-b93da0f2ce10; CNZZDATA2629111=cnzz_eid%3D1553856013-1529975217-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529980617; t_sign_auth=0; _m_h5_tk=688809ff12e05ff7762b1eb6ba3ae29b_1529987933875; _m_h5_tk_enc=4ada2c0d6ca50a32bbf70b041d03fa86; CNZZDATA921634=cnzz_eid%3D360374184-1529974107-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529984989; xm_token=aaf6b1c091505788b4fdb4cf890d85c7; uidXM=376832407; member_auth=026cE4tPvWs2gqeQRYozcCcd5uLcEjSCydlQieZ5tAsqJYhfZ9aqkKuSRQ1A2yKXqF8mR%2FfPgGMM; user=376832407%22czcmhr%22images%2Favatar_new%2F586414587_1529985867.jpg%220%220%22%3Ca+href%3D%27http%3A%2F%2Fwww.xiami.com%2Fwebsitehelp%23help9_3%27+%3ELv1%3C%2Fa%3E%220%220%220%22712a782771%221529985868; isg=",
					"fd2eaf10c4be745ffb4c19408498f8d6"
				},
				{
					"357338614",
					"366668348",
					"356467127",
					"392710780",
					"366856168"
				}
			},
			{
				{//七   3347858115----e05m81gz冻结
					"gid=152997932279965; join_from=1zufSNtP6D010%2FjCCA; _xiamitoken=fd2eaf10c4be745ffb4c19408498f8d6; _unsign_token=6d3914d8fc8f98bbd0e5159fb0c5f343; user_from=1; cna=vI+4E+zPIjoCAXoOJ+CTzTYk; UM_distinctid=16439e01220271-014290d7042441-454c092b-140000-16439e01221387; xmgid=ba6a624f-3294-49e4-b53c-b93da0f2ce10; t_sign_auth=0; _m_h5_tk=688809ff12e05ff7762b1eb6ba3ae29b_1529987933875; _m_h5_tk_enc=4ada2c0d6ca50a32bbf70b041d03fa86; CNZZDATA921634=cnzz_eid%3D360374184-1529974107-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529984989; CNZZDATA2629111=cnzz_eid%3D1553856013-1529975217-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529986017; xm_token=f106e40e843e9e2d52def9ed776aac29; uidXM=376835528; member_auth=0D2YH41Nuj0116WfH4s4ICNN5%2BTVHTKBlotRjLQl4gYhdo9bNdb8w6uSRQ1A2yWWqlAmSebVlXsS; user=376835528%22mkyxpl%22images%2Favatar_new%2F6993605_1529988052.jpg%220%220%22%3Ca+href%3D%27http%3A%2F%2Fwww.xiami.com%2Fwebsitehelp%23help9_3%27+%3ELv1%3C%2Fa%3E%220%220%220%22cab09081d8%221529988054; isg=",
					"fd2eaf10c4be745ffb4c19408498f8d6"
				},
				{
					"363525464",
					"376666656",
					"363458714",
					"242636731",
					"273681815"
				}
			},
			{
				{//八         otddmi       2152880098----0ao2dd11冻结
					"gid=152997932279965; _xiamitoken=fd2eaf10c4be745ffb4c19408498f8d6; _unsign_token=6d3914d8fc8f98bbd0e5159fb0c5f343; user_from=1; cna=vI+4E+zPIjoCAXoOJ+CTzTYk; UM_distinctid=16439e01220271-014290d7042441-454c092b-140000-16439e01221387; xmgid=ba6a624f-3294-49e4-b53c-b93da0f2ce10; _m_h5_tk=688809ff12e05ff7762b1eb6ba3ae29b_1529987933875; _m_h5_tk_enc=4ada2c0d6ca50a32bbf70b041d03fa86; join_from=1zufSNtP6D010%2FjCCA; CNZZDATA921634=cnzz_eid%3D360374184-1529974107-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529991985; CNZZDATA2629111=cnzz_eid%3D1553856013-1529975217-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1529991417; xm_token=8bc642fce48b4ea5cda101501b4f7fd0; uidXM=376726201; member_auth=123PS9xJ7D9ui6OWTI05cHcXtbDTEjGAkdlS3uN5tAQlddhcN4bwl6uSRQ1P2iaRqFkmS%2FnIiWYX; user=376726201%22otddmi%22images%2Favatar_new%2F2031346552_1529892307.jpg%220%22326%22%3Ca+href%3D%27http%3A%2F%2Fwww.xiami.com%2Fwebsitehelp%23help9_3%27+%3ELv4%3C%2Fa%3E%220%220%221%2232ada62eca%221529992737; t_sign_auth=1; isg=",
					"fd2eaf10c4be745ffb4c19408498f8d6"
				},
				{
					"30042419",
					"374516817",
					"376696285",
					"277552335",
					"387403003"
				}
			}
			





	};
	
	
	
	private String content = "默认";
	
	
	
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[][][] getList() {
		return list;
	}

	public void setList(String[][][] list) {
		this.list = list;
	}

	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		new HttpGetPost().qidong();
	}
	
	
	public void qidong() throws InterruptedException, IOException{
		System.out.println("qidong");
		for (int i = 0; i < 100000; i++) {
			//遍历所有并发布
			new HttpGetPost().bianliYibian(i);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//符号库
	String fuhaoarray [] ={" - "," = "," + "," , "," . "," * "," / "," | "," \\ "," ` "," ~ "," ! "," @ "," # "," $ "," % "," ^ "," & "};
	//使用符号id
	int fuhaoid = 0;
	
	
	
	
	
	
	
	
	
	//启动一遍，遍历所有小单并发布
	public void bianliYibian(int index) throws InterruptedException{

		System.out.println("当前应该发布第几列歌单");
		int lie = index%5;
		System.out.println(lie);
		
		System.out.println("小单遍历启动评论功能"+new java.util.Date().toString());
		
		
		String content = new HttpGetPost().getContent();
		
		
		
		String list [][][] = new HttpGetPost().getList();
		
		
		//遍历一列
		
		for (int i = 0; i < list.length; i++) {
			
			
			
			
			
			
			
			System.out.println("生成内容");
			
			int num=(int)(Math.random()*10001000);			//返回大于等于0小于n之间的随机数
			
			System.out.println("符号id"+fuhaoid);
			content =num+fuhaoarray[fuhaoid];
			content+= "_______843"+fuhaoarray[fuhaoid];
			content+= "首"+fuhaoarray[fuhaoid];
			content+= "抖"+fuhaoarray[fuhaoid]+fuhaoarray[fuhaoid]+fuhaoarray[fuhaoid];
			content+= "音"+fuhaoarray[fuhaoid];
			content+= "音"+fuhaoarray[fuhaoid];
			content+= "乐"+fuhaoarray[fuhaoid];
			content+= "下"+fuhaoarray[fuhaoid];
			content+= "载"+fuhaoarray[fuhaoid];
			content+= "   http://250xyz.xyz/douyin/jihe4ban.html?a=xiao   "+fuhaoarray[fuhaoid]+num;
			System.out.println(content);
			
			
			
			
			
			
			
			
			String yigezhanghu[][] = list[i];
			
			int j = lie;
				String cockie = yigezhanghu[0][0];
				String xiamitoken = yigezhanghu[0][1];
				String oid = yigezhanghu[1][j];
				
//				System.out.println(cockie);
//				System.out.println(xiamitoken);
				System.out.println(oid);
				System.out.println("检查是否第一小单");
					System.out.println("发布");
					
					try {
						new HttpGetPost().fabupinglun(cockie, xiamitoken, oid,content);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					//延迟
					for (int k = 0; k < 3; k++) {
						Thread.sleep(1000);
						System.out.print(k+",");
					}
					
					System.out.println("再次检查是否第一小单");
					
					
					
					
					//延迟
					for (int k = 0; k < 10; k++) {
						Thread.sleep(1000);
						System.out.print(k+",");
					}
				}
				System.out.println();
		}
		
	
	
	
	//发表评论
	public String fabupinglun(String cockie , String xiamitoken , String oid,String content) throws IOException{
		String url = "https://www.xiami.com/commentlist/add?type=2&oid=OID&relids=&mode=ajax&_xiamitoken=XIAMITOKEN";
		
		
		int num=(int)(Math.random()*1000);			//返回大于等于0小于n之间的随机数
		content = content+"     "+num;
		
		
		
	 	url = url.replaceAll("OID", ""+oid);
	 	url = url.replaceAll("XIAMITOKEN", ""+xiamitoken);
		
		Map<String, String> datamap = new HashMap<String, String>();
		datamap.put("type", "2");
		datamap.put("content", content);
		
		String cookie = cockie;
		
		Map<String, String> headermap = new HashMap<String, String>();
		
  		headermap.put("referer", "https://www.xiami.com/collect/"+oid);
  		
		String jieguo = this.httpPost(url, datamap, cookie,headermap);
		jieguo = new UnicodeToUtf8().decodeUnicode(jieguo);
		
		System.out.println(jieguo);
		
		if (jieguo.indexOf("\"status\":\"ok\"")>-1) {
			System.out.println("ok");
		}else{
			System.out.println(jieguo);
		}
		
		return jieguo;
	}
	
	
	
	//检测是否是第一个
	public String dyno1(String url) {
		// 获取源码
		Document doc = null;
		String jieguo = "";
		try {
			doc = this.httpGet(url, "gid=152997932279965; _xiamitoken=fd2eaf10c4be745ffb4c19408498f8d6; _unsign_token=6d3914d8fc8f98bbd0e5159fb0c5f343; user_from=1; cna=vI+4E+zPIjoCAXoOJ+CTzTYk; UM_distinctid=16439e01220271-014290d7042441-454c092b-140000-16439e01221387; xmgid=ba6a624f-3294-49e4-b53c-b93da0f2ce10; _m_h5_tk=688809ff12e05ff7762b1eb6ba3ae29b_1529987933875; _m_h5_tk_enc=4ada2c0d6ca50a32bbf70b041d03fa86; uidXM=376828102; CNZZDATA921634=cnzz_eid%3D360374184-1529974107-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1530057202; CNZZDATA2629111=cnzz_eid%3D1553856013-1529975217-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1530061154; xm_token=ebb2c9a075490f1e3dc34fd658e9c13b; member_auth=2TDLG4ZPum1u0aiWT4w2e3VN5e3USTXVlttWjb8otVZ2IIlcNYCtx6uSRQ1A2iiSqFomVuzVgmMK; user=376828102%22rayoht%22images%2Favatar_new%2F750052319_1529982542.jpg%220%22151%22%3Ca+href%3D%27http%3A%2F%2Fwww.xiami.com%2Fwebsitehelp%23help9_3%27+%3ELv3%3C%2Fa%3E%220%220%220%225d47a2c085%221530061716; t_sign_auth=0; isg=");

			Elements links = doc.select("div#wall_list ul li"); //带有href属性的a元素
			
//			System.out.println(links.get(0).toString());
			//如果没有评论则直接返回false
			if(links.size()==0){
				
				System.out.println("无评论列表请注意----------");
				return "false";
			}
			
			if(links.get(0).toString().indexOf("250xyz.xyz")>0){
				
				return "true";
			}else{
//				System.out.println("未找到250");
//				System.out.println(doc.toString());
				
				return "false";
			}
			
		} catch (IOException e) {
			// 超过指定时间未返回则发消息提示
			System.out.println("dyno1超时");
			return "chaoshi";
		}
	}
}
