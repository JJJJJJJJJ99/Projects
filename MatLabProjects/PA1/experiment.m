file = fopen('floatnums.dat','w');

M = [90.5792 27.8498 97.0593; 
    12.6987 54.6882 95.7167; 
    91.3376 95.7507 48.5376;
    63.2359 96.4889 80.0280;
    9.7540 15.7613 14.1886];
for i=1:size(M, 1)
    fprintf(file, '%.4f ', M(i,:));
    fprintf(file, '\n');
end


