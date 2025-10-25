package com.group17.smartcity;

import java.util.List;

public class LocationManager {
    private final Graph graph = new Graph();

    public boolean addLocation(String name) {
        return graph.addLocation(name);
    }

    public boolean removeLocation(String name) {
        return graph.removeLocation(name);
    }

    public String addRoad(String a, String b) {
        return graph.addRoad(a, b);
    }

    public String removeRoad(String a, String b) {
        return graph.removeRoad(a, b);
    }

    public void displayConnections() {
        graph.displayConnections();
    }

    public List<String> bfs(String start) {
        return graph.bfs(start);
    }

    public List<String> dfs(String start) {
        return graph.dfs(start);
    }
}

