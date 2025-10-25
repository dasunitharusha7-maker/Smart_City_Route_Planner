package com.group17.smartcity;

public class LocationList {
    private static class Node {
        String data;
        Node next;
        Node(String d) { data = d; }
    }

    private Node head;

    public void insert(String data) {
        Node n = new Node(data);
        n.next = head;
        head = n;
    }

    public boolean remove(String data) {
        Node prev = null, cur = head;
        while (cur != null) {
            if (cur.data.equals(data)) {
                if (prev == null) head = cur.next;
                else prev.next = cur.next;
                return true;
            }
            prev = cur; cur = cur.next;
        }
        return false;
    }

    public void display() {
        if (head == null) {
            System.out.println("No locations stored in the list.");
            return;
        }
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }
}
