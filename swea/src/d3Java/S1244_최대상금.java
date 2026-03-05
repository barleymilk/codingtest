package d3Java;

import java.io.*;
import java.util.*;

public class S1244_최대상금 {
	static int N, c, maxMoney;
	static int[] nums;
	static Set<String> vSet;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1244.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			c = Integer.parseInt(st.nextToken());
			
			N = line.length();
			nums = new int[N];
			for (int i = 0; i < N; i++) {
				nums[i] = line.charAt(i) - '0';
			}
			
			// 선택 정렬 -> 순열로 선택하기 -> 반복
			maxMoney = 0;
			vSet = new HashSet<>();
			dfs(0);
			
			System.out.println("#" + tc + " " + maxMoney);
		}
	}
	
	public static void dfs(int count) {
		int currentMoney = getMoney(); // 배열을 숫자로 변환
		
		String state = "" + currentMoney + count;
		if (vSet.contains(state)) return;
		vSet.add(state);
		
		if (count == c) {
			maxMoney = Math.max(maxMoney, currentMoney);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				swap(i, j);
				dfs(count + 1);
				swap(i, j);
			}
		}
	}
	
	public static int getMoney() {
		int res = 0;
		for (int i = 0; i < N; i++) {
			res = res * 10 + nums[i];
		}
		return res;
	}
	
	public static void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
		return;
	}
}
