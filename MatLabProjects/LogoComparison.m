clear all;

image=input('Enter the image name :      ','s');
a=imread(image);
imshow(a);
title('Selected image');

[row,col,plane]=size(a);
if plane==3
a=rgb2gray(a);
end
a=im2double(a);
kpl1= [];
kp1 = [];
[kpl1, kp1] = siftDetection(a);


image=input('Enter the image to compare :      ','s');
i=imread(image);
imshow(i);
title('Image to compare');
[row,col,plane]=size(i);
if plane==3
i=rgb2gray(i);
end
i=im2double(i);
kpl2= [];
kp2= [];
[kpl2, kp2] = siftDetection(i);

%% Two images key point comparision
count=0;
for i=1:2:length(kpl1)
    for j=1:2:length(kpl2)
        if (kpl1(i)==kpl2(j))  &&   (kpl1(i+1)==kpl2(j+1))
            count=count+1;
            break;
        end
    end
end
mp = 0;
mp=(count/length(kp1))*100;
fprintf('Matching percentage between 2 images by key point location is :%f \n\n',mp);

%%%%
%%%%

