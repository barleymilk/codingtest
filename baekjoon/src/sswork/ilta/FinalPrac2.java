package sswork.ilta;

import java.util.*;

public class FinalPrac2 {
    // 경기장 규격 및 설정
    static final int TABLE_WIDTH = 254;
    static final int TABLE_HEIGHT = 127;
    static final double DIAMETER = 5.60; // 물리 엔진 오차 고려 (실제 5.73)
    static final float[][] HOLES = { {0, 0}, {127, 0}, {254, 0}, {0, 127}, {127, 127}, {254, 127} };

    public static void main(String[] args) {
        // 실제 시험 환경에서는 소켓 통신부 내부에서 이 로직을 호출하게 됩니다.
        // float[][] balls, int order 등을 수신했다고 가정합니다.
    }

    /**
     * 메인 전략 컨트롤러
     */
    public static float[] getPlayStrategy(float[][] balls, int order) {
        float whiteX = balls[0][0];
        float whiteY = balls[0][1];

        // 1. 타겟 공 선정
        float[] targetBall = selectTarget(balls, order);
        if (targetBall == null) return new float[]{0, 0};

        float tx = targetBall[0];
        float ty = targetBall[1];

        // 2. 가장 유리한 홀 선택
        float[] bestHole = selectBestHole(tx, ty);

        // 3. 고스트 볼 계산 및 벽 경계 예외 처리
        double[] ghostBall = getGhostBall(tx, ty, bestHole[0], bestHole[1]);
        ghostBall = adjustForWall(ghostBall[0], ghostBall[1]); // 벽 붙음 처리

        // 4. 각도 비교 및 샷 결정 (직구 vs 쿠션)
        double angleWH = getMathAngle(whiteX, whiteY, bestHole[0], bestHole[1]);
        double angleTH = getMathAngle(tx, ty, bestHole[0], bestHole[1]);
        double angleDiff = getAngleDiff(angleWH, angleTH);

        double targetX, targetY;
        float power;

        if (angleDiff > 80) { // 꺾기 각도가 너무 크면 쿠션 시도
            double[] cushionPos = getCushionPos(tx, ty, "TOP"); // 상황에 맞게 TOP/BOTTOM 선택
            targetX = cushionPos[0];
            targetY = cushionPos[1];
            power = calculatePower(whiteX, whiteY, targetX, targetY) + 15; // 쿠션은 더 세게
        } else {
            targetX = ghostBall[0];
            targetY = ghostBall[1];
            power = calculatePower(whiteX, whiteY, targetX, targetY);
        }

        // 5. 일타싸피 좌표계로 변환 (12시 방향 0도, 시계방향 증가)
        float finalAngle = convertToGameAngle(whiteX, whiteY, targetX, targetY);

        return new float[]{finalAngle, power};
    }

    // --- 세부 로직 함수들 ---

    /** 타겟 공 선정: -1이 아닌 공 중 순서에 맞는 공 찾기 */
    private static float[] selectTarget(float[][] balls, int order) {
        int[] targetOrder = (order == 1) ? new int[]{1, 3, 5} : new int[]{2, 4, 5};
        for (int i : targetOrder) {
            if (balls[i][0] != -1) return balls[i];
        }
        return null;
    }

    /** 가장 가까운 홀 찾기 */
    private static float[] selectBestHole(float tx, float ty) {
        float[] best = HOLES[0];
        double minD = Double.MAX_VALUE;
        for (float[] h : HOLES) {
            double d = Math.hypot(tx - h[0], ty - h[1]);
            if (d < minD) {
                minD = d;
                best = h;
            }
        }
        return best;
    }

    /** 고스트 볼 계산 */
    private static double[] getGhostBall(float tx, float ty, float hx, float hy) {
        double d = Math.hypot(hx - tx, hy - ty);
        return new double[]{
            tx - (hx - tx) / d * DIAMETER,
            ty - (hy - ty) / d * DIAMETER
        };
    }

    /** [예외 처리] 고스트 볼이 벽 밖으로 나가는 경우 보정 */
    private static double[] adjustForWall(double gx, double gy) {
        double r = DIAMETER / 2.0;
        double adjX = Math.max(r, Math.min(TABLE_WIDTH - r, gx));
        double adjY = Math.max(r, Math.min(TABLE_HEIGHT - r, gy));
        return new double[]{adjX, adjY};
    }

    /** 쿠션 위치 계산 */
    private static double[] getCushionPos(float tx, float ty, String side) {
        double r = DIAMETER / 2.0;
        if (side.equals("TOP")) return new double[]{tx, (TABLE_HEIGHT - r) * 2 - ty};
        if (side.equals("BOTTOM")) return new double[]{tx, -ty + r * 2};
        return new double[]{tx, ty};
    }

    /** 힘 계산: 거리에 따른 비례식 + 최소 파워 확보 */
    private static float calculatePower(double x1, double y1, double x2, double y2) {
        double d = Math.hypot(x2 - x1, y2 - y1);
        float p = (float) (d * 0.4 + 30); 
        return Math.min(100, p);
    }

    /** 일타싸피 좌표계 변환 함수 */
    private static float convertToGameAngle(double x1, double y1, double x2, double y2) {
        double radian = Math.atan2(y2 - y1, x2 - x1);
        float angle = (float) (90 - Math.toDegrees(radian));
        return (angle < 0) ? angle + 360 : angle;
    }

    /** 수학용 일반 각도(라디안->디그리) 계산 */
    private static double getMathAngle(double x1, double y1, double x2, double y2) {
        return Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
    }

    /** 두 각도의 최소 차이 계산 */
    private static double getAngleDiff(double a1, double a2) {
        double diff = Math.abs(a1 - a2);
        return (diff > 180) ? 360 - diff : diff;
    }
}