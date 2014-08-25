package outpost.dumb;

import java.util.*;

import outpost.sim.Pair;
import outpost.sim.Point;
import outpost.sim.movePair;

public class Player extends outpost.sim.Player {

	
    public Player(int id_in) {
		super(id_in);
		// TODO Auto-generated constructor stub
	}

	public void init() {
    	
    }
    
    static double distance(Point a, Point b) {
        return Math.sqrt((a.x-b.x) * (a.x-b.x) +
                         (a.y-b.y) * (a.y-b.y));
    }
    
    // Return: the next position
    // my position: dogs[id-1]


   
    static Random random = new Random();
	@Override
	public movePair move(ArrayList<Pair> prarr, int noutpost) {
		//System.out.printf("paarr size and noutpost size is %d, %d", prarr.size(), noutpost);
		if (prarr.size()>noutpost) {
			movePair mpr = new movePair(0, new Pair(0,0), true);
			return mpr;
		}
		else {
			
			if (prarr.get(0).x<5) {
				movePair mpr = new movePair(0, new Pair(prarr.get(0).x+1, prarr.get(0).y), false);
				return mpr;
			}
			if (prarr.get(0).x>5) {
				movePair mpr = new movePair(0, new Pair(prarr.get(0).x-1, prarr.get(0).y), false);
				return mpr;
			}
			if (prarr.get(0).y<4) {
				movePair mpr = new movePair(0, new Pair(prarr.get(0).x, prarr.get(0).y+1), false);
				return mpr;
			}
			if (prarr.get(0).y>4){
				movePair mpr = new movePair(0, new Pair(prarr.get(0).x, prarr.get(0).y-1), false);
				return mpr;
			}
			else {
				movePair mpr = new movePair(0, new Pair(prarr.get(0).x, prarr.get(0).y), false);
				return mpr;
		}
	}
    
}
}
