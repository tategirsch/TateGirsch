/**
 * The Particle class represents a particle in the simulation with properties
 * such as position, velocity, radius, and gravity. Particles can move, experience gravity,
 * and handle collisions with obstacles.
 * 
 * @author Tate Girsch
 * @version 1.0
 * @since 2024-11-10
 */
public class Particle {
    double x;
    double y;
    double radius;
    double velocityX;
    double velocityY;
    double gravity;

    /**
     * Constructs a Particle with the specified position and radius, and initializes
     * its velocity and gravity.
     * @param xPos the initial x-coordinate of the particle
     * @param yPos the initial y-coordinate of the particle
     * @param initRadius the radius of the particle
     */
    public Particle(double xPos, double yPos, double initRadius) {
        this.x = xPos;
        this.y = yPos;
        this.radius = initRadius;
        velocityX = 0.0;
        velocityY = 0.0;
        gravity = -1;
    }

    /**
     * Gets the x-coordinate of the particle.
     * @return the x-coordinate
     */
    public double getX() { return x; }

    /**
     * Gets the y-coordinate of the particle.
     * @return the y-coordinate
     */
    public double getY() { return y; }

    /**
     * Gets the radius of the particle.
     * @return the radius
     */
    public double getRadius() { return radius; }

    /**
     * Gets the x-component of the particle's velocity.
     * @return the x-component of the velocity
     */
    public double getVelocityX() { return velocityX; }

    /**
     * Gets the y-component of the particle's velocity.
     * @return the y-component of the velocity
     */
    public double getVelocityY() { return velocityY; }

    /**
     * Sets the x-coordinate of the particle.
     * @param xPos the new x-coordinate
     */
    public void setX(double xPos) { this.x = xPos; }

    /**
     * Sets the y-coordinate of the particle.
     * @param yPos the new y-coordinate
     */
    public void setY(double yPos) { this.y = yPos; }

    /**
     * Sets the radius of the particle.
     * @param r the new radius
     */
    public void setRadius(double r) { this.radius = r; }

    /**
     * Sets the x-component of the particle's velocity.
     * @param vx the new x-component of the velocity
     */
    public void setVelocityX(double vx) { this.velocityX = vx; }

    /**
     * Sets the y-component of the particle's velocity.
     * @param vy the new y-component of the velocity
     */
    public void setVelocityY(double vy) { this.velocityY = vy; }

    /**
     * Sets the gravity affecting the particle.
     * @param g the gravity value
     */
    public void setGravity(double g) { this.gravity = g; }

    /**
     * Updates the particle's position and velocity based on the time step and gravity.
     * If the particle hits the ground (y <= radius), it bounces back with reduced velocity.
     * @param dt the time step for the update
     */
    public void update(double dt) {
        velocityY = velocityY + gravity * dt;
        
        x = x + velocityX * dt;
        y = y + velocityY * dt;

        if (y <= radius) {
            y = radius;
            velocityY = -1.0 * velocityY * 0.6;
        }
    }

    /**
     * Handles the collision of the particle with a specified obstacle.
     * Adjusts the particle's velocity based on the collision normal.
     * @param obstacle the Obstacle to check for collision
     */
    public void handleCollision(Obstacle obstacle) {
        if (obstacle.checkCollision(this)) {
            double[] normal = obstacle.collide(this);
        
            if (Vector2DMath.magnitude(velocityX, velocityY) < 0.2) {
                velocityX = normal[0] * 0.5;
                velocityY = normal[1] * 0.5;
            } else {
                double[] reflect = Vector2DMath.reflect(normal, velocityX, velocityY);
                velocityX = reflect[0] * 0.6;
                velocityY = reflect[1] * 0.6;
            }
        }
    }

    /**
     * Sets both components of the particle's velocity.
     * @param vx the x-component of the velocity
     * @param vy the y-component of the velocity
     */
    public void setVelocity(double vx, double vy) {
        velocityX = vx;
        velocityY = vy;
    }

    /**
     * Returns the radius of the particle. Included for printing or testing purposes.
     * @return the radius of the particle
     */
    public double print() {
        return radius;
    }
}
