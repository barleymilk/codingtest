package sswork.ilta;

public class BilliardTest {
    public static void main(String[] args) {
        // 내 공(White)과 목적구(Target)의 좌표
        double whiteX = 0, whiteY = 0;
        double targetX = 10, targetY = 10;

        // 1. 두 공 사이의 거리와 각도 계산
        double dx = targetX - whiteX;
        double dy = targetY - whiteY;
        
        // 피타고라스 정리 (거리)
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        // 2. atan2를 이용한 각도 계산 (라디안 -> 디그리)
        double radian = Math.atan2(dy, dx);
        double degree = Math.toDegrees(radian);

        // 3. 일타싸피 좌표계(북쪽 0도, 시계방향)로 변환
        double angle = 90 - degree;
        if (angle < 0) angle += 360;

        System.out.printf("거리: %.2f, 조준 각도: %.2f도", distance, angle);
    }
}