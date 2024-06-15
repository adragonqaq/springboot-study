package com.lzl.jsp.pk.util;

import java.util.Random;

/**
 * 
 *类的功能：生产随机数
 *类名ReturnRandom
 * @author LZL
 * @version 1.0.0
 *时间 2018年5月5日-下午12:27:55
 */
public class ReturnRandom {

	 public static int returnRandom(int i){
		 
		 Random random = new Random();
		 return random.nextInt(i) ;
	 }
	 public static void main(String[] args) {
		System.out.println(returnRandom(3));
	}
}
