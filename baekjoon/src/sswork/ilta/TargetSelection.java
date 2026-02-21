package sswork.ilta;

import java.util.ArrayList;
import java.util.List;

public class TargetSelection {
    public static void main(String[] args) {
        double whiteX = 10, whiteY = 10;
        
        // 목적구들의 좌표 리스트 (시험에서는 배열로 들어옴)
        double[][] targets = {
            {50, 50},   // 1번 공
            {120, 80},  // 2번 공
            {200, 10}   // 3번 공
        };

        double minDistance = Double.MAX_VALUE;
        double[] bestTarget = null;

        for (double[] target : targets) {
            // 아직 테이블에 남아있는 공인지 확인 (예: -1이 아니면)
            if (target[0] == -1) continue;

            double dx = target[0] - whiteX;
            double dy = target[1] - whiteY;
            double dist = Math.sqrt(dx * dx + dy * dy);

            // 가장 가까운 공을 최우선 목표로 설정
            if (dist < minDistance) {
                minDistance = dist;
                bestTarget = target;
            }
        }

        if (bestTarget != null) {
            System.out.printf("최우선 목표 공: (%.2f, %.2f)\n", bestTarget[0], bestTarget[1]);
        }
    }
}