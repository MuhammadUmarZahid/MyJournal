package com.bbx.myjournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel:MainViewModel
    lateinit var concatAdapter:ConcatAdapter
    lateinit var adapterList:ArrayList<RecyclerView.Adapter<RecyclerView.ViewHolder>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val createFab = findViewById<FloatingActionButton>(R.id.create)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        createFab.setOnClickListener {
            val i = Intent (this,CreateEmotionActivity::class.java)
            startActivity(i)
        }

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)




       // lifecycleScope.launch {
        //mainViewModel.getEmotionsByMonthDay()
        //mainViewModel.getGroupByMonthDay()
        //mainViewModel.getGroupByMonth()
        //mainViewModel.getGroupByDay()
        //}
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    mainViewModel.mainUiState
                        .collect { it ->
                            // Update UI elements
                        /*val adapter = EmotionsAdapter(this@MainActivity, it.items)
                        recyclerView.adapter = adapter*/
                            System.out.println(">>>activity "+it.newItems)
                            adapterList= arrayListOf()
                            val list = it.newItems

                            list.forEach {
                                val monthAdapter = MonthViewAdapter(this@MainActivity,it)
                                    adapterList.add(monthAdapter)
                                //create an adapter for single month item
                                //add adapter to list
                                it.days?.forEach {
                                    val dayAdapter = DayViewAdapter(this@MainActivity,it)
                                        adapterList.add(dayAdapter)
                                    //create an adapter for single day item
                                    //add adapter to list
                                    val emotionAdapter = it.emotions?.let { it1 ->
                                        EmotionViewAdapter(this@MainActivity,
                                            it1
                                        )
                                    }
                                    emotionAdapter?.let { it1 -> adapterList.add(it1) }
                                    //create emotion adapter provide list
                                    //add adapter to list
                                }
                            }
                            concatAdapter = ConcatAdapter(adapterList)
                            recyclerView.adapter = concatAdapter

                    }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            mainViewModel.getEmotionsGroupedByMonthDay()
        }
    }
}