%Shakey the robot

%actions
%go(x,y)
act(go(X,Y),
[door(X,Y), shakey(Shakey), in(Shakey,X), on(floor)],    %consider remove shakey as var
[in(Shakey,X)],
[in(Shakey,Y)]
).
act(pushInRoom(B,X,Y),
[box(B), boxPos(B,X), same(Y,X), shakey(Shakey), in(Shakey,X), on(floor), switch(S), switchPos(S,X), switched(S,on)],%switch(S), switchPos(S,X), switched(S,on),  sameroom(X,Y)
[in(Shakey, X), boxPos(B,X)],
[in(Shakey, Y), boxPos(B,Y)]
).
%push(b,x,y)
act(push(B,X,Y),
[box(B), boxPos(B,X), door(X,Y), shakey(Shakey), in(Shakey,X), on(floor) ],%switch(S), switchPos(S,X), switched(S,on),
[boxPos(B,X),in(Shakey,X)],
[in(Shakey,Y), boxPos(B,Y)]
).
%predicate box(...)
%lightOn=true for shakey to find a box
%shakey must climb onto box to switch lightswitch
%climbUp(b)
act(climbUp(B),
[shakey(Shakey), in(Shakey,LP), same(LP,X), on(floor), box(B), boxPos(B, LP)],    %location(X), location(LP),
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
[location(X), box(B), switch(S), switchPos(S,X), switched(S,off), on(B), shakey(Shakey), in(Shakey, X)],
[switched(S,off)],
[switched(S,on)]
).
%turnOff(s)
act(turnOff(S),
[location(X), box(B), switch(S), switchPos(S,X), switched(S,on), on(B), shakey(Shakey), in(Shakey, X)],
[switched(S,on)],
[switched(S,off)]
).

%States
goal_state(
[switched(one,off)]%in(s,room1)  % %boxPos(b,room2)  %switched(one,off)
).                  %make switchpositions  constants
                     %box must be at s of that room
initial_state([
shakey(s),
floor,
on(floor),
in(s, room3),
box(a),
box(b),
box(c),
box(d),

boxPos(a, room1),
boxPos(b, room1),
boxPos(c, room1),
boxPos(d, room1),

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

location(s1),
location(s2),
location(s3),
location(s4),

location(room1),
location(room2),
location(room3),
location(room4),

same(room1,s1),
same(s1,room1),
same(room2,s2),
same(s2,room2),
same(room3,s3),
same(s3,room3),
same(room4,s4),
same(s4,room4),

door(room1,corridor),
door(room2,corridor),
door(room3,corridor),
door(room4,corridor),
door(corridor, room1),
door(corridor, room2),
door(corridor, room3),
door(corridor, room4),

door(one,corridor),
door(two,corridor),
door(three,corridor),
door(four,corridor)
]).
