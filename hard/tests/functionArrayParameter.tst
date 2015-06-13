boolean printArray(int[] array) begin
  int count;
  int[] numbers;
  int arrayLength;
  boolean successful;
  count = 0;
  numbers = array;
  arrayLength = numbers.length;
  if (arrayLength < 1) then
  begin
    successful = false;
  end
  if (!(arrayLength < 1)) then
  begin
    do
    begin
      print(numbers[count]);
      count = count + 1;
    end
    while(count < arrayLength);

    successful = true;
  end
  return successful;
end

main begin
  int[] array;
  boolean successfulPrint;
  array = new int [8];
  array[0] = 0;
  array[1] = 1;
  array[2] = 2;
  array[3] = 3;
  array[4] = 4;
  array[5] = 5;
  array[6] = 6;
  array[7] = 7;
  successfulPrint = printArray(array);
  return successfulPrint;
end

