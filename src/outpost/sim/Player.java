package outpost.sim;

import java.util.ArrayList;

import outpost.sim.Point;

public abstract class Player {
    public int id; 

    public Pair[] outposts;
    
    public Player(int id_in) {this.id = id_in;}
    
    public abstract void init() ;
    
    public abstract movePair move(ArrayList<Pair> prarr, int noutpost); // positions of the rats

}
