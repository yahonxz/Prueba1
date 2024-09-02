package classes

//class Variables {

fun main(){
    //Numeric Variables
    val age:Int = 20
    val longNumber:Long = 284042455451111111;
    val temperature:Float = 27.123f;
    val Weight:Double = 68.4;

    //Variables String
    val genero:Char = 'F'
    val name:String = "Yahaira Rosale"

    //Boolean
    val isGrater:Boolean = true

    //Array
    val names = arrayOf("Paola", "Samantha", "Omar", "Jorge")

    println("Welcome $name, to your first Kotlin project")

    println(add())
    println(product(19,20))

    printArray(names)
    println(names.joinToString())

    val numbers = arrayOf(1,2,3,4,5,6,7,8,9)
    calculateIfPar(numbers)


    println(getDay(3))

    val person = Persona("Yahaira",21)
    println(person.name)
    println(person.age)
    println(person.display())
}

//Estructura de una función
fun add():Int{
    val x = 5
    val y = 10

    return x+y
}

//Funcion con parametros y retorno
fun product(x:Int, y:Int):Int{
    return x+y
}

//ForEach
fun printArray (names:Array<String>){
    for(name in names){
        print("hello $name")
    }
}

//If con foreach
fun calculateIfPar (numbers:Array<Int>){
    for(number in numbers){
        if (number%2 == 0){
            println("El numero $number es par")
        }else{
            println("El numero $number es impar")
        }
    }
}

//Switch (When)
fun getDay(day:Int):String{
    var name = ""

    when(day){
        1 -> name = "Monday"
        2 -> name = "Tuesday"
        3 -> name = "Wednesday"
        4 -> name = "Thursday"
        5 -> name = "Friday"
        6 -> name = "Saturday"
        7 -> name = "Sunday"
        else -> name = "Incorrect value"
    }
    return name
}

//Clase Persona
class Persona(val name:String, val age:Int){
    fun display(){
        println("Mi nombre es: $name")
        println("Mi edad es: $age")
    }
}
//}