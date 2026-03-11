package d6Java;

import java.io.*;
import java.util.*;

public class S1257_K번째문자열 {
	static String str;
	static Set<String> charSet;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine().trim()); // 사전 순서 K번째
			str = br.readLine().trim(); // 문자열
			
			charSet = new HashSet<>();
			makeSubset();

			List<String> list = new ArrayList<>(charSet);
			Collections.sort(list);
			
			String answer = list.get(K-1); // K-1
			
			System.out.println("#" + tc + " " + answer);
		}
		
	}
	
	static void makeSubset() {
		for (int i = 0; i < str.length(); i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = i; j < str.length(); j++) {
				sb.append(str.charAt(j));
				charSet.add(sb.toString());
			}
		}
	}
}
