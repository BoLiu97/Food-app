package com.example.burger_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_summary.*
import java.lang.Integer.parseInt
import java.lang.Math
import java.lang.Math.abs

class Summary : AppCompatActivity() {
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: RecyclerView.LayoutManager

    class RecyclerViewAdapter(val foodData: ArrayList<food>):
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val viewItem =
                LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem, parent, false)
            return RecyclerViewHolder(viewItem)

        }


        override fun getItemCount(): Int {
            return foodData.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.bind(foodData[position])
        }

        //A holder class for each item in the list.
        class RecyclerViewHolder(val viewItem: View) : RecyclerView.ViewHolder(viewItem) {
            //Set the item and event call backs for the view holders
            fun bind(food: food) {
                viewItem.findViewById<ImageView>(R.id.item_image).setImageResource(
                    when (food.name) {
                        "bacon_cheeseburger" -> R.drawable.bacon_cheesburger
                        "bacon_double_cheesburger" -> R.drawable.bacon_double_cheesburger
                        "cheeseburger" -> R.drawable.chessburger
                        "chicken_jr" -> R.drawable.chicken_jr
                        "chicken_nuggets" -> R.drawable.chicken_nuggets
                        "coca_cola" -> R.drawable.coca_cola
                        "diet_coke" -> R.drawable.diet_coke
                        "double_cheeseburger" -> R.drawable.double_cheeseburger
                        "dr_pepper" -> R.drawable.dr_pepper
                        "french_fries" -> R.drawable.french_fries
                        "frozen_fanta_cherry_icee" -> R.drawable.frozen_fanta_cherry_icee
                        "onion_rings" -> R.drawable.onion_rings
                        "spicy_chicken_jr" -> R.drawable.spicy_chicken_jr
                        "sprite" -> R.drawable.sprite
                        "vanilla_soft_serve" -> R.drawable.vanilla_soft_serve
                        else -> R.drawable.sprite
                    }
                )
                viewItem.findViewById<TextView>(R.id.item_name).text = food.name!!
                viewItem.findViewById<TextView>(R.id.item_Cal).text = food.Calo.toString()
                //viewItem.setOnClickListener { clickListener(food) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        val foodNM = intent.getStringArrayExtra("name")
        val foodIG = intent.getIntArrayExtra("image")
        val foodCl = intent.getIntArrayExtra("cal")
        val foodAL = ArrayList<food>()
        var lenFD = foodNM.size
        var i = 0
        while (i != lenFD){

            foodAL.add(food(foodNM[i],"",foodIG[i],foodCl[i],false))
            i++
        }
        viewManager = LinearLayoutManager(this)
        viewAdapter =
            RecyclerViewAdapter(foodAL)
        food_recyclerView.apply{
            layoutManager = viewManager
            adapter = viewAdapter
            setHasFixedSize(true)
        }
        calTV.text = "Order Calories: " + foodCl.sum().toString()
        suggesTV.text = foodCl.sum().toString()
        updateBT.setOnClickListener{
            val intent = Intent(this, PersonDetail::class.java)
            startActivityForResult(intent, 1)
            //startActivityForResult(Intent(this,PersonDetail::class.java),1)
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            bmrTV.text = "Your daily BRM need: " +data?.getStringExtra("result")
            if(data?.getStringExtra("wrongMsg")!=null){
                nameTV.text = data?.getStringExtra("wrongMsg")
            }else{
            nameTV.text =data?.getStringExtra("personal_information")}
            val per = parseInt(data?.getStringExtra("result").toString())
            val sum =parseInt((suggesTV.text).toString())
            val abse = abs((parseInt(data?.getStringExtra("result").toString())-parseInt((suggesTV.text).toString())))
            if(per > sum){
                suggesTV.text =  "you should eat " + abse + " Calories more"
            }else{
                suggesTV.text =  "you should eat " + abse + " Calories less"
            }
        }
    }
}

