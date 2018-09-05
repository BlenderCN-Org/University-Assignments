function [sum, k] = arctanseries(cur_val)

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

sum = cur_val;

end



