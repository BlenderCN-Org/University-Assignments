format long e

% This variable is the current value of the summation after iteration 'k'.
cur_val = single(0);

% k is our iterater value, that is, it iterates through the summation.
k = 0;

% Running the infinite series until the sum no longer changes
[cur_val, k] = arctanseries(cur_val);

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
fprintf("a)\n")
fprintf("  1.a) Actual. value of pi from MatLab = %e\n", pi_actual)
fprintf("  1.b) Approx. value of pi using Maclaurin series: 4arctan(1) = %e\n", pi_approx)
fprintf("  2) Absolute Error: %e\n", abs_err)
fprintf("  3) Relative Error: %e\n", rel_err)
fprintf("  4) Number of 'k' terms needed to approx. in single percision: %i\n",k)

fprintf("b)\n")
fprintf("  Eventually the next value in the series becomes extremely small (since k is constantly increasing in the denominator, the next value to be added in the series will be small). This is the result of the phenomenon SWAMPING, since we are trying to add two numbers whose sizes are very different (one large and one extremely small). Therefore, the percision will eventually lose track of the very small values computed due to the rounding and computational limitations.\n")
