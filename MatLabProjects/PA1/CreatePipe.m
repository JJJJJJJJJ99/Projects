file = fopen('pipe.dat','w');
M = [2.14 10.30;
    2.32 10.36;
    2.20 10.35];

for i=1:size(M, 1)
    fprintf(file, ' %.2f', M(i,:));
    fprintf(file, '\n');
end