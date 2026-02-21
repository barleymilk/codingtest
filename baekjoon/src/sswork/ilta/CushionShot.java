package sswork.ilta;

public class CushionShot {
    public static void main(String[] args) {
        // 당구대 크기 (예시: 가로 254, 세로 127)
        double TABLE_WIDTH = 254.0;
        double TABLE_HEIGHT = 127.0;

        // 좌표 설정
        double whiteX = 50, whiteY = 50;
        double targetX = 150, targetY = 80;

        // 1. 상단 벽(y = TABLE_HEIGHT)을 기준으로 목적구를 대칭 이동
        // 벽에서 목적구까지의 거리만큼 벽 너머에 가상의 점을 찍음
        double virtualTargetX = targetX;
        double virtualTargetY = TABLE_HEIGHT + (TABLE_HEIGHT - targetY);

        // 2. 내 공에서 가상의 목적구까지의 각도 계산
        double dx = virtualTargetX - whiteX;
        double dy = virtualTargetY - whiteY;

        double radian = Math.atan2(dy, dx);
        double degree = Math.toDegrees(radian);

        // 3. 일타싸피 좌표계 변환 (북쪽 0도, 시계방향)
        double angle = 90 - degree;
        if (angle < 0) angle += 360;

        // 4. 거리 계산 (실제 이동 거리이므로 힘 조절에 사용)
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        System.out.printf("쿠션 조준 각도: %.2f도\n", angle);
        System.out.printf("가상 목적구 위치: (%.2f, %.2f)\n", virtualTargetX, virtualTargetY);
    }
}
