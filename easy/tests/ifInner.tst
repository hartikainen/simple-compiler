main begin
  int a;
  int ret;
  a = 6;
  if ((3 < a) && (4 < a)) then
  begin
    if (5 < a) then
    ret = 1;

    if (!(5 < a)) then
    ret = 0;

  end
  if (!((3 < a) && (4 < a))) then
  begin
    ret = 0 - 1;
  end
  print(ret);
  return ret;
end

