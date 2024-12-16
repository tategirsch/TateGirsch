/**
 * The Obstacle class represents a circular obstacle in the simulation,
 * defined by its position and radius. Obstacles can detect and handle collisions with particles.
 * 
 * @author Tate Girsch
 * @version 1.0
 * @since 2024-11-10
 */
public class Obstacle {
    private double id;
    private double x;
    private double y;
    private double radius;

    /**
     * Constructs an Obstacle with the specified position and radius.
     * @param xPos the x-coordinate of the obstacle
     * @param yPos the y-coordinate of the obstacle
     * @param r the radius of the obstacle
     */
    public Obstacle(double id, double xPos, double yPos, double r) {
        this.id = id;
        this.x = xPos;
        this.y = yPos;
        this.radius = r;
    }

    /**
     * gets the id of the obstacle
     * @return the id
     */
    public double getId() {
        return id;
    }

    /**
     * Gets the x-coordinate of the obstacle.
     * @return the x-coordinate
     */
    public double getX() { return x; }

    /**
     * Gets the y-coordinate of the obstacle.
     * @return the y-coordinate
     */
    public double getY() { return y; }

    /**
     * Gets the radius of the obstacle.
     * @return the radius
     */
    public double getRadius() { return radius; }

    /**
     * Sets the x-coordinate of the obstacle.
     * @param xPos the new x-coordinate
     */
    public void setX(double xPos) { this.x = xPos; }

    /**
     * Sets the y-coordinate of the obstacle.
     * @param yPos the new y-coordinate
     */
    public void setY(double yPos) { this.y = yPos; }

    /**
     * Sets the radius of the obstacle.
     * @param r the new radius
     */
    public void setRadius(double r) { this.radius = r; }

    /**
     * Checks if there is a collision between this obstacle and the specified particle.
     * A collision occurs if the distance between the obstacle and the particle is
     * less than or equal to the sum of their radii.
     * @param p the Particle to check for collision
     * @return true if a collision is detected, false otherwise
     */
    public boolean checkCollision(Particle p) {
        double distance = Vector2DMath.magnitude(x - p.getX(), y - p.getY());
        return distance <= (radius + p.getRadius());
    }

    /**
     * Calculates the collision normal between the obstacle and the specified particle.
     * Repositions the particle to prevent overlap, based on the calculated normal.
     * @param p the Particle involved in the collision
     * @return an array representing the collision normal
     */
    public double[] collide(Particle p) {
        double[] normal = Vector2DMath.normal(p.getX() - this.x, p.getY() - this.y);
        double distance = radius + p.getRadius();
        p.setX(x + normal[0] * distance);
        p.setY(y + normal[1] * distance);
        return normal;
    }
}
