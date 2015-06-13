int successor(int x) begin
  return x + 1;
end

main begin
  int s;
  int[] array;
  array = new int [3];
  array[0] = 0;
  array[1] = 1;
  array[2] = 2;
  s = successor(array);
  return s;
end

