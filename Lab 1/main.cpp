#include "node.h"

int main()
{
    // Define the start state, randomized
    State start;
    start.puzzleState[0] = 3;
    start.puzzleState[1] = 6;
    start.puzzleState[2] = 0;
    start.puzzleState[3] = 1;
    start.puzzleState[4] = 4;
    start.puzzleState[5] = 2;
    start.puzzleState[6] = 8;
    start.puzzleState[7] = 5;
    start.puzzleState[8] = 7;

    int zeroPos = 2;

    Board board(start, zeroPos);

    board.solveBoard(board);

    board.print();
    // board.print(board.getGoal());
    //priority_queue<int, vector<int>, greater<int>> openList;

    //const auto data = {8,5,2,1,4,3 };
    //for (int n : data) {
    //    openList.push(n);
    //    cout<<(openList.top());

    //}




    return 0;
}