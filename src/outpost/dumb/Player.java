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
    	//System.out.printf("Player %d\n", this.id);
    	for (int i=0; i<gridin.length; i++) {
    		grid[i]=new Point(gridin[i]);
    	}
    	ArrayList<Pair> prarr = new ArrayList<Pair>();
    	prarr = king_outpostlist.get(this.id);
    	for (int j =0; j<prarr.size()-1; j++) {
    		ArrayList<Pair> positions = new ArrayList<Pair>();
    		positions = surround(prarr.get(j));
    		boolean gotit=false;
    		while (!gotit) {
    			Random random = new Random();
				int theta = random.nextInt(positions.size());
				//System.out.println(theta);
    		if (!PairtoPoint(positions.get(theta)).water && positions.get(theta).x>0 && positions.get(theta).y>0 && positions.get(theta).x<size && positions.get(theta).y<size) {
    			movePair next = new movePair(j, positions.get(theta), false);
    			nextlist.add(next);
    			//next.printmovePair();
    			gotit = true;
    			break;
    		}
    		}
    	}
    	if (prarr.size()>noutpost) {
			movePair mpr = new movePair(prarr.size()-1, new Pair(0,0), true);
			nextlist.add(mpr);
			//mpr.printmovePair();
		}
    	else {
    		ArrayList<Pair> positions = new ArrayList<Pair>();
    		positions = surround(prarr.get(prarr.size()-1));
    		boolean gotit=false;
    		while (!gotit) {
    			Random random = new Random();
				int theta = random.nextInt(positions.size());
				//System.out.println(theta);
    		if (!PairtoPoint(positions.get(theta)).water && positions.get(theta).x>=0 && positions.get(theta).y>=0 && positions.get(theta).x<size && positions.get(theta).y<size) {
    			movePair next = new movePair(prarr.size()-1, positions.get(theta), false);
    			nextlist.add(next);
    			//next.printmovePair();
    			gotit = true;
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
