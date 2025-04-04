// import java.util.InputMismatchException;
// import java.util.Scanner;

// public class Djikstra {

//     public static int output_dist[];

//     static final int V = 10;

//     int minDistance(int dist[], Boolean sptSet[]) {
//         int min = Integer.MAX_VALUE, min_index = -1;

//         for (int v = 0; v < V; v++)
//             if (!sptSet[v] && dist[v] <= min) {
//                 min = dist[v];
//                 min_index = v;
//             }

//         return min_index;
//     }

//     public static int getMinDistance(int[] dist, int destination) {
//         return dist[destination];
//     }

//     void printSolution(int dist[], int source, String[] names) {
//         String sourceName = names[source];
//         System.out.println("Vertex Name \t\t Distance from " + sourceName);
//         for (int i = 0; i < V; i++) {
//             if (dist[i] > 0 || i == source) {
//                 System.out.println(names[i] + " \t\t " + dist[i]);
//             }
//         }
//     }

//     int[] dijkstra(int graph[][], int src) {
        
//         int dist[] = new int[V];
//         Boolean sptSet[] = new Boolean[V];

//         for (int i = 0; i < V; i++) {
//             dist[i] = Integer.MAX_VALUE;
//             sptSet[i] = false;
//         }

//         dist[src] = 0;

//         for (int count = 0; count < V - 1; count++) {
//             int u = minDistance(dist, sptSet);
//             sptSet[u] = true;
//             for (int v = 0; v < V; v++)
//                 if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
//                     dist[v] = dist[u] + graph[u][v];
//         }
//         return dist;
//     }

//     static int destination_location;

//     public static int setDestLocation(int user_destination_location) {
//         destination_location = user_destination_location;
//         int final_dist = getMinDistance(output_dist, destination_location);
//         return final_dist;
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         String[] names = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
//         int graph[][] = new int[][] {
//             { 0, 3, 0, 0, 1, 0, 0, 0, 0, 0 },
//             { 3, 0, 1, 0, 0, 0, 0, 2, 0, 0 },
//             { 0, 1, 0, 2, 0, 0, 0, 5, 2, 0 },
//             { 0, 0, 2, 0, 2, 0, 1, 0, 0, 3 },
//             { 1, 0, 0, 2, 0, 2, 0, 0, 0, 0 },
//             { 0, 0, 0, 0, 2, 0, 5, 0, 0, 0 },
//             { 0, 0, 0, 1, 0, 5, 0, 0, 0, 0 },
//             { 0, 2, 5, 0, 0, 0, 0, 0, 3, 0 },
//             { 0, 0, 2, 0, 0, 0, 0, 3, 0, 1 },
//             { 0, 0, 0, 3, 0, 0, 0, 0, 1, 0 }
//         };
//         Djikstra t = new Djikstra();
//         System.out.println("Enter the source location (1-10): ");
//         int source = -1;
//         boolean validInput = false;

//         while (!validInput) {
//             try {
//                 source = sc.nextInt() - 1;
                
//                 if (source >= 0 && source < V) {
//                     validInput = true;
//                 } else {
//                     System.out.println("Invalid input. Please enter a number between 1 and 10.");
//                 }
//             } catch (InputMismatchException e) {
//                 System.out.println("Invalid input. Please enter a number between 1 and 10.");
//                 sc.next(); // consume the invalid token
//             }
//         }

//         output_dist = t.dijkstra(graph, source);
//         t.printSolution(output_dist, source, names);
//         t.printGraphStructure(graph, names);
//     }
// }




import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Djikstra extends JFrame {
    static final int V = 10;
    private final int[][] graph;
    private final String[] names;
    private final Map<String, Point> positions;

    public Djikstra(int[][] graph, String[] names) {
        this.graph = graph;
        this.names = names;
        this.positions = new HashMap<>();
        initializePositions();
        setupFrame();
    }

    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Graph Visualization");
        this.setSize(800, 600);
        this.setVisible(true);
    }

    private void initializePositions() {
        positions.put("A", new Point(100, 100));
        positions.put("B", new Point(200, 100));
        positions.put("C", new Point(300, 200));
        positions.put("D", new Point(100, 300));
        positions.put("E", new Point(200, 300));
        positions.put("F", new Point(400, 100));
        positions.put("G", new Point(500, 200));
        positions.put("H", new Point(400, 300));
        positions.put("I", new Point(500, 100));
        positions.put("J", new Point(600, 300));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 12));
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != 0) {
                    Point p1 = positions.get(names[i]);
                    Point p2 = positions.get(names[j]);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    g.drawString(String.valueOf(graph[i][j]), (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
                }
            }
        }

        for (String name : names) {
            Point p = positions.get(name);
            g.setColor(Color.RED);
            g.fillOval(p.x - 15, p.y - 15, 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(name, p.x - 5, p.y + 5);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        int graph[][] = {
            {0, 3, 0, 0, 1, 0, 0, 0, 0, 0},
            {3, 0, 1, 0, 0, 0, 0, 2, 0, 0},
            {0, 1, 0, 2, 0, 0, 0, 5, 2, 0},
            {0, 0, 2, 0, 2, 0, 1, 0, 0, 3},
            {1, 0, 0, 2, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 5, 0, 0, 0},
            {0, 0, 0, 1, 0, 5, 0, 0, 0, 0},
            {0, 2, 5, 0, 0, 0, 0, 0, 3, 0},
            {0, 0, 2, 0, 0, 0, 0, 3, 0, 1},
            {0, 0, 0, 3, 0, 0, 0, 0, 1, 0}
        };
        Djikstra app = new Djikstra(graph, names);
        app.setVisible(true);

        System.out.println("Enter the source location (1-10): ");
        int source = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                source = sc.nextInt() - 1;
                
                if (source >= 0 && source < V) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
                sc.next(); // consume the invalid token
            }
        }

        int[] output_dist = app.dijkstra(graph, source);
        app.printSolution(output_dist, source, names);
    }

    public int[] dijkstra(int graph[][], int src) {
        int dist[] = new int[V];
        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < V; v++)
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
        return dist;
    }

    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    void printSolution(int dist[], int source, String[] names) {
        String sourceName = names[source];
        System.out.println("Vertex Name \t\t Distance from " + sourceName);
        for (int i = 0; i < V; i++) {
            if (dist[i] > 0 || i == source) {
                System.out.println(names[i] + " \t\t " + dist[i]);
            }
        }
    }
}

