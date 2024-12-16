from flask import Flask, redirect, url_for, request, jsonify
import csv
import requests

app = Flask(__name__)

# load from csv
obstacles = [] 

with open('obstacles.csv', 'r') as csvfile:
    reader = csv.DictReader(csvfile)
    for row in reader:
        obstacles.append({'id': float(row['id']), 'xPos': float(row['xPos']), 'yPos': float(row['yPos']), 'radius': float(row['radius'])})
        
@app.route('/obstacle', methods=["POST"])
def add_obstacle():
    # add to the obstacles (request.json)
    # save obstacles to csv
    obstacle = request.json
    obstacles.append(obstacle)
    
    with open('obstacles.csv', 'w', newline='') as csvfile:
        fieldnames = ['id', 'xPos', 'yPos', 'radius']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(obstacles)
    
    return "Success"

@app.route('/obstacle/<id>', methods=["POST"])
def delete_obstacle(id):
    # delete obstacles from obstacles (perhaps using an id)
    # save obstacles to csv
    for obstacle in obstacles:
        print(obstacle.get('id'))
        if float(id) == obstacle.get('id'):
            obstacles.remove(obstacle)     
            return "Success"
    return "Fail"


@app.route('/obstacle', methods=["GET"])
def get_obstacles():
    return jsonify(obstacles)

if __name__ == '__main__':
    app.run(debug=True)