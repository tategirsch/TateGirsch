import java.util.ArrayList;

Simulation sim;
ArrayList<Obstacle> obstacles;

void setup() {
  size(800,800);
  ellipseMode(RADIUS);

  sim = new Simulation(50);

  sim.addParticles(50);
}

void draw(){
  background(200, 200, 200);
  obstacles = sim.getObstacles();

  double mouseXPos = (double)mouseX / width;
  double mouseYPos = (double)(height - mouseY) / height;
  
  sim.update(0.01);
  Particle[] particles = sim.getParticles();
  for(Particle p: particles) {
    if(p != null) {
      fill(0, 0, 200);
      circle((int)(p.getX() * width), (int)(height - p.getY() * height), (int)(p.getRadius() * width));
    }
  }
  
  for(Obstacle o : obstacles) {
     fill(200, 0, 0);
     circle((int)(o.getX() * width), (int)(height - o.getY() * height), (int)(o.getRadius() * width));
  }

  if(mousePressed && (mouseButton == LEFT)) {
    sim.addObstacle(mouseXPos, mouseYPos, 0.1);
    System.out.println(obstacles.size());
  }
  double dist;
  if(mousePressed && (mouseButton == RIGHT)) {
    for(Obstacle o : obstacles) {
        dist = sqrt(sq((int)(mouseX - o.getX() * height)) + sq((int)(mouseY - (height - o.getY() * height))));
        if(dist <= 80) {
          System.out.print(o.getId() + ": ");
          sim.removeObstacle(o);
          break;
        }
    }
  }
}

void keyPressed() {
  if(key == '+') {
    sim.addParticles(50);
    draw();
  }
  else if(key == '-') {
    sim.removeParticles(50);
    draw();
  }
}
