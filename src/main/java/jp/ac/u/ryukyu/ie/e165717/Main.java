package jp.ac.u.ryukyu.ie.e165717;

import java.util.Scanner;

/**
 * Created by e165717 on 2017/02/02. 
 */
public class Main {
    static int bd[]  = new int[25]; // 盤面(0:空，1:○，2:×)

    /**
     * 盤面を表示する
     */
    public static void displayBoard() {
        int i;
        for (i = 0; i < 25; i++) {
            if(bd[i] == 1) {
                System.out.print("○");
            } else if(bd[i] == 2) {
                System.out.print("×");
            } else {
                System.out.print(" " + i);
            }
            // 5個表示したら改行
            if (i % 5 == 4) {
                System.out.print("\n");
            }
        }
    }

    /**
     * 勝敗の判定
     * 戻り値
     *   0:続行，1:○の勝，2:×の勝，3:引き分け
     */
    public static int judge() {
        int i;
        // 横の検査
        if (bd[0] != 0 && bd[0] == bd[1] && bd[0] == bd[2] && bd[0] == bd[3] && bd[0] == bd[4]) return bd[0];
        if (bd[5] != 0 && bd[5] == bd[6] && bd[5] == bd[7] && bd[5] == bd[8] && bd[5] == bd[9]) return bd[5];
        if (bd[10] != 0 && bd[10] == bd[11] && bd[10] == bd[12] && bd[10] == bd[13] && bd[10] == bd[14]) return bd[10];
        if (bd[15] != 0 && bd[15] == bd[16] && bd[15] == bd[17] && bd[15] == bd[17] && bd[15] == bd[18]) return bd[15];
        if (bd[20] != 0 && bd[20] == bd[21] && bd[20] == bd[22] && bd[20] == bd[23] && bd[20] == bd[24]) return bd[20];
        // 縦の検査
        if (bd[0] != 0 && bd[0] == bd[5] && bd[0] == bd[10] && bd[0] == bd[15] && bd[0] == bd[20]) return bd[0];
        if (bd[1] != 0 && bd[1] == bd[6] && bd[1] == bd[11] && bd[1] == bd[16] && bd[1] == bd[21]) return bd[1];
        if (bd[2] != 0 && bd[2] == bd[7] && bd[2] == bd[12] && bd[2] == bd[17] && bd[2] == bd[22]) return bd[2];
        if (bd[3] != 0 && bd[3] == bd[8] && bd[3] == bd[13] && bd[3] == bd[18] && bd[3] == bd[23]) return bd[3];
        if (bd[4] != 0 && bd[4] == bd[9] && bd[4] == bd[14] && bd[4] == bd[19] && bd[4] == bd[24]) return bd[4];
        // 引き分けの検査
        for (i = 0; i < 25; i++) {
            if (bd[i] == 0) return 0; // 一つでも空きがあれば続行
        }
        return 5;
    }

    /**
     * コンピュータの手
     * 空いているところを見つけたら，そこに置く
     */
    public static int computer() {
        int i;

        for (i = 0; i < 25; i++) {
            if (bd[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        int r = 0;
        // 盤面の初期化
        int i;
        for (i = 0; i < 25; i++) {
            bd[i] = 0;
        }
        displayBoard();
        while (true) {
            // 人間の番
            System.out.print("\nあなたの番です。\n");
            System.out.print("どこに置きますか? ");
            Scanner sc = new Scanner(System.in);
            String a = sc.next();
            int x = Integer.parseInt(System.console().readLine());
            if (x < 0 || x >= 25 || bd[x] != 0) {
                System.out.print("反則です。あなたの負けです。\n");
                break;
            }
            bd[x] = 1;
            displayBoard();
            // 判定
            r = judge();
            if (r != 0) break;
            // コンピュータの番
            System.out.print("\nコンピュータの番です。\n");
            int y = computer();
            bd[y] = 2;
            displayBoard();
            System.out.print("コンピュータは" + y + "に置きました。\n");
            // 判定
            r = judge();
            if (r != 0) break;
        }
    }
}
