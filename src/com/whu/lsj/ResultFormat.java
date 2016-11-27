package com.whu.lsj;

import java.io.*;
import java.util.Iterator;

/**
 * 将测试结果转化为分词结果文本形式
 * @author Enginner-Jie
 */
public class ResultFormat {
	/**
	 *
	 */
	public static void TextFormat(){

		File result = new File("data/seg_result.txt");
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(result));
			Iterator<String> it = Command.DOS_text.iterator();
			while(it.hasNext()) {
				String token = it.next();
				if (token.equals("")) {
					bw.write("\n");
					continue;
				}
				char word = token.charAt(0);
				char label = token.charAt(token.length() - 1);

				if (label == 'S' || label == 'E') {
					bw.write(word + "  ");
				} else {
					bw.write(word);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
