# Created: September 2022
import random
class Ant:
    def __init__(self, start_node):
        self.current_node = start_node
        self.visited_nodes = [start_node]
    def move(self, graph, alpha, beta):
        neighbors = graph.get_neighbors(self.current_node)
        next_node = self.choose_next_node(graph, neighbors, alpha, beta)
        self.visited_nodes.append(next_node)
        self.current_node = next_node
    def choose_next_node(self, graph, neighbors, alpha, beta):
        pheromone_sum = sum(graph.get_pheromone_level(self.current_node, neighbor) ** alpha *
                           graph.get_attractiveness(self.current_node, neighbor) ** beta for neighbor in neighbors)
        probabilities = [(graph.get_pheromone_level(self.current_node, neighbor) ** alpha *
                          graph.get_attractiveness(self.current_node, neighbor) ** beta) / pheromone_sum
                          for neighbor in neighbors]
        next_node = random.choices(neighbors, probabilities)[0]
        return next_node
