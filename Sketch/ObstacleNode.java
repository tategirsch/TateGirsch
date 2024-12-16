/**
 * The ObstacleNode class represents a node in a linked list of obstacles
 * in the simulation. Each node holds a reference to an obstacle and the next node in the list.
 * 
 * @author Tate Girsch
 * @version 1.0
 * @since 2024-11-10
 */
public class ObstacleNode {
  private Obstacle val;
  private ObstacleNode next;
  private static ObstacleNode head = null;

  /**
   * Creates a new ObstacleNode with the specified obstacle. 
   * If this is the first node in the list, it sets the head to this node.
   * @param obs the obstacle to be placed in the linked list
   */
  public ObstacleNode(Obstacle obs) {
    val = obs;
    next = null;
    if(head == null) {
      head = this;
    }
  }

  /**
   * Returns the head of the linked list of obstacles.
   * @return the head Obstacle in the Linked List of Obstacle nodes.
   */
  public ObstacleNode getHead() {
    return head;
  }
  
  /**
   * Returns the obstacle stored in this node.
   * @return the Obstacle object in this node
   */
  public Obstacle getObstacle() {
      return val;
  }
  
  /**
   * Adds a new obstacle to the end of the linked list by creating a new ObstacleNode.
   * @param obs the Obstacle to add
   */
  public void addObstacle(Obstacle obs) {
    ObstacleNode newNode = new ObstacleNode(obs);
    for(ObstacleNode n = head; n != null; n = n.next) {
      if(n.next == null) {
        n.next = newNode;
      }
    }
  }

  /**
   * Returns the next node in the linked list.
   * @return the next ObstacleNode
   */
  public ObstacleNode getNext() {
    return next;
  }
  
  /**
   * Sets the next node in the linked list.
   * @param next the next ObstacleNode
   */
  public void setNext(ObstacleNode next) {
    this.next = next;
  }
}
