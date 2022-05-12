%Shakey the robot

%actions
%go(x,y)
act(go(X,Y),
[location(X), location(Y), door(X,Y), shakey(X), on(floor)],
[],
[shakey(Y)]
).

%push(b,x,y)
act(push(B,X,Y),
[box(B), boxpos(B,X), door(X,Y), shakey(X), location(X), location(Y), handEmpty, switch(S), switchPos(S,X), switched(S,on), on(floor) ],
[boxpos(B,X),shakey(X), handEmpty],
[shakey(Y), boxpos(B,Y)]
).
%predicate box(...)
%lightOn=true for shakey to find a box
%shakey must climb onto box to switch lightswitch
%climbUp(b)
act(climbUp(B),
[location(X), on(floor), box(B), boxPos(B, X)],
[on(floor)],
[on(B)]
).
%climbDown(B)
act(climbDown(B),
[box(B), on(B)],
[on(B)],
[on(floor)]
).
%turnOn(s)
act(turnOn(S),
[location(X), box(B), switch(S), switchPos(S,X), switched(S,off), on(B)],
[switched(S,off)],
[switched(S,on)]
).
%turnOff(s)
act(turnOff(S),
[location(X), box(B), switch(S), switchPos(S,X), switched(S,on), on(B)],
[switched(S,on)],
[switched(S,off)]
).
%States
goal_state(
[shakey(room1)]
).

initial_state([
shakey(room3),
floor,
on(floor),

box(a),
box(b),
box(c),
box(d),
boxPos(a, room1),
boxPos(b, room1),
boxPos(c, room1),
boxPos(d, room1),
handEmpty,

switch(one),
switch(two),
switch(three),
switch(four),
switched(one, on),
switched(two, off),
switched(three, off),
switched(four, on),
switchPos(one, room1),
switchPos(two, room2),
switchPos(three, room3),
switchPos(four, room4),

location(room1),
location(room2),
location(room3),
location(room4),
%location(corridor),
door(room1,corridor),
door(room2,corridor),
door(room3,corridor),
door(room4,corridor),
door(corridor, room1),
door(corridor, room2),
door(corridor, room3),
door(corridor, room4)

]).
