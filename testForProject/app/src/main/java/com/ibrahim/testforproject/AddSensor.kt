package com.ibrahim.testforproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_add_sensor.*
import kotlinx.android.synthetic.main.sensor_ticket.view.*
import com.ibrahim.testforproject.MainActivity
import com.ibrahim.testforproject.R
class AddSensor : AppCompatActivity() {
    var listOfSensors = ArrayList<Sensor>()
    var adapter:AddSensorsAdapter?=AddSensorsAdapter(this,listOfSensors)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sensor)

        listOfSensors.add(
            Sensor("Temperature",image = R.drawable.temperature_icon))
        listOfSensors.add(
            Sensor("Humdidty",image = R.drawable.humidity_icon))

        adapter = AddSensorsAdapter(this,listOfSensors)
        tvListSensor.adapter =  adapter


    }

    fun delete(index:Int){
        listOfSensors.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    fun  add(index:Int){

            var myadd=MainActivity.listOfSensores.add(listOfSensors[index])
            var updateMyMain=MainActivity.adapter!!.notifyDataSetChanged()


    }

    inner class  AddSensorsAdapter: BaseAdapter {
        var  listOfSensors= ArrayList<Sensor>()
        var context: Context?=null
        constructor(context: Context, listOfSensors: ArrayList<Sensor>):super(){
            this.listOfSensors=listOfSensors
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val Sensor =  listOfSensors[p0]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView = inflator.inflate(R.layout.sensor_ticket, null)
            myView.tvName.text = Sensor.name!!
            myView.ivSensorImage.setImageResource(Sensor.image!!)
            myView.ivSensorImage.setOnClickListener {
                add(p0)

                val intent = Intent(context,SensorDetails::class.java)
                intent.putExtra("name",Sensor.name!!)
                intent.putExtra("value",Sensor.value!!)
                intent.putExtra("image",Sensor.image!!)
                //context!!.startActivity(intent)


            }
            return myView

        }

        override fun getItem(p0: Int): Any {
            return listOfSensors[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return  listOfSensors.size
        }

    }

    }







