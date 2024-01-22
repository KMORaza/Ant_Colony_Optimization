# Created: September 2022
import random
class AntColony:
    def __init__(self, graph, num_ants, alpha=1.0, beta=2.0, evaporation_rate=0.5):
        self.graph = graph
        self.num_ants = num_ants
        self.alpha = alpha
        self.beta = beta
        self.evaporation_rate = evaporation_rate
        self.pheromone_matrix = self.initialize_pheromones()
        self.ants = [Ant(graph.get_random_node()) for _ in range(num_ants)]
    def initialize_pheromones(self):
        num_nodes = len(self.graph.nodes)
        return [[1.0] * num_nodes for _ in range(num_nodes)]
    def simulate(self, num_iterations):
        for _ in range(num_iterations):
            for ant in self.ants:
                ant.move(self.graph)
            self.update_pheromones()
            self.reset_ants()
    def update_pheromones(self):
        for i in range(len(self.pheromone_matrix)):
            for j in range(len(self.pheromone_matrix[i])):
                self.pheromone_matrix[i][j] *= (1 - self.evaporation_rate)
        for ant in self.ants:
            total_pheromone_delta = 1 / len(ant.visited_nodes)
            for i in range(len(ant.visited_nodes) - 1):
                current_node = ant.visited_nodes[i]
                next_node = ant.visited_nodes[i + 1]
                pheromone_delta = total_pheromone_delta
                self.pheromone_matrix[current_node][next_node] += pheromone_delta
    def reset_ants(self):
        for ant in self.ants:
            ant.visited_nodes = [ant.current_node]
    def get_pheromone_level(self, start_node, end_node):
        return self.pheromone_matrix[start_node][end_node]
