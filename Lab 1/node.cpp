#include "node.h";

Board::Board(){
    // Define the goal state
    for(auto index : goal.puzzleState){
        goal.puzzleState[index] = index;
    }

    // Define the start state, randomized
    State current;
    current.puzzleState = random_shuffle(goal.puzzleState.begin(), goal.puzzleState.end());

}

void Board::print(){
    for(auto index : goal.puzzleState){
          cout << current.puzzleState[index] << endl;
    }
}