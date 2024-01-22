# Created: September 2022
class Edge:
    def __init__(self, start_node, end_node):
        self.start_node = start_node
        self.end_node = end_node
        self.calculate_distance()
    def calculate_distance(self):
        # Simple Euclidean distance as the edge weight
        dx = self.start_node.x - self.end_node.x
        dy = self.start_node.y - self.end_node.y
        self.distance = (dx**2 + dy**2)**0.5
    def __repr__(self):
        return f"Edge({self.start_node.name}, {self.end_node.name})"
