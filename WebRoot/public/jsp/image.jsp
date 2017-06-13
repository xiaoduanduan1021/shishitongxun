<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"%>
<%@ page import="java.io.*"%>
<%!Color getRandColor(int fc, int bc) {//ç»å®èå´è·å¾éæºé¢è² 
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	//è®¾ç½®é¡µé¢ä¸ç¼å­ 
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	// å¨åå­ä¸­åå»ºå¾è±¡ 
	int width = 70, height = 25;
	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);

	// è·åå¾å½¢ä¸ä¸æ 
	Graphics g = image.getGraphics();

	//çæéæºç±» 
	Random random = new Random();

	// è®¾å®èæ¯è² 
	g.setColor(getRandColor(180, 200));
	g.fillRect(0, 0, width, height);

	//è®¾å®å­ä½ 
	g.setFont(new Font("Times New Roman", Font.PLAIN, 24));

	//ç»è¾¹æ¡ 
	//g.setColor(new Color()); 
	//g.drawRect(0,0,width-1,height-1); 

	// éæºäº§ç155æ¡å¹²æ°çº¿ï¼ä½¿å¾è±¡ä¸­çè®¤è¯ç ä¸æè¢«å¶å®ç¨åºæ¢æµå° 
	g.setColor(getRandColor(160, 200));
	for (int i = 0; i < 155; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(12);
		int yl = random.nextInt(12);
		g.drawLine(x, y, x + xl, y + yl);
	}

	// åéæºäº§ççè®¤è¯ç (4ä½æ°å­) 
	String sRand = "";
	for (int i = 0; i < 4; i++) {
		String rand = String.valueOf(random.nextInt(10));
		sRand += rand;
		// å°è®¤è¯ç æ¾ç¤ºå°å¾è±¡ä¸­ 
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
				.nextInt(110), 20 + random.nextInt(110)));//è°ç¨å½æ°åºæ¥çé¢è²ç¸åï¼å¯è½æ¯å ä¸ºç§å­å¤ªæ¥è¿ï¼æä»¥åªè½ç´æ¥çæ 
		g.drawString(rand, 13 * i + 9, 21);
	}

	// å°è®¤è¯ç å­å¥SESSION 
	session.setAttribute("rand", sRand);

	// å¾è±¡çæ 
	g.dispose();

	try {
		OutputStream os = response.getOutputStream();
		// è¾åºå¾è±¡å°é¡µé¢ 
		ImageIO.write(image, "JPEG", os);

		os.flush();
		os.close();
		out.clear();
		out = pageContext.pushBody();

	} catch (Exception e) {
	}
%>
