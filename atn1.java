import java.util.HashSet;
import java.util.Set;
public class atn1 
{
//defining the total number of nodes
int N=40;
public final int size = 1024;
int b[][] = new int[size][size];
int a[][]= new int[size][size];
int c[][]= new int[size][size];
int d[] = new int[size];
int prev[]= new int[size];
int visited[] = new int[size];

public static void main(String[] args) 
	{
	atn1 expt = new atn1();
System.out.println("Emulation of the algorithm\n");	
System.out.println("k \t Cost \t Density\n");

	//run the experiment for different k values
	for (int k =3; k<=15; k++)
		{
			expt.runExpt(k);
			System.out.println(k+"\t"+expt.cost()+"\t"+expt.density());
		}
	}

private void runExpt(int k) 
	{
	//Generation of bij values.
	for(int i=1;i<=N;i++)
		{
		for (int j=1;j<=N;j++)
			{
				//choose random numbers between 0-3
				b[i][j] = (int)((Math.random()*10) % 4);
			}
		}
//generation of aij values.
Set<Integer> set = new HashSet<Integer>();
	while(set.size() < k)
		{
			//choose random number between 0-4
			set.add((int) (Math.random()*100)%N);
		}
	for(int i=1;i<=N;i++)
		{
		for(int j=1;j<=N;j++)
			{
				if (set.contains(j))
			{
				a[i][j] = 1;
			}
			else
			{
				a[i][j] = 300;
			}
		}
	}

//apply Dijkstra's algo for shortest path.
for (int i=1;i<=N;i++)
dijkstra(i);
}

private void dijkstra(int s) 
{
int min;
for (int i = 1; i <= N; i++)
	{
	d[i] = size;   //some large distance initialization
	prev[i] = -1;   // previous node, initially set to -1
	visited[i] = 0; // not been visited initially
	}
d[s] = 0;      //distance from itself is 0
	for (int k = 1; k <= N; k++)
	{
		min = -1;
		for (int i = 1; i <= N; i++)
		if ((visited[i] == 0) && ((min == -1) || (d[i] < d[min])))
		min = i;
		visited[min] = 1;
		for (int i = 1; i <= N; ++i)
		if (a[min][i] != 0)
		if (d[min] + a[min][i] < d[i])
		{
			d[i] = d[min] + a[min][i];  //setting the distance
			prev[i] = min;  //setting the next hop element
		}
	}
	for(int j=1; j <= N; j++)
	path(j);
}

private void path(int node) 
{
	if (prev[node] != -1)
	path(prev[node]);
	if(prev[node]> -1)
	c[prev[node]][node] += a[prev[node]][node]*b[prev[node]][node];
}

private int cost()
	{
		//find the total network cost according to the weights of the links
		int totalCost = 0;
		for (int i =1;i<=N;i++)
		{
			for (int j=1;j<=N;j++)
			{
				totalCost+= a[i][j]*b[i][j];
			}
		}
	return totalCost;
	}

private double density()
	{
		//find the network density as given in problem statement.
		double density =0.0;
		for (int i=1;i<=N;i++)
		for (int j=1;j<=N;j++)
		if(c[i][j]!=0)
		density += c[i][j];
		return density/(N*(N-1));
	}
}
