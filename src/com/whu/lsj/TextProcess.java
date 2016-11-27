package com.whu.lsj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 训练语料库和测试语料的处理
 * @author Enginner-Jie
 */
public class TextProcess {
	/**
	 * 将训练语料库处理成CRF要求的格式,不带词性的两列数据，4 tags：B M E S,句子空开
	 * @param filePath 待处理语料库文件路径
	 */
	public static void make_train_data(String filePath){
		long start = System.currentTimeMillis();
		File train_file = new File(filePath);
		File crf_train_file = new File(WordSeg.crf_train_file);
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(train_file));
			bw = new BufferedWriter(new FileWriter(crf_train_file));
			String line;
			while((line = br.readLine()) != null){
				String words_list[] = line.trim().split("  ");
				for (int i = 0; i < words_list.length; i++) {
					String word = words_list[i];  //word是单个词
					int len = word.length();
					if(len == 1){
						bw.write(word + "\tS\n");  //一个token
					}else if(len == 2){
						bw.write(word.charAt(0)+ "\tB\n");
						bw.write(word.charAt(1) + "\tE\n");
					}else if(len >= 2){
						bw.write(word.charAt(0)+ "\tB\n");
						for (int j = 1; j < len-1; j++)
							bw.write(word.charAt(j)+ "\tM\n");
						bw.write(word.charAt(len-1) + "\tE\n");  
					}else{
						bw.write("\n");
					}
				}
				bw.write("\n");
			}
			long end = System.currentTimeMillis();
			System.out.println("----------CRF训练语料转换完成！用时"+(end - start)/1000+"秒----------");
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bw != null & br != null){
				try {
					br.close();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将测试语料转换为CRF要求的格式：   单字   B(占位符)
	 * 传入文件编码 UTF-8
	 * @param filePath  测试语料文件路径
	 */
	public static void make_test_data(String filePath){
		long start = System.currentTimeMillis();
		File test_file = new File(filePath);
		File crf_test_file = new File(WordSeg.crf_test_file);
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(test_file));
			bw = new BufferedWriter(new FileWriter(crf_test_file));
			String line;
				
			while((line = br.readLine()) != null){
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) != ' '){  //跳开空格
						bw.write(line.charAt(i)+"\tB\n");  //B占位符
					}else{
						bw.write("\n");
					}
					bw.flush();
				}
				bw.write("\n");
			}
			
			long end = System.currentTimeMillis();
			System.out.println("----------CRF测试语料转换完成！用时"+(end - start)/1000+"秒----------");
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bw != null || br != null){
				try {
					br.close();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
	//	make_train_data("data/pku_training.utf8");
	//	make_test_data("test.utf8");
	}
}
