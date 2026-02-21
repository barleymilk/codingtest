package sswork.ilta;

public class FinalPrac {
  public static void main(String[] args) {
    // ... (위쪽 소켓 통신 및 데이터 수신 부분은 그대로 유지) ...

    // 1. 내 공과 목적구 설정
    float whiteBall_x = balls[0][0];
    float whiteBall_y = balls[0][1];

    float targetBall_x = -1;
    float targetBall_y = -1;

    // 선공(1, 3, 5), 후공(2, 4, 5)에 따른 타겟 설정
    int[] targetOrder = (order == 1) ? new int[]{1, 3, 5} : new int[]{2, 4, 5};

    for (int num : targetOrder) {
        if (balls[num][0] != -1 && balls[num][1] != -1) {
            targetBall_x = balls[num][0];
            targetBall_y = balls[num][1];
            break;
        }
    }

    // 2. 가장 가까운 홀(Hole) 찾기
    double minDistanceToHole = Double.MAX_VALUE;
    int[] bestHole = HOLES[0];

    for (int[] hole : HOLES) {
        double dx = hole[0] - targetBall_x;
        double dy = hole[1] - targetBall_y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        
        if (dist < minDistanceToHole) {
            minDistanceToHole = dist;
            bestHole = hole;
        }
    }

    // 3. 고스트 볼(조준점) 계산
    // 공의 반지름 (일타싸피 공 반지름은 약 2.865cm 정도이나, 
    // 충돌 효율을 위해 지름인 5.73보다 살짝 작게 5.5 정도로 잡는 것이 팁입니다)
    double diameter = 5.55; 

    double dx_TH = bestHole[0] - targetBall_x;
    double dy_TH = bestHole[1] - targetBall_y;
    double dist_TH = Math.sqrt(dx_TH * dx_TH + dy_TH * dy_TH);

    // 목적구에서 홀 반대 방향으로 공 지름만큼 떨어진 지점
    double ghostX = targetBall_x - (dx_TH / dist_TH * diameter);
    double ghostY = targetBall_y - (dy_TH / dist_TH * diameter);

    // 4. 내 공에서 고스트 볼까지의 각도 및 거리 계산
    double dx_WG = ghostX - whiteBall_x;
    double dy_WG = ghostY - whiteBall_y;

    // Math.atan2로 한 번에 각도 구하기
    double radian = Math.atan2(dy_WG, dx_WG);
    float angle = (float) Math.toDegrees(radian);

    // 5. 일타싸피 좌표계(북쪽 0도, 시계방향)로 변환
    angle = (float) (90 - angle);
    if (angle < 0) angle += 360;

    // 6. 거리 기반 힘(Power) 조절
    double distance = Math.sqrt(dx_WG * dx_WG + dy_WG * dy_WG);
    // 거리가 멀수록 세게, 목적구와 홀 사이가 멀수록 세게 (상수 0.6 등은 테스트하며 조정)
    float power = (float) (distance * 0.5 + minDistanceToHole * 0.2); 
    if (power > 100) power = 100; // 최대 파워 제한

    // ... (아래쪽 데이터 전송 부분으로 연결) ...
  }
}
