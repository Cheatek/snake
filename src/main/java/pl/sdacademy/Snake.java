package pl.sdacademy;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Point head;
    private List<Point> body;
    private Dir direction;
    private int headX;
    private int headY;

    public Snake(Point head, List<Point> body, Dir direction) {
        this.head = head;
        this.body = new ArrayList<>(body);
        this.direction = direction;
    }

    public Point getHead() {
        return head;
    }

    public List<Point> getBody() {
        return body;
    }

    public Snake(Point head, List<Point> body) {
        this.head = head;
        this.body = body;
    }

    public void setDirection(Dir direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

    public void cutTail() {
        int snakeLength = body.size();
        body.remove(snakeLength - 1);
    }

    public void bodyValidator() {
        headX = head.getX();
        headY = head.getY();
        int validateSum;
        if (Math.abs((body.get(0).getX() - headX)) > 1 || Math.abs((body.get(0).getY() - headY)) > 1) { //if first element of body array is not in appropiate position throw exception
            throw new IllegalArgumentException();
        }
        validateSum = Math.abs((body.get(0).getX() - headX)) + Math.abs((body.get(0).getY() - headY));
        if (validateSum > 1) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i < body.size(); i++) {
            validateSum = Math.abs((body.get(i - 1).getX() - body.get(i).getX())) + Math.abs((body.get(i - 1).getY() - body.get(i).getY())); //if n element of body array isn't in appropiate position throw exception
            if (Math.abs((body.get(i - 1).getX() - body.get(i).getX())) > 1 && Math.abs((body.get(i - 1).getY() - body.get(i).getY())) > 1) {
                throw new IllegalArgumentException();
            }
            if (validateSum > 1) {
                throw new IllegalArgumentException();
            }
        }
        System.out.println("Waz utworzony");
    }

    public void expand() throws IllegalMoveException {
        headX = head.getX();
        headY = head.getY();
        Point newHead;
        if (direction == Dir.UP) {
            newHead = new Point(headX, headY - 1);
        } else if (direction == Dir.DOWN) {
            newHead = new Point(headX, headY + 1);
        } else if (direction == Dir.LEFT) {
            newHead = new Point(headX - 1, headY);
        } else {
            newHead = new Point(headX + 1, headY);
        }
        if (body.contains(newHead)) {
            throw new IllegalMoveException();
        }
        body.add(0, new Point(headX, headY));
        this.head = newHead;
    }

    public boolean contains(Point point) {
        return head.equals(point) || body.contains(point);
    }

    public boolean headConatins(Point point) {
        return head.equals(point);
    }

    public boolean bodyContains(Point point) {
        return body.contains(point);
    }
}
