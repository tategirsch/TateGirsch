# Particle Simulator
Project Lead: Tate Girsch
### Overview

The Particle Simulator project visualizes an interactive particle system where particles experience gravity, collisions, and interactions with obstacles in the simulation. The simulator demonstrates fundamental physics principles, such as vector-based motion, and elastic collisions. It allows users to manipulate the environment by adding or removing particles and interacting with obstacles through mouse input linked to a csv file via an API.

### Features
* **Collision Handling:** Each particles collisions are individually calculated with the obstacle to ensure precise interactions.
* **Gravity Simulation:** brings the particles to the floor and they bounce
* **Adding Particles:** Pressing "+" adds 50 particles from the simulation
* **Deleting Particles:** Pressing "-" deletes 50 particles from the simulation
* **Adding Obstacles:** Left clicking anywhere on the screen adds an obstacle there and stores it in the obstacles.csv file via serverAPI.py
* **Removing Obstacles:** Right clicking on obstacles removes them from the screen as well as the obstacles.csv file via serverAPI.py

### How to Run the Program
* **Load obstacles.csv** with obstacles you want to have placed in the program
* **Launch serverAPI.py**
* **Launch Processing Sketch.pde**
