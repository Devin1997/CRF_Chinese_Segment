package com.whu.lsj;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 调用CMD命令行执行exe文件
 * @author Enginner-Jie
 */
public class Command {
	/*DOS_text存取DOS输出的内容*/
	protected static List<String> DOS_text = new ArrayList<>();
	/**
	 * @param command  传入DOS命令
	 * @param Isread  是否读入DOS控制台内容
	 */
	public static void exeCMD(String command,boolean Isread){

		BufferedReader br = null;
		BufferedInputStream bis = null;
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec(command);
			if(Isread){
				bis = new BufferedInputStream(pr.getInputStream());
				br = new BufferedReader(new InputStreamReader(bis));
				String line;
				while ((line = br.readLine()) != null) {
				//	System.out.println(line);
					DOS_text.add(line);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		
	}
}
