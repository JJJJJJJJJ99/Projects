A = imread('coca.jpeg');
B = imread('coca2.jpg');

% Get size of existing image A. 
[rowsA, colsA, numberOfColorChannelsA] = size(A); 
% Get size of existing image B. 
[rowsB, colsB, numberOfColorChannelsB] = size(B); 
% See if lateral sizes match. 
if rowsB ~= rowsA || colsA ~= colsB 
% Size of B does not match A, so resize B to match A's size. 
B = imresize(B, [rowsA colsA]); 
end

lala = siftDescriptor(A, B);