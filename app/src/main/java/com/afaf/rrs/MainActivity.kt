package com.afaf.rrs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var rvAdapter : Adapter

    lateinit var questionsRecyclerView: RecyclerView
    lateinit var questions : ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questions = arrayListOf()

        questionsRecyclerView = findViewById(R.id.questions_rv)
        rvAdapter = Adapter(questions )
        questionsRecyclerView.adapter = rvAdapter
        questionsRecyclerView.layoutManager = LinearLayoutManager(this)

        parseRRS()
    }


    private fun parseRRS(){
        CoroutineScope(IO).launch {
            val data = async {
                val perser = XMLParser()
                perser.pars()
            }.await()
            try{
                withContext(Main){
                    rvAdapter.update(data)
                }
            }catch (e: java.lang.Exception){
                Log.d("MAin","unable to get")
            }
        }
    }

}