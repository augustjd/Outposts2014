package outpost.dumb;

import java.util.*;

import outpost.sim.Pair;
import outpost.sim.Point;
import outpost.sim.movePair;

public class Player extends outpost.sim.Player {
	 static int size =100;
	static Point[] grid = new Point[size*size];
	
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
    
	//public movePair move(ArrayList<ArrayList<Pair>> king_outpostlist, int noutpost, Point[] grid) {
    public ArrayList<movePair> move(ArrayList<ArrayList<Pair>> king_outpostlist, int noutpost, Point[] gridin){
    	ArrayList<movePair> nextlist = new ArrayList<movePair>();
    	
    	for (int i=0; i<gridin.length; i++) {
    		grid[i]=new Point(gridin[i]);
    	}
    	ArrayList<Pair> prarr = new ArrayList<Pair>();
    	prarr = king_outpostlist.get(this.id);
    	for (int j =0; j<prarr.size()-1; j++) {
    		ArrayList<Pair> positions = new ArrayList<Pair>();
    		positions = surround(prarr.get(j));
    		for (int f=0; f<positions.size(); f++) {
    		if (!PairtoPoint(positions.get(f)).water && positions.get(f).x>0 && positions.get(f).y>0 && positions.get(f).x<size && positions.get(f).y<size) {
    			movePair next = new movePair(j, positions.get(f), true);
    			nextlist.add(next);
    			break;
    		}
    		}
    	}
    	if (prarr.size()>noutpost) {
			movePair mpr = new movePair(0, new Pair(0,0), true);
			nextlist.add(mpr);
		}
    	else {
    		ArrayList<Pair> positions = new ArrayList<Pair>();
    		positions = surround(prarr.get(prarr.size()-1));
    		for (int f=0; f<positions.size(); f++) {
    		if (!PairtoPoint(positions.get(f)).water) {
    			movePair next = new movePair(prarr.size()-1, positions.get(f), true);
    			nextlist.add(next);
    			break;
    		}
    		
    	}
    	}
    	return nextlist;
    
    }
    
    
    static ArrayList<Pair> surround(Pair start) {
   // 	System.out.printf("start is (%d, %d)", start.x, start.y);
    	ArrayList<Pair> prlist = new ArrayList<Pair>();
    	for (int i=0; i<4; i++) {
    		Pair tmp0 = new Pair(start);
    		Pair tmp;
    		if (i==0) {
    			if (start.x>0) {
    			tmp = new Pair(tmp0.x-1,tmp0.y);
    	//		if (!PairtoPoint(tmp).water)
    			prlist.add(tmp);
    			}
    		}
    		if (i==1) {
    			if (start.x<size-1) {
    			tmp = new Pair(tmp0.x+1,tmp0.y);
    		//	if (!PairtoPoint(tmp).water)
    			prlist.add(tmp);
    			}
    		}
    		if (i==2) {
    			if (start.y>0) {
    			tmp = new Pair(tmp0.x, tmp0.y-1);
    			//if (!PairtoPoint(tmp).water)
    			prlist.add(tmp);
    			}
    		}
    		if (i==3) {
    			if (start.y<size-1) {
    			tmp = new Pair(tmp0.x, tmp0.y+1);
    			//if (!PairtoPoint(tmp).water)
    			prlist.add(tmp);
    			}
    		}
    		
    	}
    	
    	return prlist;
    }
    
    static Point PairtoPoint(Pair pr) {
    	return grid[pr.x*size+pr.y];
    }
    static Pair PointtoPair(Point pt) {
    	return new Pair(pt.x, pt.y);
    }
}
