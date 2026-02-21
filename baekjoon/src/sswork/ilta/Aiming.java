package sswork.ilta;

public class Aiming {
    public static void main(String[] args) {
        // 1. 기본 설정 (단위: mm)
        double r = 5.73 / 2.0; // 공의 반지름 (실제 규격에 맞게 조정)
        double diameter = 2 * r;

        // 좌표 설정 (내 공, 목적구, 구멍)
        double whiteX = 10, whiteY = 10;
        double targetX = 100, targetY = 100;
        double holeX = 200, holeY = 200;

        // 2. 목적구에서 홀까지의 거리와 방향 계산
        double dx_TH = holeX - targetX;
        double dy_TH = holeY - targetY;
        double dist_TH = Math.sqrt(Math.pow(dx_TH, 2) + Math.pow(dy_TH, 2));

        // 3. 고스트 볼(조준점) 좌표 구하기
        // 목적구 중심에서 홀 반대 방향으로 '지름'만큼 떨어진 좌표
        double ghostX = targetX - (dx_TH / dist_TH * diameter);
        double ghostY = targetY - (dy_TH / dist_TH * diameter);

        // 4. 내 공(White)에서 고스트 볼(Ghost)까지의 각도 계산
        double dx_WG = ghostX - whiteX;
        double dy_WG = ghostY - whiteY;

        double radian = Math.atan2(dy_WG, dx_WG);
        double degree = Math.toDegrees(radian);

        // 5. 일타싸피 좌표계(북쪽 0도, 시계방향) 변환
        double angle = 90 - degree;
        if (angle < 0) angle += 360;

        System.out.printf("최종 조준 각도: %.2f도\n", angle);
        System.out.printf("고스트 볼 위치: (%.2f, %.2f)\n", ghostX, ghostY);
    }
}
