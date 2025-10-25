package com.group17.smartcity;

import java.util.*;

public class Graph {
    private final Map<String, Set<String>> graph = new HashMap<>();

    public boolean addLocation(String name) {
        if (graph.containsKey(name)) return false;
        graph.put(name, new HashSet<>());
        return true;
    }

    public boolean removeLocation(String name) {
        if (!graph.containsKey(name)) return false;
        // remove edges to this location
        for (String neighbor : new HashSet<>(graph.get(name))) {
            graph.get(neighbor).remove(name);
        }
        graph.remove(name);
        return true;
    }

    public String addRoad(String a, String b) {
        if (!graph.containsKey(a) || !graph.containsKey(b)) return "One or both locations do not exist.";
        if (graph.get(a).contains(b)) return "Road already exists.";
        graph.get(a).add(b);
        graph.get(b).add(a);
        return "Road added.";
    }

    public String removeRoad(String a, String b) {
        if (!graph.containsKey(a) || !graph.containsKey(b)) return "One or both locations do not exist.";
        if (!graph.get(a).contains(b)) return "Road does not exist.";
        graph.get(a).remove(b);
        graph.get(b).remove(a);
        return "Road removed.";
    }

    public void displayConnections() {
        if (graph.isEmpty()) {
            System.out.println("No locations in the network.");
            return;
        }
        List<String> keys = new ArrayList<>(graph.keySet());
        Collections.sort(keys);
        for (String loc : keys) {
            List<String> neigh = new ArrayList<>(graph.get(loc));
            Collections.sort(neigh);
            System.out.printf("%s -> %s%n", loc, neigh.isEmpty() ? "No connections" : String.join(", ", neigh));
        }
    }

    // BFS using queue
    public List<String> bfs(String start) {
        if (!graph.containsKey(start)) return Collections.emptyList();
        List<String> order = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(start); visited.add(start);
        while (!q.isEmpty()) {
            String cur = q.poll();
            order.add(cur);
            List<String> neigh = new ArrayList<>(graph.get(cur));
            Collections.sort(neigh);
            for (String n : neigh) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    q.add(n);
                }
            }
        }
        return order;
    }

    // DFS using stack (iterative)
    public List<String> dfs(String start) {
        if (!graph.containsKey(start)) return Collections.emptyList();
        List<String> order = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            String cur = stack.pop();
            if (visited.contains(cur)) continue;
            visited.add(cur);
            order.add(cur);
            List<String> neigh = new ArrayList<>(graph.get(cur));
            Collections.sort(neigh, Collections.reverseOrder()); // deterministic
            for (String n : neigh) {
                if (!visited.contains(n)) stack.push(n);
            }
        }
        return order;
    }
}

