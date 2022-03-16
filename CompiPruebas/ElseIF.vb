Module Module1 

Dim num1 as Integer
Dim num2 as Integer

	

     Sub Main() 
	
	Dim num3 as Integer
Dim num4 as Integer
       	
	num1 = 1
	num2 = 0
	num3 = 2
	num4 = 4 
	
	 if  num1 > num2 Or num1 = num2 then
		if  num1 > num2 And num4 > num3  then
		num1 = num1 + 1
                Console.WriteLine(num1)
		else
		num1 = num1 + 2
                Console.WriteLine(num1)
                   	       end if
	end if


      
    End Sub

End Module