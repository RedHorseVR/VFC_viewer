PRAGMA LIST(Off); -- PRAGMA LIST(On);.
WITH Ada.Text_IO; USE Ada.Text_IO;
PROCEDURE Factorial IS
  IF 
  
    BEGIN
    FOR I IN 2 .. 8 LOOP
      Put("Factorial of "); -- Put("Fatorial de ");.
      Put( Integer'IMAGE(I) );
      Put(" = "); -- Put(" e' ");.
      Put_Line( Integer'IMAGE( Factorial(I) ) );
    END LOOP;
    FOR I IN 2 .. 8 LOOP
      Put("Factorial of "); -- Put("Fatorial de ");.
      Put( Integer'IMAGE(I) );
      Put(" = "); -- Put(" e' ");.
      Put_Line( Integer'IMAGE( Factorial(I) ) );
    END LOOP;
    END Factorial;
  
    FUNCTION Factorial(N : Integer) RETURN Integer IS
    BEGIN
    IF N <= 1 THEN
     --  auto path.
      RETURN 1;
    ELSE
      RETURN N * Factorial(N - 1);
    END IF;
    EXCEPTION
    WHEN Numeric_Error =>
      Put(" !");
      return -1;
    
    END Factorial;
  

= FUNCTION Factorial(N : Integer) RETURN Integer IS
FUNCTION Factorial(N : Integer) RETURN Integer IS
  BEGIN
  IF N <= 1 THEN
   --  auto path.
    RETURN 1;
  ELSE
    RETURN N * Factorial(N - 1);
  END IF;
  EXCEPTION
  WHEN Numeric_Error =>
    Put(" !");
    return -1;
  
END Factorial;
--  FlowCode File: test.ins.
--  Export  File: TEST.ADA.
--  Export  Date: 04:01:21 PM - 03:Sep:1999.

