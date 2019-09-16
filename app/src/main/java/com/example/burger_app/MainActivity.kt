package com.example.burger_app

import android.app.ListActivity
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_person_details.view.*
import java.net.URI
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity() {
    //var adapter:FoodAdapter?=null
    var listOfFoods = ArrayList<food>()

    //override fun onClick(p0: View?) {
       // val intent = Intent(this, Summary::class.java)
        //Use when to find which button is clicked. Assign sector name to the sector string
        //var sector = when (p0?.id) {
            //btCC.id -> "Cal"
            //else -> "Cal"
    //, View.OnClickListener
        //}
        //Pass the state information to the ListActivity
        //intent.putExtra("sector", sector)
        //startActivity(intent)

    //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load foods
        listOfFoods.add(
            food(
                "bacon_cheeseburger",
                "https://www.bk.com/menu-item/bacon-cheeseburger",
                R.drawable.bacon_cheesburger,
                320,
                false
            )
        )
        listOfFoods.add(
            food(
                "bacon_double_cheesburger",
                "https://www.bk.com/menu-item/bacon-double-cheeseburger",
                R.drawable.double_cheeseburger,
                400,
                false
            )
        )
        listOfFoods.add(
            food(
                "cheeseburger",
                "https://www.bk.com/menu-item/cheeseburger",
                R.drawable.chessburger,
                280,
                false
            )
        )
        listOfFoods.add(
            food(
                "chicken_jr",
                "https://www.bk.com/menu-item/chicken-jr",
                R.drawable.chicken_jr,
                450,
                false
            )
        )
        listOfFoods.add(
            food(
                "chicken_nuggets",
                "https://www.bk.com/menu-item/chicken-nuggets",
                R.drawable.chicken_nuggets,
                170,
                false
            )
        )
        listOfFoods.add(
            food(
                "coca_cola",
                "https://www.bk.com/menu-item/coca-cola%C2%AE",
                R.drawable.coca_cola,
                140,
                false
            )
        )
        listOfFoods.add(
            food(
                "diet_coke",
                "https://www.bk.com/menu-item/diet-coke",
                R.drawable.diet_coke,
                0,
                false
            )
        )
        listOfFoods.add(
            food(
                "double_cheeseburger",
                "https://www.bk.com/menu-item/double-cheeseburger",
                R.drawable.double_cheeseburger,
                390,
                false
            )
        )
        listOfFoods.add(
            food(
                "dr_pepper",
                "https://www.bk.com/menu-item/dr-pepper",
                R.drawable.dr_pepper,
                140,
                false
            )
        )
        listOfFoods.add(
            food(
                "french_fries",
                "https://www.bk.com/menu-item/french-fries",
                R.drawable.french_fries,
                380,
                false
            )
        )
        listOfFoods.add(
            food(
                "frozen_fanta_cherry_icee",
                "https://www.bk.com/menu-item/frozen-fanta-cherry-icee",
                R.drawable.frozen_fanta_cherry_icee,
                110,
                false
            )
        )
        listOfFoods.add(
            food(
                "onion_rings",
                "https://www.bk.com/menu-item/onion-rings",
                R.drawable.onion_rings,
                150,
                false
            )
        )
        listOfFoods.add(
            food(
                "spicy_chicken_jr",
                "https://www.bk.com/menu-item/spicy-chicken-jr",
                R.drawable.spicy_chicken_jr,
                390,
                false
            )
        )
        listOfFoods.add(
            food(
                "sprite",
                "https://www.bk.com/menu-item/sprite",
                R.drawable.sprite,
                140,
                false
            )
        )
        listOfFoods.add(
            food(
                "vanilla_soft_serve",
                "https://www.bk.com/menu-item/vanilla-soft-serve",
                R.drawable.vanilla_soft_serve,
                140,
                false
            )
        )


        //adapter= FoodAdapter(this,listOfFoods)
        //gvListFood.adapter = adapter
        gvListFood.adapter = FoodAdapter(listOfFoods) {
            val openURL = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(openURL)
        }
        //btCC.setOnClickListener(this)
        btCC.setOnClickListener {
            val intent = Intent(this, Summary::class.java)
            val checkedWebs = listOfFoods.filter { it.checked}
            /*
            //btCC.setOnClickListener(this)

            val checkname = checkedWebs.joinToString (","){ it.name!!}
                Toast.makeText(this, checkedWebs.joinToString(",") { it.name!! }, Toast.LENGTH_LONG)
                    .show()

            intent.putExtra("checkedNM", checkname)
            intent.putExtra("foodList", listOfFoods)
            Toast toast = Toast.makeText(getApplicationContext(),
                "This is a message displayed in a Toast",
                Toast.LENGTH_SHORT);
             */

            if(checkedWebs.size >0){
                intent.putExtra("name",checkedWebs.map { it.name }.toTypedArray())
                intent.putExtra("image",checkedWebs.map { it.image!! }.toIntArray())
                intent.putExtra("cal",checkedWebs.map { it.Calo!! }.toIntArray())
                startActivity(intent)
            }
            else{
                val text = "Invaild input!!!!!!! Should at least select one"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }

        }

    }




        class FoodAdapter(val listOfFood: ArrayList<food>, val click: (String) -> Unit) :
            BaseAdapter() {
            //var listOfFood = ArrayList<food>()
            /*
        var context:Context?=null
        constructor(context: Context,listOfFood:ArrayList<food>):super(){
            this.context=context
            this.listOfFood=listOfFood
        */

            override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
                val food = this.listOfFood[p0]
                var foodView =
                    LayoutInflater.from(p2?.context).inflate(R.layout.food_ticket, p2, false)
                foodView.ivFoodImg.setImageResource(food.image!!)
                /*
            foodView.ivFoodImg.setOnClickListener {
                val url = food.des
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse("")
                startActivity(intent)
                //val intent = Intent(Intent.ACTION_VIEW,Uri.parse(food.des),context,PersonDetail::class.java)
                context!!.startActivity(intent)
            }

             */
                //val click: (String) -> Unit
                foodView.tvCalo.text = Integer.toString(food.Calo!!)
                foodView.tvName.text = food.name!!
                foodView.ivFoodImg.setOnClickListener {
                    click(food.des)
                }
                foodView.cbChoose.setOnCheckedChangeListener { compoundButton, b ->
                    food.checked = b
                }
                return foodView

            }

            override fun getItem(p0: Int): Any {
                return listOfFood[p0]
            }

            override fun getItemId(p0: Int): Long {
                return p0.toLong()
            }

            override fun getCount(): Int {
                return listOfFood.size

            }

        }
    }



