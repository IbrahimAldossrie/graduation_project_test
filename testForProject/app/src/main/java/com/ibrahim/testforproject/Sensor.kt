package com.ibrahim.testforproject

class Sensor{
    var name:String?=null
    var value:Double?=null
    var image:Int?=null

    constructor(name:String,value:Double=0.0, image:Int?=null){
        this.name=name
        this.value=value
        this.image=image
    }
}