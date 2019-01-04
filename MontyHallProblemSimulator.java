package com.jc.sideproject.ArticleNotification;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
最終機率結果
機率= (1-(1/門數))*(1/更換數字後剩下的門數量)
*/

public class MontyHallProblemSimulator {
	static int COUNTER = 0;
	static int AWARDTIMES = 0;
	
	public static void main(String[] args) {
		int gameTimes = 0;
		int baseNumber = 1000;	//how many doors
		while(gameTimes < 10000) {
			simulate(baseNumber);
			gameTimes++;
		}
	}
	
	public static void simulate(int baseNumber) {
		COUNTER++;
		Scanner scanner = new Scanner(System.in);
    System.out.println("請輸入1個數字(1 ~ " + baseNumber + ")：");
		
//    int pickNum = scanner.nextInt();   
    int pickNum = (int)(Math.random() * baseNumber + 1);
		while(true) {
			if(pickNum < 1 || pickNum > baseNumber) {
				System.out.println("請重新選擇");
		    System.out.println("請輸入1個數字(1 ~ " + baseNumber + ")：");
				pickNum = scanner.nextInt(); 
			}else {
				break;
			}
		}
    System.out.println("挑選為" + pickNum);
		int lotteryNum = (int)(Math.random() * baseNumber + 1);
		
		List<Integer> numberPool = new ArrayList<Integer>();
		for(int i = 1; i <= baseNumber ; i++) {
			numberPool.add(i);
		}
		
		//刪去
		while(numberPool.size() > 34) {
			if(pickNum == lotteryNum) {
				int removeNum = 0;
				while(true) {
					removeNum = (int)(Math.random() * baseNumber + 1);
					if(removeNum != lotteryNum) {
						break;
					}
				}
				numberPool.remove((Object)removeNum);
			}else {
				int removeNum = 0;
				while(true) {
					removeNum = (int)(Math.random() * baseNumber + 1);
					if(removeNum != lotteryNum && removeNum != pickNum) {
						break;
					}
				}
				numberPool.remove((Object)removeNum);
			}
		}
		List<Integer> remainingNumberPool = numberPool;
		
		//更換
		Scanner scanner2 = new Scanner(System.in);
//		System.out.println("是否更換選擇?(y/n)");
//		String answer = scanner2.nextLine();
		String answer = "y";
		//TODO 留下多個
		if(answer.equals("y")) {
			remainingNumberPool.remove((Object)pickNum);
			Random randomGen = new Random();
			int index = randomGen.nextInt(remainingNumberPool.size()); 
			pickNum = remainingNumberPool.get(index);
		}
		
		//開獎
		System.out.println("你最終選擇是:" + pickNum);
		System.out.println("中獎號碼是:" + lotteryNum);
		if(pickNum == lotteryNum) {
			AWARDTIMES++;
		}
		
		//算機率
		System.out.println("這是第" + COUNTER + "次遊戲,中獎次數:" + AWARDTIMES);
		System.out.println("機率是"+ new Double(AWARDTIMES)/new Double(COUNTER));
	}
}
