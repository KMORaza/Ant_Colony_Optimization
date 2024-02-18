/*
An example implementation of reversible circuit synthesis using ant colony optimization to improve efficiency
*/
// Created: September 2022
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>
#define NUM_ANTS 10
#define NUM_ITERATIONS 100
#define ALPHA 1.0   
#define BETA 2.0    
#define RHO 0.5
#define Q 100       
#define Q0 0.9      
#define NUM_NODES 6
#define INDEX(i, j, num_nodes) ((i) * (num_nodes) + (j))
typedef struct {
    int value;
} Node;
typedef struct {
    int source;
    int destination;
    double pheromone;
    double heuristic;
} Edge;
typedef struct {
    int num_nodes;
    int num_edges;
    Node nodes[NUM_NODES];
    Edge edges[NUM_NODES * (NUM_NODES - 1) / 2];
} Graph;
typedef struct {
    int* tour;
    double tour_length;
} Ant;
void initializeGraph(Graph *graph) {
    graph->num_nodes = NUM_NODES;
    graph->num_edges = NUM_NODES * (NUM_NODES - 1) / 2;
    for (int i = 0; i < graph->num_nodes; ++i) {
        graph->nodes[i].value = rand() % 100; 
    }
    int edgeIndex = 0;
    for (int i = 0; i < graph->num_nodes - 1; ++i) {
        for (int j = i + 1; j < graph->num_nodes; ++j) {
            graph->edges[edgeIndex].source = i;
            graph->edges[edgeIndex].destination = j;
            graph->edges[edgeIndex].pheromone = 1.0; 
            graph->edges[edgeIndex].heuristic = 1.0 / abs(graph->nodes[i].value - graph->nodes[j].value); 
            ++edgeIndex;
        }
    }
}
void initializePheromones(Graph *graph) {
    for (int i = 0; i < graph->num_edges; ++i) {
        graph->edges[i].pheromone = 1.0; 
    }
}
void generateRandomTour(Graph *graph, Ant *ant) {
    ant->tour_length = 0;
    ant->tour = malloc(graph->num_nodes * sizeof(int));
    for (int i = 0; i < graph->num_nodes; ++i) {
        ant->tour[i] = i;
    }
    for (int i = graph->num_nodes - 1; i > 0; --i) {
        int j = rand() % (i + 1);
        int temp = ant->tour[i];
        ant->tour[i] = ant->tour[j];
        ant->tour[j] = temp;
    }
}
double calculateTourLength(Graph *graph, Ant *ant) {
    double tourLength = 0.0;
    for (int i = 0; i < graph->num_nodes - 1; ++i) {
        int source = ant->tour[i];
        int destination = ant->tour[i + 1];
        tourLength += abs(graph->nodes[source].value - graph->nodes[destination].value);
    }
    int lastNode = ant->tour[graph->num_nodes - 1];
    tourLength += abs(graph->nodes[lastNode].value - graph->nodes[ant->tour[0]].value);
    return tourLength;
}
bool containsNode(int *tour, int length, int node) {
    for (int i = 0; i < length; ++i) {
        if (tour[i] == node) {
            return true;
        }
    }
    return false;
}
void updatePheromones(Graph *graph, Edge *deltaPheromones) {
    for (int i = 0; i < graph->num_edges; ++i) {
        for (int j = 0; j < NUM_ANTS; ++j) {
            graph->edges[i].pheromone *= (1 - RHO); 
            graph->edges[i].pheromone += deltaPheromones[i].pheromone; 
        }
    }
}
int chooseNextNode(Graph *graph, Ant *ant, Edge *pheromones, Edge *heuristics, double explorationProb) {
    int current = ant->tour[(int)ant->tour_length - 1];
    double probabilities[graph->num_nodes];
    double totalProbability = 0.0;
    for (int i = 0; i < graph->num_nodes; ++i) {
        if (!containsNode(ant->tour, (int)ant->tour_length, i)) {
            probabilities[i] = pow(pheromones[INDEX(current, i, graph->num_nodes)].pheromone, ALPHA) *
                               pow(heuristics[INDEX(current, i, graph->num_nodes)].heuristic, BETA);
            totalProbability += probabilities[i];
        } else {
            probabilities[i] = 0.0;
        }
    }
    if (rand() / (double)RAND_MAX < explorationProb) {
        double randomValue = (rand() / (double)RAND_MAX) * totalProbability;
        double cumulativeProbability = 0.0;
        for (int i = 0; i < graph->num_nodes; ++i) {
            if (cumulativeProbability + probabilities[i] >= randomValue) {
                return i;
            }
            cumulativeProbability += probabilities[i];
        }
    } else {
        double maxProbability = -1.0;
        int nextNode = -1;
        for (int i = 0; i < graph->num_nodes; ++i) {
            if (probabilities[i] > maxProbability) {
                maxProbability = probabilities[i];
                nextNode = i;
            }
        }
        return nextNode;
    }
    return -1;
}
void antColonyOptimization(Graph *graph) {
    Edge *deltaPheromones = malloc(graph->num_edges * sizeof(Edge));
    Ant ants[NUM_ANTS];
    for (int iteration = 0; iteration < NUM_ITERATIONS; ++iteration) {
        for (int antIndex = 0; antIndex < NUM_ANTS; ++antIndex) {
            generateRandomTour(graph, &ants[antIndex]);
            while (ants[antIndex].tour_length < graph->num_nodes) {
                int nextNode = chooseNextNode(graph, &ants[antIndex], graph->edges, graph->edges, Q0);
                ants[antIndex].tour[(int)ants[antIndex].tour_length] = nextNode;
                ants[antIndex].tour_length++;
            }
        }
        double bestTourLength = INFINITY;
        int bestAntIndex = -1;
        for (int antIndex = 0; antIndex < NUM_ANTS; ++antIndex) {
            ants[antIndex].tour_length = calculateTourLength(graph, &ants[antIndex]);
            if (ants[antIndex].tour_length < bestTourLength) {
                bestTourLength = ants[antIndex].tour_length;
                bestAntIndex = antIndex;
            }
        }
        printf("Iteration %d: Best Tour Length: %lf\n", iteration + 1, bestTourLength);
        for (int i = 0; i < graph->num_edges; ++i) {
            deltaPheromones[i].pheromone = Q / ants[bestAntIndex].tour_length;
        }
        updatePheromones(graph, deltaPheromones);
    }
    free(deltaPheromones);
}
int main() {
    srand(time(NULL)); 
    Graph graph;
    initializeGraph(&graph);
    initializePheromones(&graph);
    antColonyOptimization(&graph);
    return 0;
}
