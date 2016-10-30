file = fopen('costssales.dat','w');
M = [1100 800; 
    1233 650; 
    1111 1001; 
    1222 1300; 
    999 1221];
for i=1:size(M, 1)
    fprintf(file, ' %d', M(i,:));
    fprintf(file, '\n');
end

