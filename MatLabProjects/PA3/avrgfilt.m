function f = avrgfilt(image, mask)

I = im2double(image);
[m, n] = size(I);

masklength = mask(1);
maskwidth = mask(2);
x = m/mask(1);
y = n/mask(2);

for i = 1:x
   for j = 1:y
        square = I(masklength*(i-1)+1: masklength*i, maskwidth*(j-1)+1:maskwidth*j);
        tier = reshape(square,[1, masklength*maskwidth]);
%         display(tier);
        avrg = mean(tier);
%         display(avrg);
        I(masklength*(i-1)+1: masklength*i, maskwidth*(j-1)+1:maskwidth*j) = avrg;
   end
end
f = I;
end

