#include <queue>
#include <random>
#include <algorithm> // std::shuffle
#include <iostream>
using namespace std;

class State
{
public:
    int puzzleState[9] = {0}; // position of all 8 tiles
    int cost = 0;             // g, real cost from start to current position
    int heuristic = 0;        // h, expected cost from current to the goal state
    int evaluation = 0;       // f, cost + heuristic
};

class Board
{
public:
    Board(State current, int zero);
    void print();
    State getGoal();
    std::vector<int> allowedMoves();
    int manhattan();
    int hamming();

private:
    State goal;
    State current;
    int zeroPos;

    // Create openList
    priority_queue<int, vector<int>, greater<int>> openList; // with smallest value first

    // Create closeList, what data structure?
};

// Create moveFunction by swapping tiles

// Check if the board is the same as the goal

// Update evaluation function

// A* Seach algorithm
// Expand first element in openList
// save path
