package algorithm2.cjs_20211122;


//�Ʒ��� ���� 5�� ��Ģ���길���� 12�� ǥ���� �� �ֽ��ϴ�.
//
//12 = 5 + 5 + (5 / 5) + (5 / 5)
//12 = 55 / 5 + 5 / 5
//12 = (55 + 5) / 5
//
//5�� ����� Ƚ���� ���� 6,5,4 �Դϴ�. �׸��� ���� ���� ���� ���� 4�Դϴ�.
//��ó�� ���� N�� number�� �־��� ��, N�� ��Ģ���길 ����ؼ� ǥ�� �� �� �ִ� ��� �� N ���Ƚ���� �ּڰ��� return �ϵ��� solution �Լ��� �ۼ��ϼ���.
//
//���ѻ���
//N�� 1 �̻� 9 �����Դϴ�.
//number�� 1 �̻� 32,000 �����Դϴ�.
//���Ŀ��� ��ȣ�� ��Ģ���길 �����ϸ� ������ ���꿡�� �������� �����մϴ�.
//�ּڰ��� 8���� ũ�� -1�� return �մϴ�.
//����� ��
//N	number	return
//5	12	4
//2	11	3
//����� �� ����
//���� #1
//������ ���� ���� �����ϴ�.
//
//���� #2
//11 = 22 / 2�� ���� 2�� 3���� ����Ͽ� ǥ���� �� �ֽ��ϴ�.
//
//��ó
//
//�� ���� - 2020�� 9�� 3�� �׽�Ʈ���̽��� �߰��Ǿ����ϴ�.

public class N_present {
	
	static int answer=-1;
	public static void dfs(int N, int number, int count, int sum) {
		if(count>8) return;
		if(number==sum)	{
//			System.out.printf("N,number,count,sum : %d, %d, %d, %d \n",N,number,count,sum);
			if(answer==-1)	answer=count;
			else answer=Math.min(answer, count);
		}
		System.out.printf("N,number,count,sum : %d, %d, %d, %d \n",N,number,count,sum);
		int X=N;
		for(int i=1;i<=8-count;i++) {
			dfs(N,number,i+count,sum+X);
			dfs(N,number,i+count,sum-X);
			dfs(N,number,i+count,sum*X);
			dfs(N,number,i+count,sum/X);
			X=(10*X)+N;
		}
		
	}
    public static int solution(int N, int number) {
        dfs(N,number,0,0);
		return answer;
    }
    public static void main(String[] args) {
		int N=5;
		int number = 12;
		System.out.println(solution(N, number));
	}
}
