function [f] = fpnewt(I)

f = 30000 * (1 + (I/12))^59 - 60000;

end

