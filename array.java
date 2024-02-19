import java.util.Scanner;
public class array{
	//made by Rajat khatua @ratsnpotats
	static int best(int i,char[][] a,char t){
		int k =0;
			for(int j=0;j<3;k++) {
				//horizontal
				if(a[k%3][j]==a[(k+1)%3][j]&&a[(k+1)%3][j]==t && '-'==a[(k+2)%3][j])
					return(j*3+(k+2)%3);
				//vertical
				if(a[j][(k+2)%3]==a[j][(k+1)%3]&&a[j][(k+1)%3]==t && '-'==a[j][k])
					return(j+(k*3));
				//diagonal (top left)
				if(a[(j+2)%3][(j+2)%3]==a[(j+1)%3][(j+1)%3]&&a[(j+1)%3][(j+1)%3]==t&&a[j][j]=='-') {
					return(j+j*3);
				}
				
				//diagonal (top right)
				if(a[1][1]==t||a[2][0]==t) {
					if(a[1][1]==a[2][0]&&a[0][2]=='-') {
						return 6;}
					if(a[1][1]==t&&a[2][0]=='-'&&a[0][2]==t)return 2;
					if(a[2][0]==t&&a[1][1]=='-'&&a[0][2]==t)return 4;
						
					}
				
				
				if (k==2) {
					j++;
					k=-1;
				}
			}
			
		return i;
	}
	
	
	public static void main(String[] args) {
		char[][] bd= {{'-','-','-'},{'-','-','-'},{'-','-','-'}};
		int in = 0 ;
		boolean xo=true;
		char win ='-';
		int path =0;
		System.out.print("choose bot mode or normal(b/n):");
		Scanner sc = new Scanner(System.in);
		boolean f = true;
		boolean bot = (sc.next().charAt(0)=='b')?true:false;
		if(bot) {
			System.out.print("do you want to play first(y/n):");
			f = (sc.next().charAt(0)=='y')?true:false;
		}
		int best=0;
		main:
		for(int i=1;i<=10;i++) {
			
			//printing board
			for(int j =0; j<=8;j++) {
				if(j%3==0&&j!=0)
					System.out.println();
				System.out.print(bd[j%3][j/3]+" ");
			}
			System.out.println();
			if (i==10)break;
			
			//checking win conditions (
			if(('-'==bd[2][2])?false:
				bd[2][2]==bd[2][0]&&bd[2][2]==bd[2][1]||
				bd[2][2]==bd[1][2]&&bd[2][2]==bd[0][2])
			{
				win = bd[2][2];
				break;
				
			}
			
			else if(('-'==bd[0][0])?false:
					bd[0][0]==bd[1][1]&&bd[0][0]==bd[2][2]||
					bd[0][0]==bd[1][0]&&bd[0][0]==bd[2][0]||
					bd[0][0]==bd[0][1]&&bd[0][0]==bd[0][2])
			{
				win = bd[0][0];
				break;
			}
			else if (('-'==bd[1][1])?false:
					bd[1][1]==bd[1][0]&&bd[1][1]==bd[1][2]||
					bd[1][1]==bd[0][1]&&bd[1][1]==bd[2][1]||
					bd[1][1]==bd[0][2]&&bd[1][1]==bd[2][0]) 
			{
				win = bd[1][1];
				break;
				
			}
			
			//if bot plays first
			if(bot&&!f&&i%2==1) {
				System.out.println("bot is choosing...");
				xo=!xo;
				best=best(9,bd,'x');
				if(best!=9) {
					bd[best%3][best/3]='x';
					win='x';
					continue;
				}
				best=best(9,bd,'o');
				if(best!=9) {
					bd[best%3][best/3]='x';
					continue;
				}
				switch (i) {
				case 1:
					bd[0][0]='x';
					continue;
				case 3:
					if (bd[1][1]=='o'||bd[2][0]=='o'||bd[2][1]=='o') {
						path =(in==4)?1:2;
						bd[2][2]='x';
						continue;
					}
					bd[1][1]='x';
					continue;
				case 5:
					if (path ==2) {
						if(bd[2][0]=='-')
							bd[2][0]='x';
						else bd[0][2]='x';
						continue;//path two won
					}
					if(path==0) {
						if(bd[0][1]=='o')
							bd[1][0]='x';
						else bd [0][1]='x';
						
					}
				case 7:
				case 9:
					for (int last=0;last<=8;last++)
						if(bd[last%3][last/3]=='-') {
							bd[last%3][last/3]='x';
							continue main;}
				}
			}
			
			
			//if bot plays second
			else if(bot&&f&&i%2==0) {
				System.out.println("bot is choosing...");
				xo=!xo;
				best=best(9,bd,'o');
				if(best!=9) {
					bd[best%3][best/3]='o';
					continue;
				}
				best=best(9,bd,'x');
				if(best!=9) {
					bd[best%3][best/3]='o';
					continue;}
				
				
				switch (i) {
				case 2:
					if (in!=4) {
					bd[1][1]='o';
					continue;}
				case 4:				
					if((bd[0][0]=='x'&&bd[2][2]=='x')||(bd[2][0]=='x'&&bd[0][2]=='x')) {
						bd[1][0]='o';
						continue;
					}
					if(bd[1][0]=='x'&&bd[0][1]=='x') {
						bd[0][0]='o';
						continue;
					}
					if(bd[0][1]=='-'&&bd[2][1]=='-'){
						bd[0][1]='o';
						continue;
					}
					
					bd[1][0]='o';
					continue;
				case 6:
					for (int m =0;m<=2;m+=2) {
						if(bd[m][m]=='-') {bd[m][m]='o';
							continue main;}
						if (bd[(m+2)%3][m]=='-') {
							bd[(m+2)%3][m]='o';
							continue main;
						}
					}
				case 8:
					for (int last=0;last<=8;last++)
						if(bd[last%3][last/3]=='-') {
							bd[last%3][last/3]='o';	
							continue main;}
					
				}
			}
		System.out.print("input value(1-9):");
		in = (sc.nextInt()-1)%9;
		//making sure you don't overwrite a previous number
		if (bd[in%3][in/3]!='-') {
			
			System.out.println("invalid input choose again");
			i--;
			continue;
		}
		
		
		bd[in%3][in/3]=(xo)?'x':'o';
		xo = (xo)?false:true;
		}
		System.out.println(win+" is the winner");
		System.out.println("made by Rajat khatua @ratsnpotats");
	}
}
