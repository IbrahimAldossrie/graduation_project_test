package com.ibrahim.testforproject

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import  kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sensor_ticket.view.*

class MainActivity : AppCompatActivity() {


    companion object {
        var adapter:SensorAdapter?=null
   var listOfSensores =ArrayList<Sensor>()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load foods
       // listOfSensores.add(Sensor("Temperature",23.0,R.drawable.temperature_icon))
     //  listOfSensores.add(Sensor("Humidity",23.0,R.drawable.humidity_icon))
        adapter= SensorAdapter(this,listOfSensores)

        gvListSensors.adapter =adapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val sv: SearchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

        val sm= getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        adapter= SensorAdapter(this,listOfSensores)

        gvListSensors.adapter =adapter
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()

    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show()

    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            when(item.itemId){
                R.id.addsensor->{
                    //Got to add paage
                    var intent= Intent(this,AddSensor::class.java)
                    startActivity(intent)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }


fun Add_Sensor_to_main(sensor:Sensor){
    listOfSensores.add(sensor)
}
    class  SensorAdapter:BaseAdapter {
        var listOfSensores= ArrayList<Sensor>()
        var context:Context?=null
        constructor(context:Context,listOfSensor:ArrayList<Sensor>):super(){
            this.context=context
            this.listOfSensores=listOfSensor
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val sensor = this.listOfSensores[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var sensorView= inflator.inflate(R.layout.sensor_ticket,null)
            if(p0==listOfSensores.size){
                sensorView.ivSensorImage.setImageResource(R.drawable.add_button)
                //sensorView.setOnClickListener {  }
                sensorView.ivSensorImage.setOnClickListener {

                    val intent = Intent(context,SensorDetails::class.java)
                    intent.putExtra("name",sensor.name!!)
                    intent.putExtra("value",sensor.value!!)
                    intent.putExtra("image",R.drawable.add_button)
                    context!!.startActivity(intent)
                }
            }else{
            sensorView.ivSensorImage.setImageResource(sensor.image!!)
            sensorView.ivSensorImage.setOnClickListener {

                val intent = Intent(context,SensorDetails::class.java)
                intent.putExtra("name",sensor.name!!)
                intent.putExtra("value",sensor.value!!)
                intent.putExtra("image",sensor.image!!)
                context!!.startActivity(intent)
            }
            sensorView.tvName.text =  sensor.name!!}
            return  sensorView

        }

        override fun getItem(p0: Int): Any {
            return listOfSensores[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listOfSensores.size
        }

    }
}