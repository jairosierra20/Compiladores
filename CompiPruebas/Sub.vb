Module Module1 

   Function User(var1 as Integer,var2 as String, var3 as Integer)  as Integer
	Dim num1 as Integer
	Dim num2 as String
	Dim cadena as Integer

	num1  =  var1
	num2  = "hola"
	cadena = var3

	return num1
End Function

Sub Persona()
	Dim num1 as Integer
	Dim num2 as String
	Dim cadena as Integer

	num1  =  2
	num2  = "hola"
	cadena = 3
End Sub


Sub Persona2(var1 as Integer,var2 as String, var3 as Integer)
	Dim num1 as Integer
	Dim num2 as String
	Dim cadena as Integer

	
	num1  =  var1
	num2  = "hola"
	cadena = var3
End Sub
	
	

     Sub Main() 

        Dim variable As Integer = User(33, "Hola", 22) 
        Dim num1 as Integer
	Persona()
	Persona2(30,"Mesa",40)


      
    End Sub

End Module