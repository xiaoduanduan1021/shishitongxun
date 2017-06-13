package util.qiniu;

import com.qiniu.util.Auth;

public class qiniuGetToken {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "eiG_hSVKio6Yu7ouYmUDWoOAdSiZreYTJwkUL0K4";
	String SECRET_KEY = "6j8-RmG6JS9NYibZFwzsdufvvQVn9GLNhFVby8ND";
	// 要上传的空间
	String bucketname = "keke";

	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	public static void main(String[] args) {
	}
}
