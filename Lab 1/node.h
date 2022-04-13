#include <queue>
#include <random>
#include <algorithm> // std::shuffle
#include <iostream>
#include  <unordered_set>
using namespace std;

class State
{
public:
    State() = default;
    State(int puzzle[], int cost);
    bool operator==(const State& s)const;

    int puzzleState[9] = { 0 }; // position of all 8 tiles
    int cost = 0;             // g, real cost from start to current position
    int heuristic = 0;        // h, expected cost from current to the goal state
    int evaluation = 0;       // f, cost + heuristic
};

//Function object that returns wheter the first argument is greater than the other see cplusplus std::greater
struct compareStates {
    bool operator()(const State& ls, const State& rs) {
        return ls.evaluation > rs.evaluation;
    }
};

class Board
{
public:
    Board(State current, int zero);
    void print();
    std::vector<int> allowedMoves();
    int manhattan();
    int hamming();
    bool isGoal();
    void solveBoard(Board& b);
    void moveZero(int move);


private:
    State goal;
    State current;
    int zeroPos;

    // Create openList
    priority_queue<State, vector<State>, compareStates> openList; // with smallest value first
    // Closedlist
    vector<State>closedList;
};

// Create moveFunction by swapping tiles

// Update evaluation function
