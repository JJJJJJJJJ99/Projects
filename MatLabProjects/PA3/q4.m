in = 'coins0.png';
I = imread(in);

bw = imbinarize(I);
figure, imshow(bw);
title('black & white');

h = ones(5,5) / 25;
BWfilted = imfilter(bw,h);
figure, imshow(BWfilted);
title('filted image');

BWfill = imfill(BWfilted,'holes');
figure, imshow(BWfill);
title('filled image');

state=regionprops(BWfill,'Area','Centroid');

% Count number of coins
countDimes = 0;
countPennies = 0;
countNickels = 0;
countQuaters = 0;


for i = 1:size(state,1) %For 1 to Total number of coins
     area = state(i).Area;
     if state(i).Area>15000
        countQuaters = countQuaters + 1;
     elseif state(i).Area < 15000 && state(i).Area > 10000
        countNickels = countNickels + 1; 
     elseif state(i).Area < 10000 && state(i).Area > 9000
        countPennies = countPennies +1;
     elseif state(i).Area < 9000 && state(i).Area > 8000
        countDimes = countDimes + 1;
     end
end

X = sprintf('There are %d pennies. \n There are %d nickels. \n There are %d dimes. \n There are %d quaters.', countPennies, countNickels, countDimes, countQuaters);
disp(X);



