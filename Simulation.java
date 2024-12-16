import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
/**
 * The Simulation class manages a simulation of particles falling around obstacles.
 * It allows adding and removing particles and obstacles, setting the position of obstacles,
 * and updating the state of the simulation over time.
 * 
 * @author Tate Girsch
 * @version 1.0
 * @since 2024-11-10
 */
public class Simulation {
  private int numParticles;
  private double numObstacles;
  private Random rand = new Random();
  private Particle[] particles;
  private Obstacle initialObstacle;
  private obstacleAPI api;
  private ArrayList<Obstacle> obstacles;
//   private Obstacle[] obstacles = api.getObstaclesAPI();

  /**
   * Initializes the simulation with a specified number of particles and a single obstacle.
   * @param n the initial number of particles in the simulation
   */
  public Simulation(int n) {
    api = new obstacleAPI();
    obstacles = new ArrayList<>();
    particles = new Particle[n];
    numParticles = n;
    for (int i = 0; i < numParticles; i++) {
        particles[i] = new Particle(rand.nextDouble(), 1, 0.02);
    }
    loadObstacles();
    numObstacles = obstacles.size();
  }


  private void loadObstacles() {
    obstacles.addAll(api.getObstacles());
    System.out.println("Loaded obstacles from backend.");
  }

  /**
   * Returns the numObstacles
   * @return the number of obstacles in the simulation
   */
  public double getNumObstacles() {
    return numObstacles;
  }

  /**
   * Returns an array of particles currently in the simulation.
   * @return an array of Particle objects
   */
  public Particle[] getParticles() {  
      return particles;
  }

  /**
   * Returns the main obstacle of the simulation.
   * @return the primary Obstacle object
   */
  public Obstacle getObstacle() {  
      return initialObstacle;
  }

  /**
   * Returns the number of particles in the simulation.
   * @return the number of particles
   */
  public int getNumParticles(){  
      return numParticles;
  }

  /**
   * Returns the head of the linked list of obstacles in the simulation.
   * @return the head ObstacleNode
   */
  public ArrayList<Obstacle> getObstacles() {  
    numObstacles = obstacles.size();
    return obstacles;
  }

  /**
   * Adds a specified number of particles to the simulation. If the array is full,
   * it doubles the capacity of the array.
   * @param amount the number of particles to add
   */
  public void addParticles(int amount) {
      numParticles += amount;
      Particle[] temp;
      if(numParticles != 0) {
          temp = new Particle[particles.length * 2];
      } else {
          temp = new Particle[10];
          for (int i = 0; i < 10; i++) {
              temp[i] = new Particle(rand.nextDouble(), 1, 0.02);
          }
          numParticles = 20;
      }
      for(int i = 0; i < particles.length; i++) {
          temp[i] = particles[i];
      }
      
      if(temp.length != 0) {
          for(int i = numParticles - amount; i < numParticles; i++) {
              if(temp[i] == null) {
                  temp[i] = new Particle(rand.nextDouble(), 1, 0.02);
              }  
          }
          particles = temp;
      }
  }

  /**
   * Removes a specified number of particles from the simulation.
   * If the requested amount exceeds the current number of particles,
   * it removes all particles.
   * @param amount the number of particles to remove
   */
  public void removeParticles(int amount) {
      if(numParticles < amount) {
          numParticles = 0;
          particles = new Particle[0];
      } else {
          numParticles -= amount;
          Particle[] temp = new Particle[numParticles];
          for(int i = 0; i < numParticles; i++) {
              temp[i] = particles[i];
          }
          particles = temp;
      }
  }

  /**
   * Adds a new obstacle to the simulation by appending it to the array list of obstacles.
   * @param obs the Obstacle to add
   */
    public void addObstacle(double mouseX, double mouseY, double radius) {
        Obstacle newObs = new Obstacle(numObstacles, mouseX, mouseY, radius);
        try {
            api.addObstacleAPI(newObs.getId(), newObs.getX(), newObs.getY(), 0.1);
            obstacles.add(newObs);
            numObstacles += 1.0;
        } catch (Exception ex) {
            System.out.println("Error adding obstacle to API.");
        }
    }

  /**
   * Sets the position of the main obstacle in the simulation.
   * @param obs the obstacle which will have its x and y-coordinates altered
   * @param mouseX the new x-coordinate of the obstacle
   * @param mouseY the new y-coordinate of the obstacle
   */
    public void setObstaclePos(Obstacle obs, double mouseX, double mouseY) {
        obs.setX(mouseX);
        obs.setY(mouseY);
    }

  /**
   * Removes a specific obstacle from the simulation's linked list of obstacles.
   * @param obs the Obstacle to remove
   */
    public void removeObstacle(Obstacle obs) {
    try {
        api.removeObstacleAPI(obs.getId());        
        obstacles.remove(obs);
    } catch (Exception ex) {
        System.out.println("Error removing obstacle from API");
    }
  }

  /**
   * Updates the state of the simulation by advancing each particle based on the given time step,
   * and handling collisions with the main obstacle.
   * @param dt the time step for the update
   */
  public void update(double dt) {
      for(int i = 0; i < numParticles; i++) {
        particles[i].update(dt);
        for(Obstacle obs: obstacles)  
            if(obs != null) {
                particles[i].handleCollision(obs);
            }
      }
  }
}
