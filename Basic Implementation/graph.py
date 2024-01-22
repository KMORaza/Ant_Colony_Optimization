# Created: September 2022
class Graph:
    def __init__(self):
        self.nodes = []
        self.edges = []
        self.pheromone_matrix = None 
    def add_node(self, node):
        self.nodes.append(node)
    def add_edge(self, edge):
        self.edges.append(edge)
    def get_neighbors(self, node):
        return [edge.end_node for edge in self.edges if edge.start_node == node]
    def get_random_node(self):
        return random.choice(self.nodes)
    def get_pheromone_level(self, start_node, end_node):
        return self.pheromone_matrix[start_node][end_node]
    def get_attractiveness(self, start_node, end_node):
        distance = self.calculate_distance(start_node, end_node)
        return 1 / (distance + 1)  # Adding 1 to avoid division by zero
    def calculate_distance(self, start_node, end_node):
        dx = start_node.x - end_node.x
        dy = start_node.y - end_node.y
        return (dx**2 + dy**2)**0.5
