package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S5658_보물상자비밀번호 {
	static int N, K;
	static char[] nums;
	static Set<String> pwArray;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s5658.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			nums = new char[N];
			String line = br.readLine().trim();
			for (int i = 0; i < N; i++) {
				nums[i] = line.charAt(i);
			}
			
			pwArray = new TreeSet<>(Collections.reverseOrder());
			for (int rotate = 0; rotate < N/4; rotate++) { // 시계방향 회전
				for (int i = 0; i < 4; i++) { // 한 변
					String password = "";
					for (int j = 0; j < N/4; j++) { // 비밀번호 1개 만들기
						password += nums[(N/4) * i + j];
					}
					pwArray.add(password);
				}
				
				// 회전시키기
				char last = nums[N-1];
				for (int i = N-1; i > 0; i--) {
					nums[i] = nums[i-1];
				}
				nums[0] = last;
			}
			
			// K번째 숫자 구하기
			Object[] result = pwArray.toArray();
			String kHex = (String) result[K-1];
			
			long ans = Long.parseLong(kHex, 16);
			System.out.println("#" + tc + " " + ans);
		}
	}
}
