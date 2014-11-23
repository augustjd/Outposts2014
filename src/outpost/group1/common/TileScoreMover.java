package outpost.group1.common;

import java.util.*;

public class TileScoreMover extends ScoreMover {
    public static Random rand = new Random();

    public double scoreTile(Point p, Game g, Outpost o) {
        return 0.3  * distanceScore(p, g) 
             + 10.0 * avoidEdgesScore(p, g) 
             + 10.0  * avoidNeighborsScore(p, g, o);
    }

    public static final int TARGET_DISTANCE = Board.BOARD_SIZE / 2;
    public double distanceScore(Point p, Game g) {
        Commander me = g.getMe();
        int distance = p.distanceTo(me.getCorner());

        return 100*TARGET_DISTANCE - Math.pow(TARGET_DISTANCE - distance, 2);
    }

    public double avoidEdgesScore(Point p, Game g) {
        Point closestEdge = p.closestTo(Rectangle.BOARD_RECTANGLE.getCornersList());
        Point offset = p.sub(closestEdge);

        double min_distance = Math.min(Math.abs(offset.getX()), Math.abs(offset.getY()));

        // cap the score at radius
        return Math.min(g.radius, min_distance);
    }

    public double targetDistanceScore(Point current, Point other, double target) {
        return targetDistanceScore(current, other, target, Double.POSITIVE_INFINITY);
    }

    public double targetDistanceScore(Point current, Point other, double target, double cap) {
        double dist = current.distanceTo(other);
        double score = target - Math.abs(target - dist);
        return Math.min(cap, score);
    }

    public double avoidNeighborsScore(Point p, Game g, Outpost o) {
        List<Point> neighbor_positions = new ArrayList<Point>();

        for (Outpost other : g.getMe().getOutposts()) {
            if (other.getId() == o.getId()) continue;

            neighbor_positions.add(other.getPosition());
        }

        Point closestNeighbor = p.closestTo(neighbor_positions);

        if (closestNeighbor == null) return 0.0;
        
        return targetDistanceScore(p, closestNeighbor, g.radius * 2, 20.0);
    }
}
