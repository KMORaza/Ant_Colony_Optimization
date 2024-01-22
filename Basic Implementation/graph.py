# Created: September 2022
class Graph:
    def __init__(self):
        self.nodes = []
        self.edges = []
    def add_node(self, node):
        self.nodes.append(node)
    def add_edge(self, edge):
        self.edges.append(edge)
    def get_neighbors(self, node):
        return [edge.end_node for edge in self.edges if edge.start_node == node]
    def get_random_node(self):
        return random.choice(self.nodes)
