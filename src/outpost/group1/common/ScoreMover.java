package outpost.group1.common;

import java.util.*;

public abstract class ScoreMover {
    public Move getMove(Game g, Outpost o) {
        List<Point> valid_moves = g.getBoard().getLandNeighbors(o.getPosition());

        // allow the player to stay in the same place.
        valid_moves.add(o.getPosition());

        Double best = null;
        Point destination = null;
        for (Point p : valid_moves) {
            double score = scoreTile(p, g, o);
            if (best == null || score > best) {
                best = score;
                System.out.format("Outpost %s likes %s best with score %f\n", o, p, score);
                destination = p;
            }
        }

        return o.getMoveTo(destination);
    }

    public abstract double scoreTile(Point p, Game g, Outpost o);
}
