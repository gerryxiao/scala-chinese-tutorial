/**
* innerclass.scala
* 这个是scala内部类的例子，它和java的内部类有很大的不同
* @author scala.org
* @version 0.1 
*/

class Graph {
  class Node {
    var connectedNodes:List[Node]=Nil
    def connectTo(node:Node) {
      if(connectedNodes.find(node.equals).isEmpty){
	connectedNodes=node::connectedNodes
      }
    }
  }
  var nodes:List[Node]=Nil
  def newNode:Node={
    val res=new Node
    nodes=res::nodes
    res
  }
}

object GraphTest extends Application {
  val g=new Graph
  val n1=g.newNode
  val n2=g.newNode
  val n3=g.newNode
  n1.connectTo(n2)
  println("innerclass test") 
}
