#include <queue>
#include <random>
#include <algorithm> // std::shuffle
#include <iostream>
#include  <unordered_set>
#include <chrono>
#include <map>
#include <string>
using namespace std;
using namespace std::chrono;

class State
{
public:
    State() = default;
    State(int puzzle[], int cost, State* parent);
    //bool operator==(const State& s)const;

    State* parent;              // for printing the path
    int puzzleState[9] = { 0 }; // position of all 8 tiles
    int cost = 0;             // g, real cost from start to current position
    int heuristic = 0;        // h, expected cost from current to the goal state
    int evaluation = 0;       // f, cost + heuristic
    string key = "k";         // For effective search in closedlist

    //Make unique keys for all states by using puzzlestate
    void setKey() {
        for (int i = 0; i < 9; ++i) {

            key += to_string(puzzleState[i]);
        }
    }
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
    std::vector<int> allowedMoves();
    int manhattan(State& s);
    int hamming(State& s);
    bool isGoal();
    void solveBoard(Board& b);
    void moveZero(int move, State& s);
    void findZero();


private:
    State goal;
    State current;
    int zeroPos;

    // Create openList
    priority_queue<State, vector<State>, compareStates> openList; // with smallest value first

    // Closedlist
    //vector<State>closedList; // Vector is too slow!

    map<string, State>closedList; //hashtable
    
};
