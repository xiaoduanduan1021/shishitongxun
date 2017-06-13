package util.string;

public class RandNumber {

	//生成随机数字字符串
	//参数获取位数
	public String getRandNumber(int charLength){
		String randNumberString = String.valueOf(Math.random());
		randNumberString = randNumberString.substring(2, charLength+2);
		return randNumberString;
	}
	public static void main(String[] args) {
		//System.out.println(new RandNumber().getRandNumber(6));
	}
}
