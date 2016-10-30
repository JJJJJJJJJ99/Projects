%{ 
Author:Jingjing Wang
Date: Feb 2
Describtion: This script will check how many rejects of pipe are there.
***************
File pipe.dat need to be created before executing this script.
***************
%}

%Sample file, you can comment it out for testing
file = fopen('pipe.dat','w');
M = [2.14 10.30;
    2.32 10.36;
    2.20 10.35];

for i=1:size(M, 1)
    fprintf(file, ' %.2f', M(i,:));
    fprintf(file, '\n');
end
%Sample file, you can comment it out for testing


load pipe.dat;

count = 0;
for i = 1:size(pipe, 1)
    if pipe(i, 1) < 2.1 || pipe(i, 1)> 2.3 || pipe (i, 2) < 10.3 || pipe(i, 2) > 10.4
        count = count + 1;
    end
end

X = sprintf('There were %d rejects', count);
disp(X);
