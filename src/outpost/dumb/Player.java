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

    
   static int size =10;
    static Random random = new Random();
    
	//public movePair move(ArrayList<ArrayList<Pair>> king_outpostlist, int noutpost, Point[] grid) {
    public movePair move(ArrayList<ArrayList<Pair>> king_outpostlist, int noutpost, Point[] gridin) {
    	Point[] grid = new Point[100*100];
    	for (int i=0; i<gridin.length; i++) {
    		grid[i]=new Point(gridin[i]);
    	}
    	System.out.println(grid[5*size+5].water);
    	ArrayList<Pair> prarr = new ArrayList<Pair>();
    	prarr = king_outpostlist.get(this.id);
    	Point target = null;
    	boolean hastarget = false;
    	ArrayList<Point> waterList = orderWater(grid);
    	System.out.printf("waterList size is %d", waterList.size());
    	int moveid = 0;
		Pair movepair = null;
    //System.out.printf("paarr size and noutpost size is %d, %d", prarr.size(), noutpost);
		if (prarr.size()>noutpost) {
			movePair mpr = new movePair(0, new Pair(0,0), true);
			return mpr;
		}
		else {
			if (!hastarget) {
			for (int i=0; i<waterList.size(); i++) {
				if (waterList.get(i).ownerlist.size()==0) {
				//	target = PairtoPoint(surround(PointtoPair(waterList.get(i))).get(0));
					System.out.println("haha, we are here");
					break;
				}
			}
			hastarget = true;
			moveid = prarr.size()-1;
			movepair = new Pair(prarr.get(prarr.size()-1).x, prarr.get(prarr.size()-1).y);
			if (movepair.x<target.x) {
				movePair mpr = new movePair(moveid, new Pair(movepair.x+1, movepair.y), false);
				return mpr;
			}
			if (movepair.x>target.x) {
				movePair mpr = new movePair(moveid, new Pair(movepair.x-1, movepair.y), false);
				return mpr;
			}
			if (movepair.y<target.y) {
				movePair mpr = new movePair(moveid, new Pair(movepair.x, movepair.y+1), false);
				return mpr;
			}
			if (movepair.y>target.y){
				movePair mpr = new movePair(moveid, new Pair(movepair.x, movepair.y-1), false);
				return mpr;
			}
			else {
				if (king_outpostlist.size()-1>moveid) {
					hastarget = false;
				}
				movePair mpr = new movePair(moveid, new Pair(movepair.x, movepair.y), false);
				return mpr;
			}
			}
			else {
			if (movepair.x<target.x) {
				movePair mpr = new movePair(moveid, new Pair(movepair.x+1, movepair.y), false);
				return mpr;
			}
			if (movepair.x>target.x) {
				movePair mpr = new movePair(moveid, new Pair(movepair.x-1, movepair.y), false);
				return mpr;
			}
			if (movepair.y<target.y) {
				movePair mpr = new movePair(moveid, new Pair(movepair.x, movepair.y+1), false);
				return mpr;
			}
			if (movepair.y>target.y){
				movePair mpr = new movePair(moveid, new Pair(movepair.x, movepair.y-1), false);
				return mpr;
			}
			else {
				if (king_outpostlist.size()-1>moveid) {
					hastarget = false;
				}
				movePair mpr = new movePair(moveid, new Pair(movepair.x, movepair.y), false);
				return mpr;
			}
			}
	}
    
}
    static ArrayList<Point> orderWater(Point[] gridin) {
    	Point[] grid = new Point[100*100];
    	for (int i=0; i<gridin.length; i++) {
    		grid[i]=new Point(gridin[i]);
    	}
    	Pair cornel = null;
    	boolean edge = false;
    	System.out.println("orderwater");
    	if (id==0) {
    		cornel = new Pair(0,0);
    	}
    	if (id==1) {
    		cornel = new Pair(size,0);
    	}
    	if (id==2) {
    		cornel = new Pair(size,size);
    	}
    	if (id==3) {
    		cornel = new Pair(0,size);
    	}
    	
    	ArrayList<Point> waterList= new ArrayList<Point>();
    	for (int i=0; i<size; i++) {
    		for (int j=0; j<size; j++) {
    		//	System.out.println(grid[i*size+j].water);
    			if (grid[i*size+j].water) {
    				System.out.println("we are here, water");
    				for (int f=0; f<surround(PointtoPair(grid[i*size+j])).size(); f++) {
    		//			if (!PairtoPoint(surround(PointtoPair(grid[i*size+j])).get(f)).water) {
    						edge = true; 
    						System.out.println("we are here");
    			//		}
    				}
    				if (edge) {
    				//	grid[i*size+j].distance = distance(PairtoPoint(cornel), grid[i*size+j]);
    					waterList.add(grid[i*size+j]);
    					edge = false;
    				}
    			}
    		}
    	}
    	
    Collections.sort(waterList, new Comparator<Point>() {
       
        public int compare(Point o1, Point o2) {
        	return Double.compare(o1.distance, o2.distance);
        }
    });
    return waterList;
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
    
    
    static Pair PointtoPair(Point pt) {
    	return new Pair(pt.x, pt.y);
    }
}
