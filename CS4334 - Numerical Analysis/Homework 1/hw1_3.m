format long e

% Defining the variables 'a,b,c' as a list for simplicity.
function1 = [1, power(-10,5), 1];
function2 = [1, power(10,5), 1];

% Running the coefficients of both functions into quadroots and storing
% their results.
[function1_x1, function1_x2] = quadroots(function1(1), function1(2), function1(3));
[function2_x1, function2_x2] = quadroots(function2(1), function2(2), function2(3));

% Clearing the console and outputting the results.
clc
fprintf("Function Roots (Computational):\n")
fprintf("function1(x1): %0.15e\nfunction1(x2): %0.15e\nfunction2(x1): %0.15e\nfunction2(x2): %0.15e\n", function1_x1, function1_x2, function2_x1, function2_x2)

% Calculating the real soulutions for function 1 and function 2 using the
% roots obtained in the previous part.
function1_solution1 = abs(power(function1_x1,2) + power(-10,5)*(function1_x1) + 1);
function1_solution2 = abs(power(function1_x2,2) + power(-10,5)*(function1_x2) + 1);

function2_solution1 = abs(power(function2_x1,2) + power(10,5)*(function2_x1) + 1);
function2_solution2 = abs(power(function2_x2,2) + power(10,5)*(function2_x2) + 1);

% Printing the results.
fprintf("\nFunction Actuals (With Computational Roots):\n")
fprintf("function1(x1): %0.15e\nfunction1(x2): %0.15e\n", function1_solution1, function1_solution2);
fprintf("function2(x1): %0.15e\nfunction2(x2): %0.15e\n", function2_solution1, function2_solution2);

% Calculating the roots of the functions 'bad' computational way.
[function1_ncomp_x1, function1_ncomp_x2] = noncompquadroots(function1(1), function1(2), function1(3));
[function2_ncomp_x1, function2_ncomp_x2] = noncompquadroots(function2(1), function2(2), function2(3));

% Printing the restults.
fprintf("\nFunction Roots (Non-Computational):\n")
fprintf("function1(x1): %0.15e\nfunction1(x2): %0.15e\nfunction2(x1): %0.15e\nfunction2(x2): %0.15e\n", function1_ncomp_x1, function1_ncomp_x2, function2_ncomp_x1, function2_ncomp_x2)

% Calculating the solutions of the 'non' computational roots and
% retrieving their errors.
function1_ncomp_solution1 = abs(power(function1_ncomp_x1,2) + power(-10,5)*(function1_ncomp_x1) + 1);
function1_ncomp_solution2 = abs(power(function1_ncomp_x2,2) + power(-10,5)*(function1_ncomp_x2) + 1);

function2_ncomp_solution1 = abs(power(function2_ncomp_x1,2) + power(10,5)*(function2_ncomp_x1) + 1);
function2_ncomp_solution2 = abs(power(function2_ncomp_x2,2) + power(10,5)*(function2_ncomp_x2) + 1);

% Printing the results.
fprintf("\nFunction Actuals (Backwards Errors With Non-Computational Roots):\n")
fprintf("function1(x1): %0.15e\nfunction1(x2): %0.15e\n", function1_ncomp_solution1, function1_ncomp_solution2);
fprintf("function2(x1): %0.15e\nfunction2(x2): %0.15e\n", function2_ncomp_solution1, function2_ncomp_solution2);

