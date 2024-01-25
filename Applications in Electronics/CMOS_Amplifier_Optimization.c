/* 
An example implementation of the ant colony optimization of CMOS-based 
sense amplifier that could move to optimal solutions in mimimal duration 
*/
// Created: September 2022
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#define NUM_ANTS 30
#define NUM_ITERATIONS 300
#define ALPHA 1.0
#define BETA 2.0
#define RHO 0.1
#define Q0 0.7
#define PHEROMONE_MIN 0.01
#define PHEROMONE_MAX 1.0
#define NUM_SENSORS 15
#define NUM_TRANSISTORS 25
#define MAX_SENSOR_VALUE 10
#define MAX_POWER_CONSUMPTION 100.0
#define TARGET_DELAY 50.0
double evaluateSolution(int solution[], double powerConsumption[], double delay[]);
void updatePheromones(double pheromones[][NUM_TRANSISTORS], 
double deltaPheromones[][NUM_TRANSISTORS], int ant, int solution[], double solutionQuality);
void antColonyOptimization(double pheromones[][NUM_TRANSISTORS], double powerConsumption[], double delay[]);
int main() {
    srand(time(NULL));
    double pheromones[NUM_SENSORS][NUM_TRANSISTORS];
    for (int i = 0; i < NUM_SENSORS; i++) {
        for (int j = 0; j < NUM_TRANSISTORS; j++) {
            pheromones[i][j] = 1.0;
        }
    }
    double powerConsumption[NUM_TRANSISTORS] = {15.0, 12.0, 18.0, 10.0, 20.0, 
                                                14.0, 16.0, 22.0, 25.0, 30.0, 
                                                8.0, 17.0, 19.0, 21.0, 24.0, 
                                                13.0, 11.0, 23.0, 28.0, 26.0, 
                                                9.0, 27.0, 7.0, 29.0, 31.0};
    double delay[NUM_TRANSISTORS] = {45.0, 48.0, 42.0, 50.0, 40.0, 
                                     46.0, 44.0, 38.0, 35.0, 30.0, 
                                     52.0, 43.0, 41.0, 39.0, 36.0, 
                                     47.0, 49.0, 37.0, 32.0, 34.0, 
                                     51.0, 33.0, 53.0, 31.0, 29.0};
    antColonyOptimization(pheromones, powerConsumption, delay);
    return 0;
}
double evaluateSolution(int solution[], double powerConsumption[], double delay[]) {
    double totalSensorProduct = 1.0;
    double totalPowerConsumption = 0.0;
    double totalDelay = 0.0;
    for (int i = 0; i < NUM_TRANSISTORS; i++) {
        totalSensorProduct *= solution[i];
        totalPowerConsumption += powerConsumption[i];
        totalDelay += delay[i];
    }
    double powerPenalty = fmax(0.0, totalPowerConsumption - MAX_POWER_CONSUMPTION);
    double delayPenalty = fmax(0.0, totalDelay - TARGET_DELAY);
    double objective = totalSensorProduct - 0.5 * powerPenalty - 0.5 * delayPenalty;
    return objective;
}
void updatePheromones(double pheromones[][NUM_TRANSISTORS], double deltaPheromones[][NUM_TRANSISTORS], 
int ant, int solution[], double solutionQuality) {
    double evaporation = RHO;
    for (int i = 0; i < NUM_TRANSISTORS; i++) {
        int sensor = solution[i];
        deltaPheromones[sensor][i] += (1.0 - evaporation) * (Q0 / solutionQuality);
    }
    for (int i = 0; i < NUM_SENSORS; i++) {
        for (int j = 0; j < NUM_TRANSISTORS; j++) {
            pheromones[i][j] = fmax(PHEROMONE_MIN, fmin(PHEROMONE_MAX, pheromones[i][j] * evaporation + deltaPheromones[i][j]));
        }
    }
}
void antColonyOptimization(double pheromones[][NUM_TRANSISTORS], double powerConsumption[], double delay[]) {
    double bestObjective = -1e9; 
    for (int iteration = 0; iteration < NUM_ITERATIONS; iteration++) {
        double deltaPheromones[NUM_SENSORS][NUM_TRANSISTORS] = {0};
        double bestIterationObjective = -1e9;
        for (int ant = 0; ant < NUM_ANTS; ant++) {
            int antSolution[NUM_TRANSISTORS];
            for (int i = 0; i < NUM_TRANSISTORS; i++) {
                double randValue = (double)rand() / RAND_MAX;
                if (randValue < Q0) {
                    int bestSensor = 0;
                    double bestPheromone = pheromones[0][i];
                    for (int s = 1; s < NUM_SENSORS; s++) {
                        if (pheromones[s][i] > bestPheromone) {
                            bestSensor = s;
                            bestPheromone = pheromones[s][i];
                        }
                    }
                    antSolution[i] = bestSensor;
                } else {
                    double totalProbability = 0.0;
                    for (int s = 0; s < NUM_SENSORS; s++) {
                        totalProbability += pow(pheromones[s][i], ALPHA) * pow(1.0 / (s + 1), BETA);
                    }
                    double randomValue = (double)rand() / RAND_MAX;
                    double cumulativeProbability = 0.0;
                    int selectedSensor = 0;
                    for (int s = 0; s < NUM_SENSORS; s++) {
                        cumulativeProbability += pow(pheromones[s][i], ALPHA) * pow(1.0 / (s + 1), BETA) / totalProbability;
                        if (randomValue <= cumulativeProbability) {
                            selectedSensor = s;
                            break;
                        }
                    }
                    antSolution[i] = selectedSensor;
                }
            }
            double solutionQuality = evaluateSolution(antSolution, powerConsumption, delay);
            updatePheromones(pheromones, deltaPheromones, ant, antSolution, solutionQuality);
            if (solutionQuality > bestIterationObjective) {
                bestIterationObjective = solutionQuality;
            }
        }
        if (bestIterationObjective > bestObjective) {
            bestObjective = bestIterationObjective;
            printf("Iteration %d: Best Objective = %.2f\n", iteration + 1, bestObjective);
        }
    }
}
