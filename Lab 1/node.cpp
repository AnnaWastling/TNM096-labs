#include "node.h"
State::State(int puzzle[], int cost, State* parent) : cost{ cost }, parent(parent) {
	for (int i = 0; i < 9; i++) {
		puzzleState[i] = puzzle[i];
	}
};


Board::Board(State curr, int zero) : zeroPos{ zero }
{
	// Define the goal state
	for (int i = 0; i < size(goal.puzzleState)-1; i++)
	{
		goal.puzzleState[i] = i+1;
	};
	goal.puzzleState[8] = 0;

	current = curr;
};

void Board::print()
{
	cout << "The current state is" << endl;
	for (auto index : current.puzzleState)
	{
		cout << index << endl;
	}
};

void Board::printPath() {
	cout << "cost: " << current.cost << endl;
	cout << current.puzzleState[0] << " " << current.puzzleState[1] << " " << current.puzzleState[2] << " " << endl;
	cout << current.puzzleState[3] << " " << current.puzzleState[4] << " " << current.puzzleState[5] << " " << endl;
	cout << current.puzzleState[6] << " " << current.puzzleState[7] << " " << current.puzzleState[8] << " " << endl<<endl<<endl;
	
}

std::vector<int> Board::allowedMoves()
{
	std::vector<int> moves;

	switch (zeroPos)
	{
	case 0:
		moves = { 1, 3 };
		break;
	case 1:
		moves = { 0, 2, 4 };
		break;
	case 2:
		moves = { 1, 5 };
		break;
	case 3:
		moves = { 4, 0, 6 };
		break;
	case 4:
		moves = { 3, 1, 7, 5 };
		break;
	case 5:
		moves = { 4, 2, 8 };
		break;
	case 6:
		moves = { 7, 3 };
		break;
	case 7:
		moves = { 6, 8, 4 };
		break;
	case 8:
		moves = { 7, 5 };
		break;

	default:
		break;
	}
	return moves;
};

int Board::hamming(State& s)
{
	int heuristic = 0;

	for (int i = 0; i < size(s.puzzleState); i++)
	{
		if (s.puzzleState[i] != goal.puzzleState[i])
		{
			++heuristic;
		}
	}

	return heuristic;
};

int Board::manhattan(State& s)
{
	int heuristic = 0;

	for (int i = 0; i < size(s.puzzleState); i++)
	{
		if (s.puzzleState[i] != goal.puzzleState[i])
		{                        // if tile is in wrong place
			int atCol = (i % 3); // ex. i = 4 gives 4 % 3 = 1, where the colums are [0,1,2]
			int atRow = (i / 3); // ex. i = 4 gives 4 / 3 = 1.33.. = int 1, where the rows are [0,1,2]

			int corrCol = goal.puzzleState[goal.puzzleState[i]] % 3;
			int corrRow = goal.puzzleState[goal.puzzleState[i]] / 3;

			heuristic += abs(atRow - corrRow) + abs(atCol - corrCol);
		}
	};

	return heuristic;
}

bool Board::isGoal() {
	for (int i = 0; i < size(current.puzzleState); i++) {
		if (current.puzzleState[i] != goal.puzzleState[i]) {
			return false;
		}
	}
	return true;
}

void Board::moveZero(int move, State& s) {
	swap(s.puzzleState[zeroPos], s.puzzleState[move]);
	//zeroPos = move;
}

void Board::findZero() {
	for (int i = 0; i < size(current.puzzleState); i++) {
		if (current.puzzleState[i] == 0) zeroPos = i;
	}
}

void Board::solveBoard(Board& b) {

	//add root state to the openlist
	openList.push(current);

	vector<State>::iterator it;
	vector<int>moves;

	while (!openList.empty()) {
		//get smallest heuristic value state
		current = openList.top();
		//remove state from openlist
		openList.pop();
		//find the position of the zero
		findZero();

		//add to closed list
		//current = expanded node
		if (closedList.find(current.key) != closedList.end()) {
			continue;
		}

		closedList[current.key] = current;


		// goal don´t continue
		if (isGoal()) {
			cout << " Goal is found! " << endl;
			cout << " The cost is: " << current.cost << endl;

			State* path = &current;
			while (path) { //while path != nullptr
				
				cout << "|" << path->puzzleState[0] << ", " << path->puzzleState[1] << ", " << path->puzzleState[2] << "|\n";
				cout << "|" << path->puzzleState[3] << ", " << path->puzzleState[4] << ", " << path->puzzleState[5] << "|\n";
				cout << "|" << path->puzzleState[6] << ", " << path->puzzleState[7] << ", " << path->puzzleState[8] << "|\n\n";
				path = path->parent;
			}

			break;
		}

		moves = allowedMoves();

		for (int i = 0; i < moves.size(); i++) {
			//create new boards from the expanded node for every allowed move
			State newState = State(current.puzzleState, current.cost + 1, &closedList[current.key]);
			moveZero(moves[i], newState);
			newState.setKey();
			//calculate heuristic
			newState.heuristic = manhattan(newState);
			newState.evaluation = newState.cost + newState.heuristic;
				
			openList.push(newState);
		}
	}

}

bool State::operator==(const State& s)const
{
	for (int i = 0; i < 9; i++)
	{
		if (puzzleState[i] != s.puzzleState[i])
		{
			return false;
		}
	}
	return true;
}