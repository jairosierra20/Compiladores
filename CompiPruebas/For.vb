Module Module1 

	


     Sub Main() 
	
	Dim num1 as Integer
	Dim num2 as Integer
	Dim num3 as Integer
       	
	num1 = 1
	num2 = 0

	
	
	for num3 = 1 to 10
	
	num1 = 2
                      Console.WriteLine("Ingrese un numero: ")
                      num2 = Console.ReadLine()
                      num1 = num1*num2
                      Console.WriteLine(num1)
	
	next

      
    End Sub

End Module