package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class DGraph implements graph,Serializable{

	private HashMap<Integer,node_data> hashnodes;
	private HashMap<Integer,HashMap<Integer,edge_data>> hashedges;
	private GraphListener Lis;

	private int countedgeg=0;
	private int MC=0;
	
	public void addListener(GraphListener Lis) {
		this.Lis=Lis;
	}



	public DGraph(HashMap<Integer,node_data> hashnodes,HashMap<Integer,HashMap<Integer,edge_data>> hashedges,int countedgeg) {
		this.hashnodes=new HashMap<Integer,node_data>() ;
		this.hashnodes.putAll(hashnodes);
		this.hashedges=new HashMap<Integer,HashMap<Integer,edge_data>>();
		this.hashedges.putAll(hashedges);
		this.countedgeg=countedgeg;
		
	}
	
	public DGraph() {
		this.hashnodes=new HashMap<Integer,node_data>() ;
		this.hashedges=new HashMap<Integer,HashMap<Integer,edge_data>>();
	}
	public DGraph(DGraph other) {
		this.hashnodes=new HashMap<Integer,node_data>();
		hashnodes.putAll(other.hashnodes);
		
		this.hashedges=new HashMap<Integer,HashMap<Integer,edge_data>>();
		this.hashedges.putAll(other.hashedges);
		//this.hashnodes=new HashMap<Integer,node_data>(other.hashnodes) ;
		//this.hashedges=new HashMap<Integer,HashMap<Integer,edge_data>>(other.hashedges);
		this.countedgeg=other.countedgeg;
		
	}


	@Override
	public node_data getNode(int key) {
		// TODO Auto-generated method stub
		if(this.getHashnodes().get(key)==null) return null;
		else
		return hashnodes.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		// TODO Auto-generated method stub

		if( hashnodes.get(src)!=null&& hashedges.get(src).get(dest)!=null)   //to check if its doesnt connect if it returns null{

		{
			double weight=hashedges.get(src).get(dest).getWeight();
			int tag=hashedges.get(src).get(dest).getTag();
			nodedata source= (nodedata)hashnodes.get(src);
			nodedata des=(nodedata)hashnodes.get(dest);
			edgedata edg=new edgedata(source,des,weight,tag);
			return edg;
		}
		return null;

	}

	@Override
	public void addNode(node_data n) {  //check
		// TODO Auto-generated method stub
		if(hashnodes.get(n.getKey())!=null) return;
		hashnodes.put(n.getKey(), (nodedata)n);
		HashMap<Integer,edge_data> value= new HashMap<Integer,edge_data>();
		hashedges.put(n.getKey(), value);
		MC++;
		updateListener();
	}




	private void updateListener() 
	{
		if(Lis != null)
		{
			Lis.graphUpdated();
		}

	}



	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub

		//if your trying to connect something that is allready connected
		if(hashnodes.get(src)==null||hashnodes.get(dest)==null) {
			System.out.println("not exsist");
		}
		else {
			edgedata newedge=new edgedata((nodedata)hashnodes.get(src),(nodedata)hashnodes.get(dest),w,0);
			hashedges.get(src).put(dest, newedge);		
			countedgeg++;
		}
		MC++;
		updateListener();

		

	}

	public HashMap<Integer, node_data> getHashnodes() {
		return hashnodes;
	}
	public void setHashnodes(HashMap<Integer, node_data> hashnodes) {
		this.hashnodes = hashnodes;
		MC++;
		updateListener();
	}
	public HashMap<Integer, HashMap<Integer, edge_data>> getHashedges() {
		return hashedges;
	}
	public void setHashedges(HashMap<Integer, HashMap<Integer, edge_data>> hashedges) {
		this.hashedges = hashedges;
		MC++;
		updateListener();
	}
	public int getCountedgeg() {
		return countedgeg;
	}
	public void setCountedgeg(int countedgeg) {
		this.countedgeg = countedgeg;
		MC++;
		updateListener();
	}
	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return hashnodes.values();   
	}

	@Override
	public Collection<edge_data> getE(int node_id) {

		// TODO Auto-generated method stub
		if(this.getHashnodes().get(node_id)==null) return null;
		else
		return hashedges.get(node_id).values();


	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		node_data remove=new nodedata();
		if(hashnodes.get(key)==null) {
			System.out.println("do not exsist");
		}
		else {
			remove=new nodedata((nodedata)hashnodes.get(key));
			hashnodes.remove(key);
			hashedges.remove(key);
			Collection<node_data> node=this.getV();
			Iterator J=node.iterator();
			while(J.hasNext()) {
			   nodedata current=(nodedata) J.next();			
			   HashMap<Integer,edge_data> hashcurrent=this.getHashedges().get(current.getKey());
			   hashcurrent.remove(key);
			   this.countedgeg=this.countedgeg-1;

			}
		}
		MC++;
		updateListener();
		return remove;

	}




	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub

		edge_data remove=new edgedata();
		if(hashnodes.get(src)==null||hashnodes.get(dest)==null) {
			System.out.println("there is no posibility to remove");
		}

		else {
			remove=(edgedata)hashedges.get(src).get(dest);
			hashedges.get(src).remove(dest);

		}
		countedgeg--;
		updateListener();
		MC++;
		return remove; 
	}


	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return hashnodes.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return countedgeg;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return MC;
	}

}
