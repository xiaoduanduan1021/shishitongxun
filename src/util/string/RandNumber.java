package util.string;

import java.util.Random;

public class RandNumber {

	//生成随机数字字符串
	//参数获取位数
	public String getRandNumber(int charLength){
		String randNumberString = String.valueOf(Math.random());
		randNumberString = randNumberString.substring(2, charLength+2);
		return randNumberString;
	}
	
	
	
	//产生指定个数的随机数不重复，指定范围
	//max不包括本身，例如max是10则生成中最大是9
	public int[] getRandmNumber(Integer num,Integer max){
		if (num>max) {
			return null;
		}
		Random random = new Random();
		int a[] = new int[num];
		for (int i = 0; i < a.length; i++) {
			//生成一个介于0到max的数字
			a[i] = random.nextInt(max);
			for (int j = 0; j < i; j++) {
				if(a[i] == a[j]) {//如果重复，退回去重新生成随机数
					i--;
					break;
				}
			}
		}
		return a;
	}

	
	//要生成在[min,max]之间的随机整数，生成1个指定范围的随机数,,包括最小数，但不包括最大数
	public int getRandmMinMax(int min,int max){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
	}
	
	
	
	
	//测试--------------------------------------------------------------
	//测试生成不重复的指定个数和范围的随机数
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(new RandNumber().getRandmMinMax(0,10)); 
		}
	}
	
	
}
