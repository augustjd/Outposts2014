package outpost.group1.common;

import java.util.*;

public class Game {
    Board board;
    public Board getBoard() { return board; }
    Commander[] commanders = null;

    public List<Outpost> getMyOutposts() {
        return commanders[my_id].getOutposts();
    }

    public Commander getMe() { return commanders[my_id]; };

    int my_id;

    public int radius = 10;
    public Game(int id, outpost.sim.Point[] game_board) {
        my_id = id;
        board = new Board(game_board);
    }

    private List<Outpost> outpostsFromPairs(List<outpost.sim.Pair> pairs) {
        List<Outpost> posts = new ArrayList<Outpost>();
        for (outpost.sim.Pair p : pairs) {
            posts.add(new Outpost(posts.size(), p));
        }
        return posts;
    }

    public void loadOutposts(ArrayList<ArrayList<outpost.sim.Pair>> outposts) {
        Point[] corners = Rectangle.BOARD_RECTANGLE.getCorners();
        commanders = new Commander[4];
        for (int i = 0; i < 4; i++) {
            commanders[i] = new Commander(corners[i], 
                                          outpostsFromPairs(outposts.get(i)),
                                          i == my_id);
        }
    }
}
