package Programmers;

/**
 * 자물쇠와 열쇠
 * link : https://school.programmers.co.kr/learn/courses/30/lessons/60059
 */
public class LockAndKey {
    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(solution(key, lock));
    }
    public static boolean solution(int[][] key, int[][] lock) {

        // 자물쇠의 기준
        // 확장된 자물쇠에서 기존의 자물쇠 위치를 찾기 위해 필요
        int offset = key.length - 1;

        for (int r = 0; r < key.length + lock.length - 1; r++) {
            for (int c = 0; c < key.length + lock.length - 1; c++) {
                // 이 반복문을 통해 열쇠의 기준을 이동 시킨다.

                for (int rotate = 0; rotate < 4; rotate++) {
                    // 4번 회전시키기 위한 반복문

                    // 확장된 자물쇠를 만든다.
                    int newLockSize = key.length * 2 + lock.length - 2;
                    int[][] newLock = new int[newLockSize][newLockSize];
                    for (int i = 0; i < lock.length; i++) {
                        for (int j = 0; j < lock.length; j++) {
                            newLock[i + offset][j + offset] = lock[i][j];
                        }
                    }

                    // newLock 에 해당 key 를 더한다.
                    match(newLock, key, r, c, rotate);

                    // 해당 키가 자물쇠를 열 수 있는지 확인한다.
                    if(check(newLock, offset, lock.length)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void match(int[][] newLock, int[][] key, int row, int col, int rotate) {
        int keyLen = key.length;
        for (int i = 0; i < keyLen; i++) {
            for (int j = 0; j < keyLen; j++) {
                switch (rotate){
                    case 0:
                        newLock[i + row][j + col] += key[i][j];
                        break;
                    case 1:
                        newLock[i + row][j + col] += key[j][keyLen-1 - i];
                        break;
                    case 2:
                        newLock[i + row][j + col] += key[keyLen-1 - i][keyLen-1 - j];
                        break;
                    case 3:
                        newLock[i + row][j + col] += key[keyLen-1 - j][i];
                        break;
                }
            }
        }
    }

    public static boolean check(int[][] newLock, int offset, int lockLength) {
        for (int i = 0; i < lockLength; i++) {
            for (int j = 0; j < lockLength; j++) {
                if (newLock[i + offset][j + offset] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
