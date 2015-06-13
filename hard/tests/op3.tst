main begin
  boolean a;
  a =  ! false && 6 < 3 && 3 < 4;
  if (a) then
  begin
    print(1);
  end
  if (!(a)) then
  begin
    print(0);
  end
  return a;
end

