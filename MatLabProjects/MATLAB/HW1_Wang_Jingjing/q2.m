%{ 
Author:Jingjing Wang
Date: Feb 2
Describtion:  a script that will read the data from this file into a matrix. When the script is executed,
it will do three things. First, it will print how many quarters were
represented in the file. Next, it will plot the costs using black circles and sales using red stars (*)
*********************
File costssales.dat is created this script
*********************
%}
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
load costssales.dat;
columns = size(costssales, 1);

fprintf('There were %d quarters in the file \n', columns);

yCost = costssales(:,1);
ySales = costssales(:,2);
plot(yCost, 'ko');
hold on;
plot(ySales, 'r*');
legend('Costs', 'Sales');
xlabel('Quarter');
title('Company Costs and Sales');

newcostssales = costssales';
tempCosts = newcostssales(1,:);
newcostssales(1,:) = newcostssales(2,:);
newcostssales(2,:) = tempCosts;

newfile = fopen('newfile.dat','w');

for i=1:size(newcostssales, 1)
    fprintf(newfile,' %d', newcostssales(i,:));
    fprintf(newfile, '\n');
end






