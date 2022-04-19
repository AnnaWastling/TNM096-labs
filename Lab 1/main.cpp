#include "node.h"

int main()
{
    // Define the start state, randomized
    int easy[] = { 4, 1, 3, 7, 2, 6, 0, 5, 8 };
    int medium[] = { 7, 2, 4, 5, 0, 6, 8, 3, 1 };
    int difficult1[] = { 6, 4, 7, 8, 5, 0, 3, 2, 1 };
    int difficult2[] = { 8, 6, 7, 2, 5, 4, 3, 0, 1 };
   
    State start = State(difficult2, 0);
    int zeroPos = 7;

    Board board(start, zeroPos);
    cout << "making board..." << endl;
    auto startTime = high_resolution_clock::now();
    board.solveBoard(board);
    auto stopTime = high_resolution_clock::now();

    auto duration = duration_cast<microseconds>(stopTime - startTime);

    // To get the value of duration use the count()
    // member function on the duration object
    cout << "duration time: " << duration.count() << " micro seconds" << endl;

    return 0;
}