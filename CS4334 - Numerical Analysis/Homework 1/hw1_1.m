format long e

% The degree of the greatest polynomial in the given function p(x).
degree = 25;

% Defining the coefficients of the function p(x) in accordance
% with horner-form, with the constant defined as the first
% value. Each other coefficient is placed by adding is exponent
% value to the previous index. 
% i.e.   4x^5, the smallest exponent in the function p(x), has an exponent 
% value of '5', so we move '5' indicies from the constant term, filling
% in the spaces in between with 0's in accordance with horner-form.
coef = [-1,0,0,0,0,4,0,0,0,0,-1,0,0,0,0,7,0,0,0,0,0,0,0,0,0,2];

% The value at which the function p(x) is evaluated at.
x = -2;

% C code that prints / formats the answer for readability. 
sprintf('Evaluation of p(%i): %f\n', x, nest(degree,coef,x))
