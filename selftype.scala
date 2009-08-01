
/*                     __                                               *\
**     ________ ___   / /  ___     Scala                                **
**    / __/ __// _ | / /  / _ |    (c) 2005-2009 , LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */
/** 
* 这是个scala自我类型指示的例子
* @author scala.org 
* @version 0.1
* $Id$
*/
abstract class Graph {
  type Edge
  type Node <: NodeIntf   //Node是NodeIntf的子类
  abstract class NodeIntf {
    def connectWith(node:Node):Edge
  }
  def nodes: List[Node]
  def edges: List[Edge]
  def addNode:Node
}

abstract class DirectedGraph extends Graph {
  type Edge <: EdgeImpl
  class EdgeImpl(orgin:Node,dest:Node) {
    def from=orgin
    def to=dest
   
  }
  class NodeImpl extends NodeIntf { //NodeImpl也是NodeIntf的子类，但它和Node是什么关系?
    self:Node=>    //没有这个说明，下面函数的定义不能编译！！！！
    def connectWith(node:Node):Edge= {
      val edge=newEdge(this,node)  //这个this的类型是NodeImpl
      edges=edge::edges
      edge
    }
  }
  protected def newNode:Node
  protected def newEdge(from:Node,to:Node):Edge //这个方法的参数类型是Node
  var nodes:List[Node]=Nil
  var edges:List[Edge]=Nil
  def addNode:Node={
    val node=newNode
    nodes=node::nodes
    node
  }
}

class ConcreteDirectedGraph extends DirectedGraph {
  type Edge=EdgeImpl
  type Node=NodeImpl
  protected def newNode:Node=new NodeImpl
  protected def newEdge(f:Node,t:Node):Edge=
    new EdgeImpl(f,t)
}
object GraphTest extends Application {
  val g:Graph=new ConcreteDirectedGraph
  val n1=g.addNode
  val n2=g.addNode
  val n3=g.addNode
  n1.connectWith(n2)
  n2.connectWith(n3)
  n1.connectWith(n3)
  println("oops,it works")
}
