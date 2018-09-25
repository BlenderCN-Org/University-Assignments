format long e

clc

x1 = 5;
x2 = 5 + 10^(-10);

fun = @(x)exp(x-1) - 1;
actual = 1;

fprintf("fzero(function1, x) results::\n")
[rootest, fval] = fzero(fun, x1);
backerr = abs(fun(rootest));
forerr = abs(actual - rootest);
fprintf("(x, rootest, fval): %d, %d, %d\n", x1, rootest, fval)
fprintf("(Backward error, Forward error): %d, %d\n", backerr, forerr)

[rootest, fval] = fzero(fun, x2);
backerr = abs(fun(rootest));
forerr = abs(actual - rootest);
fprintf("(x, rootest, fval): %d, %d, %d\n", x2, rootest, fval)
fprintf("(Backward error, Forward error): %d, %d\n\n", backerr, forerr)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

fun = @(x)exp(4*x-4) - 2*exp(3*x-3) + 2*exp(x-1) - 1;

fprintf("fzero(function2, x) results::\n")
[rootest, fval] = fzero(fun, x1);
backerr = abs(fun(rootest));
forerr = abs(actual - rootest);
fprintf("(x, rootest, fval): %d, %d, %d\n", x1, rootest, fval)
fprintf("(Backward error, Forward error): %d, %d\n", backerr, forerr)

[rootest, fval] = fzero(fun, x2);
backerr = abs(fun(rootest));
forerr = abs(actual - rootest);
fprintf("(x, rootest, fval): %d, %d, %d\n", x2, rootest, fval)
fprintf("(Backward error, Forward error): %d, %d\n\n", backerr, forerr)

syms f(x)
f(x) = exp(x-1) - 1;

mult1 = 0;
while f(actual) == 0
    f = diff(f, x);
    mult1 = mult1 + 1;
end

f(x) = exp(4*x-4) - 2*exp(3*x-3) + 2*exp(x-1) - 1;

mult2 = 0;
while f(actual) == 0
    f = diff(f, x);
    mult2 = mult2 + 1;
end
fprintf("Multiplicity f1: %d\n", mult1)
fprintf("Multiplicity f2: %d\n", mult2)
fprintf("As the multiplicity increases, stability decreases.\n Thus, with multiplicity 1 for the first function, we\n have a pretty stable algorithm. However, when we \n introduce higher multiplicity in function 2, we start to \n lose some of our stability and it begins to slightly affect \n our approximations. The inital guess doesn't affect the first \n function very much, but you can see the increased variablity in \n the seconds function from the root difference.\n")
