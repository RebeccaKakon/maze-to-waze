package gui;

import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import algorithms.Graph_Algo;

import java.util.Set;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.edgedata;
import dataStructure.node_data;
import dataStructure.nodedata;
import utils.Point3D;
import utils.StdDraw;

public class GraphGUIstddraw implements ActionListener, MouseListener {
	

	
	public void drawFunctions(DGraph x) {
		StdDraw.setCanvasSize(1000,1000);
		drawpoints(x);
		drawedges(x);
		
	}
	public  void drawpoints(DGraph x) {
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.05);
		StdDraw.setScale(-100, 100);
		Iterator<node_data> I= x.getV().iterator();
		while(I.hasNext()) {
			//System.out.println("did");
			node_data current=new nodedata((nodedata)I.next());
			Point3D p=new Point3D(current.getLocation());
			//System.out.println(p);
			StdDraw.point(p.x(), p.y());
			StdDraw.setPenColor(Color.RED);
			StdDraw.setPenRadius(0.5);
			String txt=Integer.toString(current.getKey());
			StdDraw.text(p.x(), p.y()+4, txt);
			
			StdDraw.setPenRadius(0.05);
			StdDraw.setPenColor(Color.BLACK);
		}
		
		
		
	}
	public void drawedges(DGraph x) {
		StdDraw.setPenColor(Color.pink);
		StdDraw.setPenRadius(0.01);
		Iterator<node_data> I=x.getV().iterator();
		//Iterator<HashMap<Integer,edge_data>> I= (Iterator<HashMap<Integer, edge_data>>) x.getHashedges().entrySet();
		while(I.hasNext()) {
			//HashMap<Integer,edge_data> current=new HashMap<Integer,edge_data>((HashMap<Integer,edge_data>)I.next());
			node_data B=new nodedata((nodedata)I.next());
			HashMap<Integer,edge_data>edg2=new HashMap<Integer,edge_data>(x.getHashedges().get(B.getKey()));
			Collection<edge_data> edges=edg2.values();
			Iterator <edge_data> j=edges.iterator();
			while(j.hasNext()) {
				edgedata edg=new edgedata((edgedata)j.next()); 
				StdDraw.line(edg.getSource().getLocation().x(),edg.getSource().getLocation().y(),
						edg.getDes().getLocation().x(),edg.getDes().getLocation().y());
				String txt=Double.toString(edg.getWeight());
				double x_txt=(Math.abs(edg.getDes().getLocation().x())+Math.abs(edg.getSource().getLocation().x()))/2;
				double y_txt=placeontheline(edg.getSource().getLocation(),edg.getDes().getLocation(),x_txt);
				StdDraw.setPenColor(Color.BLUE);
				StdDraw.text( x_txt,  y_txt,txt);
				double distance=(edg.getDes().getLocation().x())-(edg.getSource().getLocation().x());
				double xx=edg.getDes().getLocation().x()-distance/(10);
				double yy=placeontheline(edg.getSource().getLocation(),edg.getDes().getLocation(),xx);
				//Point3D vector=new Point3D(edg.getDes().getLocation().x()-edg.getSource().getLocation().x(),edg.getDes().getLocation().y()-edg.getSource().getLocation().y(),0);  
				Point3D yellow=new Point3D(xx,yy,0);
				StdDraw.setPenColor(Color.YELLOW);
				StdDraw.setPenRadius(0.03);
				StdDraw.point(yellow.x(), yellow.y());
				StdDraw.setPenRadius(0.01);
				StdDraw.setPenColor(Color.pink);
				
			}
			
			
		}
		
		
	}
	
	public static double placeontheline(Point3D p1,Point3D p2,double x0) {    //return the f(x0)
		double m=((p2.y()-p1.y())/(p2.ix()-p1.x()));
		return m*(x0-p2.x())+p2.y();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		Point3D p1=new Point3D(10,15,0);
		Point3D p2=new Point3D(50,60,0);
		Point3D p3=new Point3D(90,40,0);
		Point3D p4=new Point3D(20,50,0);
		Point3D p5=new Point3D(80,10,0);
		Point3D p6=new Point3D(5,90,0);
		

		nodedata a=new nodedata(1,p1,0,0);
		nodedata b=new nodedata(2,p2,0,0);
		nodedata c=new nodedata(3,p3,0,0);
		nodedata d=new nodedata(4,p4,0,0);
		nodedata e=new nodedata(5,p5,0,0);
		nodedata f=new nodedata(6,p6,0,0);
		
		DGraph x=new DGraph();
		
		x.addNode(a);
		x.addNode(b);
		x.addNode(c);
		x.addNode(d);
		x.addNode(e);
		x.addNode(f);
		
		x.connect(a.getKey(), b.getKey(), 80);
		x.connect(b.getKey(), d.getKey(), 10);
		x.connect(c.getKey(), a.getKey(), 10);
		x.connect(a.getKey(), e.getKey(), 10);
		
		x.connect(e.getKey(), b.getKey(), 10);
		x.connect(f.getKey(), c.getKey(), 10);
		x.connect(d.getKey(), f.getKey(), 40);
		x.connect(e.getKey(), c.getKey(), 50);
		x.connect(c.getKey(), b.getKey(), 5);

		
		//x.connect(c.getKey(), b.getKey(), 30);
		Graph_Algo test=new Graph_Algo(x);
		GraphGUIstddraw g=new GraphGUIstddraw();
		x.addNode(a);	
		x.addNode(b);
	x.connect(a.getKey(), b.getKey(), 10);
//		g.drawFunctions(x);
		//System.out.println(x.removeNode(6));
	//	System.out.println(x.removeEdge(3, 6));
	//	x.connect(4, 6, 20);
	//	x.removeEdge(4, 6);
		System.out.println(test.isConnected());
//		System.out.println(test.shortestPathDist(1,6));
//		System.out.println(test.shortestPath(1,6));
	
		g.drawFunctions(x);
//		//x.removeEdge(6, 3);
	
//		System.out.println(test.shortestPath(1,6));
//		//x.removeNode(2);
//		g.drawFunctions(x);
//		System.out.println("done");
		List<Integer> targets=new LinkedList();
		targets.add(4);
		targets.add(3);
		targets.add(6);
		targets.add(2);
////		
//		System.out.println("**");
//		List answertest=test.shortestPath(1, 5);
//		Iterator answer22=answertest.iterator();
//		while(answer22.hasNext()) {
//			nodedata pp=(nodedata) answer22.next();
//			System.out.print(pp.getKey());
//		}
//		System.out.println("**");
//		
////		
	List answer=test.TSP(targets);
	if(answer!=null) {
		Iterator answer2=answer.iterator();
		while(answer2.hasNext()) {
			nodedata pp=(nodedata) answer2.next();
			System.out.print(pp.getKey());
		}
	}
	else
	System.out.println("null");
		
	
	

	
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Graph_Algo g=new Graph_Algo(null);
		String action=e.getActionCommand();
		if(action.equals("short pass")) {
			System.out.println(g.shortestPath(1, 3));
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
