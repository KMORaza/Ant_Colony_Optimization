# Created: September 2022
from node import Node
from edge import Edge
from graph import Graph
from ant import Ant
from ant_colony import AntColony
def main():
    node1 = Node("A", 0, 0)
    node2 = Node("B", 1, 2)
    node3 = Node("C", 3, 1)
    node4 = Node("D", 5, 2)
    graph = Graph()
    graph.add_node(node1)
    graph.add_node(node2)
    graph.add_node(node3)
    graph.add_node(node4)
    graph.add_edge(Edge(node1, node2))
    graph.add_edge(Edge(node2, node3))
    graph.add_edge(Edge(node3, node4))
    graph.add_edge(Edge(node4, node1))
    ant_colony = AntColony(graph, num_ants=5)
    ant_colony.simulate(num_iterations=10)
if __name__ == "__main__":
    main()
