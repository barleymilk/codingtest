package 코딩테스트준비_기초.chap5_그래프와BFS;

import java.io.*;
import java.util.*;

public class Q10845_큐 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Queue<Integer> q = new LinkedList<>();
        
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            String[] arr = str.split(" ");
            String order = arr[0];
            if (order == "push") q.offer(Integer.parseInt(arr[1]));
            else if (order == "front") {}
            else if (order == "back") {}
            else if (order == "size") {}
            else if (order == "empty") {}
            else if (order == "pop") {}
        }
        
    }
}
