package backEnd;

import java.util.LinkedList;

import IbackEnd.IMason;
import IbackEnd.INode;
import IbackEnd.IPath;
import frontEnd.DrawEdge;
import frontEnd.DrawNode;

public class Main {

	public static void main (String[]args){
		 DrawNode a=new DrawNode();
		 DrawNode b=new DrawNode();
		 DrawNode c=new DrawNode();
		 DrawNode d=new DrawNode();
		 DrawNode e=new DrawNode();
		 DrawNode f=new DrawNode();
		 DrawNode g=new DrawNode();
		 
		 DrawEdge ab =new DrawEdge(a,1);
		 ab.setEndNode(b);
		 ab.setGainValue(1);
		 
		 DrawEdge bc =new DrawEdge(b,2);
		 bc.setEndNode(c);
		 bc.setGainValue(5);
		 
		 DrawEdge cd =new DrawEdge(c,3);
		 cd.setEndNode(d);
		 cd.setGainValue(10);
		 
		 DrawEdge de =new DrawEdge(d,4);
		 de.setEndNode(e);
		 de.setGainValue(2);
		 
		 DrawEdge ef =new DrawEdge(e,5);
		 ef.setEndNode(f);
		 ef.setGainValue(1);
		 
		 DrawEdge ed =new DrawEdge(e,6);
		 ed.setEndNode(d);
		 ed.setGainValue(-2);
		 
		 DrawEdge dc =new DrawEdge(d,7);
		 dc.setEndNode(c);
		 dc.setGainValue(-1);
		 
		 DrawEdge eb =new DrawEdge(e,8);
		 eb.setEndNode(b);
		 eb.setGainValue(-1);
		 
		 DrawEdge bg =new DrawEdge(b,9);
		 bg.setEndNode(g);
		 bg.setGainValue(10);
		 
		 DrawEdge ge =new DrawEdge(g,10);
		 ge.setEndNode(e);
		 ge.setGainValue(2);
		 
		 DrawEdge gg =new DrawEdge(g,11);
		 gg.setEndNode(g);
		 gg.setGainValue(-1);
		 
		 a.addEdges(ab);
		 b.addEdges(bc);
		 b.addEdges(bg);
		 c.addEdges(cd);
		 d.addEdges(de);
		 d.addEdges(dc);
		 e.addEdges(ed);
		 e.addEdges(eb);
		 e.addEdges(ef);
		 g.addEdges(gg);
		 g.addEdges(ge);
		 
		 INode vertices[]={a,b,c,d,e,f,g};
		 
		 IMason x = new MasonMethod(vertices);
		 LinkedList<IPath> p=x.getForwardPaths(a, f);
		// LinkedList<IPath> l=x.getIndividualLoops();
		 for (int i=0;i<p.size();i++)
			// for (int j=0;j<l.get(i).getPath().size();j++)
			//	System.out.println(l.get(i).getPath().get(j).getnum());
				 System.out.println(p.get(i).getPathGain());
		 
		 double sol=x.OverallTransferFunction(a, f);
		 System.out.println(p.getFirst());

		 
		 System.out.println(sol);
	}
}
