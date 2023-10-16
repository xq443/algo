import java.util.*;

public class busRoutes{
    public static int numberOfBueses(int[][] routes, int Source, int Target){
        int bus = 0;

        Map<Integer, List<Integer>> next = new HashMap<>();
        //build up stoptoroute graph
        for(int i = 0; i < routes.length; i++){
            for(int stop : routes[i]){
                next.computeIfAbsent(stop, k -> new ArrayList<>()).add(i); //stop connects to the stops belonging to the next route
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[500];
        q.add(Source);

        //bfs
        while(!q.isEmpty()){
            int len = q.size();
            bus++;
            while(len -->0){
                int currStop = q.poll();

                for(int routeStop : next.get(currStop)){
                    if(visited[routeStop]) continue;
                    visited[routeStop] = true;

                    for(int stop : routes[routeStop]){
                        if(stop == Target) return bus;
                        q.add(stop);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] routes = {{1,2,7},{3,6,7}};
        int Source = 1;
        int Target = 6;
        System.out.println(numberOfBueses(routes,Source,Target));
    }

}


//1 2 7
//3 6 7
//source 1 target 6
//bus 2