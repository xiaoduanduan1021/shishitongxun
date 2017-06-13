package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class test {
public static void main(String[] args) throws UnsupportedEncodingException {
	
//	System.out.println(URLDecoder.decode("%u6781%u54C1", "UTF-8"));
	System.out.println(new String("%u6781%u54C1".getBytes(),"UTF-8"));
}
}
