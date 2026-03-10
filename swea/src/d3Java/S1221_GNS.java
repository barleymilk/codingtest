package d3Java;

import java.io.*;
import java.util.*;

public class S1221_GNS {
	static String[] strNums = new String[] {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
	static HashMap<String, Integer> strMap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		strMap = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			strMap.put(strNums[i], i);
		}
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			String pass = st.nextToken();
			int N = Integer.parseInt(st.nextToken());
			
			List<Integer> nums = new ArrayList<>();
			st = new StringTokenizer(br.readLine().trim()); 
			for (int i = 0; i < N; i++) {
				String temp = st.nextToken();
				int num = strMap.get(temp);
				nums.add(num);
			}
			Collections.sort(nums);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				String strNum = strNums[nums.get(i)];
				sb.append(strNum).append(" ");
			}
			
			System.out.println(pass);
			System.out.println(sb.toString().trim());
		}
	}
}
