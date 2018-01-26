/*【程序11】 
 * 作者 若水飞天
题目：有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？ 
1.程序分析：可填在百位、十位、个位的数字都是1、2、3、4。组成所有的排列后再去 掉不满足条件的排列。 
 */
package com.delicacy.oatmeal.test.suanfa.classic;

public class EleventhNumberRange {
	public static void main(String[] args) {
		int count = 0;
		for (int x = 1; x < 5; x++) {
			for (int y = 1; y < 5; y++) {
				for (int z = 1; z < 5; z++) {
					if (x != y && y != z && x != z) {
						count++;
						System.out.print(x * 100 + y * 10 + z + "   ");
						if (count % 4 == 0) {
							System.out.println();
						}
					}
				}
			}
		}
		System.out.println("共有" + count + "个三位数");
	}
}
