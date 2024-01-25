/*
An example of ant colony optimization of forms of antenna
*/
// Created: September 2022
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ANT_COUNT 50
#define ITERATIONS 1000
#define ALPHA 1.0
#define BETA 2.0 
#define RHO 0.5 
#define ANTENNA_PARAMS 10 
typedef struct {
    double position[ANTENNA_PARAMS];
    double pheromone;
    double fitness;
} Ant;
Ant ants[ANT_COUNT];
void initializeAnts();
void antColonyAlgorithm();
void updatePheromones();
void calculateFitness();
void printOptimizedParameters();
int main() {
    initializeAnts();
    for (int iteration = 0; iteration < ITERATIONS; iteration++) {
        antColonyAlgorithm();
        updatePheromones();
        calculateFitness();
    }
    printOptimizedParameters();
    return 0;
}
void initializeAnts() {
    for (int i = 0; i < ANT_COUNT; i++) {
        for (int j = 0; j < ANTENNA_PARAMS; j++) {
            ants[i].position[j] = (double)rand() / RAND_MAX; 
        }
        ants[i].pheromone = 1.0; 
        ants[i].fitness = 0.0;   
    }
}
void antColonyAlgorithm() {
    for (int i = 0; i < ANT_COUNT; i++) {
        for (int j = 0; j < ANTENNA_PARAMS; j++) {
            double random_change = ((double)rand() / RAND_MAX - 0.5) * 0.2;  
            ants[i].position[j] += random_change;
        }
    }
}
void updatePheromones() {
    for (int i = 0; i < ANT_COUNT; i++) {
        ants[i].pheromone *= (1.0 - RHO);  
        ants[i].pheromone += ALPHA * ants[i].fitness;  
    }
}
void calculateFitness() {
    for (int i = 0; i < ANT_COUNT; i++) {
        ants[i].fitness = 1.0 / (1.0 + fabs(0.5 - ants[i].position[0])); 
    }
}
void printOptimizedParameters() {
    printf("Optimized Antenna Parameters:\n");
    for (int i = 0; i < ANTENNA_PARAMS; i++) {
        double avg_param = 0.0;
        for (int j = 0; j < ANT_COUNT; j++) {
            avg_param += ants[j].position[i];
        }
        avg_param /= ANT_COUNT;
        printf("Parameter %d: %.4f\n", i + 1, avg_param);
    }
}
