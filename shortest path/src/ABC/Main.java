package ABC;
import java.util.*;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
 private int dist[];
 private Set<Integer> settled;
 private PriorityQueue<Node> pq;
 // Number of vertices or number of cities
 private int V;
 List<List<Node> > adj;
 public Main(int V)
 {
     this.V = V;
     dist = new int[V];
     settled = new HashSet<Integer>();
     pq = new PriorityQueue<Node>(V, new Node());
 }
 // Method 1
 // Dijkstra's Algorithm
 public void dijkstra(List<List<Node> > adj, int src)
 {
     this.adj = adj;
     for (int i = 0; i < V; i++)
         dist[i] = Integer.MAX_VALUE;
     pq.add(new Node(src, 0));
     // Distance to the source is 0
     dist[src] = 0;
     while (settled.size() != V) {
         if (pq.isEmpty())
             return;
         int u = pq.remove().node;
         if (settled.contains(u))
             continue;
         settled.add(u);
         e_Neighbours(u);
     }
 }
 private void e_Neighbours(int u)
 {
     int edgeDistance = -1;
     int newDistance = -1;
     for (int i = 0; i < adj.get(u).size(); i++) {
         Node v = adj.get(u).get(i);
         if (!settled.contains(v.node)) {
             edgeDistance = v.cost;
             newDistance = dist[u] + edgeDistance;
             if (newDistance < dist[v.node])
                 dist[v.node] = newDistance;
             pq.add(new Node(v.node, dist[v.node]));
         }
     }
 }
 public static void main(String arg[])
 {
	 System.out.println("''FIRST PROGRAM AND OUTPUT USING DIJKSTRA'S ALGORITHM''");
	 System.out.println("");
Scanner get = new Scanner(System.in);
System.out.println("Enter number of cities:-");
     int V = get.nextInt();
     System.out.println("Cities are:-");
     for(int i=0;i<V;i++) {
    	 System.out.println("City "+i);
     }
     System.out.println("Enter the Source City(City from which you want to start):-");
     int source = get.nextInt();
     List<List<Node> > adj
         = new ArrayList<List<Node> >();
     for (int i = 0; i < V; i++) {
         List<Node> item = new ArrayList<Node>();
         adj.add(item);
     }
for(int i=0;i<V;i++){
      for(int j=0;j<V;j++){
System.out.println("If you want to enter the distance between the City "+i+" and City "+j+" ,press '1'(Don't enter distance for the same cities) or else press '0'");
          int a=get.nextInt();
          if(a==1){
              System.out.println("Enter distance between City "+i+" and City "+j+":-");
              int d=get.nextInt();
              adj.get(i).add(new Node(j, d));
      }
      else if(a==0){}
  }
  }
     Main dpq = new Main(V);
     dpq.dijkstra(adj, source);
     System.out.println("Enter the city till which you want to find the shortest distance:-");
     int f=get.nextInt();
     System.out.print("The shortest distance from ");
              System.out.println(source + " to " + f + " is "
                            + dpq.dist[f]);
         System.out.println("");
         System.out.println("''SECOND PROGRAM AND OUTPUT BASED ON A* ALGORITHM''");
         System.out.println("Enter number of cities:-");
         int p=get.nextInt();
         Node1 Objects[]=new Node1[p];
                  System.out.println("cities are:-");
         for(int i=0;i<p;i++){
             System.out.println("City "+i);
         }
         for(int i=0;i<p;i++){
                         System.out.println("Enter the staright line distance of City "+i+" from a starting reference point:-");
                         double d2=get.nextDouble();
                         String s2=Integer.toString(i);
             Objects[i]=new Node1(s2,d2);
         }
         for(int i=0;i<p;i++){
             for(int j=0;j<p;j++){
                 System.out.println("If you want to enter the distance between the City "+i+" and City "+j+" ,press '1'(Don't enter distance for the same cities) or else press '0'");
   int a2=get.nextInt();
   if(a2==1){
       System.out.println("Enter distance between City "+i+" and City "+j+":-");
       double d2=get.nextDouble();
         Objects[i].adjacencies1=new Edge1[]{new Edge1(Objects[j],d2)};           
             }
             else if(a2==0){}
         }}
         System.out.println("Enter the cities between which you want to find the shortest path respectively:-");
         int g1=get.nextInt();
         int h1=get.nextInt();
         
          AstarSearch(Objects[g1],Objects[h1]);

          List<Node1> path = printPath(Objects[h1]);

                  System.out.println("Path: " + path);
 }
 public static List<Node1> printPath(Node1 target){
         List<Node1> path = new ArrayList<Node1>();
 
 for(Node1 node1 = target; node1!=null; node1 = node1.prnt){
     path.add(node1);
 }

 Collections.reverse(path);

 return path;
 }

 public static void AstarSearch(Node1 source, Node1 goal){

         Set<Node1> explored = new HashSet<Node1>();

         PriorityQueue<Node1> queue = new PriorityQueue<Node1>(20, 
                 new Comparator<Node1>(){
                          //override compare method
          public int compare(Node1 i, Node1 j){
             if(i.score_f > j.score_f){
                 return 1;
             }

             else if (i.score_f < j.score_f){
                 return -1;
             }

             else{
                 return 0;
             }
          }

                 }
                 );

         source.score_g = 0;

         queue.add(source);

         boolean found = false;

         while((!queue.isEmpty())&&(!found)){

                 Node1 current = queue.poll();

                 explored.add(current);


                 if(current.value.equals(goal.value)){
                         found = true;
                 }


                 for(Edge1 e : current.adjacencies1){
                         Node1 child = e.target;
                         double cost = e.cost;
                         double temp_score_g = current.score_g + cost;
                         double temp_score_f = temp_score_g + child.score_h;



                         
                         if((explored.contains(child)) && 
                                 (temp_score_f >= child.score_f)){
                                 continue;
                         }


                         
                         else if((!queue.contains(child)) || 
                                 (temp_score_f < child.score_f)){

                                 child.prnt = current;
                                 child.score_g = temp_score_g;
                                 child.score_f = temp_score_f;

                                 if(queue.contains(child)){
                                         queue.remove(child);
                                 }

                                 queue.add(child);

                         }

                 }

         }

 }
}
//Helper class implementing Comparator interface ,representing a node in the graph
class Node implements Comparator<Node> {
 public int node;
 public int cost;
 public Node() {}
 public Node(int node, int cost)
 {
     this.node = node;
     this.cost = cost;
 }
 @Override public int compare(Node node1, Node node2)
 {
     if (node1.cost < node2.cost)
         return -1;
     if (node1.cost > node2.cost)
         return 1;
     return 0;
 }
}
class Node1{

    public final String value;
    public double score_g;
    public final double score_h;
    public double score_f = 0;
    public Edge1[] adjacencies1;
    public Node1 prnt;

    public Node1(String val, double hVal){
            value = val;
            score_h = hVal;
    }

    public String toString(){
            return value;
    }

}

class Edge1{
    public final double cost;
    public final Node1 target;

    public Edge1(Node1 tN, double cV){
            target = tN;
            cost = cV;
    }
}
// DHRUV GUPTA
// 2020A7PS1680P
// PROJECT NUMBER 24-A* ALGORITHM


