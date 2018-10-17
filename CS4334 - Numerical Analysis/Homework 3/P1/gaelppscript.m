format long e

% Clears console for clean testing
clc

% Given A Matrix
A = [3.03 -12.1 14;
    -3.03 12.1 -7;
    6.11 -14.2 21];

% Vector of values we want to solve for
b = [-119; 120; -139;];

% Calling Gaussian Elimination with Partial Pivoting
[A, P] = gaelpp(A);

A
P

% This checks to make sure that our PA=LU worked
% Forms L Matrix
L = [1 0 0
    A(2,1) 1 0 
    A(3,1) A(3,2) 1];
% Forms U Matrix
U = [A(1,1) A(1,2) A(1,3)
    0   A(2,2)  A(2,3) 
    0   0   A(3,3)];

% Forward Sub and Backward Sub Output
y = forsub(L,b)
s = backsub(U, y)

