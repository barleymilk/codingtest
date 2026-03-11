package d6Java;

import java.io.*;
import java.util.*;

public class S1257_K번째문자열_접미사배열 {
	static String str;
	static Integer[] sa; // Comparator 사용을 위해 Integer로 선언
	static int[] lcp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1257.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine().trim()); // 사전 순서 K번째
			str = br.readLine().trim(); // 문자열
			int n = str.length();

			// 1. 접미사 배열(SA) 초기화 및 정렬
			sa = new Integer[n];
			for (int i = 0; i < n; i++) sa[i] = i;

			// 직접 만든 compareSuffix를 사용하여 정렬
			Arrays.sort(sa, (o1, o2) -> compareSuffix(o1, o2, str));

			// 2. LCP 배열 계산 (인접한 접미사끼리 앞에서부터 몇 개 겹치는지)
			lcp = new int[n];
			for (int i = 1; i < n; i++) {
				int s1 = sa[i-1];
				int s2 = sa[i];
				int count = 0;

				while(
					s1 + count < n && s2 + count < n && // 범위 확인
					str.charAt(s1 + count) == str.charAt(s2 + count)) { // 두 글자가 같은지 확인
						count++; // 같으면 다음 글자로
				}

				lcp[i] = count;
			}

			// 3. K번째 문자열 찾기
			String answer = "none";
			long currentCount = 0;
			for (int i = 0; i < n; i++) {
				int newSubstrings = (n - sa[i]) - lcp[i];

				if (currentCount + newSubstrings >= K) {
					int targetLen = lcp[i] + (int)(K - currentCount);
					answer = str.substring(sa[i], sa[i] + targetLen);
					break;
				}
				currentCount += newSubstrings;
			}
			
			System.out.println("#" + tc + " " + answer);
		}
		
	}

	static int compareSuffix(int i, int j, String S) {
		int n = S.length();

		while(i < n && j < n) {
			if (S.charAt(i) != S.charAt(j)) {
				return S.charAt(i) - S.charAt(j); // 문자가 다르면 아스키코드 차이 반환
			}
			i++;
			j++;
		}

		// 한 쪽이 끝에 도달했다면, 길이가 더 짧은 쪽이 사전순으로 앞선다.
		// "ana" vs "anana"
		return (n - i) - (n - j);
	}

}
