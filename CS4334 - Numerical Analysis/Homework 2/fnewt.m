function [f] = fnewt(I)

f = 6000 * (1 + (I/12))^60 - 60000*I - 6000;

end

