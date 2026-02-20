package plus;

import java.io.*;
import java.util.*;

public class Q1759_암호만들기 {
  static int L, C;
  static char[] charArr, perm;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    L = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    charArr = new char[C];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < C; i++) {
      charArr[i] = st.nextToken().charAt(0);
    }

    Arrays.sort(charArr);

    perm = new char[L];
    dfs(0, 0);
  }

  static void dfs(int start, int depth) {
    if (depth == L) {
      if (check()) printPassword();
      return;
    }

    for (int i = start; i < C; i++) {
      perm[depth] = charArr[i];
      dfs(i + 1, depth + 1);
      perm[depth] = '0';
    }
  }

  static boolean check() {
    int aeiou = 0;
    int notAeiou = 0;
    for (int i = 0; i < L; i++) {
      char c = perm[i];
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') aeiou++;
      else notAeiou++;
    }
    if (aeiou >= 1 && notAeiou >= 2) return true;
    return false;
  }

  static void printPassword() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < L; i++) {
      sb.append(perm[i]);
    }
    System.out.println(sb);
  }
}
