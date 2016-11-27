package com.whu.lsj;

import java.util.Scanner;

/**
 * 主测试类
 */
public class WordSeg {
	public static String crf_train_file = "data/crf_train.data";
	public static String crf_test_file = "data/crf_test.data";
//	private static String model_file = "data/crf_model.data";

	public void tarin(){
		String learn_cmd = "crf_learn -p 3 template " + crf_train_file + " model";
		Command.exeCMD(learn_cmd,true);
	}
	
	public void test(){
		String test_cmd = "crf_test -m model2.0 " + crf_test_file;
		Command.exeCMD(test_cmd,true);
		
	}
	public static void main(String[] args) {
		WordSeg ws = new WordSeg();
		System.out.print("开始CRF分词，是否需要训练语料库？1-是 2-否\n");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		if(a == 1){
			TextProcess.make_train_data("data/msr_training.utf8");  //训练语料路径
			ws.tarin();
		}
		TextProcess.make_test_data("data/1.txt"); //测试语料路径
		ws.test();

		ResultFormat.TextFormat();
	}
}