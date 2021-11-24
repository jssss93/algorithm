package algorithm2.cjs_20211119;

import java.util.Arrays;

//����̴� ���� M���Ͱ� �ʿ��ϴ�. ��ó�� ������ ������ ���� ��� ���ع��ȱ� ������, ���ο� ���� �㰡�� ��û�ߴ�. 
//���δ� ����̳� �� ��ó�� ���� �� �ٿ� ���� ���� �㰡�� ���־���, ����̴� ���� ������ �������ܱ⸦ �̿��ؼ� ������ ���Ұ��̴�.
//
//�������ܱ�� ������ ���� �����Ѵ�. ����, ����̴� ���ܱ⿡ ���� H�� �����ؾ� �Ѵ�. ���̸� �����ϸ� �鳯�� �����κ��� H���� ���� �ö󰣴�.
//�� ����, �� �ٿ� �������ִ� ������ ��� �����ع�����. ����, ���̰� H���� ū ������ H ���� �κ��� �߸� ���̰�, ���� ������ �߸��� ���� ���̴�.

//���� ���, �� �ٿ� �������ִ� ������ ���̰� 20, 15, 10, 17�̶�� ����. ����̰� ���̸� 15�� �����ߴٸ�, ������ �ڸ� ���� ���̴� 15, 15, 10, 15�� �� ���̰�, 
//����̴� ���̰� 5�� ������ 2�� ������ ��� ���� �� ���̴�. (�� 7���͸� ���� ��� ����) ���ܱ⿡ ������ �� �ִ� ���̴� ���� ���� �Ǵ� 0�̴�.
//
//����̴� ȯ�濡 �ſ� ������ ���� ������, ������ �ʿ��� ��ŭ�� ������ ���������� �Ѵ�. �̶�, ��� M������ ������ ���� �������� ���ؼ� ���ܱ⿡ ������ �� �ִ� ������ �ִ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
//
//�Է�
//ù° �ٿ� ������ �� N�� ����̰� ������ ���������� �ϴ� ������ ���� M�� �־�����. (1 �� N �� 1,000,000, 1 �� M �� 2,000,000,000)
//
//��° �ٿ��� ������ ���̰� �־�����. ������ ������ ���� �׻� M���� ũ�ų� ���� ������, ����̴� ���� �ʿ��� ������ �׻� ������ �� �ִ�. ���̴� 1,000,000,000���� �۰ų� ���� ���� ���� �Ǵ� 0�̴�.
//
//���
//��� M������ ������ ���� �������� ���ؼ� ���ܱ⿡ ������ �� �ִ� ������ �ִ��� ����Ѵ�.
//
//���� �Է� 1 
//4 7
//20 15 10 17
//���� ��� 1 
//15
//���� �Է� 2 
//5 20
//4 42 40 26 46
//���� ��� 2 
//36


//https://www.acmicpc.net/category/detail/72
public class BStest1 {
	public static void main(String args[]) {
	
		long input[] = {5 ,20};//��������,����
		long trees[] = {4 ,42 ,40 ,26 ,46}; //���ӵ� ���� ����
		
		Arrays.sort(trees);
		
		long start = 1;
		long end = trees[trees.length-1];
		long mid = (start+end)/2;
		long target = input[1];
		
		
		int cnt = 0;
		while(start<=end) {
//		while(cnt<10) {
			cnt++;
			mid = (start+end)/2;
			long sum = 0;
			for(int i=0;i<trees.length;i++) {
				if((trees[i]-mid)>0) {
					sum += trees[i]-mid;
				}
			}
			System.out.println("start : "+start+",,mid : "+mid+",,sum : "+sum+",,end : "+end);
			
			if(sum>=target) {
				start=mid+1;
			}else {
				end=mid-1;
				
			}
			
			
		}
		System.out.println(mid-1);
		
		
	}
	
//	public int Solution(int )
}