import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Graph{
	Integer[][] matrix;
   
	public Graph(String graph){
		Scanner input = new Scanner(graph);
		String typeGraph = "";
		typeGraph = input.next();
		int nodes= input.nextInt();
		matrix= new Integer[nodes][nodes];
			if(typeGraph.equals("matrix")){
				for(int i = 0;i<nodes;i++)
			   {
					for(int j = 0;j<nodes;j++)
					{
						matrix[i][j] = input.nextInt();
					}
				}
			}
			if(typeGraph.equals("nodes")){
				for(int i = 0 ;i<nodes;i++){
					for(int j = 0;j<nodes ;j++){
						if(i == j){
					 		matrix[i][j] = 0; 
						}
					 	else{
					 		matrix[i][j] = -1;
					 	}
					}
				}
				for(int i = 0;i <input.nextInt();i++){
					matrix[input.nextInt()][input.nextInt()] = input.nextInt();
				}		
			}	
			if(typeGraph.equals("list")){
				for(int i = 0 ;i<nodes;i++){
					for(int j = 0;j<nodes ;j++){
						if(i == j){
					 		matrix[i][j] = 0; 
						}
					 	else{
					 		matrix[i][j] = -1;
					 	}
					}
				}
				for(int i = 0;i <nodes;i++){
					int id = input.nextInt();
					int nnodes =  input.nextInt();
					
					for(int j=0; j< nnodes ;j++){
					   int distance = input.nextInt();
						matrix[id][input.nextInt()] = distance;
					}
				}		
			}			
	}
	public void commands(String command){
		Scanner input = new Scanner(command);
		int x = 0;
		int y = 0;
		int length = 0;
      String cmd = input.next();
		switch (cmd){
			case 	"find":
			   x = input.nextInt();
			   y = input.nextInt();
				System.out.printf("%b",bread(x,y));
				System.out.printf("\n");
				break;
			case "distance":
			   x = input.nextInt();
			   y = input.nextInt();
				System.out.printf("%d",distance(x,y));
				System.out.printf("\n");
				break;
			case "length":
				System.out.printf("%d",length());
				System.out.printf("\n");
				break;
			case "add":
				System.out.printf("%d",add());
				System.out.printf("\n");
				break;
			case "connect":
			   x = input.nextInt();
			   y = input.nextInt();
			   length = input.nextInt();
				System.out.printf("%b",connect(x,y,length));
				break;
			case "remove":
				 x = input.nextInt();
				 System.out.printf("%b",remove(x));
				 System.out.printf("\n");
				 break;
			}	 								
	}
	public boolean bread(int start,int find)
	{
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		ArrayList<Integer>been = new ArrayList<Integer>();
		while(q.size() !=0)
		{
			Integer at = q.poll();
			if(been.contains(at))
			{
				continue;
			}
			System.err.printf("We are at node %d\n",at);
			been.add(at);
			for(int i = 0;i<matrix[at].length;i++)
			{
				if(matrix[at][i]>0)
				{
					q.add(i);
				}
			}
		}
		if(been.contains(find))
		{
			return true;
		}
		return false;
	}
	public int distance(int start,int find){
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		ArrayList<Integer>been = new ArrayList<Integer>();
		Queue<Integer> distance = new LinkedList<Integer>();
		distance.add(0);
		int curdist;
		while(q.size() !=0)
		{
			Integer at = q.poll();
			curdist = distance.poll();
			if(at.equals(find))
			{
			return curdist;
			}

			if(been.contains(at))
			{
				continue;
			}
			System.err.printf("We are at node %d\n",at);
			been.add(at);
			
			for(int i = 0;i<matrix[at].length;i++)
			{
				if(matrix[at][i]>0)
				{
					q.add(i);
					distance.add(curdist+matrix[at][i]);
				}
			}
		}
		return -1;
	}
	public int length(){
		return matrix.length;	
	}
	public int add(){
		Integer [][] temp = new Integer[matrix.length + 1][matrix.length + 1];
                for(int i = 0;i<temp.length-1;i++){
                  for(int j = 0;j<temp.length-1;j++){
                     temp[i][j] = matrix[i][j];
                  }       
                }
                for(int i = 0 ;i<=temp.length-1;i++){
                  temp[temp.length-1][i] = -1;
                  temp[i][temp.length-1] = -1;            
                }
                temp[temp.length-1][temp.length-1] = 0;                
                matrix = temp;
                return temp.length;
  	}
	public boolean connect(int x,int y ,int length){
		if(x<0 || y< 0){
			return false;
		}
		if(x > length() || y > length()){
			return false;
		}
		matrix[x][y] = length;
		return true;
	}
	public boolean remove(int node){
	   int tmpX=0;
      int tmpY=-1;
		int counter = 0;
		Integer [][] temp = new Integer[matrix.length-1][matrix.length-1];///
		for(int i=0; i<=temp.length-1; i++)
      {    
         if(i==node){
				 continue; 
         } 
         tmpY = 0;
            for(int j=0; j<node; j++){ 
              if(j==node){
				   continue;
              }
				  temp[tmpX][tmpY] = matrix[i][j];
				  tmpY++;
				}
				tmpX++;
		}
	   return true;
	}
	public static void main (String[] args){
		Scanner input = new Scanner(System.in);
		int nodes;
		int nodesList;
		String graph ="" + input.next();
		while(input.hasNextInt()){
			graph += " " +input.nextInt();
		}	
		Graph mygraph = new Graph(graph);
      input.nextLine();
		while(1==1){
			String mycommand = input.nextLine();
			mygraph.commands(mycommand);
		}
	}	
}