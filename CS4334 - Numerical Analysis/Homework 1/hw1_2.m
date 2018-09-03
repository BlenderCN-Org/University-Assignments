format long e

% Boolean variable to determine when the series will no longer
% produce a new terms due to swamping.
hasSolved = 0;

% k is our iterater value, that is, it iterates through the summation.
k = 0;

% x is our input the the pseduo 'f(x) = arctan(x)' function.
x = single(1);

% This variable stores the previous value added to the series. We use this
% to determine if the summation has succumbed to swamping.
prev_val = single(0);

% This variable is the current value of the summation after iteration 'k'.
cur_val = single(0);

% Pseudocode:
% while (the series has not begun to swamp)
%   store the previous value of the series
%   calculate the new value of the series
%   if (the series has swamped)
%       stop looping
%   end
%   go to the next term in the series
%  end
while ~hasSolved
    prev_val = cur_val;
    
    % You can remove the power(x,2*k+1) since this always evaluates to 1
    % for increased performance, however I have left this in here for \
    % semantic reasons.
    cur_val = cur_val + power(-1,k) * power(x,2*k+1) / (2*k+1);
   
    if cur_val == prev_val
        break
    end
    
    k=k+1;
end

% Equivelant to 4*arctan(1), the approx. value of pi.
pi_approx = 4*cur_val;

% The actial value of pi.
pi_actual = pi;

% Calculating the absolute error.
abs_err = abs(pi_actual - pi_approx);

% Calculating the relative error.
rel_err = abs((pi_actual - pi_approx) / pi_actual);

% Formatting.
% Clears the console, then prints out the required informatWion.
clc
fprintf("| Matthew McMillian | Homework 1 | Problem 2 |\n")

fprintf("a)\n")
fprintf("  1.a) Actual. value of pi from MatLab = %e\n", pi_actual)
fprintf("  1.b) Approx. value of pi using Maclaurin series: 4arctan(1) = %e\n", pi_approx)
fprintf("  2) Absolute Error: %e\n", abs_err)
fprintf("  3) Relative Error: %e\n", rel_err)
fprintf("  4) Number of 'k' terms needed to approx. in single percision: %i\n",k)

fprintf("b)\n")
fprintf("  Eventually the next value in the series becomes extremely small (since k is constantly increasing in the denominator, the next value to be added in the series will be small). This is the result of the phenomenon SWAMPING, since we are trying to add two numbers whose sizes are very different (one large and one extremely small). Therefore, the percision will eventually lose track of the very small values computed due to the rounding and computational limitations.\n")
