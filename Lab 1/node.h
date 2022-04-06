#include <queue>
#include <random>
#include <algorithm> // std::shuffle
using namespace std;

struct State
{
    int puzzleState[9]; // position of all 8 tiles
    int cost;           // real cost from start to current position
    int heuristic;      // expected cost from current to the goal state
    int evaluation;     // cost + heuristic
}

class Board
{
public:
    Board();

    void print();

private:
    State goal;
    priority_queue<int> openList;
    // closedList? vector, map?
}
