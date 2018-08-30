format long e

hasSolved = 0;
k = single(0);
x = single(1);

prev_val = single(0);
cur_val = single(0);
while(~hasSolved)
    prev_val = cur_val;
    cur_val = cur_val + power(-1,k) * power(x,2*k+1) / (2*k+1);
   
    if(cur_val == prev_val)
        break
    end
    
    k=k+1;
end

pi_approx = 4*cur_val;
pi_actual = pi;
 
abs_err = abs(pi_actual - pi_approx);
rel_err = abs((pi_actual - pi_approx) / pi_actual);

clc
fprintf("| Matthew McMillian | Homework 1 | Problem 2 |\n")

fprintf("a)\n")
fprintf("  1) Approx. value of pi using Maclaurin series: 4arctan(1) = %e\n", pi_approx)
fprintf("  2) Absolute Error: %e\n", abs_err)
fprintf("  3) Relative Error: %e\n", rel_err)
fprintf("  4) Number of 'k' terms needed to approx. in single percision: %i\n",k)

fprintf("b)\n")
fprintf("  Eventually the next value in the series becomes extremely small (since k is constantly increasing in the denominator, the next value to be added in the series will be small). This is the result of the phenomenon SWAMPING, since we are trying to add two numbers whose sizes are very different (one large and one extremely small). Therefore, the percision will eventually lose track of the very small values computed due to the rounding and computational limitations.\n")
