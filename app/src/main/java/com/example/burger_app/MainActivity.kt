package com.example.burger_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {
    var adapter:FoodAdapter?=null
    var listOfFoods = ArrayList<food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load foods
        listOfFoods.add(food("bacon_cheeseburger","https://www.bk.com/menu-item/bacon-cheeseburger",R.drawable.bacon_cheesburger))
        listOfFoods.add(food("bacon_double_cheesburger","https://www.bk.com/menu-item/bacon-double-cheeseburger",R.drawable.double_cheeseburger))
        listOfFoods.add(food("cheeseburger","https://www.bk.com/menu-item/cheeseburger",R.drawable.chessburger))
        listOfFoods.add(food("chicken_jr","https://www.bk.com/menu-item/chicken-jr",R.drawable.chicken_jr))
        listOfFoods.add(food("chicken_nuggets","https://www.bk.com/menu-item/chicken-nuggets",R.drawable.chicken_nuggets))
        listOfFoods.add(food("coca_cola","https://www.bk.com/menu-item/coca-cola%C2%AE",R.drawable.coca_cola))
        listOfFoods.add(food("diet_coke","https://www.bk.com/menu-item/diet-coke",R.drawable.diet_coke))
        listOfFoods.add(food("double_cheeseburger","https://www.bk.com/menu-item/double-cheeseburger",R.drawable.double_cheeseburger))
        listOfFoods.add(food("dr_pepper","https://www.bk.com/menu-item/dr-pepper",R.drawable.dr_pepper))
        listOfFoods.add(food("french_fries","https://www.bk.com/menu-item/french-fries",R.drawable.french_fries))
        listOfFoods.add(food("frozen_fanta_cherry_icee","https://www.bk.com/menu-item/frozen-fanta-cherry-icee",R.drawable.frozen_fanta_cherry_icee))
        listOfFoods.add(food("onion_rings","https://www.bk.com/menu-item/onion-rings",R.drawable.onion_rings))
        listOfFoods.add(food("spicy_chicken_jr","https://www.bk.com/menu-item/spicy-chicken-jr",R.drawable.spicy_chicken_jr))
        listOfFoods.add(food("sprite","https://www.bk.com/menu-item/sprite",R.drawable.sprite))
        listOfFoods.add(food("vanilla_soft_serve","https://www.bk.com/menu-item/vanilla-soft-serve",R.drawable.vanilla_soft_serve))
        adapter= FoodAdapter(this,listOfFoods)
        gvListFood.adapter = adapter

    }
    class FoodAdapter: BaseAdapter {
        var listOfFood = ArrayList<food>()
        var context:Context?=null
        constructor(context: Context,listOfFood:ArrayList<food>):super(){
            this.context=context
            this.listOfFood=listOfFood

        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val food = this.listOfFood[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflator.inflate(R.layout.food_ticket,null)
            foodView.ivFoodImg.setImageResource(food.image!!)
            foodView.tvName.text= food.name!!
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
