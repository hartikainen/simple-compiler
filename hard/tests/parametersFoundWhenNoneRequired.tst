int getOne() begin
  return 1;
end

main begin
  int s;
  int[] array;
  array = new int [3];
  array[0] = 0;
  array[1] = 1;
  array[2] = 2;
  s = getOne(array);
  return s;
end

